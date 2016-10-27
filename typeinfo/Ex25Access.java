package typeinfo;

import typeinfo.ex25package.Ex25;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Ex25Access {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Ex25 ex25 = new Ex25();
        callMethod(ex25,"doPackage");
        callMethod(ex25,"doProtected");
        callMethod(ex25,"doPrivate");
    }

    static void callMethod(Object object, String name) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = object.getClass().getDeclaredMethod(name);
        method.setAccessible(true);
        method.invoke(object);
    }
}
