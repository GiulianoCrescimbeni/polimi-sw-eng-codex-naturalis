package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public void game_list() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.GAME_LIST_MENU);
    }

    @FXML
    public void create_game() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.CREATE_GAME_MENU);
    }

    @FXML
    public void exit(){
        System.exit(0);
    }
}