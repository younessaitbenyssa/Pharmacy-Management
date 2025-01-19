package org.example;

import javax.swing.*;
import java.awt.*;

public class Chat {
    private JPanel chatPanel;
    private JTextField chatArea;
    private JTextArea chatMessage;

    // Constructor
    Chat() {
        chatPanel = new JPanel();
        chatPanel.setLayout(null);
        chatPanel.setBounds(250, 0, 950, 630);

        chatArea = new JTextField();
        chatArea.setBounds(100, 560, 750, 40);
        chatArea.setLayout(null);



        chatMessage = new JTextArea();
        chatMessage.setBounds(100, 50, 750, 500);


        chatPanel.add(chatArea);
        chatPanel.add(chatMessage);


    }

    public JPanel getChatPanel() {
        return chatPanel;
    }
}
