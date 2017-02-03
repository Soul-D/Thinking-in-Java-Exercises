package annotations.database;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.SimpleElementVisitor8;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes({"DBTable", "SQLBoolean", "SQLInteger", "SQLString"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class Ex3TableCreationProcessor extends AbstractProcessor {
    List<String> sqlList = new ArrayList<>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element elem : roundEnv.getElementsAnnotatedWith(DBTable.class)) {
            DBTable dbTable = elem.getAnnotation(DBTable.class);
            final StringBuilder res = new StringBuilder("CREATE TABLE ");
            res.append(dbTable.name().length() != 0 ? dbTable.name() : elem.getSimpleName()).append(" (");
            for (Element element : elem.getEnclosedElements()) {
                element.accept(new SimpleElementVisitor8<Void, Void>() {
                    @Override
                    public Void visitVariable(VariableElement e, Void aVoid) {
                        Ex1SQLBoolean sqlBoolean;
                        SQLInteger sqlInteger;
                        SQLString sqlString;
                        if ((sqlBoolean = e.getAnnotation(Ex1SQLBoolean.class)) != null) {
                            res.append(sqlBoolean.name().length() != 0 ? sqlBoolean.name() : e.getSimpleName())
                                    .append(" ").append("BOOLEAN").append(" ").append(getConstraints(sqlBoolean.constraints())).append(", ");
                        } else if ((sqlInteger = e.getAnnotation(SQLInteger.class)) != null) {
                            res.append(sqlInteger.name().length() != 0 ? sqlInteger.name() : e.getSimpleName())
                                    .append(" ").append("INTEGER").append(" ").append(getConstraints(sqlInteger.constraints())).append(", ");
                        } else if ((sqlString = e.getAnnotation(SQLString.class)) != null) {
                            res.append(sqlString.name().length() != 0 ? sqlString.name() : e.getSimpleName())
                                    .append(" ").append("VARCHAR(").append(sqlString.value()).append(")")
                                    .append(" ").append(getConstraints(sqlString.constraints())).append(", ");
                        }
                        return null;
                    }
                }, null);
            }
            res.delete(res.length() - 2, res.length());
            res.append(")");
            sqlList.add(res.toString());
        }
        System.out.println(sqlList);
        return true;
    }

    private String getConstraints(Constraints con) {
        StringBuilder constraints = new StringBuilder();
        if (!con.allowNull())
            constraints.append(" NOT NULL");
        if (con.primaryKey())
            constraints.append(" PRIMARY KEY");
        if (con.unique())
            constraints.append(" UNIQUE");
        return constraints.toString();
    }
}
