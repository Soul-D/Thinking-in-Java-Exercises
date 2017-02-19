package concurrency;

import java.util.Timer;
import java.util.TimerTask;

public class Ex14 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("first");
            }
        }, 5000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("second");
            }
        }, 4000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("third");
            }
        }, 3000);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("forth");
            }
        }, 2000);
    }
}
