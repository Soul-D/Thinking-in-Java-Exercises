package annotations;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeVariable;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@SupportedAnnotationTypes("ExtractInterface")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NewInterfaceExtractorProcessor extends AbstractProcessor {
    private ArrayList<Element> interfaceMethods = new ArrayList<>();

    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment env) {
        for (Element typeDecl : env.getElementsAnnotatedWith(ExtractInterface.class)) {
            interfaceMethods.clear();
            ExtractInterface annot = typeDecl.getAnnotation(ExtractInterface.class);
            if (annot == null)
                break;
            interfaceMethods.addAll(typeDecl.getEnclosedElements().stream().filter(m -> m.getKind() == ElementKind.METHOD
                    && m.getModifiers().contains(Modifier.PUBLIC)
                    && !(m.getModifiers().contains(Modifier.STATIC))).collect(Collectors.toList()));
            if (interfaceMethods.size() > 0) {
                try {
                    TypeElement clazz = (TypeElement) typeDecl;
                    PrintWriter writer = new PrintWriter(processingEnv.getFiler().createSourceFile(annot.value()).openWriter());
                    String pack = typeDecl.getEnclosingElement().getSimpleName().toString();
                    if (pack.length() > 0)
                        writer.println("package " + pack + ";");
                    writer.println("public interface " + annot.value() + " {");
                    for (Element m : interfaceMethods) {
                        ExecutableType method = (ExecutableType) m.asType();
                        writer.print("  public ");
                        writer.print(method.getReturnType() + " ");
                        writer.print(m.getSimpleName() + " (");
                        int i = 0;
                        for (TypeVariable parm : method.getTypeVariables()) {
                            writer.print(parm.asElement().asType() + " " + parm.asElement().getSimpleName());
                            if (++i < method.getTypeVariables().size())
                                writer.print(", ");
                        }
                        writer.println(");");
                    }
                    writer.println("}");
                    writer.close();
                } catch (IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
        }
        return true;
    }
}
