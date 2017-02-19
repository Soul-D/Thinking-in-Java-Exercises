package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Ex6 implements Runnable {
    @Override
    public void run() {
        Random random = new Random();
        try {
            int time = random.nextInt(10) + 1;
            TimeUnit.SECONDS.sleep(time);
            System.out.println(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            ExecutorService service = Executors.newCachedThreadPool();
            for (int i = 0; i < Integer.parseInt(args[0]); i++)
                service.execute(new Ex6());
            service.shutdown();
        }
    }
}
