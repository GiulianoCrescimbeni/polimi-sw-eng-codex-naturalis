package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameListMenuController {
    @FXML
    public void back() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.MAIN_MENU);
    }
}