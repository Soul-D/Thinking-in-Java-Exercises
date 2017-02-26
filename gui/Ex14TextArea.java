package gui;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

public class Ex14TextArea extends JFrame {
    private JButton b = new JButton("Add Text");
    private JTextArea tp = new JTextArea();
    private static Generator sg = new RandomGenerator.String(7);

    public Ex14TextArea() {
        b.addActionListener(e -> {
            for (int i = 1; i < 10; i++)
                tp.setText(tp.getText() + sg.next() + "\n");
        });
        add(new JScrollPane(tp));
        add(BorderLayout.SOUTH, b);
    }

    public static void main(String[] args) {
        run(new Ex14TextArea(), 475, 425);
    }
}
