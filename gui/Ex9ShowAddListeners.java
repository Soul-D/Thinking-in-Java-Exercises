package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static net.mindview.util.SwingConsole.run;

public class Ex9ShowAddListeners extends JFrame {
    private JTextField className = new JTextField(15);
    private JTextField methodName = new JTextField(15);
    private JTextArea results = new JTextArea(40, 65);
    private static Pattern p = Pattern.compile("\\w+\\.");

    class NameL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                results.setText("");
                Class<?> c = Class.forName(className.getText());
                Method[] methods = c.getMethods();
                Constructor[] ctors = c.getConstructors();
                if (methodName.getText().equals("")) {
                    for (Method method : methods)
                        results.append(p.matcher(method.toString()).replaceAll("") + "\n");
                    for (Constructor ctor : ctors)
                        results.append(p.matcher(ctor.toString()).replaceAll("") + "\n");
                } else {
                    for (Method method : methods)
                        if (method.toString().contains(methodName.getText())) {
                            results.append(p.matcher(method.toString()).replaceAll("") + "\n");
                        }
                    for (Constructor ctor : ctors)
                        if (ctor.toString().contains(methodName.getText())) {
                            results.append(p.matcher(ctor.toString()).replaceAll("") + "\n");
                        }
                }
            } catch (ClassNotFoundException ex) {
                results.append("No such class: " + ex);
            }
        }
    }

    public Ex9ShowAddListeners() {
        NameL nameListener = new NameL();
        className.addActionListener(nameListener);
        methodName.addActionListener(nameListener);
        JPanel top1 = new JPanel();
        top1.add(new JLabel("Swing class name (press Enter):"));
        top1.add(className);
        JPanel top2 = new JPanel();
        top2.add(new JLabel("Method signature (press Enter):"));
        top2.add(methodName);
        JPanel all = new JPanel(new GridLayout(2,1));
        all.add(top1);
        all.add(top2);
        add(BorderLayout.NORTH, all);
        add(new JScrollPane(results));
        className.setText("java.util.ArrayList");
        nameListener.actionPerformed(new ActionEvent("", 0, ""));
    }

    public static void main(String[] args) {
        run(new Ex9ShowAddListeners(), 500, 400);
    }
}

