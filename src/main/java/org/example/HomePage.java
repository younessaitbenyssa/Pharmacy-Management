package org.example;

import javax.swing.*;
import java.awt.*;

public class HomePage {

    private JPanel home;
    JLabel label1, label2, label3, label1_text1, label1_text2, label2_text1, label2_text2, label3_text1, label3_text2;
    private RoundedPanel panel1, panel2, panel3;
    private ImageIcon icon1, icon2, icon3;

    HomePage(JFrame frame) {
        home = new JPanel();
        home.setLayout(null);
        home.setBounds(250, 0, 950, 630);


        panel1 = new RoundedPanel(30, 30);
        panel1.setBounds(20, 40, 280, 170);
        panel1.setBackground(new Color(218, 165, 32));
        panel1.setLayout(null);

        label1_text1 = new JLabel("Balance");
        label1_text1.setForeground(Color.WHITE);
        label1_text1.setBounds(100, 130, 100, 30);
        label1_text1.setFont(new Font("Arial", Font.BOLD, 24));

        label1_text2 = new JLabel("8.000 MAD");
        label1_text2.setForeground(Color.WHITE);
        label1_text2.setBounds(150, 15, 140, 30);
        label1_text2.setFont(new Font("Arial", Font.BOLD, 24));

        label1 = new JLabel();
        label1.setBounds(10, 35, 100, 100);


        panel2 = new RoundedPanel(30, 30);
        panel2.setBounds(320, 40, 280, 170);
        panel2.setBackground(new Color(147, 112, 219));
        panel2.setLayout(null);

        label2_text1 = new JLabel("Sales");
        label2_text1.setForeground(Color.WHITE);
        label2_text1.setBounds(100, 130, 100, 30);
        label2_text1.setFont(new Font("Arial", Font.BOLD, 24));

        label2_text2 = new JLabel("10.000 MAD");
        label2_text2.setForeground(Color.WHITE);
        label2_text2.setBounds(140, 15, 140, 30);
        label2_text2.setFont(new Font("Arial", Font.BOLD, 24));

        label2 = new JLabel();
        label2.setBounds(10, 35, 100, 100);


        panel3 = new RoundedPanel(30, 30);
        panel3.setBounds(620, 40, 280, 170);
        panel3.setBackground(new Color(112, 128, 144));
        panel3.setLayout(null);

        label3_text1 = new JLabel("Costs");
        label3_text1.setForeground(Color.WHITE);
        label3_text1.setBounds(100, 130, 100, 30);
        label3_text1.setFont(new Font("Arial", Font.BOLD, 24));

        label3_text2 = new JLabel("2.000 MAD");
        label3_text2.setForeground(Color.WHITE);
        label3_text2.setBounds(150, 15, 140, 30);
        label3_text2.setFont(new Font("Arial", Font.BOLD, 24));

        label3 = new JLabel();
        label3.setBounds(10, 35, 100, 100);


        icon1 = new ImageIcon("src\\main\\java\\assets\\images\\balance.png");
        Image scaledImage1 = icon1.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        label1.setIcon(new ImageIcon(scaledImage1));

        icon2 = new ImageIcon("src\\main\\java\\assets\\images\\income-icon.png");
        Image scaledImage2 = icon2.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        label2.setIcon(new ImageIcon(scaledImage2));

        icon3 = new ImageIcon("src\\main\\java\\assets\\images\\money-bag.png");
        Image scaledImage3 = icon3.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        label3.setIcon(new ImageIcon(scaledImage3));


        PieChart charpan = new PieChart("");
        charpan.setBounds(10, 250, 350, 350);
        charpan.setVisible(true);

        ChartC bar = new ChartC(); 
        bar.setBounds(360, 280, 600, 350);
        bar.setVisible(true);


        panel1.add(label1);
        panel2.add(label2);
        panel3.add(label3);
        panel1.add(label1_text1);
        panel1.add(label1_text2);
        panel2.add(label2_text1);
        panel2.add(label2_text2);
        panel3.add(label3_text1);
        panel3.add(label3_text2);


        home.add(panel1);
        home.add(panel2);
        home.add(panel3);
        home.add(charpan);
        home.add(bar);


        home.setVisible(true);
    }

    public JPanel getHomePanel() {
        return home;
    }
}
