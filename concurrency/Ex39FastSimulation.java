package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;

public class Ex39FastSimulation {
    static final int N_ELEMENTS = 100000;
    static final int N_GENES = 30;
    static final int N_EVOLVERS = 50;
    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];
    static final int[][] GRID2 = new int[N_ELEMENTS][N_GENES];
    static final Lock[] locks = new ReentrantLock[N_ELEMENTS];
    static final AtomicInteger count1 = new AtomicInteger(0);
    static final AtomicInteger count2 = new AtomicInteger(0);
    static Random rand = new Random(47);

    static class Evolver implements Runnable {
        public void run() {
            while (!Thread.interrupted()) {
                int element = rand.nextInt(N_ELEMENTS);
                for (int i = 0; i < N_GENES; i++) {
                    int previous = element - 1;
                    if (previous < 0) previous = N_ELEMENTS - 1;
                    int next = element + 1;
                    if (next >= N_ELEMENTS) next = 0;
                    int oldvalue = GRID[element][i].get();
                    int newvalue = oldvalue +
                            GRID[previous][i].get() + GRID[next][i].get();
                    newvalue /= 3;
                    if (!GRID[element][i].compareAndSet(oldvalue, newvalue)) {
                        //print("Old value changed from " + oldvalue);
                    }
                }
                count1.incrementAndGet();
            }
        }
    }

    static class Evolver2 implements Runnable {
        public void run() {
            while (!Thread.interrupted()) {
                int element = rand.nextInt(N_ELEMENTS);
                locks[element].lock();
                try {
                    for (int i = 0; i < N_GENES; i++) {
                        int previous = element - 1;
                        if (previous < 0) previous = N_ELEMENTS - 1;
                        int next = element + 1;
                        if (next >= N_ELEMENTS) next = 0;
                        int oldvalue = GRID2[element][i];
                        int newvalue = oldvalue + GRID2[previous][i] + GRID2[next][i];
                        newvalue /= 3;
                        GRID2[element][i] = newvalue;
                    }
                } finally {
                    locks[element].unlock();
                    count2.incrementAndGet();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++)
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
        for (int i = 0; i < N_ELEMENTS; i++)
            for (int j = 0; j < N_GENES; j++)
                GRID2[i][j] = rand.nextInt(1000);
        for (int i = 0; i < N_ELEMENTS; i++) {
            locks[i] = new ReentrantLock();
        }
        for (int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new Evolver2());
        for (int i = 0; i < N_EVOLVERS; i++)
            exec.execute(new Evolver());
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
        System.out.println("Atomic : " + count1.get());
        System.out.println("int : " + count2.get());
        System.out.println((.0 + count1.get()) / count2.get());
    }
}
