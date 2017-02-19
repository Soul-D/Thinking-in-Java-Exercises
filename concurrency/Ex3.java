package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex3 implements Runnable {
    public Ex3() {
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
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++){
            exec.execute(new Ex3());
        }
        exec.shutdown();
        exec = Executors.newFixedThreadPool(13);
        for (int i = 0; i < 5; i++){
            exec.execute(new Ex3());
        }
        exec.shutdown();
        exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++){
            exec.execute(new Ex3());
        }
        exec.shutdown();
    }
}
