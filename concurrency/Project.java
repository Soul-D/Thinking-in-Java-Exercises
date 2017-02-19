package concurrency;

import javassist.*;
import net.mindview.util.ProcessFiles;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Project implements ProcessFiles.Strategy {

    public static void main(String[] args) throws Exception {
        String[] arr = new String[]{"./target/classes/concurrency"};
        new ProcessFiles(new Project(), "class").start(arr);
    }

    public void process(File cFile) {
        try {
            String cName = cFile.getCanonicalPath().replace(".class", "");
            int index = cName.lastIndexOf("classes\\");
            cName = cName.substring(index + 8);
            cName = cName.replace('\\', '.');
            if (!cName.contains("."))
                return;
            ClassPool cPool = ClassPool.getDefault();
            CtClass ctClass = cPool.get(cName);
            if (ctClass.hasAnnotation(Active.class)) {
                CtField field = new CtField(cPool.get("java.util.concurrent.ExecutorService"), "exec", ctClass);
                ctClass.addField(field, "java.util.concurrent.Executors.newSingleThreadExecutor()");
                for (CtMethod method : ctClass.getDeclaredMethods()) {
                    String name = method.getName();
                    method.setName("passive" + name);
                    CtClass nestedClass = ctClass.makeNestedClass("Active" + name,true);
                    nestedClass.addField(new CtField(ctClass,"par",nestedClass));
                    nestedClass.addConstructor(CtNewConstructor.make("public Active" + name + "(" + cName + " ppo){par = ppo;}",nestedClass));
                    nestedClass.addInterface(cPool.get("java.lang.Runnable"));
                    nestedClass.addMethod(CtMethod.make("public void run(){par.passive" + name + "();}",nestedClass));
                    String methodBody = "public void " + name + "(){ exec.execute(new " + cName + ".Active" + name + "(this));}";
                    ctClass.addMethod(CtMethod.make(methodBody, ctClass));
                }
            }
            try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(cFile))) {
                ctClass.toBytecode(stream);
                ctClass.detach();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

