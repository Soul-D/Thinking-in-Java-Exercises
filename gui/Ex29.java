package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

public class Ex29 extends JFrame {
    public Ex29() throws HeadlessException {
        JButton button = new JButton("Choose your color");
        button.addActionListener(e -> {
            Color color = JColorChooser.showDialog(Ex29.this, "Do it!", Color.RED);
            if (color != null)
                button.setBackground(color);
        });
        add(button);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex29(), 400, 400);
    }
}
