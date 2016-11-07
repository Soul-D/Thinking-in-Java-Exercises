package generics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Ex22 {
    public Ex22(String s){
        System.out.println(s);
    }
}

class Ex22Foo<T> {
    final private Class<T> clazz;

    public Ex22Foo(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T create() throws IllegalAccessException, InstantiationException, InvocationTargetException {
        Constructor<?>[] constructors = clazz.getConstructors();
        Constructor<?> constructor = constructors[0];
        Class<?>[] types = constructor.getParameterTypes();
        Object[] objects = new Object[types.length];
        for (int i = 0; i < types.length; i++){
            objects[i] = types[i].newInstance();
        }
        return (T)constructor.newInstance(objects);
    }
}

class Ex22Test {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, InvocationTargetException {
        new Ex22Foo<Ex22>(Ex22.class).create();
    }
}
