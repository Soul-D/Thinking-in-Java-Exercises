package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

public class Ex32Progress extends JFrame {
    private JProgressBar pb = new JProgressBar();
    private ProgressMonitor pm = new ProgressMonitor(this, "Monitoring Progress", "Test", 0, 100);
    private JSlider sb = new JSlider(JSlider.HORIZONTAL, 0, 100, 60);

    public Ex32Progress() {
        setLayout(new GridLayout(2, 1));
        add(pb);
        pm.setProgress(0);
        pm.setMillisToPopup(1000);
        sb.setValue(0);
        sb.setPaintTicks(true);
        sb.setMajorTickSpacing(20);
        sb.setMinorTickSpacing(5);
        sb.setBorder(new TitledBorder("Slide Me"));
        add(sb);
        sb.addChangeListener(e -> {
            pm.setProgress(sb.getValue());
            pb.setValue(sb.getValue());
        });
    }

    public static void main(String[] args) {
        run(new Ex32Progress(), 300, 200);
    }
}
