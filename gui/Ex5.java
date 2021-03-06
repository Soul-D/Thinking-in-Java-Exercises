package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Ex5 extends JFrame {
    private JButton
            b1 = new JButton("B1"),
            b2 = new JButton("B2"),
            b3 = new JButton("B3");
    private JTextField jTextField = new JTextField(10);
    private ActionListener listener = e -> jTextField.setText(((JButton) e.getSource()).getText());

    public Ex5() throws HeadlessException {
        b1.addActionListener(listener);
        b2.addActionListener(listener);
        b3.addActionListener(listener);
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        add(jTextField);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex5(),500,500);
    }
}
