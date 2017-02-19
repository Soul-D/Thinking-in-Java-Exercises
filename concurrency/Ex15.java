package concurrency;

public class Ex15 {
    private final Object obj1 = new Object();
    private final Object obj2 = new Object();
    private final Object obj3 = new Object();

    void a() {
        synchronized (obj1) {
            for (int i = 0; i < 10; i++) {
                System.out.println("a() " + i);
                Thread.yield();
            }
        }
    }

    void b() {
        synchronized (obj2) {
            for (int i = 0; i < 10; i++) {
                System.out.println("b() " + i);
                Thread.yield();
            }
        }
    }

    void c() {
        synchronized (obj3) {
            for (int i = 0; i < 10; i++) {
                System.out.println("c() " + i);
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        Ex15 ex15 = new Ex15();
        new Thread(ex15::a).start();
        new Thread(ex15::b).start();
        new Thread(ex15::c).start();
    }
}
