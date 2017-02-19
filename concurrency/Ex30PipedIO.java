package concurrency;

import java.util.concurrent.*;
import java.io.*;
import java.util.*;

import static net.mindview.util.Print.*;

class Ex30Sender implements Runnable {
    private Random rand = new Random(47);
    private BlockingQueue<Character> queue;

    public Ex30Sender(BlockingQueue<Character> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (!Thread.interrupted())
                for (char c = 'A'; c <= 'z'; c++) {
                    queue.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
        } catch (InterruptedException e) {
            print(e + " Sender interrupted");
        }
    }
}

class Ex30Receiver implements Runnable {
    private BlockingQueue<Character> queue;

    public Ex30Receiver(BlockingQueue<Character> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("Read: " + queue.take() + ", ");
            }
        } catch (InterruptedException e) {
            print(e + " Receiver interrupted");
        }
    }
}

public class Ex30PipedIO {
    public static void main(String[] args) throws Exception {
        BlockingQueue<Character> queue = new LinkedBlockingDeque<>();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Ex30Sender(queue));
        exec.execute(new Ex30Receiver(queue));
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}