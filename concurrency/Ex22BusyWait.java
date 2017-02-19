package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex22BusyWait implements Runnable {
    @Override
    public void run() {
        while (!Ex22Sleeper.flag) {
        }
        Ex22Sleeper.flag = false;
        System.out.println("Busy: Changed flag to false");
    }
}

class Ex22Sleeper implements Runnable {
    public static volatile boolean flag = false;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        flag = true;
    }
}

class Ex22Wait implements Runnable {
    @Override
    public void run() {
        synchronized (Ex22SleeperSync.monitor) {
            while (!Ex22SleeperSync.flag)
                try {
                    Ex22SleeperSync.monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            Ex22SleeperSync.flag = false;
            System.out.println("Free: Changed flag to false");
        }
    }
}

class Ex22SleeperSync implements Runnable {
    public static Boolean flag = false;
    public static final Object monitor = new Object();

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ignore) {
        }
        synchronized (monitor) {
            flag = true;
            monitor.notify();
        }
    }
}

class Ex22Test {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new Ex22BusyWait());
        service.execute(new Ex22Sleeper());
        TimeUnit.SECONDS.sleep(2);
        service.execute(new Ex22Wait());
        service.execute(new Ex22SleeperSync());
        service.shutdown();
    }
}
