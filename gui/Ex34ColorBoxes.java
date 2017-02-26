package gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.SwingConsole.*;

class Ex34CBox extends JPanel implements Runnable {
    private int pause;
    private static Random rand = new Random();
    private Color color = new Color(0);

    public void paintComponent(Graphics g) {
        g.setColor(color);
        Dimension s = getSize();
        int xStep = (int) s.getWidth() / 6;
        int yStep = (int) s.getHeight() / 4;
        int[] xPoints = new int[]{
                xStep, xStep * 4, xStep * 3, xStep * 2, xStep * 5
        };
        int[] yPoints = new int[]{
                yStep * 2, yStep * 3, yStep, yStep * 3, yStep * 2
        };
        g.fillPolygon(xPoints, yPoints, 5);
    }

    public Ex34CBox(int pause) {
        this.pause = pause;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                color = new Color(rand.nextInt(0xFFFFFF));
                repaint();
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        } catch (InterruptedException e) {
        }
    }
}

public class Ex34ColorBoxes extends JFrame {
    private int grid = 12;
    private int pause = 50;
    private static ExecutorService exec = Executors.newCachedThreadPool();

    public void setUp() {
        setLayout(new GridLayout(grid, grid));
        for (int i = 0; i < grid * grid; i++) {
            Ex34CBox cb = new Ex34CBox(pause);
            add(cb);
            exec.execute(cb);
        }
    }

    public static void main(String[] args) {
        Ex34ColorBoxes boxes = new Ex34ColorBoxes();
        boxes.setUp();
        run(boxes, 500, 400);
    }
}
