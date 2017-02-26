package gui;

import net.mindview.util.TaskItem;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static net.mindview.util.SwingConsole.run;


public class Ex33MonitoredLongRunningCallable extends JFrame {
    private JButton
            b1 = new JButton("Start Long Running Task"),
            b2 = new JButton("End Long Running Task"),
            b3 = new JButton("Get results");
    private Ex33TaskManager<String, MonitoredCallable> manager = new Ex33TaskManager<>();

    public Ex33MonitoredLongRunningCallable() {
        b1.addActionListener(e -> {
            MonitoredCallable task = new MonitoredCallable(new ProgressMonitor(Ex33MonitoredLongRunningCallable.this, "Long-Running Task", "", 0, 0));
            manager.add(task);
            System.out.println(task + " added to the queue");
        });
        b2.addActionListener(e -> {
            for (String result : manager.purge())
                System.out.println(result);
        });
        b3.addActionListener(e -> {
            for (String result : manager.getResults())
                System.out.println(result);
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }

    public static void main(String[] args) {
        run(new Ex33MonitoredLongRunningCallable(), 200, 500);
    }
}

class Ex33TaskManager<R, C extends Callable<R>> extends ArrayList<TaskItem<R, C>> {
    private ExecutorService exec = Executors.newCachedThreadPool();

    public void add(C task) {
        add(new TaskItem<>(exec.submit(task), task));
    }

    public java.util.List<R> getResults() {
        Iterator<TaskItem<R, C>> items = iterator();
        java.util.List<R> results = new ArrayList<>();
        while (items.hasNext()) {
            TaskItem<R, C> item = items.next();
            if (item.future.isDone()) {
                try {
                    results.add(item.future.get());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                items.remove();
            }
        }
        return results;
    }

    public java.util.List<String> purge() {
        Iterator<TaskItem<R, C>> items = iterator();
        java.util.List<String> results = new ArrayList<>();
        while (items.hasNext()) {
            TaskItem<R, C> item = items.next();
            if (!item.future.isDone()) {
                results.add("Cancelling " + item.task);
                item.future.cancel(true);
                items.remove();
            }
        }
        return results;
    }
}