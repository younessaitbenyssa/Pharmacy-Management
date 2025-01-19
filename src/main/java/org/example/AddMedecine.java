package org.example;


import javax.swing.*;
import java.awt.*;

public class AddMedecine  {
    JPanel ajouterMedical, containerPanel;

    public AddMedecine() {
        containerPanel = new JPanel();
        containerPanel.setLayout(null);
        containerPanel.setBounds(250, 0, 950, 630);


        ajouterMedical = new JPanel();
        ajouterMedical.setBounds(0, 0, 350, 650);
        ajouterMedical.setBackground(new Color(0x71D97C));
        ajouterMedical.setLayout(null);

        JLabel imageLabel = new JLabel();
        imageLabel.setBounds(110, 20, 120, 90);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ajouterMedical.add(imageLabel);

        JButton importButton = new JButton("Importer Image");
        importButton.setBounds(110, 120, 120, 30);
        ajouterMedical.add(importButton);

        JLabel ID = new JLabel("ID du Médicament:");
        ID.setBounds(30, 170, 140, 30);
        ajouterMedical.add(ID);

        JTextField ID_text = new JTextField();
        ID_text.setBounds(160, 170, 160, 30);
        ajouterMedical.add(ID_text);

        JLabel name = new JLabel("Nom:");
        name.setBounds(30, 210, 140, 30);
        ajouterMedical.add(name);

        JTextField name_text = new JTextField();
        name_text.setBounds(160, 210, 160, 30);
        ajouterMedical.add(name_text);


        JLabel category = new JLabel("Catégorie:");
        category.setBounds(30, 250, 140, 30);
        ajouterMedical.add(category);

        JComboBox<String> category_text = new JComboBox<>(new String[] {
                "Choisir...", "Antibiotique", "Antalgique", "Antiseptique", "Vitamines", "Autres"
        });
        category_text.setBounds(160, 250, 160, 30);
        ajouterMedical.add(category_text);


        JLabel prix = new JLabel("Prix (DH):");
        prix.setBounds(30, 290, 140, 30);
        ajouterMedical.add(prix);

        JTextField prix_text = new JTextField();
        prix_text.setBounds(160, 290, 160, 30);
        ajouterMedical.add(prix_text);


        JLabel stock = new JLabel("Stock:");
        stock.setBounds(30, 330, 140, 30);
        ajouterMedical.add(stock);

        JTextField stock_text = new JTextField();
        stock_text.setBounds(160, 330, 160, 30);
        ajouterMedical.add(stock_text);


        JLabel autorisation = new JLabel("Autorisation:");
        autorisation.setBounds(30, 370, 140, 30);
        ajouterMedical.add(autorisation);

        JComboBox<String> autorisation_text = new JComboBox<>(new String[] {"Autorisé", "Nécessite un certificat médical"});
        autorisation_text.setBounds(160, 370, 160, 30);
        ajouterMedical.add(autorisation_text);


        JButton addButton = new JButton("Ajouter");
        addButton.setBounds(40, 440, 120, 35);
        addButton.setBackground(new Color(0x007BFF));
        addButton.setForeground(Color.WHITE);
        ajouterMedical.add(addButton);

        JButton clearButton = new JButton("Effacer");
        clearButton.setBounds(180, 440, 120, 35);
        clearButton.setBackground(new Color(0x28A745));
        clearButton.setForeground(Color.WHITE);
        ajouterMedical.add(clearButton);

        JButton deleteButton = new JButton("Supprimer");
        deleteButton.setBounds(40, 490, 120, 35);
        deleteButton.setBackground(new Color(0xD3151A));
        deleteButton.setForeground(Color.WHITE);
        ajouterMedical.add(deleteButton);

        JButton updateButton = new JButton("Mise à jour");
        updateButton.setBounds(180, 490, 120, 35);
        updateButton.setBackground(new Color(0x81599C));
        updateButton.setForeground(Color.WHITE);
        ajouterMedical.add(updateButton);

        ViewMedecine viewMedecine = new ViewMedecine();

        containerPanel.add(ajouterMedical);
        containerPanel.add(viewMedecine.getPanel());

        containerPanel.setVisible(true);
    }

    public JPanel getPanel() {
        return containerPanel;
    }
}
