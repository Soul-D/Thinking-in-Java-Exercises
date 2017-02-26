package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

public class Ex8 extends JFrame {
    private JTextField textField = new JTextField(100);

    public Ex8() throws HeadlessException {
        setLayout(new FlowLayout());
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textField.setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
        add(textField);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex8(),500,500);
    }
}
