package concurrency;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.printnb;

public class Ex7Daemons {
    public static void main(String[] args) throws Exception {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        printnb("d.isDaemon() = " + d.isDaemon() + ", ");
        TimeUnit.NANOSECONDS.sleep(1);
    }
}