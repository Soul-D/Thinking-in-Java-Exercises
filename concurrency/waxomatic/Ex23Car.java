package concurrency.waxomatic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

class Ex23Car {
    private boolean waxOn = false;

    public synchronized void waxed() {
        waxOn = true;
        notify();
    }

    public synchronized void buffed() {
        waxOn = false;
        notify();
    }

    public synchronized void waitForWaxing() throws InterruptedException {
        while (!waxOn)
            wait();
    }

    public synchronized void waitForBuffing() throws InterruptedException {
        while (waxOn)
            wait();
    }
}

class Ex23WaxOn implements Runnable {
    private Ex23Car car;

    public Ex23WaxOn(Ex23Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                printnb("Wax On! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class Ex23WaxOff implements Runnable {
    private Ex23Car car;

    public Ex23WaxOff(Ex23Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                printnb("Wax Off! ");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

class Ex23WaxOMatic {
    public static void main(String[] args) throws Exception {
        Ex23Car car = new Ex23Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Ex23WaxOff(car));
        exec.execute(new Ex23WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}

