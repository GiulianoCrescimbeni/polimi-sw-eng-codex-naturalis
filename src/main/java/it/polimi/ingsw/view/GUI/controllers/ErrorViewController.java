package it.polimi.ingsw.view.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorViewController extends ViewController {
    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button closeButton;

    public void setErrorMessage(String message) {
        errorMessageLabel.setText(message);
    }

    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
