package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.view.TUI.View;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ChatViewController extends ViewController {

    @FXML
    ScrollPane scrollPane;
    @FXML
    Button sendButton;
    @FXML
    VBox chatLog;
    @FXML
    TextArea messageBox;

    public void addLabel(String message, Pos position) {
        HBox hBox = new HBox();
        hBox.setAlignment(position);
        hBox.setPadding(new Insets(5, 5, 5, 10));

        Text text = new Text(message);
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-background-color: rgb(233,233,235);" +
                "-fx-background-radius: 20px");
        textFlow.setPadding(new Insets(5, 10, 5, 10));

        hBox.getChildren().add(textFlow);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                chatLog.getChildren().add(hBox);
            }
        });
    }

    public void setSendButtonAction() {
        String message = messageBox.getText().replaceAll("[\n\r]", "");
        try {
            if (!message.isEmpty()) {
                addLabel(message, Pos.CENTER_RIGHT);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
