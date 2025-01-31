package org.example;

import BD.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Setting implements ActionListener {
    private JPanel settingPanel;
    private JTextField nameField, emailField, phoneField,oldPasswordField, newPasswordField;
    private JButton saveButton;
    int id;
    String name,email,phone,passwordb;



    public Setting() {
        id = Login.getid();
        try (Connection conn = DBConnection.getConnection()) {

            PreparedStatement T = conn.prepareStatement("SELECT *  FROM users WHERE id = ? ");
            T.setInt(1, id);

            ResultSet resultSet = T.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
                email = resultSet.getString("email");
                phone = resultSet.getString("phone");
                passwordb = resultSet.getString("password");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'inscription !");
            e.printStackTrace();
        }


        settingPanel = new JPanel();
        settingPanel.setBounds(250, 0, 950, 630);
        settingPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Paramètres du compte");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(350, 20, 300, 30);
        settingPanel.add(titleLabel);


        JLabel nameLabel = new JLabel("Nom complet:");
        nameLabel.setBounds(250, 120, 120, 30);
        settingPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(400, 120, 250, 30);
        settingPanel.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(250, 170, 120, 30);
        settingPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(400, 170, 250, 30);
        settingPanel.add(emailField);

        JLabel phoneLabel = new JLabel("Téléphone:");
        phoneLabel.setBounds(250, 220, 120, 30);
        settingPanel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(400, 220, 250, 30);
        settingPanel.add(phoneField);

        JLabel oldPassLabel = new JLabel("Ancien mot de passe:");
        oldPassLabel.setBounds(250, 270, 150, 30);
        settingPanel.add(oldPassLabel);

        oldPasswordField = new JTextField();
        oldPasswordField.setBounds(400, 270, 250, 30);
        settingPanel.add(oldPasswordField);

        JLabel newPassLabel = new JLabel("Nouveau mot de passe:");
        newPassLabel.setBounds(250, 320, 150, 30);
        settingPanel.add(newPassLabel);

        newPasswordField = new JTextField();
        newPasswordField.setBounds(400, 320, 250, 30);
        settingPanel.add(newPasswordField);

        saveButton = new JButton("Enregistrer");
        saveButton.setBounds(400, 390, 150, 40);
        saveButton.setBackground(new Color(0, 153, 76));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(this);
        settingPanel.add(saveButton);


        nameField.setText(name);
        emailField.setText(email);
        phoneField.setText(phone);

    }

    public JPanel getPanel() {
        return settingPanel;
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            try (Connection conn = DBConnection.getConnection()) {

                PreparedStatement T = conn.prepareStatement("SELECT *  FROM users WHERE id = ? ");
                T.setInt(1, id);

                ResultSet resultSet = T.executeQuery();
                if (resultSet.next()) {
                    name = resultSet.getString("name");
                    email = resultSet.getString("email");
                    phone = resultSet.getString("phone");
                    passwordb = resultSet.getString("password");
                }

            } catch (SQLException g) {
                JOptionPane.showMessageDialog(null, "Erreur lors de connextion!");
                g.printStackTrace();
            }
            try (Connection conn = DBConnection.getConnection()) {
                String oldmotdepass =oldPasswordField.getText().trim();
                String newmotdepass = newPasswordField.getText().trim();

                if (oldmotdepass.isEmpty() && newmotdepass.isEmpty()) {
                    PreparedStatement t = conn.prepareStatement("UPDATE users SET name=?, email=?, phone=? WHERE id=?");
                    t.setString(1, nameField.getText().trim());
                    t.setString(2, emailField.getText().trim());
                    t.setString(3, phoneField.getText().trim());
                    t.setInt(4, id);

                    t.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Informations mises à jour !");
                } else {
                    if (!oldmotdepass.equals(passwordb)) {
                        JOptionPane.showMessageDialog(null, "Ancien mot de passe incorrect !");
                        return;
                    }

                    PreparedStatement t = conn.prepareStatement("UPDATE users SET name=?, email=?, phone=?, password=? WHERE id=?");
                    t.setString(1, nameField.getText().trim());
                    t.setString(2, emailField.getText().trim());
                    t.setString(3, phoneField.getText().trim());
                    t.setString(4, newmotdepass);
                    t.setInt(5, id);

                    t.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Informations et mot de passe mis à jour !");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
