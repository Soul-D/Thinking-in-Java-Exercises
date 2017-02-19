package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

class Ex19Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Ex19Entrance> entrances = new ArrayList<Ex19Entrance>();
    private int number = 0;
    private final int id;

    public Ex19Entrance(int id) {
        this.id = id;
        entrances.add(this);
    }

    public void run() {
        while (true) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
                print("Stopping " + this);
                return;
            }
        }
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
        for (Ex19Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

class Ex19Test {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Ex19Entrance(i));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Ex19Entrance.getTotalCount());
        print("Sum of Entrances: " + Ex19Entrance.sumEntrances());
    }
}

