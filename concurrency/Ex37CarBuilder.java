package concurrency;

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*; //CR

class Ex37Car {
    private final int id;
    private boolean
            engine = false, driveTrain = false, wheels = false, exhaustSystem = false, body = false, fenders = false;

    public Ex37Car(int idn) {
        id = idn;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    public synchronized void addExhaustSystem() {
        exhaustSystem = true;
    }

    public synchronized void addBody() {
        body = true;
    }

    public synchronized void addFenders() {
        fenders = true;
    }

    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine
                + " driveTrain: " + driveTrain
                + " wheels: " + wheels
                + " exhaustSystem: " + exhaustSystem
                + " body: " + body
                + " fenders: " + fenders + " ]";
    }
}

class Ex37CarQueue extends LinkedBlockingQueue<Ex37Car> {
}

class Ex37ChassisBuilder implements Runnable {
    private Ex37CarQueue carQueue;
    private int counter = 0;

    public Ex37ChassisBuilder(Ex37CarQueue cq) {
        carQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Ex37Car c = new Ex37Car(counter++);
                print("ChassisBuilder created " + c);
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            print("Interrupted: ChassisBuilder");
        }
        print("ChassisBuilder off");
    }
}

abstract class Ex37Assembler implements Runnable {
    protected Ex37CarQueue inputQueue, finishingQueue;
    protected Ex37Car car;
    protected CyclicBarrier barrier = new CyclicBarrier(4);
    protected Ex37RobotPool robotPool;

    public Ex37Assembler(Ex37CarQueue cq, Ex37CarQueue fq, Ex37RobotPool rp) {
        inputQueue = cq;
        finishingQueue = fq;
        robotPool = rp;
    }

    public Ex37Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    /*public void run() {
        try {
            while (!Thread.interrupted()) {
                car = inputQueue.take();
                robotPool.hire(Ex37EngineRobot.class, this);
                robotPool.hire(Ex37DriveTrainRobot.class, this);
                robotPool.hire(Ex37WheelRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            print("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print("Assembler off");
    }*/
}

class Ex37FirstAssembler extends Ex37Assembler {
    public Ex37FirstAssembler(Ex37CarQueue cq, Ex37CarQueue fq, Ex37RobotPool rp) {
        super(cq, fq, rp);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car = inputQueue.take();
                robotPool.hire(Ex37EngineRobot.class, this);
                robotPool.hire(Ex37DriveTrainRobot.class, this);
                robotPool.hire(Ex37WheelRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            print("Exiting FirstAssembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print("FirstAssembler off");
    }
}

class Ex37SecondAssembler extends Ex37Assembler {
    public Ex37SecondAssembler(Ex37CarQueue cq, Ex37CarQueue fq, Ex37RobotPool rp) {
        super(cq, fq, rp);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car = inputQueue.take();
                robotPool.hire(Ex37ExhaustSystemRobot.class, this);
                robotPool.hire(Ex37BodyRobot.class, this);
                robotPool.hire(Ex37FenderRobot.class, this);
                barrier.await();
                finishingQueue.put(car);
            }
        } catch (InterruptedException e) {
            print("Exiting FirstAssembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print("FirstAssembler off");
    }
}

class Ex37Reporter implements Runnable {
    private Ex37CarQueue carQueue;

    public Ex37Reporter(Ex37CarQueue cq) {
        carQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(carQueue.take());
            }
        } catch (InterruptedException e) {
            print("Exiting Reporter via interrupt");
        }
        print("Reporter off");
    }
}

abstract class Ex37Robot implements Runnable {
    private Ex37RobotPool pool;

    public Ex37Robot(Ex37RobotPool p) {
        pool = p;
    }

    protected Ex37Assembler assembler;

    public Ex37Robot assignAssembler(Ex37Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    abstract protected void performService();

    public void run() {
        try {
            powerDown();
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await();
                powerDown();
            }
        } catch (InterruptedException e) {
            print("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print(this + " off");
    }

    private synchronized void powerDown() throws InterruptedException {
        engage = false;
        assembler = null;
        pool.release(this);
        while (!engage)
            wait();
    }

    public String toString() {
        return getClass().getName();
    }
}

class Ex37EngineRobot extends Ex37Robot {
    public Ex37EngineRobot(Ex37RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing engine");
        assembler.car().addEngine();
    }
}

class Ex37DriveTrainRobot extends Ex37Robot {
    public Ex37DriveTrainRobot(Ex37RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}

class Ex37WheelRobot extends Ex37Robot {
    public Ex37WheelRobot(Ex37RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class Ex37ExhaustSystemRobot extends Ex37Robot {
    public Ex37ExhaustSystemRobot(Ex37RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing ExhaustSystem");
        assembler.car().addExhaustSystem();
    }
}

class Ex37BodyRobot extends Ex37Robot {
    public Ex37BodyRobot(Ex37RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing Body");
        assembler.car().addBody();
    }
}

class Ex37FenderRobot extends Ex37Robot {
    public Ex37FenderRobot(Ex37RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing Fenders");
        assembler.car().addFenders();
    }
}

class Ex37RobotPool {
    private Set<Ex37Robot> pool = new HashSet<>();

    public synchronized void add(Ex37Robot r) {
        pool.add(r);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Ex37Robot> robotType, Ex37Assembler d) throws InterruptedException {
        for (Ex37Robot r : pool)
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        wait();
        hire(robotType, d);
    }

    public synchronized void release(Ex37Robot r) {
        add(r);
    }
}

public class Ex37CarBuilder {
    public static void main(String[] args) throws Exception {
        Ex37CarQueue chassisQueue = new Ex37CarQueue(),
                stage2Queue = new Ex37CarQueue(),
                finishingQueue = new Ex37CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        Ex37RobotPool robotPool = new Ex37RobotPool();
        exec.execute(new Ex37EngineRobot(robotPool));
        exec.execute(new Ex37DriveTrainRobot(robotPool));
        exec.execute(new Ex37WheelRobot(robotPool));
        exec.execute(new Ex37ExhaustSystemRobot(robotPool));
        exec.execute(new Ex37BodyRobot(robotPool));
        exec.execute(new Ex37FenderRobot(robotPool));
        exec.execute(new Ex37FirstAssembler(chassisQueue, stage2Queue, robotPool));
        exec.execute(new Ex37SecondAssembler(stage2Queue, finishingQueue, robotPool));
        exec.execute(new Ex37Reporter(finishingQueue));
        exec.execute(new Ex37ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
