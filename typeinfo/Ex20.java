package typeinfo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class Ex20 {
    static void classInfo(Class<?> clazz){
        System.out.println("SimpleName : " + clazz.getSimpleName());
        System.out.println("CanonicalName : " + clazz.getCanonicalName());
        System.out.println("Name : " + clazz.getName());
        System.out.println("Annotations : ");
        for(Annotation annotation : clazz.getAnnotations()){
            System.out.println(annotation);
        }
        System.out.println("---------------------");
        System.out.println("Classes : ");
        for(Class classes : clazz.getClasses()){
            System.out.println(classes);
        }
        System.out.println("---------------------");
        System.out.println("Constructors : ");
        for(Constructor constructor : clazz.getConstructors()){
            System.out.println(constructor);
        }
        System.out.println("---------------------");
        System.out.println("DeclaredConstructors : ");
        for(Constructor constructor : clazz.getDeclaredConstructors()){
            System.out.println(constructor);
        }
        System.out.println("---------------------");
        System.out.println("DeclaredAnnotations : ");
        for(Annotation annotation : clazz.getDeclaredAnnotations()){
            System.out.println(annotation);
        }
        System.out.println("---------------------");
        System.out.println("Fields : ");
        for(Field field : clazz.getFields()){
            System.out.println(field);
        }
        System.out.println("---------------------");
        System.out.println("DeclaredFields : ");
        for(Field field : clazz.getDeclaredFields()){
            System.out.println(field);
        }
        System.out.println("---------------------");
        System.out.println("Methods : ");
        for(Method method : clazz.getMethods()){
            System.out.println(method);
        }
        System.out.println("---------------------");
        System.out.println("DeclaredMethods : ");
        for(Method method : clazz.getDeclaredMethods()){
            System.out.println(method);
        }
        System.out.println("---------------------");
        System.out.println("Interfaces : ");
        for(Type type : clazz.getInterfaces()){
            System.out.println(type);
        }
        System.out.println("---------------------");
    }

    public static void main(String[] args) {
        classInfo(java.util.Random.class);
        classInfo(Ex20.class);
    }
}
