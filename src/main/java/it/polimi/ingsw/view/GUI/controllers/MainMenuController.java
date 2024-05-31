package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.client.commands.RefreshAvailableGamesCommand;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainMenuController {

    @FXML
    public void game_list() throws IOException {
        try {
            RefreshAvailableGamesCommand cmd = new RefreshAvailableGamesCommand();
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
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