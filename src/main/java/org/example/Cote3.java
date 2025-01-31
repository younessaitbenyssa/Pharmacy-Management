package org.example;

import javax.swing.*;
import java.awt.*;

public class Cote3{
    JPanel cot3;
    JLabel partie1, partie2,iconLabel;

    public Cote3() {

        cot3 = new JPanel();
        cot3.setBounds(600, 0, 600, 630);
        cot3.setLayout(null);
        cot3.setBackground(new Color(0,128,128));

        partie1 = new JLabel("BIENVENUE");
        partie1.setBounds(130, 10, 600, 200);
        partie1.setForeground(Color.WHITE);
        partie1.setFont(new Font("Serif", Font.BOLD, 45));

        partie2 = new JLabel("A NOTRE APPLICATION");
        partie2.setBounds(20, 100, 600, 200);
        partie2.setForeground(Color.WHITE);
        partie2.setFont(new Font("Serif", Font.BOLD, 45));

        ImageIcon icon = new ImageIcon("src\\main\\java\\assets\\images\\pharmacyimagewhite.png");
        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(180, 250, 200, 200);

        cot3.add(partie1);
        cot3.add(partie2);
        cot3.add(iconLabel);
    }

    public JPanel getPanel() {
        return cot3;
    }
}