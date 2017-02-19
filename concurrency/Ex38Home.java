package concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static net.mindview.util.Print.print;

public class Ex38Home {
    private static AtomicInteger count = new AtomicInteger(0);
    private final int id = count.getAndIncrement();
    private boolean
            footing = false,
            steel = false,
            concreteForm = false,
            concreteFoundation = false,
            plumb = false,
            concreteSlab = false,
            frame = false;

    public synchronized void digFooting() {
        footing = true;
    }

    public synchronized void laySteel() {
        steel = true;
    }

    public synchronized void buildConcreteForm() {
        concreteForm = true;
    }

    public synchronized void pourConcreteFoundation() {
        concreteFoundation = true;
    }

    public synchronized void plumbing() {
        plumb = true;
    }

    public synchronized void pourConcreteSlab() {
        concreteSlab = true;
    }

    public synchronized void framing() {
        frame = true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ex38Home{");
        sb.append("id=").append(id);
        sb.append(", footing=").append(footing);
        sb.append(", steel=").append(steel);
        sb.append(", concreteForm=").append(concreteForm);
        sb.append(", concreteFoundation=").append(concreteFoundation);
        sb.append(", plumb=").append(plumb);
        sb.append(", concreteSlab=").append(concreteSlab);
        sb.append(", frame=").append(frame);
        sb.append('}');
        return sb.toString();
    }
}

class Ex38HomeQueue extends LinkedBlockingDeque<Ex38Home> {
}

class Ex38FootingDigger implements Runnable {
    private Ex38HomeQueue homeQueue;

    public Ex38FootingDigger(Ex38HomeQueue cq) {
        homeQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                Ex38Home h = new Ex38Home();
                h.digFooting();
                print("FootingDigger created " + h);
                homeQueue.put(h);
            }
        } catch (InterruptedException e) {
            print("Interrupted: ChassisBuilder");
        }
        print("ChassisBuilder off");
    }
}

class Ex38Assembler implements Runnable {
    private Ex38HomeQueue footingQueue, finishingQueue;
    private CyclicBarrier barrier = new CyclicBarrier(3);
    private CyclicBarrier sequenceBarrier = new CyclicBarrier(2);
    private Ex38Home home;
    private Ex38RobotPool robotPool;
    private boolean secondSequenceStage;

    public Ex38Assembler(Ex38HomeQueue cq, Ex38HomeQueue fq, Ex38RobotPool rp) {
        footingQueue = cq;
        finishingQueue = fq;
        robotPool = rp;
    }

    public CyclicBarrier barrier() {
        return secondSequenceStage ? sequenceBarrier : barrier;
    }

    public Ex38Home getHome() {
        return home;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                secondSequenceStage = false;
                home = footingQueue.take();
                robotPool.hire(Ex38SteelRobot.class, this);
                robotPool.hire(Ex38ConcreteFormRobot.class, this);
                barrier.await();
                secondSequenceStage = true;
                robotPool.hire(Ex38ConcreteFoundationRobot.class, this);
                sequenceBarrier.await();
                robotPool.hire(Ex38PlumbRobot.class, this);
                sequenceBarrier.await();
                robotPool.hire(Ex38ConcreteSlabRobot.class, this);
                sequenceBarrier.await();
                robotPool.hire(Ex38FrameRobot.class, this);
                sequenceBarrier.await();
                finishingQueue.put(home);
            }
        } catch (InterruptedException e) {
            print("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        print("Assembler off");
    }
}

class Ex38Reporter implements Runnable {
    private Ex38HomeQueue homeQueue;

    public Ex38Reporter(Ex38HomeQueue cq) {
        homeQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(homeQueue.take());
            }
        } catch (InterruptedException e) {
            print("Exiting Reporter via interrupt");
        }
        print("Reporter off");
    }
}

abstract class Ex38Robot implements Runnable {
    private Ex38RobotPool pool;

    public Ex38Robot(Ex38RobotPool p) {
        pool = p;
    }

    protected Ex38Assembler assembler;

    public Ex38Robot assignAssembler(Ex38Assembler assembler) {
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

class Ex38SteelRobot extends Ex38Robot {
    public Ex38SteelRobot(Ex38RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " laying steel");
        assembler.getHome().laySteel();
    }
}

class Ex38ConcreteFormRobot extends Ex38Robot {
    public Ex38ConcreteFormRobot(Ex38RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " building concrete form");
        assembler.getHome().buildConcreteForm();
    }
}

class Ex38ConcreteFoundationRobot extends Ex38Robot {
    public Ex38ConcreteFoundationRobot(Ex38RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " pouring concrete foundation");
        assembler.getHome().pourConcreteFoundation();
    }
}

class Ex38PlumbRobot extends Ex38Robot {
    public Ex38PlumbRobot(Ex38RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " plumbing");
        assembler.getHome().plumbing();
    }
}

class Ex38ConcreteSlabRobot extends Ex38Robot {
    public Ex38ConcreteSlabRobot(Ex38RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " pouring concrete slab");
        assembler.getHome().pourConcreteSlab();
    }
}

class Ex38FrameRobot extends Ex38Robot {
    public Ex38FrameRobot(Ex38RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " is framing");
        assembler.getHome().framing();
    }
}

class Ex38RobotPool {
    private Set<Ex38Robot> pool = new HashSet<>();

    public synchronized void add(Ex38Robot r) {
        pool.add(r);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Ex38Robot> robotType, Ex38Assembler d) throws InterruptedException {
        for (Ex38Robot r : pool)
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage();
                return;
            }
        wait();
        hire(robotType, d);
    }

    public synchronized void release(Ex38Robot r) {
        add(r);
    }
}

class Ex38HomeBuilder {
    public static void main(String[] args) throws Exception {
        Ex38HomeQueue footingQueue = new Ex38HomeQueue(),
                finishingQueue = new Ex38HomeQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        Ex38RobotPool robotPool = new Ex38RobotPool();
        exec.execute(new Ex38SteelRobot(robotPool));
        exec.execute(new Ex38ConcreteFormRobot(robotPool));
        exec.execute(new Ex38ConcreteFoundationRobot(robotPool));
        exec.execute(new Ex38PlumbRobot(robotPool));
        exec.execute(new Ex38ConcreteSlabRobot(robotPool));
        exec.execute(new Ex38FrameRobot(robotPool));
        exec.execute(new Ex38Assembler(footingQueue, finishingQueue, robotPool));
        exec.execute(new Ex38Reporter(finishingQueue));
        exec.execute(new Ex38FootingDigger(footingQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}

