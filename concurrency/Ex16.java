package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ex16 {
    private final Lock lock1 = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();
    private final Lock lock3 = new ReentrantLock();

    void a() {
        lock1.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("a() " + i);
                Thread.yield();
            }
        } finally {
            lock1.unlock();
        }
    }

    void b() {
        lock2.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("b() " + i);
                Thread.yield();
            }
        } finally {
            lock2.unlock();
        }
    }

    void c() {
        lock3.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("c() " + i);
                Thread.yield();
            }
        } finally {
            lock3.unlock();
        }
    }

    public static void main(String[] args) {
        Ex16 ex15 = new Ex16();
        new Thread(ex15::a).start();
        new Thread(ex15::b).start();
        new Thread(ex15::c).start();
    }
}
