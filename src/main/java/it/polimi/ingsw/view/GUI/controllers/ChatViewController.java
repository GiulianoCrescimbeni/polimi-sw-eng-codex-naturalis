package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.event.ActionEvent;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class ChatViewController extends ViewController {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox chatLog;

    @FXML
    private TextArea messageBox;

    @FXML
    private Button sendButton;

    @FXML
    private ComboBox<String> channelBox;

    @FXML
    public void initialize() {
        instance = this;
        loadMessages();
        setupChannels();
    }

    private static ChatViewController instance;

    public ChatViewController() {}

    public static ChatViewController getInstance() {
        return instance;
    }

    /**
     * Loads the messages from the client controller and displays them in the chat log.
     */
    private void loadMessages() {
        if (ClientController.getInstance().getMessages() == null) return;
        ClientController clientController = ClientController.getInstance();
        for (String message : clientController.getMessages()) {
            TextFlow text = parseColoredText(message);
            text.getStyleClass().add("chat-message");
            chatLog.getChildren().add(text);
        }
        scrollToBottom();

    }

    /**
     * Sets up the channels in the channel box.
     */
    private void setupChannels() {
        channelBox.getItems().add("Public");
        for (Player p : ClientController.getInstance().getPlayers()) {
            if (!p.getNickname().equals(ClientController.getInstance().getUsername())) {
                channelBox.getItems().add(p.getNickname());
            }
        }
        channelBox.setValue("Public");
    }

    /**
     * Sets the action for the send button.
     * @param event the action event triggered by clicking the send button
     */
    @FXML
    private void setSendButtonAction(ActionEvent event) {
        sendMessage();
    }

    /**
     * Sends a message when the Enter key is pressed.
     * @param event the key event triggered by pressing the Enter key
     */
    @FXML
    private void onEnterSend(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            event.consume();
            sendMessage();
        }
    }

    /**
     * Sends a message from the message box.
     */
    private void sendMessage() {
        String message = messageBox.getText().trim();
        if (!message.isEmpty()) {

            if (channelBox.getValue().equals("Public")) {
                messageBox.clear();
                String[] toSend = new String[message.split(" ").length + 1];
                for (int i = 0; i < message.split(" ").length; i++) {
                    if (i == 0) {
                        toSend[i] = ".";
                    }
                    toSend[i+1] = message.split(" ")[i];
                }

                ClientController.getInstance().sendPublicMessage(toSend);
            } else {
                messageBox.clear();
                String[] toSend = new String[message.split(" ").length + 1];
                for (int i = 0; i < message.split(" ").length; i++) {
                    if (i == 0) {
                        toSend[i] = channelBox.getValue();
                    }
                    toSend[i+1] = message.split(" ")[i];
                }

                ClientController.getInstance().sendPrivateMessage(toSend);
            }

        }
    }

    /**
     * Updates the message history in the chat log.
     */
    public void updateMessageHistory() {
        if (ClientController.getInstance().getMessages() == null) return;
        String message = ClientController.getInstance().getMessages().getLast();
        TextFlow text = parseColoredText(message);
        text.getStyleClass().add("chat-message");
        chatLog.getChildren().add(text);
        scrollToBottom();
    }

    /**
     * Scrolls the chat log to the bottom.
     */
    private void scrollToBottom() {
        Platform.runLater(() -> {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                scrollPane.layout();
                scrollPane.setVvalue(1.0);
            }));
            timeline.setCycleCount(1);
            timeline.play();
        });
    }

    /**
     * Parses colored text from the message and returns it as a TextFlow object.
     * @param message the message containing colored text
     * @return the TextFlow object with parsed colored text
     */
    private TextFlow parseColoredText(String message) {
        TextFlow textFlow = new TextFlow();
        String[] parts = message.split("(\u001B\\[[;\\d]*m)");
        for (String part : parts) {
            Text text = new Text(part);
            if (part.matches("\u001B\\[\\d{1,2}m")) {
                String colorCode = part.replace("\u001B[", "").replace("m", "");
                switch (colorCode) {
                    case "30": text.setStyle("-fx-fill: black;"); break;
                    case "31": text.setStyle("-fx-fill: red;"); break;
                    case "32": text.setStyle("-fx-fill: green;"); break;
                    case "33": text.setStyle("-fx-fill: yellow;"); break;
                    case "34": text.setStyle("-fx-fill: blue;"); break;
                    case "35": text.setStyle("-fx-fill: magenta;"); break;
                    case "36": text.setStyle("-fx-fill: cyan;"); break;
                    case "37": text.setStyle("-fx-fill: white;"); break;
                    default: text.setStyle("-fx-fill: black;"); break;
                }
            }
            textFlow.getChildren().add(text);
        }
        return textFlow;
    }
}
