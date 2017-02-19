package concurrency.waxomatic;

import java.util.concurrent.*;

import static net.mindview.util.Print.*;

class Ex42Car {
    private ExecutorService service = Executors.newSingleThreadExecutor();
    private volatile boolean waxOn = false;

    public void waxed() {
        service.execute(() -> {
            if (!waxOn) {
                printnb("Wax On! ");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waxOn = true;
            }
            else {
                waxed();
            }
        });
    }

    public synchronized void buffed() {
        service.execute(() -> {
            if (waxOn) {
                printnb("Wax Off! ");
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waxOn = false;
            }
            else {
                buffed();
            }
        });
    }
}

class Ex42WaxOn implements Runnable {
    private Ex42Car car;

    public Ex42WaxOn(Ex42Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax On task");
    }
}

class Ex42WaxOff implements Runnable {
    private Ex42Car car;

    public Ex42WaxOff(Ex42Car c) {
        car = c;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Ending Wax Off task");
    }
}

public class Ex42WaxOMatic {
    public static void main(String[] args) throws Exception {
        Ex42Car car = new Ex42Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Ex42WaxOff(car));
        exec.execute(new Ex42WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}