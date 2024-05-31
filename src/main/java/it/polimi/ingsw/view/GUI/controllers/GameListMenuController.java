package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.client.commands.RefreshAvailableGamesCommand;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class GameListMenuController {

    @FXML
    public void refresh() throws IOException {
        try {
            RefreshAvailableGamesCommand cmd = new RefreshAvailableGamesCommand();
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void back() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.MAIN_MENU);
    }
}