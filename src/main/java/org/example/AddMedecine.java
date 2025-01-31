package org.example;


import BD.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddMedecine implements ActionListener {
    JPanel ajouterMedical;
    JButton importButton,clearButton,addButton,updateButton,deleteButton;
    JLabel imageLabel;
    JTextField name_text,ID_text,prix_text,stock_text;
    JComboBox category_text;
    String chemin;

    private JTextField searchField;
    private JTable table;
    private DefaultTableModel tableModel;


    int id=Login.getid();

    public AddMedecine() {

        ajouterMedical = new JPanel();
        ajouterMedical.setBounds(250, 0, 950, 650);
        ajouterMedical.setLayout(null);

        imageLabel = new JLabel();
        imageLabel.setBounds(110, 20, 120, 90);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        ajouterMedical.add(imageLabel);

        importButton = new JButton("Importer Image");
        importButton.setBounds(110, 120, 120, 30);
        importButton.addActionListener(this);
        ajouterMedical.add(importButton);

        JLabel ID = new JLabel("ID du Médicament:");
        ID.setBounds(30, 170, 140, 30);
        ajouterMedical.add(ID);

        ID_text = new JTextField();
        ID_text.setBounds(160, 170, 160, 30);
        ajouterMedical.add(ID_text);

        JLabel name = new JLabel("Nom:");
        name.setBounds(30, 210, 140, 30);
        ajouterMedical.add(name);

        name_text = new JTextField();
        name_text.setBounds(160, 210, 160, 30);
        ajouterMedical.add(name_text);


        JLabel category = new JLabel("Catégorie:");
        category.setBounds(30, 250, 140, 30);
        ajouterMedical.add(category);

        category_text = new JComboBox<>(new String[] {
                "Choisir...", "Antibiotique", "Antalgique", "Antiseptique", "Vitamines", "Autres"
        });
        category_text.setBounds(160, 250, 160, 30);
        ajouterMedical.add(category_text);


        JLabel prix = new JLabel("Prix (DH):");
        prix.setBounds(30, 290, 140, 30);
        ajouterMedical.add(prix);

        prix_text = new JTextField();
        prix_text.setBounds(160, 290, 160, 30);
        ajouterMedical.add(prix_text);


        JLabel stock = new JLabel("Stock:");
        stock.setBounds(30, 330, 140, 30);
        ajouterMedical.add(stock);

        stock_text = new JTextField();
        stock_text.setBounds(160, 330, 160, 30);
        ajouterMedical.add(stock_text);




        addButton = new JButton("Ajouter");
        addButton.setBounds(40, 440, 120, 35);
        addButton.setBackground(new Color(0x007BFF));
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        ajouterMedical.add(addButton);

        clearButton = new JButton("Effacer");
        clearButton.setBounds(180, 440, 120, 35);
        clearButton.setBackground(new Color(0x28A745));
        clearButton.addActionListener(this);
        clearButton.setForeground(Color.WHITE);
        ajouterMedical.add(clearButton);

        deleteButton = new JButton("Supprimer");
        deleteButton.setBounds(40, 490, 120, 35);
        deleteButton.setBackground(new Color(0xD3151A));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(this);
        ajouterMedical.add(deleteButton);

        updateButton = new JButton("Mise à jour");
        updateButton.setBounds(180, 490, 120, 35);
        updateButton.setBackground(new Color(0x81599C));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(this);
        ajouterMedical.add(updateButton);


        JLabel searchLabel = new JLabel("Rechercher :");
        searchLabel.setBounds(355, 20, 100, 30);
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ajouterMedical.add(searchLabel);


        searchField = new JTextField();
        searchField.setBounds(460, 20, 350, 30);
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));
        ajouterMedical.add(searchField);


        JButton searchButton = new JButton("recherche");
        searchButton.setBounds(820, 20, 110, 30);
        searchButton.setBackground(new Color(0x007BFF));
        searchButton.setForeground(Color.WHITE);
        ajouterMedical.add(searchButton);


        String[] columnNames = {"ID", "Nom", "Catégorie", "Prix (DH)", "Stock"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(new Color(0xD3D3D3));

        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(345, 70, 610, 570);
        ajouterMedical.add(tableScrollPane);

        loadData();


        table.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = table.getSelectedRow();
                try (Connection conn = DBConnection.getConnection()) {
                    String query = "SELECT image FROM medicament WHERE id = ?";
                    try (PreparedStatement ps = conn.prepareStatement(query)) {
                        ps.setString(1, tableModel.getValueAt(selectedRow, 0).toString());
                        try (ResultSet rs = ps.executeQuery()) {
                            if (rs.next()) {
                                String imagePath = rs.getString("image");
                                displayImage(imagePath);
                            } else {
                                imageLabel.setIcon(null);
                            }
                        }
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Erreur lors du chargement de l'image !");
                    e.printStackTrace();
                }
                if (selectedRow >= 0) {
                    ID_text.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    name_text.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    category_text.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
                    prix_text.setText(tableModel.getValueAt(selectedRow, 3).toString());
                    stock_text.setText(tableModel.getValueAt(selectedRow, 4).toString());
                }
            }

        });



    }

    public JPanel getPanel() {
        return ajouterMedical;
    }

    private void displayImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source= e.getSource();
        if(source.equals(importButton)){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Selectionner une photo");


            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(File file) {
                    String name = file.getName().toLowerCase();
                    return file.isDirectory() || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".png");
                }

                @Override
                public String getDescription() {
                    return "Fichiers Image (*.jpg, *.jpeg, *.png)";
                }
            });

            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                displayImage(selectedFile.getAbsolutePath());
                chemin=selectedFile.getAbsolutePath();
            }
        } else if (source.equals(clearButton)) {
            ID_text.setText("");
            name_text.setText("");
            prix_text.setText("");
            stock_text.setText("");
            category_text.setSelectedIndex(0);
            imageLabel.setIcon(null);
            ajouterMedical.revalidate();
            ajouterMedical.repaint();
        } else if (source.equals(addButton)) {
            String id_m=ID_text.getText().trim();
            String name_m=name_text.getText().trim();
            String prix_m=prix_text.getText().trim();
            String stock_m=stock_text.getText().trim();

            Object selectedItem = category_text.getSelectedItem();
            String selectedValue = selectedItem.toString();

            if (id_m.isEmpty() || name_m.isEmpty() || prix_m.isEmpty() || stock_m.isEmpty() || selectedValue.equals("Choisir...") || chemin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir tous les champs !");
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                PreparedStatement t = conn.prepareStatement("SELECT id FROM medicament WHERE id = ?");
                t.setString(1, id_m);
                ResultSet rs = t.executeQuery();

                if (rs.next()){
                    JOptionPane.showMessageDialog(null, "Le produit avec cet ID existe déjà !");
                    return;
                }
                    PreparedStatement ps = conn.prepareStatement("INSERT INTO medicament VALUES (?, ?, ?, ?, ?, ?,?)");
                    ps.setString(1, id_m);
                    ps.setInt(2, id);
                    ps.setString(3, name_m);
                    ps.setString(4, selectedValue);
                    ps.setString(5, prix_m);
                    ps.setString(6, stock_m);
                    ps.setString(7, chemin);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Produit ajouté avec succès !");
                        ID_text.setText("");
                        name_text.setText("");
                        prix_text.setText("");
                        stock_text.setText("");
                        category_text.setSelectedIndex(0);
                        imageLabel.setIcon(null);
                        ajouterMedical.revalidate();
                        ajouterMedical.repaint();

                        tableModel.setRowCount(0);
                        loadData();
                    }
            } catch (SQLException g) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la conixion!");
                g.printStackTrace();
            }
        } else if (source.equals(updateButton)) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un médicament à mettre à jour !");
                return;
            }
            String id_m = table.getValueAt(selectedRow, 0).toString().trim();
            String prix_m = prix_text.getText().trim();
            String stock_m = stock_text.getText().trim();

            if (prix_m.isEmpty() || stock_m.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Veuillez remplir les champs Prix et Stock !");
                return;
            }
            try {
                Double.parseDouble(prix_m);
                Integer.parseInt(stock_m);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Le prix doit être un nombre décimal et le stock un entier !");
                return;
            }
            try (Connection conn = DBConnection.getConnection()) {
                    PreparedStatement ps = conn.prepareStatement("UPDATE medicament SET prix = ?, stock = ? WHERE id = ?");
                    ps.setString(1, prix_m);
                    ps.setString(2, stock_m);
                    ps.setString(3, id_m);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Mise à jour effectuée avec succès !");


                        ID_text.setText("");
                        name_text.setText("");
                        prix_text.setText("");
                        stock_text.setText("");
                        category_text.setSelectedIndex(0);
                        imageLabel.setIcon(null);
                        ajouterMedical.revalidate();
                        ajouterMedical.repaint();


                        tableModel.setRowCount(0);
                        loadData();
                    } else {
                        JOptionPane.showMessageDialog(null, "Échec de la mise à jour !");
                    }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la mise à jour !");
                ex.printStackTrace();
            }
        } else if (source.equals(deleteButton)) {
            int selectedRow = table.getSelectedRow();

            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un médicament à supprimer !");
                return;
            }

            String id_m = table.getValueAt(selectedRow, 0).toString().trim();

            int confirm = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer ce médicament ?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            try (Connection conn = DBConnection.getConnection()) {
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM medicament WHERE id = ?")) {
                    ps.setString(1, id_m);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Médicament supprimé avec succès !");

                        ID_text.setText("");
                        name_text.setText("");
                        prix_text.setText("");
                        stock_text.setText("");
                        category_text.setSelectedIndex(0);
                        imageLabel.setIcon(null);
                        ajouterMedical.revalidate();
                        ajouterMedical.repaint();


                        tableModel.setRowCount(0);
                        loadData();

                    } else {
                        JOptionPane.showMessageDialog(null, "Échec de la suppression !");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erreur lors de la suppression !");
                ex.printStackTrace();
            }
        }
    }

    private void loadData() {
        try (Connection conn = DBConnection.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT id, nom, categorie, prix, stock FROM medicament WHERE user_id = ?")) {
                ps.setInt(1, id);

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

}
