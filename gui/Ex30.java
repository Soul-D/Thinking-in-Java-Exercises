package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;

public class Ex30 extends JFrame {
    public Ex30() throws HeadlessException {
        setLayout(new GridLayout(2, 2));
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("<html><b><i>TAB", null);
        JMenuItem menuItem = new JMenuItem("<html><b><i>ITEM");
        menuItem.setToolTipText("<html><b><i>ToolTip");
        JRadioButton radioButton = new JRadioButton("<html><b><i>Button");
        JCheckBox checkBox = new JCheckBox("<html><b><i>CheckBox");
        add(tabbedPane);
        add(menuItem);
        add(radioButton);
        add(checkBox);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex30(), 400, 400);
    }
}
