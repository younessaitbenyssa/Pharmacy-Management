package org.example;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Main {


    public static void main(String[] args) {


        JFrame frame = new JFrame();
        frame.setSize(1200, 630);
        frame.setUndecorated(true);
        frame.setShape(new RoundRectangle2D.Double(0, 0, 1200, 630, 20, 20));
        frame.setLayout(null);

        Controller controller =  new Controller(frame);
        controller.showLogin();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // centrer la fenetre
        frame.setVisible(true);
    }

}
