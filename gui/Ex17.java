package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

public class Ex17 extends JFrame {
    private JPasswordField passwordField = new JPasswordField(10);

    public Ex17() throws HeadlessException {
        setLayout(new FlowLayout());
        passwordField.addActionListener(e -> {
            JPasswordField tmp = (JPasswordField) e.getSource();
            if (new String(tmp.getPassword()).equals("13"))
                JOptionPane.showMessageDialog(null,"You are right!");
            else
                JOptionPane.showMessageDialog(null,"Try again!");
            tmp.setText("");
        });
        add(passwordField);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex17(),500,100);
    }
}
