package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex21First implements Runnable {
    @Override
    public synchronized void run() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("I'm here!");
    }
}

class Ex21Second implements Runnable {
    private Runnable runnable;

    public Ex21Second(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        synchronized (runnable) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runnable.notifyAll();
        }
    }
}

class Ex21Test {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Ex21First first = new Ex21First();
        service.execute(first);
        service.execute(new Ex21Second(first));
        service.shutdown();
    }
}