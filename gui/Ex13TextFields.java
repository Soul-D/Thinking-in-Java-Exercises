package gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

public class Ex13TextFields extends JFrame {
    private JButton
            b1 = new JButton("Get Text"),
            b2 = new JButton("Set Text");
    private JTextField
            t1 = new JTextField(30),
            t2 = new JTextField(30),
            t3 = new JTextField(30);
    private String s = "";
    private Ex13UpperCaseDocument ucd = new Ex13UpperCaseDocument();

    public Ex13TextFields() {
        t1.setDocument(ucd);
        ucd.addDocumentListener(new T1());
        b1.addActionListener(new B1());
        b2.addActionListener(new B2());
        t1.addActionListener(new T1A());
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(t1);
        add(t2);
        add(t3);
    }

    class T1 implements DocumentListener {
        public void changedUpdate(DocumentEvent e) {
        }

        public void insertUpdate(DocumentEvent e) {
            t2.setText(t1.getText());
            t3.setText("Text: " + t1.getText());
        }

        public void removeUpdate(DocumentEvent e) {
            t2.setText(t1.getText());
        }
    }

    class T1A implements ActionListener {
        private int count = 0;

        public void actionPerformed(ActionEvent e) {
            t3.setText("t1 Action Event " + count++);
        }
    }

    class B1 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (t1.getSelectedText() == null)
                s = t1.getText();
            else
                s = t1.getSelectedText();
            t1.setEditable(true);
        }
    }

    class B2 implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            t1.setText("Inserted by Button 2: " + s);
            t1.setEditable(false);
        }
    }

    public static void main(String[] args) {
        run(new Ex13TextFields(), 375, 200);
    }
}

class Ex13UpperCaseDocument extends PlainDocument {

    public void insertString(int offset, String str, AttributeSet attSet) throws BadLocationException {
        super.insertString(offset, str, attSet);
    }
}
