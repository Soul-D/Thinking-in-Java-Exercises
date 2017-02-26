package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

public class Ex18MessageBoxes extends JFrame {
    private JButton[] b = {
            new JButton("Alert"), new JButton("Yes/No"),
            new JButton("Color"), new JButton("Input"),
            new JButton("3 Vals")
    };
    private JTextField txt = new JTextField(15);
    private ActionListener[] a = {
            e -> JOptionPane.showMessageDialog(null, "There's a bug on you!", "Hey!", JOptionPane.ERROR_MESSAGE),
            e -> JOptionPane.showConfirmDialog(null, "or no", "choose yes", JOptionPane.YES_NO_OPTION),
            e -> {
                Object[] options = {"Red", "Green"};
                JOptionPane.showOptionDialog(null, "Choose a Color!", "Warning", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            },
            e -> {
                String val = JOptionPane.showInputDialog("How many fingers do you see?");
                txt.setText(val);
            },
            e -> {
                Object[] selections = {"First", "Second", "Third"};
                Object val = JOptionPane.showInputDialog(null, "Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null, selections, selections[0]);
                if (val != null)
                    txt.setText(val.toString());
            }
    };

    public Ex18MessageBoxes() {
        setLayout(new FlowLayout());
        for (int i = 0; i < b.length; i++) {
            b[i].addActionListener(a[i]);
            add(b[i]);
        }
        add(txt);
    }

    public static void main(String[] args) {
        run(new Ex18MessageBoxes(), 200, 200);
    }
}
