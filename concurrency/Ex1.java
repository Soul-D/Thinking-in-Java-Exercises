package concurrency;

public class Ex1 implements Runnable {
    public Ex1() {
        System.out.println("Starting...");
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hi!");
            Thread.yield();
        }
        System.out.println("Dying...");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            new Thread(new Ex1()).start();
        }
    }
}
