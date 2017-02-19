package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

class Ex26Meal {
    private final int orderNum;

    public Ex26Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

class Ex26WaitPerson implements Runnable {
    static final Object lock = new Object();
    private Ex26Restaurant restaurant;

    public Ex26WaitPerson(Ex26Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null)
                        wait();
                }
                print("Waitperson got " + restaurant.meal);
                Ex26Meal meal;
                synchronized (restaurant.chef) {
                    meal = restaurant.meal;
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
                synchronized (lock) {
                    restaurant.isClean = false;
                    restaurant.busBoy.meal = meal;
                    lock.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Ex26Chef implements Runnable {
    private Ex26Restaurant restaurant;
    private int count = 0;

    public Ex26Chef(Ex26Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null)
                        wait();
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Ex26Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class Ex26BusBoy implements Runnable {
    private Ex26Restaurant restaurant;
    Ex26Meal meal;

    public Ex26BusBoy(Ex26Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (restaurant.waitPerson.lock) {
                    while (restaurant.isClean)
                        restaurant.waitPerson.lock.wait();
                    System.out.println("Cleaning up " + meal);
                    restaurant.isClean = true;
                    meal = null;
                }
            }
        } catch (InterruptedException ignore) {
        }
    }
}

public class Ex26Restaurant {
    Boolean isClean = true;
    Ex26Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    Ex26WaitPerson waitPerson = new Ex26WaitPerson(this);
    Ex26Chef chef = new Ex26Chef(this);
    Ex26BusBoy busBoy = new Ex26BusBoy(this);

    public Ex26Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }

    public static void main(String[] args) {
        new Ex26Restaurant();
    }
}