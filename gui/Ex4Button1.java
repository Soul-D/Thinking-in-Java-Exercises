package gui;

import javax.swing.*;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

public class Ex4Button1 extends JFrame {
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2");

    public Ex4Button1() {
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        run(new Ex4Button1(), 200, 100);
    }
}
