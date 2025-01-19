package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VenteMedicament {
    private JPanel ventePanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField quantiteField, totalField;
    private JButton ajouterButton, vendreButton;
    private JTextArea achatArea;

    public VenteMedicament() {
        ventePanel = new JPanel();
        ventePanel.setBounds(250, 0, 950, 630);
        ventePanel.setLayout(null);

        JLabel titleLabel = new JLabel("Vente de Médicaments");
        titleLabel.setBounds(350, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        ventePanel.add(titleLabel);

        String[] columnNames = {"ID", "Nom", "Prix (DH)", "Stock"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(0xD3D3D3));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
        ventePanel.add(ajouterButton);

        vendreButton = new JButton("Vendre");
        vendreButton.setBounds(650, 450, 100, 30);
        vendreButton.setBackground(new Color(0xDC143C));
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

    }

    public JPanel getPanel() {
        return ventePanel;
    }
}
