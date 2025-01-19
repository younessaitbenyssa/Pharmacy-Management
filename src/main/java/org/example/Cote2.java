package org.example;

import javax.swing.*;
import java.awt.*;

public class Cote2 {
    JPanel cot2;
    JLabel partie1, partie2,iconLabel;

    public Cote2() {

        cot2 = new JPanel();
        cot2.setBounds(0, 0, 600, 630);
        cot2.setLayout(null);
        cot2.setBackground(new Color(0,128,128));
        partie1 = new JLabel("BIENVENUE");
        partie1.setBounds(130, 10, 600, 200);
        partie1.setForeground(Color.WHITE);
        partie1.setFont(new Font("Serif", Font.BOLD, 45));

        partie2 = new JLabel("A NOTRE APPLICATION");
        partie2.setBounds(20, 100, 600, 200);
        partie2.setForeground(Color.WHITE);
        partie2.setFont(new Font("Serif", Font.BOLD, 45));

        ImageIcon icon = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\pharmacyimagewhite.png");
        Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);
        iconLabel = new JLabel(icon);
        iconLabel.setBounds(180, 250, 200, 200);

        cot2.add(partie1);
        cot2.add(partie2);
        cot2.add(iconLabel);
    }

    public JPanel getPanel() {
        return cot2;
    }

}
