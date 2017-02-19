package concurrency;

import java.util.concurrent.TimeUnit;

public class Ex11 {
    private int i;
    private int j;

    public synchronized void incFields() {
        i++;
        j++;
    }

    @Override
    public synchronized String toString() {
        final StringBuilder sb = new StringBuilder("Ex11{");
        sb.append("i=").append(i);
        sb.append(", j=").append(j);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        Ex11 ex11 = new Ex11();
        Thread thread;
        for (int i = 0; i < 5; i++) {
            thread = new Thread(() -> {
                while (true) ex11.incFields();
            });
            thread.setDaemon(true);
            thread.start();
        }
        for (int i = 0; i < 5; i++) {
            System.out.println(ex11);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
