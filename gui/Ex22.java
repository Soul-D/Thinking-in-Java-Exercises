package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Ex22 extends JFrame {
    private JSlider redSlider = new JSlider(0, 255, 0);
    private JSlider greenSlider = new JSlider(0, 255, 0);
    private JSlider blueSlider = new JSlider(0, 255, 0);
    private JTextField redTextField = new JTextField(10);
    private JTextField greenTextField = new JTextField(10);
    private JTextField blueTextField = new JTextField(10);
    private JPanel panel = new JPanel();

    public Ex22() throws HeadlessException {
        setLayout(new GridLayout(3,3));
        ChangeListener changeListener = e -> {
            setText();
            setPanelColor();
        };
        redSlider.addChangeListener(changeListener);
        greenSlider.addChangeListener(changeListener);
        blueSlider.addChangeListener(changeListener);
        redTextField.setEditable(false);
        greenTextField.setEditable(false);
        blueTextField.setEditable(false);
        add(redSlider);
        add(greenSlider);
        add(blueSlider);
        add(redTextField);
        add(greenTextField);
        add(blueTextField);
        add(panel);
        setText();
        setPanelColor();
    }

    private void setPanelColor() {
        panel.setBackground(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
    }

    private void setText() {
        redTextField.setText(String.valueOf(redSlider.getValue()));
        greenTextField.setText(String.valueOf(greenSlider.getValue()));
        blueTextField.setText(String.valueOf(blueSlider.getValue()));
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex22(), 400, 400);
    }
}
