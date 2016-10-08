package object;

public class Ex7_Incrementable {
    static void increment() { StaticTest.i++; }

    public static void main(String[] args) {
        Ex7_Incrementable sf = new Ex7_Incrementable();
        sf.increment () ;
        Ex7_Incrementable.increment();
        increment();
    }
}

class StaticTest {
    static int i = 47;
}
