package org.example;


import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;


public class SideBar {
    private JPanel sideBarPanel;
    private static JPanel contentPanel;
    private static JButton AddMedecine;
    private static JButton homeButton;
    private static JButton chatButton;
    private static JButton reports;
    private static JButton sales;
    private static JButton logoutButton;
    private static JButton settingsButton;
    private static JButton statics;
    private static JButton settings;
    private static ImageIcon icon1, icon2, icon3, icon4, icon5, icon6, icon7, icon8, icon9;
    private static JLabel profileLab, separater;

    SideBar(JFrame frame){
        sideBarPanel = new JPanel();
        sideBarPanel.setLayout(null);
        sideBarPanel.setBackground(new Color(0, 128, 128));
        sideBarPanel.setBounds(0, 0, 250, 630);

        //icons
        icon1 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\home.png");
        icon2 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\add.png");
        icon3 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\chatbot-icon.png");
        icon4 = new ImageIcon("C:\\Users\\dell\\Desktop\\icons\\reports.png");
        icon5 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\pharmacyimagewhite.png");
        icon6 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\dashboard.png");
        icon7 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\sales.png");
        icon8 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\logout.png");
        icon9 = new ImageIcon("C:\\Users\\dell\\Desktop\\java products\\Pharmacy\\src\\main\\java\\assets\\images\\settings.png");



        // Resize icons
        icon1 = resizeIcon(icon1, 28, 28);
        icon2 = resizeIcon(icon2, 28, 28);
        icon3 = resizeIcon(icon3, 28, 28);
        icon4 = resizeIcon(icon4, 28, 28);
        icon6 = resizeIcon(icon6, 28, 28);
        icon7 = resizeIcon(icon7, 28, 28);
        icon8 = resizeIcon(icon8, 24, 28);
        icon9 = resizeIcon(icon9, 28, 28);
        icon5 = resizeIcon(icon5, 150, 150);


        // Profile label
        profileLab = new JLabel();
        profileLab.setBounds(50, 30, 150, 150);
        Image scaledImage1 = icon5.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        profileLab.setIcon(new ImageIcon(scaledImage1));

        // Content panel
        contentPanel = new JPanel();
        contentPanel.setBounds(250, 0, 950, 630);
        contentPanel.setLayout(new CardLayout());

        HomePage home = new HomePage(frame);
        AddMedecine addMed = new AddMedecine();
        Chat chat = new Chat();

        VenteMedicament salesPanel = new VenteMedicament();


        // Add different panels for each section
        //JPanel expensePanel = createContentPanel("Expense Panel");
        JPanel homePanel = home.getHomePanel();
        JPanel addMedecine = addMed.getPanel();
        JPanel chatPanel = chat.getChatPanel();
        JPanel SalePanel = salesPanel.getPanel();
        JPanel reportsPanel = createContentPanel("Reports Panel");


        contentPanel.add(homePanel, "Home");
        contentPanel.add(addMedecine, "AddMedecine");
        contentPanel.add(chatPanel, "Chat");
        contentPanel.add(reportsPanel, "Reports");
        contentPanel.add(SalePanel, "Sales");

        // Sidebar buttons
        homeButton = createSidebarButton("    Home", icon1, 200);
        AddMedecine = createSidebarButton("    AddMedecine", icon2, 250);
        chatButton = createSidebarButton("    ChatBot", icon3, 300);
        reports = createSidebarButton("     Reports", icon4, 350);
        sales = createSidebarButton("     Sales", icon6, 400);
        statics = createSidebarButton("     Statistics", icon7, 450);
        logoutButton = createSidebarButton("     Logout", icon8, 530);
        settingsButton = createSidebarButton("     Settings", icon9, 580);


        separater = new JLabel();
        separater.setBounds(25, 510, 200, 1);
        separater.setOpaque(true);
        separater.setBackground(Color.WHITE);


        // Add action listeners to buttons
        AddMedecine.addActionListener(e -> switchPanel("AddMedecine"));
        homeButton.addActionListener(e -> switchPanel("Home"));
        chatButton.addActionListener(e -> switchPanel("Chat"));
        reports.addActionListener(e -> switchPanel("Reports"));
        sales.addActionListener(e -> switchPanel("Sales"));

        // Add components to sidebar
        sideBarPanel.add(profileLab);
        sideBarPanel.add(AddMedecine);
        sideBarPanel.add(homeButton);
        sideBarPanel.add(chatButton);
        sideBarPanel.add(reports);
        sideBarPanel.add(sales);
        sideBarPanel.add(statics);
        sideBarPanel.add(logoutButton);
        sideBarPanel.add(settingsButton);
        sideBarPanel.add(separater);


        //frame.add(contentPanel);



    }

    private static JButton createSidebarButton(String text, ImageIcon icon, int yPosition) {
        JButton button = new JButton(text, icon);
        button.setBounds(10, yPosition, 225, 40);
        button.setOpaque(true);
        button.setFocusable(false);
        button.setIconTextGap(10); // Space between icon and text
        button.setFont(new Font("Arial", Font.PLAIN, 16));

        // Align text and icon to the left
        button.setHorizontalAlignment(SwingConstants.LEFT); // Align both icon and text to the left
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Position text to the right of the icon

        // Add padding
        button.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15)); // Top, Left, Bottom, Right

        // Set the background color and other properties
        button.setBackground(new Color(0, 128, 128));
        button.setForeground(Color.WHITE);

        // Add mouse listener for hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 150, 150)); // Highlight color
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 128, 128)); // Default background
            }
        });

        return button;
    }





    private static void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) contentPanel.getLayout();
        cardLayout.show(contentPanel, panelName);
    }


    private static JPanel createContentPanel(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }


    private static ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }



    public JPanel getSideBarPanel() {
        return sideBarPanel;
    }

    public JPanel ContentPanel() {
        return contentPanel;
    }


}
