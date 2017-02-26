package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.concurrent.TimeUnit;

public class Ex23 extends JFrame {
    private JSlider speedSlider = new JSlider(0, 90, 45);
    private JSlider boxSizeSlider = new JSlider(1, 3, 1);

    public Ex23() throws HeadlessException {
        setLayout(new GridLayout(2, 1));
        Ex23RotatingSquare square = new Ex23RotatingSquare();
        speedSlider.addChangeListener(e -> square.rotationSpeed = ((JSlider) e.getSource()).getValue());
        boxSizeSlider.addChangeListener(e -> square.boxSize = ((JSlider) e.getSource()).getValue());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(speedSlider);
        panel.add(boxSizeSlider);
        add(panel);
        add(square);
        new Thread(square).start();
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex23(), 800, 800);
    }
}

class Ex23RotatingSquare extends JPanel implements Runnable {
    public volatile int rotationSpeed = 1;
    public volatile int boxSize = 1;
    private int currentPosition = 0;

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D ourGraphics = (Graphics2D) g;
        AffineTransform transform = new AffineTransform();
        int size = getWidth() > getHeight() ? getHeight() / 10 : getWidth() / 10;
        transform.translate(getWidth() / 2 - size / 2, getHeight() / 2 - size / 2);
        transform.scale(boxSize, boxSize);
        currentPosition += rotationSpeed;
        transform.rotate(currentPosition * Math.PI / 180.0);
        ourGraphics.setTransform(transform);
        ourGraphics.drawRect(0, 0, size, size);
    }

    @Override
    public void run() {
        try {
            while (true) {
                repaint();
                TimeUnit.MILLISECONDS.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
