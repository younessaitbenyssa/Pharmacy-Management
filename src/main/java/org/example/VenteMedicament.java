package org.example;

import BD.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenteMedicament implements ActionListener {
    private JPanel ventePanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField quantiteField, totalField;
    private JButton ajouterButton, vendreButton;
    private JTextArea achatArea;
    private List<MedicamentVendu> medicamentsVendus;
    double totalVente = 0;

    public VenteMedicament() {
        ventePanel = new JPanel();
        ventePanel.setBounds(250, 0, 950, 630);
        ventePanel.setLayout(null);

        medicamentsVendus = new ArrayList<>();

        JLabel titleLabel = new JLabel("Vente de Médicaments");
        titleLabel.setBounds(350, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ventePanel.add(titleLabel);

        String[] columnNames = {"ID", "Nom", "Catégorie", "Prix (DH)", "Stock"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(0xD3D3D3));

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(50, 70, 450, 300);
        ventePanel.add(tableScrollPane);

        JLabel quantiteLabel = new JLabel("Quantité :");
        quantiteLabel.setBounds(50, 400, 100, 30);
        ventePanel.add(quantiteLabel);

        quantiteField = new JTextField();
        quantiteField.setBounds(150, 400, 100, 30);
        ventePanel.add(quantiteField);

        JLabel totalLabel = new JLabel("Total (DH) :");
        totalLabel.setBounds(570, 400, 100, 30);
        ventePanel.add(totalLabel);

        totalField = new JTextField();
        totalField.setBounds(650, 400, 100, 30);
        totalField.setEditable(false);
        ventePanel.add(totalField);

        ajouterButton = new JButton("Ajouter");
        ajouterButton.setBounds(300, 400, 100, 30);
        ajouterButton.setBackground(new Color(0x1E90FF));
        ajouterButton.addActionListener(this);
        ventePanel.add(ajouterButton);

        vendreButton = new JButton("Vendre");
        vendreButton.setBounds(650, 450, 100, 30);
        vendreButton.setBackground(new Color(0xDC143C));
        vendreButton.addActionListener(this);
        ventePanel.add(vendreButton);

        achatArea = new JTextArea();
        achatArea.setEditable(false);
        achatArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane achatScrollPane = new JScrollPane(achatArea);
        achatScrollPane.setBounds(530, 70, 350, 300);
        ventePanel.add(achatScrollPane);

        JLabel achatLabel = new JLabel("Fichier d'Achat :");
        achatLabel.setBounds(530, 40, 200, 30);
        ventePanel.add(achatLabel);

        loadData();

    }

    public JPanel getPanel() {
        return ventePanel;
    }

    private void loadData() {
        int iduser = Login.getid();
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, nom, categorie, prix, stock FROM medicament WHERE user_id = ?")) {
                ps.setInt(1, iduser);

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        String id = rs.getString("id");
                        String name = rs.getString("nom");
                        String category = rs.getString("categorie");
                        String price = rs.getString("prix");
                        String stock = rs.getString("stock");

                        tableModel.addRow(new Object[]{id, name, category, price, stock});
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erreur lors du chargement des données !");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données !");
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
        if (source.equals(ajouterButton)){
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String idProduit = table.getValueAt(selectedRow, 0).toString();
                String nomProduit = table.getValueAt(selectedRow, 1).toString();
                double prixProduit = Double.parseDouble(table.getValueAt(selectedRow, 3).toString());
                int stockProduit = Integer.parseInt(table.getValueAt(selectedRow, 4).toString());

                String quantiteStr = quantiteField.getText().trim();
                int quantiteVente = Integer.parseInt(quantiteStr);
                if (!quantiteStr.isEmpty()) {
                    if (quantiteVente <= stockProduit) {
                        medicamentsVendus.add(new MedicamentVendu(
                                Integer.parseInt(idProduit),
                                nomProduit,
                                quantiteVente,
                                prixProduit
                        ));
                        achatArea.append(nomProduit + " - " + quantiteVente + " x " + prixProduit + " DH\n");

                        
                        double totalProduit = quantiteVente * prixProduit;
                        totalVente += totalProduit;
                        
                        totalField.setText(String.format("%.2f", totalVente));

                        
                        tableModel.setValueAt(stockProduit - quantiteVente, selectedRow, 4);
                        quantiteField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Quantité demandée dépasse le stock disponible !");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer une quantité !");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un produit dans la table !");
            }

        } else if (source.equals(vendreButton)) {
            if (totalVente > 0) {
                try (Connection conn = DBConnection.getConnection()) {
                    for (MedicamentVendu medicament : medicamentsVendus) {
                        try (PreparedStatement psInsertVente = conn.prepareStatement(
                                "INSERT INTO ventes (user_id, medicament_id, quantite_vendue, prix_unitaire, total) VALUES (?, ?, ?, ?, ?)")) {
                            int userId = Login.getid(); // ID de l'utilisateur connecté
                            psInsertVente.setInt(1, userId);
                            psInsertVente.setInt(2, medicament.getMedicamentId());
                            psInsertVente.setInt(3, medicament.getQuantite());
                            psInsertVente.setDouble(4, medicament.getPrixUnitaire());
                            psInsertVente.setDouble(5, medicament.getTotal());
                            psInsertVente.executeUpdate();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement de la vente !");
                            ex.printStackTrace();
                        }

                        try (PreparedStatement psUpdateStock = conn.prepareStatement(
                                "UPDATE medicament SET stock = stock - ? WHERE id = ?")) {
                            psUpdateStock.setInt(1, medicament.getQuantite());
                            psUpdateStock.setInt(2, medicament.getMedicamentId());
                            psUpdateStock.executeUpdate();
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour du stock !");
                            ex.printStackTrace();
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Vente effectuée avec succès ! Total : " + totalVente + " DH");

                    quantiteField.setText("");
                    totalField.setText("");
                    achatArea.setText("");
                    totalVente = 0;
                    medicamentsVendus.clear();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données !");
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Aucun produit n'a été ajouté à la vente !");
            }
        }
    }
}
