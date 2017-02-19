package concurrency;

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

class Ex32Count {
    private int count = 0;
    private Random rand = new Random(47);

    public synchronized int increment() {
        int temp = count;
        if (rand.nextBoolean())
            Thread.yield();
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Ex32Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Ex32Entrance> entrances = new ArrayList<>();
    private int number = 0;
    private final int id;
    private final CountDownLatch latch;
    private static volatile boolean canceled = false;

    public static void cancel() {
        canceled = true;
    }

    public Ex32Entrance(int id, CountDownLatch latch) {
        this.id = id;
        this.latch = latch;
        entrances.add(this);
    }

    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
            }
        }
        latch.countDown();
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Ex32Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class Ex32OrnamentalGarden {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++)
            exec.execute(new Ex32Entrance(i, latch));
        TimeUnit.SECONDS.sleep(3);
        Ex32Entrance.cancel();
        exec.shutdown();
        if (!latch.await(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Ex32Entrance.getTotalCount());
        print("Sum of Entrances: " + Ex32Entrance.sumEntrances());
    }
}