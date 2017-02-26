package gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

public class Ex16List extends JFrame {
    private String[] flavors = {
            "Chocolate", "Strawberry", "Vanilla Fudge Swirl",
            "Mint Chip", "Mocha Almond Fudge", "Rum Raisin",
            "Praline Cream", "Mud Pie"
    };
    private JList lst = new JList(flavors);
    private JTextArea t = new JTextArea(flavors.length, 20);
    private ListSelectionListener ll = e -> {
        if (e.getValueIsAdjusting()) return;
        t.setText("");
        for (Object item : lst.getSelectedValues())
            t.append(item + "\n");
    };

    public Ex16List() {
        t.setEditable(false);
        setLayout(new FlowLayout());
        Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.BLACK);
        lst.setBorder(brd);
        t.setBorder(brd);
        add(t);
        add(lst);
        lst.addListSelectionListener(ll);
    }

    public static void main(String[] args) {
        run(new Ex16List(), 250, 375);
    }
}
