package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class Ex3SubmitSwingProgram extends JFrame {
    JLabel label;

    public Ex3SubmitSwingProgram() {
        label = new JLabel("A Label");
        add(label);
    }

    static Ex3SubmitSwingProgram ssp;

    public static void main(String[] args) throws Exception {
        SwingConsole.run(ssp = new Ex3SubmitSwingProgram(),300,100);
        TimeUnit.SECONDS.sleep(1);
        SwingUtilities.invokeLater(() -> ssp.label.setText("Hey! This is Different!"));
    }
}