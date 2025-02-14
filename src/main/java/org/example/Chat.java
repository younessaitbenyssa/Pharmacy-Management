package org.example;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.control.ScrollPane;

import javax.swing.*;
import java.awt.*;

public class Chat {
    private JPanel chatPanel;
    private JTextField chatArea;
    private JFXPanel fxPanel;
    private VBox messageBox;
    private ScrollPane scrollPane;
    private Llm AskBot;

    public Chat() {
        chatPanel = new JPanel();
        chatPanel.setLayout(null);
        chatPanel.setBounds(0, 0, 950, 630);

        
        fxPanel = new JFXPanel();
        fxPanel.setBounds(50, 50, 850, 500);

       
        chatArea = new JTextField();
        chatArea.setBounds(50, 580, 740, 40);

        JButton sendButton = new JButton("Send");
        sendButton.setBounds(810, 580, 90, 40);

        chatArea.addActionListener(e -> sendMessage());
        sendButton.addActionListener(e -> sendMessage());

        chatPanel.add(chatArea);
        chatPanel.add(sendButton);
        chatPanel.add(fxPanel);
        AskBot = new Llm();

        Platform.runLater(this::initFX);
    }

    private void initFX() {
        Platform.runLater(() -> {
            messageBox = new VBox(10);
            messageBox.setStyle("-fx-background-color: #eeeeee;");

            
            scrollPane = new ScrollPane(messageBox);
            scrollPane.setFitToWidth(true);
            scrollPane.setPannable(true);
            scrollPane.setStyle(
                    "-fx-background: #eeeeee;" +
                            "-fx-border-width: 0;" +
                            "-fx-border-color: transparent;" +
                            "-fx-focus-color: transparent;" +
                            "-fx-faint-focus-color: transparent;" +
                            "-fx-background-insets: 0;" +
                            "-fx-padding: 0;"
            );

            Scene scene = new Scene(scrollPane, 900, 500, Color.WHITE);
            fxPanel.setScene(scene);
        });
    }

    private void sendMessage() {
        String userMessage = chatArea.getText();
        if (!userMessage.isEmpty()) {

            if (messageBox != null) {
                addMessage(userMessage, true);
            }
            chatArea.setText("");


            new Thread(() -> {
                try {

                    String botResponse = getBotResponse(userMessage);

                    Platform.runLater(() -> {
                        if (messageBox != null) {
                            addMessage(botResponse, false);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    private void addMessage(String message, boolean isUser) {
        Platform.runLater(() -> {
            Text text = new Text(message);
            text.setStyle("-fx-font-size: 16px;");

            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle(
                    "-fx-padding: 10px;" +
                            "-fx-background-radius: 15px;" +
                            "-fx-background-color: " + (isUser ? "#1E90FF;" : "#808080;") +
                            "-fx-text-fill: white;"
            );
            textFlow.setMaxWidth(500);
            text.wrappingWidthProperty().bind(textFlow.widthProperty());

            HBox hbox = new HBox(textFlow);
            hbox.setStyle("-fx-alignment: " + (isUser ? "top-right;" : "top-left;"));

            messageBox.getChildren().add(hbox);


            scrollPane.layout();
            scrollPane.setVvalue(1.0);
        });
    }

    private String getBotResponse(String userMessage) throws Exception {
        return AskBot.sendMessage(userMessage);
    }

    public JPanel getChatPanel() {
        return chatPanel;
    }
}
