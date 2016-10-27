package typeinfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface Files {
    public void createAndWriteFile(String filename, String data) throws FileNotFoundException;
}

/***
 * Create files and write data
 * exeption at writing => we should delete file*/
class ProjectTransactions implements Files {
    public void createAndWriteFile(String filename, String data) throws FileNotFoundException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filename);
            if (new Random().nextInt(2) == 1)
                throw new RuntimeException();
            pw.write(data);
        } finally {
            if (pw != null)
                pw.close();
        }
    }
}

class TransactionHandler implements InvocationHandler {
    private Object proxied;

    public TransactionHandler(Object proxied) {
        this.proxied = proxied;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals("createAndWriteFile")) {
            Object result = null;
            try {
                result = method.invoke(proxied, args);
            } catch (Exception e) {
                try {
                    File file = new File((String) args[0]);
                    file.deleteOnExit();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            return result;
        } else
            return method.invoke(proxied, args);
    }
}

class ProjectTest {
    static void createAndAddDataToBunchOfFiles(List<String> filenames, String data, Files files) throws FileNotFoundException {
        for (String name : filenames) {
            files.createAndWriteFile(name, data);
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Files proxy = (Files) Proxy.newProxyInstance(
                Files.class.getClassLoader(),
                new Class[]{Files.class},
                new TransactionHandler(new ProjectTransactions())
        );
        List<String> filenames = new ArrayList<String>();
        filenames.add("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\typeinfo\\1.txt");
        filenames.add("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\typeinfo\\2.txt");
        filenames.add("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\typeinfo\\3.txt");
        filenames.add("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\typeinfo\\4.txt");
        filenames.add("D:\\Java\\Bruce Eckel - Thinking in Java 4\\CodeFromNet\\src\\main\\java\\typeinfo\\5.txt");
        createAndAddDataToBunchOfFiles(filenames, "Data", proxy);
    }
}
