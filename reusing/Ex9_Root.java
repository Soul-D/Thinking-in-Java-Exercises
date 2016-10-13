package reusing;

public class Ex9_Root {
    Component1 c1 = new Component1(1);
    Component2 c2 = new Component2(1);
    Component3 c3 = new Component3(1);

    Ex9_Root(int i){
        System.out.println("Ex9_Root");
    }

    void dispose(){
        System.out.println("Dispose Ex9_Root");
        c3.dispose();
        c2.dispose();
        c1.dispose();
    }
}

class Stem extends Ex9_Root {
    Component1 c1 = new Component1(1);
    Component2 c2 = new Component2(1);
    Component3 c3 = new Component3(1);

    Stem(int i) {
        super(i);
        System.out.println("Stem");
    }

    void dispose(){
        System.out.println("Dispose Stem");
        c3.dispose();
        c2.dispose();
        c1.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        new Stem(1).dispose();
    }
}

class Component1 {
    Component1(int i){
        System.out.println("Component1");
    }
    void dispose(){
        System.out.println("Dispose Component1");
    }
}

class Component2 {
    Component2(int i){
        System.out.println("Component2");
    }
    void dispose(){
        System.out.println("Dispose Component2");
    }
}

class Component3 {
    Component3(int i){
        System.out.println("Component3");
    }
    void dispose(){
        System.out.println("Dispose Component3");
    }
}
