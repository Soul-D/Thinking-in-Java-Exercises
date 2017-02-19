package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

class Ex24Test {
    public static void main(String[] args) throws InterruptedException {
        Ex24Queue<Integer> queue = new Ex24Queue<>(1);
        Ex24Consumer<Integer> consumer = new Ex24Consumer<>(queue, 300);
        Ex24Producer<Integer> producer = new Ex24Producer<>(queue, 2000, new Supplier<Integer>() {
            private int current = 0;

            @Override
            public Integer get() {
                return current++;
            }
        });
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(consumer);
        service.execute(producer);
        TimeUnit.SECONDS.sleep(5);
        System.exit(0);
    }
}

public class Ex24Consumer<T> implements Runnable {
    private final Ex24Queue<T> queue;
    private final int delay;

    public Ex24Consumer(Ex24Queue<T> queue, int delay) {
        this.queue = queue;
        this.delay = delay;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(delay);
                System.out.println("Item " + queue.poll() + " polled");
            }
        } catch (InterruptedException ignore) {
        }
    }
}

class Ex24Producer<T> implements Runnable {
    private final Ex24Queue<T> queue;
    private final int delay;
    private final Supplier<T> supplier;

    public Ex24Producer(Ex24Queue<T> queue, int delay, Supplier<T> supplier) {
        this.queue = queue;
        this.delay = delay;
        this.supplier = supplier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(delay);
                T t = supplier.get();
                System.out.println("Item " + t + " produced");
                queue.offer(t);
            }
        } catch (InterruptedException ignore) {
        }
    }
}

class Ex24Queue<T> {
    private final int size;
    private final Queue<T> queue;

    public Ex24Queue(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public synchronized void offer(T t) {
        while (queue.size() >= size) {
            try {
                wait();
            } catch (InterruptedException ignore) {
            }
        }
        notifyAll();
        queue.offer(t);
    }

    public synchronized T poll() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (InterruptedException ignore) {
            }
        }
        notifyAll();
        return queue.poll();
    }
}