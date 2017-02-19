package concurrency.restaurant2;

import enumerated.menu.*;

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

class Ex36Table {
    private static int count = 0;
    private final int id = count++;
    private Ex36OrderTicket orderTicket;
    private Ex36WaitPerson waitPerson;
    private CyclicBarrier barrier;

    public Ex36Table(Ex36WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    public CyclicBarrier getBarrier() {
        return barrier;
    }

    public Ex36OrderTicket getOrderTicket() {
        return orderTicket;
    }

    public void setCustomers(List<Ex36Customer> customers) {
        barrier = new CyclicBarrier(customers.size() + 1);
        orderTicket = new Ex36OrderTicket(customers, waitPerson);
        new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                print(this + " interrupted");
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            barrier.reset();
            waitPerson.acceptOrderTicket(orderTicket);
            try {
                barrier.await();
                System.out.println(this + " customers are served");
                waitPerson.getRestaurant().getTables().put(Ex36Table.this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                print(this + " is closed");
            }
        }).start();
    }

    @Override
    public String toString() {
        return "table#" + id;
    }
}

class Ex36OrderTicket {
    private static int counter = 0;
    private final int id = counter++;
    private final Map<Ex36Customer, List<Food>> orders;
    private final Ex36WaitPerson waitPerson;

    public Ex36WaitPerson getWaitPerson() {
        return waitPerson;
    }

    public Ex36OrderTicket(List<Ex36Customer> customers, Ex36WaitPerson wp) {
        orders = new HashMap<>();
        customers.stream().forEach(c -> orders.put(c, new ArrayList<>()));
        waitPerson = wp;
    }

    public void addToTicket(Ex36Customer customer, Food food) {
        orders.get(customer).add(food);
    }

    public Map<Ex36Customer, List<Food>> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("OrderTicket#");
        sb.append(id).append(": ");
        orders.entrySet().stream().forEach(p -> sb.append(p.getKey()).append(" ").append(p.getValue()).append("\n"));
        return sb.toString();
    }
}

class Ex36Plate {
    private final Ex36OrderTicket order;
    private final Food food;
    private final Ex36Customer customer;

    public Ex36Plate(Ex36OrderTicket ord, Ex36Customer customer, Food f) {
        order = ord;
        this.customer = customer;
        food = f;
    }

    public String toString() {
        return food.toString();
    }

    public Ex36OrderTicket getOrder() {
        return order;
    }

    public Ex36Customer getCustomer() {
        return customer;
    }
}

class Ex36Customer implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Ex36Table table;
    private SynchronousQueue<Ex36Plate> placeSetting = new SynchronousQueue<>();
    private int ordersLeft = 0;

    public Ex36Customer(Ex36Table table) {
        this.table = table;
    }

    public void deliver(Ex36Plate p) throws InterruptedException {
        placeSetting.put(p);
    }

    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            table.getOrderTicket().addToTicket(this, food);
            ordersLeft++;
        }
        try {
            table.getBarrier().await();
            while (ordersLeft > 0) {
                placeSetting.take();
                ordersLeft--;
            }
            print(this + "finished meal, leaving");
            table.getBarrier().await();
        } catch (InterruptedException e) {
            print(this + "waiting for " + ordersLeft + "meals interrupted");
        } catch (BrokenBarrierException e) {
        }

    }

    public String toString() {
        return "Customer " + id + " ";
    }
}

class Ex36WaitPerson implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Ex36Restaurant restaurant;
    BlockingQueue<Ex36Plate> filledOrders = new LinkedBlockingQueue<>();

    public Ex36WaitPerson(Ex36Restaurant rest) {
        restaurant = rest;
    }

    public void acceptOrderTicket(Ex36OrderTicket ticket) {
        try {
            restaurant.orders.put(ticket);
        } catch (InterruptedException e) {
            print(this + " placeOrderTicket interrupted");
        }
    }

    public Ex36Restaurant getRestaurant() {
        return restaurant;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex36Plate plate = filledOrders.take();
                print(this + "received " + plate + " delivering to " + plate.getCustomer());
                plate.getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    public String toString() {
        return "WaitPerson " + id + " ";
    }
}

class Ex36Chef implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Ex36Restaurant restaurant;
    private static Random rand = new Random(47);

    public Ex36Chef(Ex36Restaurant rest) {
        restaurant = rest;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex36OrderTicket orderTicket = restaurant.orders.take();
                for (Map.Entry<Ex36Customer, List<Food>> entry : orderTicket.getOrders().entrySet()) {
                    for (Food food : entry.getValue()) {
                        TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                        Ex36Plate plate = new Ex36Plate(orderTicket, entry.getKey(), food);
                        orderTicket.getWaitPerson().filledOrders.put(plate);
                    }
                }
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    public String toString() {
        return "Chef " + id + " ";
    }
}

class Ex36Restaurant implements Runnable {
    private List<Ex36WaitPerson> waitPersons = new ArrayList<>();
    private List<Ex36Chef> chefs = new ArrayList<>();
    private BlockingQueue<Ex36Table> tables = new LinkedBlockingDeque<>();
    private ExecutorService exec;
    private static Random rand = new Random(47);
    BlockingQueue<Ex36OrderTicket> orders = new LinkedBlockingQueue<>();

    public Ex36Restaurant(ExecutorService e, int nWaitPersons, int nChefs, int nTables) {
        exec = e;
        for (int i = 0; i < nWaitPersons; i++) {
            Ex36WaitPerson waitPerson = new Ex36WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for (int i = 0; i < nChefs; i++) {
            Ex36Chef chef = new Ex36Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
        for (int i = 0; i < nTables; i++) {
            Ex36Table table = new Ex36Table(waitPersons.get(rand.nextInt(waitPersons.size())));
            tables.add(table);
        }
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Ex36Table table = tables.take();
                int customersNumber = rand.nextInt(5) + 1;
                List<Ex36Customer> customerList = new ArrayList<>();
                for (int i = 0; i < customersNumber; i++)
                    customerList.add(new Ex36Customer(table));
                table.setCustomers(customerList);
                customerList.stream().forEach(exec::execute);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Restaurant interrupted");
        }
        print("Restaurant closing");
    }

    public BlockingQueue<Ex36Table> getTables() {
        return tables;
    }
}

public class Ex36RestaurantWithQueues {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Ex36Restaurant restaurant = new Ex36Restaurant(exec, 5, 2, 3);
        exec.execute(restaurant);
        if (args.length > 0)
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            print("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}