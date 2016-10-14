//: polymorphism/Sandwich.java
// Order of constructor calls.
package interfaces;

import static net.mindview.util.Print.print;

class Meal {
    Meal() {
        print("Meal()");
    }
}

class Bread {
    Bread() {
        print("Bread()");
    }
}

class Cheese {
    Cheese() {
        print("Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        print("Lettuce()");
    }
}

class Pickle {
    Pickle() {
        print("Pickle");
    }
}

class Lunch extends Meal {
    Lunch() {
        print("Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        print("PortableLunch()");
    }
}

interface FastFood {
    void eat();
}

public class Ex8_Sandwich extends PortableLunch implements FastFood {
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();
    private Pickle p = new Pickle();

    public void eat() {
        System.out.println("Eat sandwich");
    }

    public Ex8_Sandwich() {
        print("Ex8_Sandwich()");
    }

    public static void main(String[] args) {
        new Ex8_Sandwich().eat();
    }
} /* Output:
Meal()
Lunch()
PortableLunch()
Bread()
Cheese()
Lettuce()
Ex8_Sandwich()
*///:~
