package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.ClientController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * The LoginMenuController class manages the user login interface,
 * allowing the user to enter their name and select a color
 */
public class LoginMenuController extends ViewController {

    @FXML
    private TextField nameTextField;

    @FXML
    private MenuButton colorMenuButton;

    private String selectedColor;

    @FXML
    private void initialize() {
        populateMenuButton(ClientController.getInstance().getAvailableColors());
    }

    /**
     * Populates the color menu button with available colors
     * @param options the list of available colors
     */
    private void populateMenuButton(ArrayList<Color> options) {
        for (Color option : options) {
            MenuItem item = new MenuItem(option.name());
            item.setOnAction(this::handleColorSelection);
            switch (option) {
                case Blue:
                    item.setStyle("-fx-text-fill: blue;");
                    break;
                case Red:
                    item.setStyle("-fx-text-fill: red;");
                    break;
                case Yellow:
                    item.setStyle("-fx-text-fill: #edb009;");
                    break;
                case Green:
                    item.setStyle("-fx-text-fill: green;");
                    break;
            }
            colorMenuButton.getItems().add(item);
        }
    }

    /**
     * Handles the color selection from the menu
     * @param event the action event triggered by selecting a color
     */
    @FXML
    private void handleColorSelection(javafx.event.ActionEvent event) {
        MenuItem selectedItem = (MenuItem) event.getSource();
        selectedColor = selectedItem.getText();
        Platform.runLater(() -> {
            colorMenuButton.setText(selectedColor);
            switch (selectedColor) {
                case "Blue":
                    colorMenuButton.setStyle("-fx-text-fill: blue;");
                    break;
                case "Red":
                    colorMenuButton.setStyle("-fx-text-fill: red;");
                    break;
                case "Yellow":
                    colorMenuButton.setStyle("-fx-text-fill: #edb009;");
                    break;
                case "Green":
                    colorMenuButton.setStyle("-fx-text-fill: green;");
                    break;
            }
            colorMenuButton.requestFocus();
        });
    }

    /**
     * Handles the action of the continue button,
     * sending the entered name and selected color to the client controller
     */
    @FXML
    private void handleContinueButton() {
        String name = nameTextField.getText();
        Color color;
        if (name != null && !name.isEmpty() && selectedColor != null) {
            switch (selectedColor) {
                case "Blue":
                    ClientController.getInstance().sendUsernameAndColor(name, Color.Blue);
                    break;
                case "Red":
                    ClientController.getInstance().sendUsernameAndColor(name, Color.Red);
                    break;
                case "Yellow":
                    ClientController.getInstance().sendUsernameAndColor(name, Color.Yellow);
                    break;
                case "Green":
                    ClientController.getInstance().sendUsernameAndColor(name, Color.Green);
                    break;
            }

        } else {
            System.out.println("Please enter your name and select a color.");
        }
    }

}