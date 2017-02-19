package concurrency;

import java.util.concurrent.*;
import java.util.*;

class Ex35Request {
    private final int serviceTime;

    public Ex35Request(int tm) {
        serviceTime = tm;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public String toString() {
        return "[" + serviceTime + "]";
    }
}

class Ex35RequestLine extends ArrayBlockingQueue<Ex35Request> {
    public Ex35RequestLine(int maxLineSize) {
        super(maxLineSize);
    }

    public String toString() {
        if (this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for (Ex35Request request : this)
            result.append(request);
        return result.toString();
    }
}

class Ex35RequestGenerator implements Runnable {
    private Ex35RequestLine requests;
    private static Random rand = new Random(47);
    private int requestsPerSecond;
    private int sleepTime;

    public synchronized int getRequestsPerSecond() {
        return requestsPerSecond;
    }

    public synchronized void setRequestsPerSecond(int requestsPerSecond) {
        this.requestsPerSecond = requestsPerSecond;
        sleepTime = 1000 / requestsPerSecond;
    }

    public Ex35RequestGenerator(Ex35RequestLine cq, int requestsPerSecond) {
        requests = cq;
        setRequestsPerSecond(requestsPerSecond);
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(sleepTime);
                requests.put(new Ex35Request(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("RequestGenerator interrupted");
        }
        System.out.println("RequestGenerator terminating");
    }
}

class Ex35Server implements Runnable, Comparable<Ex35Server> {
    private static int counter = 0;
    private final int id = counter++;
    private int requestsServed = 0;
    private Ex35RequestLine requests;

    public Ex35Server(Ex35RequestLine cq) {
        requests = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex35Request request = requests.take();
                TimeUnit.MILLISECONDS.sleep(request.getServiceTime());
                synchronized (this) {
                    requestsServed++;
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() {
        return "Server " + id + " ";
    }

    public String shortString() {
        return "S" + id;
    }

    public synchronized int compareTo(Ex35Server other) {
        return requestsServed < other.requestsServed ? -1 : (requestsServed == other.requestsServed ? 0 : 1);
    }
}

class Ex35ServerManager implements Runnable {
    private ExecutorService exec;
    private Ex35RequestLine requests;
    private Ex35RequestGenerator generator;
    private int adjustmentPeriod;
    private int maximumRequestLineSize;
    private int prevRequestsPerSecond = 0;
    private int requestsPerSecond;
    private int successiveTimes = 0;
    private int numberOfServers;

    public Ex35ServerManager(ExecutorService e, Ex35RequestLine requests, Ex35RequestGenerator generator, int adjustmentPeriod, int numberOfServers, int maximumRequestLineSize) {
        exec = e;
        this.requests = requests;
        this.generator = generator;
        this.adjustmentPeriod = adjustmentPeriod;
        this.maximumRequestLineSize = maximumRequestLineSize;
        this.numberOfServers = numberOfServers;
        requestsPerSecond = generator.getRequestsPerSecond();
        exec.execute(generator);
        for (int i = 0; i < numberOfServers; i++) {
            Ex35Server server = new Ex35Server(requests);
            exec.execute(server);
        }
    }

    public void adjustLoad() throws InterruptedException {
        if (requests.size() < maximumRequestLineSize) {
            successiveTimes++;
            if (successiveTimes == 60) {
                prevRequestsPerSecond = requestsPerSecond;
                successiveTimes = 0;
                requestsPerSecond++;
                generator.setRequestsPerSecond(requestsPerSecond);
                requests.clear();
            }
        } else {
            System.out.println("Optimal load for " + numberOfServers + " servers is " + prevRequestsPerSecond + " requests per second");
            exec.shutdown();
            throw new InterruptedException();
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustLoad();
                System.out.println(requests + " requestsPerSecond = " + requestsPerSecond + " successiveTimes = " + successiveTimes);
            }
        } catch (InterruptedException e) {
            System.out.println(this + "interrupted");
        }
        System.out.println(this + "terminating");
    }

    public String toString() {
        return "ServerManager ";
    }
}

public class Ex35BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int ADJUSTMENT_PERIOD = 1000;
    static final int NUMBER_OF_SERVERS = 2;
    static final int MAXIMUM_REQUEST_LINE_SIZE = 40;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Ex35RequestLine requests = new Ex35RequestLine(MAX_LINE_SIZE);
        Ex35RequestGenerator generator = new Ex35RequestGenerator(requests, 4);
        exec.execute(new Ex35ServerManager(exec, requests, generator, ADJUSTMENT_PERIOD, NUMBER_OF_SERVERS, MAXIMUM_REQUEST_LINE_SIZE));
    }
}