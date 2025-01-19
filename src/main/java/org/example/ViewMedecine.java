package org.example;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewMedecine{
    private JPanel ajouterExpensive;
    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewMedecine() {
        ajouterExpensive = new JPanel();
        ajouterExpensive.setBounds(300, 0, 650, 630);
        ajouterExpensive.setBackground(new Color(0x7A1B64));
        ajouterExpensive.setLayout(null);


        JLabel searchLabel = new JLabel("Rechercher :");
        searchLabel.setBounds(55, 20, 100, 30);
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ajouterExpensive.add(searchLabel);


        searchField = new JTextField();
        searchField.setBounds(160, 20, 350, 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        ajouterExpensive.add(searchField);


        JButton searchButton = new JButton("recherche");
        searchButton.setBounds(520, 20, 110, 30);
        searchButton.setBackground(new Color(0x007BFF));
        searchButton.setForeground(Color.WHITE);
        ajouterExpensive.add(searchButton);


        String[] columnNames = {"ID", "Nom", "Catégorie", "Prix (DH)", "Stock", "Autorisation"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(0xD3D3D3));

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(50, 70, 610, 570);
        ajouterExpensive.add(tableScrollPane);
    }

    public JPanel getPanel() {
        return ajouterExpensive;
    }
}

