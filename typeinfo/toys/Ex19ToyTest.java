//: typeinfo/toys/ToyTest.java
// Testing class Class.
package typeinfo.toys;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static net.mindview.util.Print.print;

class SuperFancyToy extends FancyToy
        implements HasBatteries, Waterproof, Shoots, Moves {
    public SuperFancyToy(int i) {
        System.out.println(i + "<----------------------------");
    }
}

public class Ex19ToyTest {
    static void printInfo(Class cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name : " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("typeinfo.toys.SuperFancyToy");
        } catch (ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces())
            printInfo(face);
        Class up = c.getSuperclass();
        Object obj = null;
        for (Constructor constructor : c.getConstructors())
            print(constructor);
    try {
      // Requires default constructor:
      obj = c.getConstructor(int.class).newInstance(13);
    } catch(InstantiationException e) {
      print("Cannot instantiate");
      System.exit(1);
    } catch(IllegalAccessException e) {
      print("Cannot access");
      System.exit(1);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    printInfo(obj.getClass());
    }
} /* Output:
Class name: typeinfo.toys.FancyToy is interface? [false]
Simple name: FancyToy
Canonical name : typeinfo.toys.FancyToy
Class name: typeinfo.toys.HasBatteries is interface? [true]
Simple name: HasBatteries
Canonical name : typeinfo.toys.HasBatteries
Class name: typeinfo.toys.Waterproof is interface? [true]
Simple name: Waterproof
Canonical name : typeinfo.toys.Waterproof
Class name: typeinfo.toys.Shoots is interface? [true]
Simple name: Shoots
Canonical name : typeinfo.toys.Shoots
Class name: typeinfo.toys.Toy is interface? [false]
Simple name: Toy
Canonical name : typeinfo.toys.Toy
*///:~
