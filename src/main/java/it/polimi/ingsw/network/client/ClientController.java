package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.network.client.commands.SelectPlayerNumberCommand;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;

public class ClientController {

    private static ClientController instance;

    private ArrayList<Color> availableColors = new ArrayList<Color>();

    private ClientController() {}

    private String username;
    private Color color;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

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
        setUsername(username);
        setColor(color);
        LoginCommand lgcmd = new LoginCommand(username, color);
        try {
            ClientSR.getInstance().sendCommand(lgcmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMaxPlayers(int maxPlayers) {
        SelectPlayerNumberCommand cmd = new SelectPlayerNumberCommand(getUsername(), maxPlayers);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
