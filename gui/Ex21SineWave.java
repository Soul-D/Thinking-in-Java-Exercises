package gui;

import javax.swing.*;
import java.awt.*;

import static net.mindview.util.SwingConsole.*;

class Ex21SineDraw extends JPanel {
    private static final int SCALEFACTOR = 200;
    private int cycles;
    private int points;
    private double[] sines;
    private int[] pts;

    public Ex21SineDraw() {
        setCycles(5);
    }

    public int getCycles() {
        return cycles;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();
        double hstep = (double) maxWidth / (double) points;
        int maxHeight = getHeight();
        pts = new int[points];
        for (int i = 0; i < points; i++)
            pts[i] = (int) (sines[i] * maxHeight / 2 * .95 + maxHeight / 2);
        g.setColor(Color.RED);
        for (int i = 1; i < points; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public void setCycles(int newCycles) {
        cycles = newCycles;
        points = SCALEFACTOR * cycles * 2;
        sines = new double[points];
        for (int i = 0; i < points; i++) {
            double radians = (Math.PI / SCALEFACTOR) * i;
            sines[i] = Math.sin(radians);
        }
        repaint();
    }
}

public class Ex21SineWave extends JFrame {
    private Ex21SineDraw sines = new Ex21SineDraw();
    private JSlider adjustCycles = new JSlider(1, 30, 5);

    public Ex21SineWave() {
        add(sines);
        adjustCycles.addChangeListener(e -> sines.setCycles(((JSlider) e.getSource()).getValue()));
        add(BorderLayout.SOUTH, adjustCycles);
    }

    public static void main(String[] args) {
        run(new SineWave(), 700, 400);
    }
}
