package annotations.database;

import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Project4 {
    public static DataSource dataSource = JdbcConnectionPool.create("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "user", "password");
    private static Map<Class, ClassDBInfo> ormMapping = new HashMap<Class, ClassDBInfo>();

    public static <T> void createTable(Class<T> clazz) throws SQLException {
        ClassDBInfo classDBInfo = getClassDBInfo(clazz);
        if (classDBInfo == null) {
            System.out.println("Class " + clazz.getSimpleName() + " doesn't have DBTable annotation");
            return;
        }
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE ").append(classDBInfo.tableName).append(" (");
        for (FieldDBInfo f : classDBInfo.tableColumns) {
            query.append(f.columnName).append(" ").append(f.columnType).append(" ").append(f.columnConstraints).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(")");
        Connection connection = dataSource.getConnection();
        connection.createStatement().execute(query.toString());
        connection.close();
    }

    private static String getConstraints(Constraints con) {
        StringBuilder constraints = new StringBuilder();
        if (!con.allowNull())
            constraints.append(" NOT NULL");
        if (con.primaryKey())
            constraints.append(" PRIMARY KEY");
        if (con.unique())
            constraints.append(" UNIQUE");
        return constraints.toString();
    }

    public static <T> void saveToDB(T bean) throws SQLException, IllegalAccessException {
        Class<?> clazz = bean.getClass();
        ClassDBInfo classDBInfo = getClassDBInfo(clazz);
        if (classDBInfo == null) {
            System.out.println("Class " + clazz.getSimpleName() + " doesn't have DBTable annotation");
            return;
        }
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ").append(classDBInfo.tableName).append(" (");
        for (FieldDBInfo f : classDBInfo.tableColumns) {
            query.append(f.columnName).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(") ");
        query.append("VALUES (");
        for (FieldDBInfo f : classDBInfo.tableColumns) {
            query.append("'").append(f.field.get(bean)).append("'").append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(")");
        Connection connection = dataSource.getConnection();
        System.out.println(query.toString());
        connection.createStatement().executeUpdate(query.toString());
        connection.close();
    }

    public static <T> List<T> loadAllFromDB(Class<T> clazz) throws SQLException, IllegalAccessException, InstantiationException {
        ClassDBInfo classDBInfo = getClassDBInfo(clazz);
        if (classDBInfo == null) {
            System.out.println("Class " + clazz.getSimpleName() + " doesn't have DBTable annotation");
            return null;
        }
        List<T> items = new ArrayList<T>();
        StringBuilder query = new StringBuilder();
        query.append("SELECT ");
        for (FieldDBInfo f : classDBInfo.tableColumns) {
            query.append(f.columnName).append(", ");
        }
        query.delete(query.length() - 2, query.length());
        query.append(" FROM ").append(classDBInfo.tableName);
        Connection connection = dataSource.getConnection();
        ResultSet rs = connection.createStatement().executeQuery(query.toString());
        while (rs.next()){
            T item = clazz.newInstance();
            for (FieldDBInfo f : classDBInfo.tableColumns) {
                f.field.set(item,rs.getObject(f.columnName));
            }
            items.add(item);
        }
        connection.close();
        return items;
    }

    private static <T> ClassDBInfo getClassDBInfo(Class<T> clazz) {
        ClassDBInfo res = ormMapping.get(clazz);
        if (res != null)
            return res;
        DBTable dbTable = clazz.getAnnotation(DBTable.class);
        if (dbTable == null) {
            return null;
        }
        String tableName = dbTable.name();
        if (tableName.length() < 1)
            tableName = clazz.getSimpleName();
        res = new ClassDBInfo(tableName);
        for (Field field : clazz.getDeclaredFields()) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof SQLInteger) {
                    SQLInteger sqlInteger = (SQLInteger) annotation;
                    String columnName = sqlInteger.name();
                    if (columnName.length() < 1)
                        columnName = field.getName();
                    String columnType = "INTEGER";
                    String columnConstraints = getConstraints(sqlInteger.constraints());
                    res.tableColumns.add(new FieldDBInfo(field, columnName, columnType, columnConstraints));
                    break;
                } else if (annotation instanceof SQLString) {
                    SQLString sqlString = (SQLString) annotation;
                    String columnName = sqlString.name();
                    if (columnName.length() < 1)
                        columnName = field.getName();
                    String columnType = "VARCHAR(" + sqlString.value() + ")";
                    String columnConstraints = getConstraints(sqlString.constraints());
                    res.tableColumns.add(new FieldDBInfo(field, columnName, columnType, columnConstraints));
                    break;
                } else if (annotation instanceof Ex1SQLBoolean) {
                    Ex1SQLBoolean sqlBoolean = (Ex1SQLBoolean) annotation;
                    String columnName = sqlBoolean.name();
                    if (columnName.length() < 1)
                        columnName = field.getName();
                    String columnType = "BOOLEAN";
                    String columnConstraints = getConstraints(sqlBoolean.constraints());
                    res.tableColumns.add(new FieldDBInfo(field, columnName, columnType, columnConstraints));
                    break;
                }
            }
        }
        ormMapping.put(clazz,res);
        return res;
    }

    private static class ClassDBInfo {
        String tableName;
        List<FieldDBInfo> tableColumns;

        public ClassDBInfo(String tableName) {
            this.tableName = tableName;
            this.tableColumns = new ArrayList<FieldDBInfo>();
        }
    }

    private static class FieldDBInfo {
        Field field;
        String columnName;
        String columnType;
        String columnConstraints;

        public FieldDBInfo(Field field, String columnName, String columnType, String columnConstraints) {
            this.field = field;
            this.columnName = columnName;
            this.columnType = columnType;
            this.columnConstraints = columnConstraints;
        }
    }

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException {
        createTable(P4Hero.class);
        P4Hero hero = new P4Hero("Golgo 13", 35, true, "Our universe");
        saveToDB(hero);
        hero = new P4Hero("Iron man", 35, false, "Marvel");
        saveToDB(hero);
        System.out.println(loadAllFromDB(P4Hero.class));
    }
}

@DBTable(name = "Hero")
class P4Hero {
    @SQLString(30)
    String name;
    @SQLInteger
    Integer age;
    @Ex1SQLBoolean
    Boolean isCool;
    @SQLString(value = 30, constraints = @Constraints(primaryKey = true))
    String universe;

    public P4Hero(String name, Integer age, Boolean isCool, String universe) {
        this.name = name;
        this.age = age;
        this.isCool = isCool;
        this.universe = universe;
    }

    public P4Hero() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("P4Hero{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", isCool=").append(isCool);
        sb.append(", universe='").append(universe).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
