package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Ex2HelloLabel {
    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Hello Swing");
        frame.setLayout(new FlowLayout());
        java.util.List<JLabel> labels = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < (new Random().nextInt(5) + 1); i++) {
            JLabel label = new JLabel("A Label " + ++count);
            labels.add(label);
            frame.add(label);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(1);
        labels.stream().forEach((l)->l.setText("Hey! This is Different!"));
    }
}
