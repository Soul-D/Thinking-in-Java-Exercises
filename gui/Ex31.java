package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ex31 extends JFrame {
    private static Random random = new Random();

    public Ex31() throws HeadlessException {
        JProgressBar progressBar = new JProgressBar(0, 100);
        add(progressBar);
        new Timer(500, e -> progressBar.setValue(progressBar.getValue() + (int) ((100 - progressBar.getValue()) * 0.2) + random.nextInt(2))).start();
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex31(), 400, 400);
    }
}
