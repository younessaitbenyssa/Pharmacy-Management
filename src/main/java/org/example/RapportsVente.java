package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import BD.DBConnection;

public class RapportsVente {
    private JPanel rapportsPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> filtreComboBox;
    private JComboBox<String> categorieComboBox;
    private JComboBox<String> periodeComboBox;
    private JButton filtrerButton;

    public RapportsVente() {
        rapportsPanel = new JPanel();
        rapportsPanel.setBounds(250, 0, 950, 630);
        rapportsPanel.setLayout(null);


        JLabel titleLabel = new JLabel("Rapports de Vente");
        titleLabel.setBounds(350, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rapportsPanel.add(titleLabel);


        String[] filtres = {"Par Catégorie", "Par Jour", "Par Période"};
        filtreComboBox = new JComboBox<>(filtres);
        filtreComboBox.setBounds(50, 70, 150, 30);
        filtreComboBox.addActionListener(e -> mettreAJourInterface());
        rapportsPanel.add(filtreComboBox);


        categorieComboBox = new JComboBox<>();
        categorieComboBox.setBounds(220, 70, 150, 30);
        categorieComboBox.setVisible(false);
        rapportsPanel.add(categorieComboBox);


        periodeComboBox = new JComboBox<>();
        periodeComboBox.setBounds(220, 70, 150, 30);
        periodeComboBox.setVisible(false);
        rapportsPanel.add(periodeComboBox);


        filtrerButton = new JButton("Actualise");
        filtrerButton.setBounds(390, 70, 100, 30);
        filtrerButton.addActionListener(e -> chargerRapports());
        rapportsPanel.add(filtrerButton);


        String[] columnNames = {"Critère", "Total des Ventes (DH)"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(50, 120, 850, 400);
        rapportsPanel.add(tableScrollPane);


        chargerCategories();
        chargerPeriodes();
    }

    public JPanel getRapportsPanel() {
        return rapportsPanel;
    }

    private void mettreAJourInterface() {
        String filtre = (String) filtreComboBox.getSelectedItem();


        if ("Par Catégorie".equals(filtre)) {
            categorieComboBox.setVisible(true);
            periodeComboBox.setVisible(false);
        } else if ("Par Période".equals(filtre)) {
            categorieComboBox.setVisible(false);
            periodeComboBox.setVisible(true);
        } else {
            categorieComboBox.setVisible(false);
            periodeComboBox.setVisible(false);
        }
    }

    private void chargerCategories() {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT DISTINCT categorie FROM medicament")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String categorie = rs.getString("categorie");
                    categorieComboBox.addItem(categorie);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données !");
            ex.printStackTrace();
        }
    }

    private void chargerPeriodes() {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT DISTINCT DATE(date_vente) AS jour FROM ventes WHERE user_id = ?")) {
                ps.setInt(1, Login.getid());
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String jour = rs.getString("jour");
                    periodeComboBox.addItem(jour);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données !");
            ex.printStackTrace();
        }
    }

    private void chargerRapports() {
        String filtre = (String) filtreComboBox.getSelectedItem();
        int userId = Login.getid();

        try (Connection conn = DBConnection.getConnection()) {
            tableModel.setRowCount(0);

            switch (filtre) {
                case "Par Catégorie":
                    String categorieSelectionnee = (String) categorieComboBox.getSelectedItem();
                    if (categorieSelectionnee != null) {
                        try (PreparedStatement ps = conn.prepareStatement(
                                "SELECT DATE(date_vente) AS jour, SUM(total) AS total_ventes " +
                                        "FROM ventes " +
                                        "JOIN medicament ON ventes.medicament_id = medicament.id " +
                                        "WHERE ventes.user_id = ? AND medicament.categorie = ? " +
                                        "GROUP BY DATE(date_vente)")) {
                            ps.setInt(1, userId);
                            ps.setString(2, categorieSelectionnee);
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                String jour = rs.getString("jour");
                                double totalVentes = rs.getDouble("total_ventes");
                                tableModel.addRow(new Object[]{jour, totalVentes});
                            }
                        }
                    }
                    break;

                case "Par Jour":
                    try (PreparedStatement ps = conn.prepareStatement(
                            "SELECT DATE(date_vente) AS jour, SUM(total) AS total_ventes " +
                                    "FROM ventes " +
                                    "WHERE ventes.user_id = ? " +
                                    "GROUP BY DATE(date_vente)")) {
                        ps.setInt(1, userId);
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            String jour = rs.getString("jour");
                            double totalVentes = rs.getDouble("total_ventes");
                            tableModel.addRow(new Object[]{jour, totalVentes});
                        }
                    }
                    break;

                case "Par Période":
                    String periodeSelectionnee = (String) periodeComboBox.getSelectedItem();
                    if (periodeSelectionnee != null) {
                        try (PreparedStatement ps = conn.prepareStatement(
                                "SELECT DATE(date_vente) AS jour, SUM(total) AS total_ventes " +
                                        "FROM ventes " +
                                        "WHERE ventes.user_id = ? AND DATE(date_vente) = ? " +
                                        "GROUP BY DATE(date_vente)")) {
                            ps.setInt(1, userId);
                            ps.setString(2, periodeSelectionnee);
                            ResultSet rs = ps.executeQuery();
                            while (rs.next()) {
                                String jour = rs.getString("jour");
                                double totalVentes = rs.getDouble("total_ventes");
                                tableModel.addRow(new Object[]{jour, totalVentes});
                            }
                        }
                    }
                    break;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données !");
            ex.printStackTrace();
        }
    }
}