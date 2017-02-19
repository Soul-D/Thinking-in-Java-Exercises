package concurrency;

import net.mindview.util.BasicGenerator;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Ex34ExchangerDemo {
    private final int id;
    private static AtomicInteger current = new AtomicInteger(0);
    static int size = 10;
    static int delay = 5;

    public Ex34ExchangerDemo() {
        this.id = current.getAndIncrement();
    }

    public static void main(String[] args) throws Exception {
        if (args.length > 0)
            size = new Integer(args[0]);
        if (args.length > 1)
            delay = new Integer(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        Exchanger<List<Ex34ExchangerDemo>> xc = new Exchanger<>();
        List<Ex34ExchangerDemo>
                producerList = new CopyOnWriteArrayList<>(),
                consumerList = new CopyOnWriteArrayList<>();
        exec.execute(new ExchangerProducer<>(xc, BasicGenerator.create(Ex34ExchangerDemo.class), producerList));
        exec.execute(new ExchangerConsumer<>(xc, consumerList));
        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }

    @Override
    public String toString() {
        return "Ex34ExchangerDemo " + id;
    }
}