package gui;

import net.mindview.util.SwingConsole;
import net.mindview.util.TextFile;

import javax.swing.*;
import java.awt.*;

public class Ex20 extends JFrame {
    private static JMenuBar bar = new JMenuBar();

    public Ex20() throws HeadlessException {
        java.util.List<String> words = new TextFile("./src/main/java/gui/Ex20.java", "\\W+");
        JMenu menu = new JMenu();
        JMenu subMenu = new JMenu();
        for (int i = 0; i < words.size(); i++) {
            if (i % 10 == 0) {
                menu = new JMenu(words.get(i));
                bar.add(menu);
            } else if ((i + 3) % 4 == 0) {
                subMenu = new JMenu(words.get(i));
                menu.add(subMenu);
            } else {
                subMenu.add(new JMenuItem(words.get(i)));
            }
        }
        setJMenuBar(bar);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex20(), 400, 400);
    }
}
