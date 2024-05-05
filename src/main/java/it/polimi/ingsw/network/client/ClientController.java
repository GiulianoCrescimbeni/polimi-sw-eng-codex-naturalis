package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.commands.LoginCommand;

import java.io.IOException;
import java.util.ArrayList;

public class ClientController {

    private static ClientController instance;

    private ArrayList<Color> availableColors = new ArrayList<Color>();

    private ClientController() {}

    public static ClientController getInstance() {
        if (instance == null) instance = new ClientController();
        return instance;
    }

    public void updateAvailableColors(ArrayList<Color> availableColors) {
        this.availableColors = availableColors;
        View.getInstance().showColors();
    }

    public ArrayList<Color> getAvailableColors() {
        return availableColors;
    }

    public void sendUsernameAndColor(String username, Color color) {
        try {
            LoginCommand lgcmd = new LoginCommand(username, color);
            ClientSR.getInstance().sendCommand(lgcmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
