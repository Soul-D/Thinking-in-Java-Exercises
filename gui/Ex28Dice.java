package gui;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.util.*;

public class Ex28Dice {
    private Random random = new Random();

    public int throwDice() {
        return random.nextInt(6) + 1;
    }
}

class Ex28MyPanel extends JPanel {
    private java.util.List<Integer> diceThrows = new ArrayList<>();

    public void addThrow(int sum) {
        diceThrows.add(sum);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        double wStep;
        if (diceThrows.size()>1)
            wStep = (double) maxWidth / ((double) diceThrows.size() - 1);
        else
            wStep = (double) maxWidth / 2;
        double hStep = (double) maxHeight / (double) 30;
        for (int i = 1; i < diceThrows.size(); i++) {
            g.drawLine((int) ((i - 1) * wStep), (int) (hStep * diceThrows.get(i - 1)), (int) (i * wStep), (int) (hStep * diceThrows.get(i)));
        }
    }
}

class Ex28MyFrame extends JFrame {
    Ex28Dice[] dices = new Ex28Dice[5];
    Ex28MyPanel drawingPanel = new Ex28MyPanel();

    public Ex28MyFrame() {
        for (int i = 0; i < dices.length; i++) {
            dices[i] = new Ex28Dice();
        }
        add(drawingPanel);
        new Timer(1000, e -> {
            int sum = 0;
            for (Ex28Dice dice : dices) {
                sum += dice.throwDice();
            }
            drawingPanel.addThrow(sum);
            drawingPanel.repaint();
        }).start();
    }

    public static void main(String[] args) {
        SwingConsole.run(new Ex28MyFrame(), 500, 500);
    }
}
