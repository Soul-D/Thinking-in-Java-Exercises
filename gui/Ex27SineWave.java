package gui;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.SwingConsole.run;

class Ex27SineDraw extends JPanel {
    private static final int SCALEFACTOR = 200;
    private int cycles;
    private int points;
    private int tPoints;
    private double[] sines;
    private int[] pts;
    private int offset = 0;
    private int offsetSpeed = 1;

    public Ex27SineDraw() {
        setCycles(5);
        new Timer(50, e -> {
            offset -= offsetSpeed;
            tPoints = points;
            computeSin();
            repaint();
        }).start();
    }

    public void paintComponent(Graphics g) {
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

    private void computeSin() {
        sines = new double[tPoints];
        for (int i = 0; i < tPoints; i++) {
            double radians = (Math.PI / SCALEFACTOR) * (i + offset);
            sines[i] = Math.sin(radians);
        }
    }

    public void setCycles(int newCycles) {
        cycles = newCycles;
        points = SCALEFACTOR * cycles * 2;
    }

    public void setSpeed(int speed) {
        offsetSpeed = speed;
    }
}

public class Ex27SineWave extends JFrame {
    private Ex27SineDraw sines = new Ex27SineDraw();
    private JSlider adjustCycles = new JSlider(1, 30, 5);
    private JSlider adjustSpeed = new JSlider(1, 50, 1);

    public Ex27SineWave() {
        add(sines);
        adjustCycles.addChangeListener(e -> sines.setCycles(((JSlider) e.getSource()).getValue()));
        adjustSpeed.addChangeListener(e -> sines.setSpeed(((JSlider) e.getSource()).getValue()));
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(adjustCycles);
        panel.add(adjustSpeed);
        add(BorderLayout.SOUTH, panel);
    }

    public static void main(String[] args) {
        run(new Ex27SineWave(), 700, 400);
    }
}
