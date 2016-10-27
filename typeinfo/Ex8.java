package typeinfo;

public class Ex8 {
    static void getHierarchy(Object object) throws IllegalAccessException, InstantiationException {
        if (object != null)
            getHierarchyRec(object.getClass());
    }

    private static void getHierarchyRec(Class clazz){
        System.out.println(clazz.getName());
        if (clazz.getSuperclass() != null)
            getHierarchyRec(clazz.getSuperclass());
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        getHierarchy(new Circle());
        getHierarchy(null);
    }
}
