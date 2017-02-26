package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex6 extends JFrame {
    private JTextArea
            input = new JTextArea(20, 40),
            output = new JTextArea(20, 40);
    private JTextField regular = new JTextField(20);
    private JButton doIt = new JButton("DO IT!");

    public Ex6() throws HeadlessException {
        doIt.addActionListener(e -> {
            output.setText("");
            Pattern p = Pattern.compile(regular.getText());
            Matcher m = p.matcher(input.getText());
            while (m.find()) {
                output.append("Match \"" + m.group() + "\" at positions " + m.start() + "-" + (m.end() - 1) + "\n");
            }
        });
        setLayout(new FlowLayout());
        add(new JScrollPane(input));
        add(regular);
        add(new JScrollPane(output));
        add(doIt);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex6(), 1300, 500);
    }
}
