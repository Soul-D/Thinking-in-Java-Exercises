package concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.*;

class Ex27Meal {
    private final int orderNum;

    public Ex27Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    public String toString() {
        return "Meal " + orderNum;
    }
}

class Ex27WaitPerson implements Runnable {
    private Ex27Restaurant restaurant;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Ex27WaitPerson(Ex27Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal == null)
                        condition.await();
                } finally {
                    lock.unlock();
                }
                print("Waitperson got " + restaurant.meal);
                restaurant.chef.lock.lock();
                try {
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                } finally {
                    restaurant.chef.lock.unlock();
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Ex27Chef implements Runnable {
    private Ex27Restaurant restaurant;
    private int count = 0;
    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public Ex27Chef(Ex27Restaurant r) {
        restaurant = r;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try {
                    while (restaurant.meal != null)
                        condition.await();
                } finally {
                    lock.unlock();
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                restaurant.waitPerson.lock.lock();
                try {
                    restaurant.meal = new Ex27Meal(count);
                    restaurant.waitPerson.condition.signalAll();
                } finally {
                    restaurant.waitPerson.lock.unlock();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

public class Ex27Restaurant {
    Ex27Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    Ex27WaitPerson waitPerson = new Ex27WaitPerson(this);
    Ex27Chef chef = new Ex27Chef(this);

    public Ex27Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Ex27Restaurant();
    }
}