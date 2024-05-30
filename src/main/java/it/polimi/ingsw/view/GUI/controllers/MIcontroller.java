package it.polimi.ingsw.view.GUI.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MIcontroller {
    @FXML
    private Label welcomeText;
    @FXML
    private AnchorPane Anchor;
    @FXML
    private Button EXIT;

    @FXML
    private Button CREATE_GAME;
    @FXML
    private Button NEW_GAME;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public void exit(){
        System.exit(0);
    }
    @FXML
    public void joinlobby() throws IOException {

    }

    @FXML

    public void join(){



    }
    @FXML
    public void return_back(){

    }

    @FXML
    public void create_game(){

    }
}