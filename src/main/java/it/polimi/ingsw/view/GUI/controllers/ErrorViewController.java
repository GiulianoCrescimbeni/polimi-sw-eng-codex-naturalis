package it.polimi.ingsw.view.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * The ErrorViewController class handles the display of error messages in the GUI
 */
public class ErrorViewController extends ViewController {
    @FXML
    private Label errorMessageLabel;

    @FXML
    private Button closeButton;

    /**
     * Sets the error message to be displayed in the error message label
     * @param message the error message to display
     */
    public void setErrorMessage(String message) {
        errorMessageLabel.setText(message);
    }

    /**
     * Handles the action of closing the error window when the close button is pressed
     */
    @FXML
    private void handleCloseButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
