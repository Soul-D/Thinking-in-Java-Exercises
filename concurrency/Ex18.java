package concurrency;

import java.util.concurrent.TimeUnit;

public class Ex18 {
    public void sleep(){
        try {
            TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

class Ex18Task implements Runnable{
    @Override
    public void run() {
        new Ex18().sleep();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Ex18Task());
        thread.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        thread.interrupt();
    }
}
