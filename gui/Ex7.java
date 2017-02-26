package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Ex7 extends JFrame {
    private JTextField textField = new JTextField(10);
    private JButton button = new JButton("Button");
    private JComboBox<String> comboBox = new JComboBox<>(new String[]{"Hi","Hello","Yo"});
    private JFileChooser fileChooser = new JFileChooser("./src/main/java/gui");
    private JMenuItem menuItem = new JCheckBoxMenuItem("Mmm?");
    private JToggleButton toggleButton = new JCheckBox("What?");

    private ActionListener listener = e -> textField.setText(e.getSource().getClass().getSimpleName());

    public Ex7() throws HeadlessException {
        textField.addActionListener(listener);
        button.addActionListener(listener);
        comboBox.addActionListener(listener);
        fileChooser.addActionListener(listener);
        menuItem.addActionListener(listener);
        toggleButton.addActionListener(listener);
        setLayout(new FlowLayout());
        add(textField);
        add(button);
        add(comboBox);
        add(fileChooser);
        add(menuItem);
        add(toggleButton);
        new Timer(3000,listener).start();
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex7(),500,500);
    }
}
