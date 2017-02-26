package gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.SwingConsole.run;

class Ex26SineDraw extends JPanel {
    private final int SCALEFACTOR = 200;
    private int cycles;
    private int points;
    private int tPoints;
    private double[] sines;
    private int[] pts;
    private int offset = 0;
    private int offsetSpeed = 1;
    private java.util.Timer timer = new java.util.Timer();

    public Ex26SineDraw() {
        setCycles(5);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                offset -= offsetSpeed;
                tPoints = points;
                computeSin();
                repaint();
            }
        }, 50, 50);
    }

    public synchronized void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();
        double hstep = (double) maxWidth / (double) tPoints;
        int maxHeight = getHeight();
        pts = new int[tPoints];
        for (int i = 0; i < tPoints; i++)
            pts[i] = (int) (sines[i] * maxHeight / 2 * .95 + maxHeight / 2);
        g.setColor(Color.RED);
        for (int i = 1; i < tPoints; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }

    private synchronized void computeSin() {
        sines = new double[tPoints];
        for (int i = 0; i < tPoints; i++) {
            double radians = (Math.PI / SCALEFACTOR) * (i + offset);
            sines[i] = Math.sin(radians);
        }
    }

    public synchronized void setCycles(int newCycles) {
        cycles = newCycles;
        points = SCALEFACTOR * cycles * 2;
    }

    public synchronized void setSpeed(int speed) {
        offsetSpeed = speed;
    }
}

public class Ex26SineWave extends JFrame {

    public Ex26SineWave(int quantity) {
        setLayout(new GridLayout(quantity, 1));
        for (int i = 0; i < quantity; i++) {
            JPanel bpanel = new JPanel(new GridLayout(2, 1));
            Ex26SineDraw sines = new Ex26SineDraw();
            JSlider adjustCycles = new JSlider(1, 30, 5);
            JSlider adjustSpeed = new JSlider(1, 50, 1);
            bpanel.add(sines);
            adjustCycles.addChangeListener(e -> sines.setCycles(((JSlider) e.getSource()).getValue()));
            adjustSpeed.addChangeListener(e -> sines.setSpeed(((JSlider) e.getSource()).getValue()));
            JPanel panel = new JPanel(new GridLayout(2, 1));
            panel.add(adjustCycles);
            panel.add(adjustSpeed);
            bpanel.add(BorderLayout.SOUTH, panel);
            add(bpanel);
        }
    }

    public static void main(String[] args) {
        run(new Ex26SineWave(3), 700, 400);
    }
}
