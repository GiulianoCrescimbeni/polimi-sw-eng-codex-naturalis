package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.view.LoginView;

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
        LoginView.getInstance().showColors();
    }

    public ArrayList<Color> getAvailableColors() {
        return availableColors;
    }

    public void sendUsernameAndColor(String username, Color color) {
        setUsername(username);
        setColor(color);
        try {
            LoginCommand lgcmd = new LoginCommand(username, color);
            ClientSR.getInstance().sendCommand(lgcmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
