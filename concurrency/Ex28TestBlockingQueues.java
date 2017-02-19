package concurrency;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

import static concurrency.TestBlockingQueues.getkey;
import static net.mindview.util.Print.print;

public class Ex28TestBlockingQueues {
    static void test(String msg, BlockingQueue<LiftOff> queue) {
        print(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);
        t.start();
        new Thread(new Ex28AddLiftOff(5, runner)).start();
        getkey("Press 'Enter' (" + msg + ")");
        t.interrupt();
        print("Finished " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue", new LinkedBlockingQueue<>());
        test("ArrayBlockingQueue", new ArrayBlockingQueue<>(3));
        test("SynchronousQueue", new SynchronousQueue<>());
    }
}

class Ex28AddLiftOff implements Runnable {
    private final int number;
    private final LiftOffRunner runner;

    public Ex28AddLiftOff(int number, LiftOffRunner runner) {
        this.number = number;
        this.runner = runner;
    }

    @Override
    public void run() {
        for (int i = 0; i < number; i++)
            runner.add(new LiftOff(5));
    }
}