package it.polimi.ingsw.view.GUI.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CGcontroller {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}