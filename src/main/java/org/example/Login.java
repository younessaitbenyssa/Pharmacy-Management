package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class Login implements FocusListener, ActionListener {
    JPanel login;
    JTextField tuser, tpassword;
    JLabel titre;
    private Controller controller;

    public Login(Controller controller, JFrame frame) {
        this.controller = controller;

        login = new JPanel();
        login.setBounds(600, 0, 600, 630);
        login.setLayout(null);

        tuser = new JTextField();
        tuser.setBounds(150, 140, 300, 50);
        tuser.setText("      email ou Telephone");
        login.add(tuser);
        tuser.addFocusListener(this);

        tpassword = new JTextField();
        tpassword.setBounds(150, 220, 300, 50);
        tpassword.setText("      mot de passe");
        login.add(tpassword);
        tpassword.addFocusListener(this);

        titre = new JLabel("Login");
        titre.setBounds(260, 20, 100, 100);
        titre.setFont(new Font("Serif", Font.BOLD, 30));
        login.add(titre);

        JButton Blogin = new JButton("Connexion");
        Blogin.setBounds(150, 290, 300, 50);
        Blogin.setBackground(new Color(24, 119, 242));
        Blogin.setForeground(Color.WHITE);
        Blogin.addActionListener(e -> showLoginScreen(frame));
        login.add(Blogin);

        JButton Bmt = new JButton("Mot de passe oublié ?");
        Bmt.setBounds(200, 370, 200, 30);
        Bmt.setContentAreaFilled(false);
        Bmt.setBorderPainted(false);
        Bmt.setForeground(Color.blue);
        login.add(Bmt);

        JButton Bcree = new JButton("Creer un compte");
        Bcree.setBounds(225, 430, 150, 30);
        Bcree.setBackground(new Color(66, 183, 42));
        Bcree.setForeground(Color.WHITE);
        login.add(Bcree);
        Bcree.addActionListener(this);
    }

    public JPanel getLogin() {
        return login;
    }

    public void focusGained(FocusEvent e) {
        Object f = e.getSource();
        if (f.equals(tuser)) {
            if (tuser.getText().equals("      email ou Telephone")) {
                tuser.setText("");
            }
        } else if (f.equals(tpassword)) {
            if (tpassword.getText().equals("      mot de passe")) {
                tpassword.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object f = e.getSource();
        if (f.equals(tuser)) {
            if (tuser.getText().isEmpty()) {
                tuser.setText("      email ou Telephone");
            }
        } else if (f.equals(tpassword)) {
            if (tpassword.getText().isEmpty()) {
                tpassword.setText("      mot de passe");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        controller.showInscription();
    }


    private static void showLoginScreen(JFrame frame){
        SideBar sideBar = new SideBar(frame);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(sideBar.getSideBarPanel());
        frame.getContentPane().add(sideBar.ContentPanel());
        frame.revalidate();
        frame.repaint();

    }
}