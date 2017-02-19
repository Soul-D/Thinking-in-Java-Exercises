package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex20CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Ex20LiftOff(1_000));
        exec.shutdownNow();
    }
}

class Ex20LiftOff implements Runnable {
    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Ex20LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        while (countDown-- > 0) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted#" + id);
                return;
            }
            System.out.println(status());
            Thread.yield();
        }
    }
}