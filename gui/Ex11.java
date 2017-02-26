package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Ex11 extends JFrame {
    private MyButton button = new MyButton("MyButton");
    private static final Random rand = new Random();

    class MyButton extends JButton {
        public MyButton(String text) {
            super(text);
            addActionListener(e -> {
                setBackground(new Color(rand.nextInt(0xFFFFFF)));
            });
        }
    }

    public Ex11() throws HeadlessException {
        add(button);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex11(), 500, 500);
    }
}
