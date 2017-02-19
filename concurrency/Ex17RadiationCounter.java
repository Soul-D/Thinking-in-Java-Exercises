package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex17RadiationCounter {
    private int count = 0;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void increment() {
        count++;
    }
}

class Ex17RemoteSensor implements Runnable {
    public static Ex17RadiationCounter counter = new Ex17RadiationCounter();
    public static List<Ex17RemoteSensor> sensors = new ArrayList<>();
    private static volatile boolean isCancelled = false;
    private int count = 0;

    public Ex17RemoteSensor() {
        sensors.add(this);
    }

    public synchronized int getCount() {
        return count;
    }

    public static void cancel() {
        isCancelled = true;
    }

    @Override
    public void run() {
        while (!isCancelled) {
            synchronized (this) {
                count++;
            }
            counter.increment();
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
        System.out.println("Shutting down");
    }
}

class Ex17Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Thread(new Ex17RemoteSensor()));
        exec.shutdown();
        TimeUnit.SECONDS.sleep(3);
        Ex17RemoteSensor.cancel();
        System.out.println("Counter: " + Ex17RemoteSensor.counter.getCount());
        System.out.println("Total: " + Ex17RemoteSensor.sensors.parallelStream().mapToInt(Ex17RemoteSensor::getCount).sum());
    }
}
