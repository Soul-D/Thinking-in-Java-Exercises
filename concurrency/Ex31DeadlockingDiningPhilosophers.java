package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

public class Ex31DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws Exception {
        int ponder = 5;
        if (args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if (args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        ChopstickBin sticks = new ChopstickBin();
        for (int i = 0; i < 2; i++) //if number is small than there is a deadlock
            sticks.put(new Chopstick());
        Ex31Philosopher.setBin(sticks);
        for (int i = 0; i < size; i++)
            exec.execute(new Ex31Philosopher(i, ponder));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}

class Ex31Philosopher implements Runnable {
    private static ChopstickBin bin = new ChopstickBin();
    private Chopstick left;
    private Chopstick right;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    public static void setBin(ChopstickBin bin) {
        Ex31Philosopher.bin = bin;
    }

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Ex31Philosopher(int ident, int ponder) {
        id = ident;
        ponderFactor = ponder;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(this + " " + "thinking");
                pause();
                print(this + " " + "grabbing right");
                right = bin.take();
                print(this + " " + "grabbing left");
                left = bin.take();
                print(this + " " + "eating");
                pause();
                bin.put(right);
                right = null;
                bin.put(left);
                left = null;
            }
        } catch (InterruptedException e) {
            print(this + " " + "exiting via interrupt");
        }
    }

    public String toString() {
        return "Philosopher " + id;
    }
}

class ChopstickBin extends LinkedBlockingQueue<Chopstick> {
}