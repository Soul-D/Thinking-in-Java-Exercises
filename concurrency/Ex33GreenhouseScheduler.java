package concurrency;

import java.util.*;
import java.util.concurrent.*;

abstract class Event implements Delayed, Runnable {
    private long eventTime;
    private long delay;

    public Event(long delay) {
        this.delay = delay;
        eventTime = System.currentTimeMillis() + delay;
    }

    public void reset() {
        eventTime = System.currentTimeMillis() + delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(eventTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Event event = (Event) o;
        return Long.compare(getDelay(TimeUnit.MILLISECONDS), event.getDelay(TimeUnit.MILLISECONDS));
    }
}

public class Ex33GreenhouseScheduler {
    private volatile boolean light = false;
    private volatile boolean water = false;
    private String thermostat = "Day";
    private DelayQueue<Event> queue = new DelayQueue<>();
    private ExecutorService service = Executors.newCachedThreadPool();

    public synchronized String getThermostat() {
        return thermostat;
    }

    public synchronized void setThermostat(String value) {
        thermostat = value;
    }

    public void add(Event event) {
        queue.put(event);
    }

    class LightOn extends Event {
        public LightOn(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Turning on lights");
            light = true;
        }
    }

    class LightOff extends Event {
        public LightOff(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Turning off lights");
            light = false;
        }
    }

    class WaterOn extends Event {
        public WaterOn(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Turning greenhouse water on");
            water = true;
        }
    }

    class WaterOff extends Event {
        public WaterOff(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Turning greenhouse water off");
            water = false;
        }
    }

    class ThermostatNight extends Event {
        public ThermostatNight(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Thermostat to night setting");
            setThermostat("Night");
        }
    }

    class ThermostatDay extends Event {
        public ThermostatDay(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Thermostat to day setting");
            setThermostat("Day");
        }
    }

    class Bell extends Event {
        public Bell(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Bing!");
        }
    }

    class Terminate extends Event {
        public Terminate(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Terminating");
            service.shutdownNow();
            new Thread() {
                public void run() {
                    for (DataPoint d : data)
                        System.out.println(d);
                }
            }.start();
        }
    }

    static class DataPoint {
        final Calendar time;
        final float temperature;
        final float humidity;

        public DataPoint(Calendar d, float temp, float hum) {
            time = d;
            temperature = temp;
            humidity = hum;
        }

        public String toString() {
            return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f", temperature, humidity);
        }
    }

    private Calendar lastTime = Calendar.getInstance();

    {
        lastTime.set(Calendar.MINUTE, 30);
        lastTime.set(Calendar.SECOND, 00);
    }

    private float lastTemp = 65.0f;
    private int tempDirection = +1;
    private float lastHumidity = 50.0f;
    private int humidityDirection = +1;
    private Random rand = new Random(47);
    List<DataPoint> data = Collections.synchronizedList(new ArrayList<>());

    class CollectData extends Event {
        public CollectData(long delay) {
            super(delay);
        }

        public void run() {
            System.out.println("Collecting data");
            synchronized (Ex33GreenhouseScheduler.this) {
                lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
                if (rand.nextInt(5) == 4)
                    tempDirection = -tempDirection;
                lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
                if (rand.nextInt(5) == 4)
                    humidityDirection = -humidityDirection;
                lastHumidity = lastHumidity +
                        humidityDirection * rand.nextFloat();
                data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
            }
        }
    }

    class Restart extends Event {
        private Event[] events;

        public Restart(long delay, Event[] events) {
            super(delay);
            this.events = events;
            for (Event event : events) {
                event.reset();
                queue.put(event);
            }
        }

        @Override
        public void run() {
            System.out.println("Restarting");
            for (Event event : events) {
                event.reset();
                queue.put(event);
            }
            reset();
            queue.put(this);
        }
    }

    public void start() {
        try {
            while (!Thread.interrupted()) {
                Event event = queue.take();
                try {
                    service.execute(event);
                } catch (RejectedExecutionException ignore) {
                }
            }
        } catch (InterruptedException e) {
        }
        System.out.println("Greenhouse is stopped");
    }

    public static void main(String[] args) {
        Ex33GreenhouseScheduler gh = new Ex33GreenhouseScheduler();
        Event[] events = new Event[]{
                gh.new Bell(1000),
                gh.new ThermostatNight(2000),
                gh.new LightOn(200),
                gh.new LightOff(400),
                gh.new WaterOn(600),
                gh.new WaterOff(800),
                gh.new ThermostatDay(1400),
                gh.new CollectData(500)
        };
        gh.add(gh.new Restart(2000, events));
        gh.add(gh.new Terminate(5000));
        gh.start();
    }
}
