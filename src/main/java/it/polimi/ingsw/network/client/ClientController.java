package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.network.client.commands.SelectPersonalGoalCommand;
import it.polimi.ingsw.network.client.commands.SelectPlayerNumberCommand;
import it.polimi.ingsw.view.TUI.View;

import java.util.ArrayList;

public class ClientController {

    private static ClientController instance;
    private ArrayList<Color> availableColors = new ArrayList<Color>();
    private ArrayList<Goal> goalsToPick = new ArrayList<>();
    private String username;
    private Color color;
    private Goal personalGoal;

    private ClientController() {}

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

    public ArrayList<Goal> getGoalsToPick() {
        return goalsToPick;
    }

    public void updateGoalsToPick(ArrayList<Goal> goalsToPick) {
        this.goalsToPick = goalsToPick;
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

    public void selectPersonalGoal(Goal goal) {
        SelectPersonalGoalCommand selectPersonalGoalCommand = new SelectPersonalGoalCommand(this.username, goal);
        try {
            ClientSR.getInstance().sendCommand(selectPersonalGoalCommand);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setPersonalGoal(Goal personalGoal) {
        this.personalGoal = personalGoal;
    }
}
