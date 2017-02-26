package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Ex24 extends JFrame {
    private JSlider horizontalSlider = new JSlider(0, 2, 1);
    private JSlider verticalSlider = new JSlider(0, 2, 1);
    private JButton eraseBtn = new JButton("Clear");
    private Ex24MyPanel panel = new Ex24MyPanel();

    public Ex24() throws HeadlessException {
        eraseBtn.addActionListener(e -> panel.cleaned());
        horizontalSlider.addChangeListener(l -> panel.moveX(((JSlider) l.getSource()).getValue()));
        verticalSlider.addChangeListener(l -> panel.moveY(((JSlider) l.getSource()).getValue()));
        setLayout(new GridLayout(2, 1));
        JPanel sliders = new JPanel();
        sliders.add(horizontalSlider);
        sliders.add(verticalSlider);
        sliders.add(eraseBtn);
        add(BorderLayout.NORTH, sliders);
        add(panel);
        new Thread(panel).start();
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex24(), 500, 500);
    }
}

class Ex24MyPanel extends JPanel implements Runnable {
    private int cursorPosX;
    private int cursorPosY;
    private volatile int moveX;
    private volatile int moveY;
    java.util.List<Point> points = new ArrayList<>();

    public Ex24MyPanel() {
        addComponentListener(new ComponentAdapter() {
            boolean first = true;

            @Override
            public void componentResized(ComponentEvent e) {
                if (first) {
                    points.clear();
                    cursorPosX = getWidth() / 2;
                    cursorPosY = getHeight() / 2;
                    first = false;
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (cursorPosX + moveX < getWidth() && cursorPosX + moveX > 0)
            cursorPosX = cursorPosX + moveX;
        if (cursorPosY + moveY < getHeight() && cursorPosY + moveY > 0)
            cursorPosY = cursorPosY + moveY;
        points.add(new Point(cursorPosX, cursorPosY));
        for (int i = 1; i < points.size(); i++) {
            g.drawLine(points.get(i - 1).x, points.get(i - 1).y, points.get(i).x, points.get(i).y);
        }
    }

    public void moveX(int i) {
        if (i == 1)
            moveX = 0;
        else if (i == 0)
            moveX = -1;
        else
            moveX = 1;
    }

    public void moveY(int i) {
        if (i == 1)
            moveY = 0;
        else if (i == 0)
            moveY = -1;
        else
            moveY = 1;
    }

    public void cleaned() {
        points.clear();
        cursorPosX = getWidth() / 2;
        cursorPosY = getHeight() / 2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                repaint();
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
        }
    }
}

class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
