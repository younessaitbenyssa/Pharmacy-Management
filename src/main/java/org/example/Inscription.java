package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BD.DBConnection;

public class Inscription implements FocusListener, ActionListener {
    private JPanel inscription;
    private JTextField tName, tEmail, tPhone, tPassword, tConfirmPassword;
    private JLabel titre;
    private Controller controller;

    public Inscription(Controller controller) {
        this.controller = controller;


        inscription = new JPanel();
        inscription.setBounds(0, 0, 600, 700);
        inscription.setLayout(null);


        tName = new JTextField();
        tName.setBounds(150, 80, 300, 50);
        tName.setText("      Nom complet");
        inscription.add(tName);
        tName.addFocusListener(this);


        tEmail = new JTextField();
        tEmail.setBounds(150, 150, 300, 50);
        tEmail.setText("      Email");
        inscription.add(tEmail);
        tEmail.addFocusListener(this);


        tPhone = new JTextField();
        tPhone.setBounds(150, 220, 300, 50);
        tPhone.setText("      Numéro de téléphone");
        inscription.add(tPhone);
        tPhone.addFocusListener(this);


        tPassword = new JTextField();
        tPassword.setBounds(150, 290, 300, 50);
        tPassword.setText("      Mot de passe");
        inscription.add(tPassword);
        tPassword.addFocusListener(this);


        tConfirmPassword = new JTextField();
        tConfirmPassword.setBounds(150, 360, 300, 50);
        tConfirmPassword.setText("      Confirmer le mot de passe");
        inscription.add(tConfirmPassword);
        tConfirmPassword.addFocusListener(this);


        titre = new JLabel("Inscription");
        titre.setBounds(230, 20, 200, 50);
        titre.setFont(new Font("Serif", Font.BOLD, 30));
        inscription.add(titre);


        JButton Bregister = new JButton("S'inscrire");
        Bregister.setBounds(150, 430, 300, 50);
        Bregister.setBackground(new Color(66, 183, 42));
        Bregister.setForeground(Color.WHITE);
        Bregister.addActionListener(e -> registerUser());
        inscription.add(Bregister);

        JButton Bback = new JButton("Retour au login");
        Bback.setBounds(200, 500, 200, 30);
        Bback.setContentAreaFilled(false);
        Bback.setBorderPainted(false);
        Bback.setForeground(Color.BLUE);
        inscription.add(Bback);
        Bback.addActionListener(this);
    }
    public JPanel getInscription() {
        return inscription;
    }

    @Override
    public void focusGained(FocusEvent e) {
        Object source = e.getSource();
        if (source.equals(tName)) {
            if (tName.getText().equals("      Nom complet")) {
                tName.setText("");
            }
        } else if (source.equals(tEmail)) {
            if (tEmail.getText().equals("      Email")) {
                tEmail.setText("");
            }
        } else if (source.equals(tPhone)) {
            if (tPhone.getText().equals("      Numéro de téléphone")) {
                tPhone.setText("");
            }
        } else if (source.equals(tPassword)) {
            if (tPassword.getText().equals("      Mot de passe")) {
                tPassword.setText("");
            }
        } else if (source.equals(tConfirmPassword)) {
            if (tConfirmPassword.getText().equals("      Confirmer le mot de passe")) {
                tConfirmPassword.setText("");
            }
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        Object source = e.getSource();
        if (source.equals(tName)) {
            if (tName.getText().isEmpty()) {
                tName.setText("      Nom complet");
            }
        } else if (source.equals(tEmail)) {
            if (tEmail.getText().isEmpty()) {
                tEmail.setText("      Email");
            }
        } else if (source.equals(tPhone)) {
            if (tPhone.getText().isEmpty()) {
                tPhone.setText("      Numéro de téléphone");
            }
        } else if (source.equals(tPassword)) {
            if (tPassword.getText().isEmpty()) {
                tPassword.setText("      Mot de passe");
            }
        } else if (source.equals(tConfirmPassword)) {
            if (tConfirmPassword.getText().isEmpty()) {
                tConfirmPassword.setText("      Confirmer le mot de passe");
            }
        }
    }


    public void actionPerformed(ActionEvent e) {
        controller.showLogin();
    }


    public void registerUser() {
        String name = tName.getText().trim();
        String email = tEmail.getText().trim();
        String phone = tPhone.getText().trim();
        String password = tPassword.getText().trim();
        String confirmPassword = tConfirmPassword.getText().trim();
        if (tName.getText().equals("      Nom complet") || tEmail.getText().equals("      Email") || tPhone.getText().equals("      Numéro de téléphone") || tPassword.getText().equals("      Mot de passe") || tConfirmPassword.getText().equals("      Confirmer le mot de passe")) {
            JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
            return;
        }
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas !");
            return;
        }
        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement T = conn.prepareStatement("SELECT id FROM users WHERE email = ? OR phone = ?");
            T.setString(1, email);
            T.setString(2, phone);
            ResultSet resultSet = T.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "L'email ou le numéro de téléphone est déjà utilisé !");
                return;
            }
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO users (name, email, phone, password) VALUES (?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, phone);
            stmt.setString(4, password);
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Inscription réussie !");
                tName.setText("      Nom complet");
                tEmail.setText("      Email");
                tPhone.setText("      Numéro de téléphone");
                tPassword.setText("      Mot de passe");
                tConfirmPassword.setText("      Confirmer le mot de passe");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription !");
            e.printStackTrace();
        }
    }
}
