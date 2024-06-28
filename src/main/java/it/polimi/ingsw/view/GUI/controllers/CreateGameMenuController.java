package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.client.commands.CreateMatchCommand;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import it.polimi.ingsw.view.TUI.View;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * The CreateGameMenuController class handles the user interactions for creating a new game
 */
public class CreateGameMenuController extends ViewController {
    @FXML
    public void back() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.MAIN_MENU);
    }

    /**
     * Handles the action to create a game with 2 players
     */
    @FXML
    public void create2Players() {
        CreateMatchCommand cmd = new CreateMatchCommand(2);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to create a game with 3 players
     */
    @FXML
    public void create3Players() {
        CreateMatchCommand cmd = new CreateMatchCommand(3);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action to create a game with 4 players
     */
    @FXML
    public void create4Players() {
        CreateMatchCommand cmd = new CreateMatchCommand(4);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}