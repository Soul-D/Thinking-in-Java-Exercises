package concurrency;

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

class Ex29Toast {
    public enum Status {DRY, BUTTERED, JAMMED, READY}

    private Status status = Status.DRY;
    private final int id;

    public Ex29Toast(int idn) {
        id = idn;
    }

    public void butter() {
        switch (status) {
            case DRY:
                status = Status.BUTTERED;
                break;
            case JAMMED:
                status = Status.READY;
                break;
            default:
                throw new RuntimeException("Toast " + this + " doesn't need butter");
        }
    }

    public void jam() {
        switch (status) {
            case DRY:
                status = Status.JAMMED;
                break;
            case BUTTERED:
                status = Status.READY;
                break;
            default:
                throw new RuntimeException("Toast " + this + " doesn't need jam");
        }
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class Ex29ToastQueue extends LinkedBlockingQueue<Ex29Toast> {
}

class Ex29Toaster implements Runnable {
    private Ex29ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);

    public Ex29Toaster(Ex29ToastQueue tq) {
        toastQueue = tq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                Ex29Toast t = new Ex29Toast(count++);
                print(t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

class Ex29Butterer implements Runnable {
    private Ex29ToastQueue butterQueue, finishedQueue;

    public Ex29Butterer(Ex29ToastQueue dry, Ex29ToastQueue buttered) {
        butterQueue = dry;
        finishedQueue = buttered;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex29Toast t = butterQueue.take();
                t.butter();
                finishedQueue.put(t);
                print(t);
            }
        } catch (InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

class Ex29Jammer implements Runnable {
    private Ex29ToastQueue jamQueue, finishedQueue;

    public Ex29Jammer(Ex29ToastQueue buttered, Ex29ToastQueue finished) {
        jamQueue = buttered;
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex29Toast t = jamQueue.take();
                t.jam();
                finishedQueue.put(t);
                print(t);
            }
        } catch (InterruptedException e) {
            print("Jammer interrupted");
        }
        print("Jammer off");
    }
}

class Ex29Eater implements Runnable {
    private Ex29ToastQueue finishedQueue;

    public Ex29Eater(Ex29ToastQueue finished) {
        finishedQueue = finished;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex29Toast t = finishedQueue.take();
                if (t.getStatus() != Ex29Toast.Status.READY) {
                    print(">>>> Error: " + t);
                    System.exit(1);
                } else
                    print("Chomp! " + t);
            }
        } catch (InterruptedException e) {
            print("Eater interrupted");
        }
        print("Eater off");
    }
}

class Ex29Manager implements Runnable {
    private Ex29ToastQueue toastQueue, butterQueue, jamQueue, finishedQueue;

    public Ex29Manager(Ex29ToastQueue toastQueue, Ex29ToastQueue butterQueue, Ex29ToastQueue jamQueue, Ex29ToastQueue finishedQueue) {
        this.toastQueue = toastQueue;
        this.butterQueue = butterQueue;
        this.jamQueue = jamQueue;
        this.finishedQueue = finishedQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex29Toast toast = toastQueue.take();
                if (toast != null) {
                    switch (toast.getStatus()) {
                        case DRY:
                            boolean butterQBiggerThanJamQ = butterQueue.size() > jamQueue.size();
                            if (butterQBiggerThanJamQ)
                                jamQueue.offer(toast);
                            else
                                butterQueue.offer(toast);
                            break;
                        case BUTTERED:
                            jamQueue.put(toast);
                            break;
                        case JAMMED:
                            butterQueue.put(toast);
                            break;
                        case READY:
                            finishedQueue.put(toast);
                            break;
                        default:
                            throw new RuntimeException("Toast " + toast + " shouldn't be in toastQueue");
                    }
                }
            }
        } catch (InterruptedException e) {
            print("Manager interrupted");
        }
        print("Manager off");
    }
}

public class Ex29ToastOMatic {
    public static void main(String[] args) throws Exception {
        Ex29ToastQueue toastQueue = new Ex29ToastQueue(),
                butterQueue = new Ex29ToastQueue(),
                jamQueue = new Ex29ToastQueue(),
                finishedQueue = new Ex29ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Ex29Toaster(toastQueue));
        exec.execute(new Ex29Butterer(butterQueue, toastQueue));
        exec.execute(new Ex29Jammer(jamQueue, toastQueue));
        exec.execute(new Ex29Manager(toastQueue, butterQueue, jamQueue, finishedQueue));
        exec.execute(new Ex29Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
