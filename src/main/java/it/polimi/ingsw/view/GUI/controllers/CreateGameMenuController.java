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

public class CreateGameMenuController extends ViewController {
    @FXML
    public void back() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.MAIN_MENU);
    }

    @FXML
    public void create2Players() {
        CreateMatchCommand cmd = new CreateMatchCommand(2);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void create3Players() {
        CreateMatchCommand cmd = new CreateMatchCommand(3);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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