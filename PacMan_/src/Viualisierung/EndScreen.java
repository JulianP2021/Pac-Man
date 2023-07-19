package Viualisierung;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Logik.Master;


public class EndScreen extends JFrame implements ActionListener{

    JButton start;
    JButton exit;
    JLabel back;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JLabel label4;
    JLabel label5;
    JTextField text;
    int counter = 0;

    int lebenzahl;


    Font font = new Font("Arial", Font.PLAIN, 30);

    JSlider slider_1 = new JSlider();

    Border border = BorderFactory.createLineBorder(Color.white, 3);

    boolean verlassen = false;
    public static boolean running = true;

    public EndScreen(int i) {
    	counter = i;

        running = true;

        setSize(java.awt.Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setAlwaysOnTop(true);

        setTitle("Pac Man - Menu");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);

        start = new JButton();
        exit = new JButton();
        back = new JLabel();
        label1 = new JLabel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        text = new JTextField();

        add(back);
        back.setBounds(0, 0, getWidth(), getHeight());
        back.setBackground(Color.black);
        back.setOpaque(true);
        back.setBorder(border);

        back.add(label2);
        back.add(label3);
        back.add(label4);
        back.add(label5);


        label3.setBounds(getWidth() / 2 - 250, getHeight() / 2 -170, 500, 50);
        label3.setText("Score");
        label3.setForeground(Color.white);
        label3.setFont(font);
        label3.setBorder(border);

        label4.setBounds(getWidth() / 2 - 150, getHeight() / 2 - 170, 400, 50);
        label4.add(text);


        text.setForeground(Color.white);
        text.setBackground(Color.black);
        text.setBounds(0,0, 400,50);
        text.setFont(font);
        text.setBorder(border);
        text.setText("" + counter);
        text.setHorizontalAlignment(label4.CENTER);

        label2.add(start);
        label2.setBounds(getWidth() / 2 - 250, getHeight() / 2, 500, 100);

        start.addActionListener(this);
        start.setSize(500, 100);
        start.setText("START");
        start.setFocusable(false);
        start.setVisible(true);
        start.setFont(font);
        start.setBackground(Color.black);
        start.setForeground(Color.white);

        label5.add(exit);
        label5.setBounds(getWidth() / 2 - 250, getHeight() / 2 + 200, 500, 100);

        exit.addActionListener(this);
        exit.setSize(500, 100);
        exit.setText("VERLASSEN");
        exit.setFocusable(false);
        exit.setVisible(true);
        exit.setFont(font);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == start) {
            dispose();
            new Master();
        }
        if (e.getSource() == exit) {
            verlassen = true;
            System.exit(0);
        }
    }

}