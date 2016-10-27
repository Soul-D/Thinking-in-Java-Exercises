package typeinfo;

import java.lang.reflect.Field;

public class Ex9 {
    static void getHierarchy(Object object) throws IllegalAccessException, InstantiationException {
        if (object != null)
            getHierarchyRec(object.getClass());
    }

    private static void getHierarchyRec(Class clazz){
        System.out.println(clazz.getName());
        for(Field field : clazz.getDeclaredFields()){
            System.out.println("Field : " + field.getName());
        }
        if (clazz.getSuperclass() != null)
            getHierarchyRec(clazz.getSuperclass());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        getHierarchy(new Circle());
        getHierarchy(null);
    }
}
