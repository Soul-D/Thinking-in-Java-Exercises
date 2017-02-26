package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Ex10 extends JFrame {
    JButton button = new JButton("I'm a button");
    JTextField textField = new JTextField(20);

    public Ex10() throws HeadlessException {
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                textField.setText(textField.getText() + e.getKeyChar());
            }
        });
        setLayout(new FlowLayout());
        add(button);
        add(textField);
        add(new JButton("Dummy"));
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex10(), 500, 500);
    }
}
