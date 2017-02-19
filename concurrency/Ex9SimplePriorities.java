package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Ex9SimplePriorities implements Runnable {
    private int countDown = 5;
    private volatile double d;

    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        while (true) {
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    private static class MyThreadFactory implements ThreadFactory{
        private final int priority;

        public MyThreadFactory(int priority) {
            this.priority = priority;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setPriority(priority);
            return thread;
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(new MyThreadFactory(Thread.MIN_PRIORITY));
        for (int i = 0; i < 5; i++)
            exec.execute(new Ex9SimplePriorities());
        exec.shutdown();
        exec = Executors.newCachedThreadPool(new MyThreadFactory(Thread.MAX_PRIORITY));
        exec.execute(new Ex9SimplePriorities());
        exec.shutdown();
    }
}