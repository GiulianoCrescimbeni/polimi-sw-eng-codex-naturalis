package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class represents the {@link Update} sent from the server after a login attempt
 */
public class LoginUpdate extends Update implements Serializable {
    private String nickname;
    private boolean logged;
    private String message;
    private ArrayList<Goal> personalGoalsToPick;

    /**
     * Constructor
     */
    public LoginUpdate() {}

    /**
     * Constructor
     * @param message the message of the update
     * @param logged a logged flag, True if the login went well
     */
    public LoginUpdate(String message, boolean logged) {
        this.message = message;
        this.logged = logged;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return login info
     */
    public boolean isLogged() {
        return logged;
    }

    public ArrayList<Goal> getPersonalGoalsToPick() {
        return personalGoalsToPick;
    }

    public void setPersonalGoalsToPick(ArrayList<Goal> personalGoalsToPick) {
        this.personalGoalsToPick = personalGoalsToPick;
    }

    /**
     * Execute the Update on the client with the result of the login
     */
    @Override
    public void execute() {
        if(isLogged()) {
            System.out.println(message);
            ClientController.getInstance().updateGoalsToPick(personalGoalsToPick);
            ClientController.getInstance().setUsername(nickname);
            ClientController.getInstance().getViewInterface().selectPersonalGoal();
        } else {
            ClientController.getInstance().getViewInterface().showError(message);
            ClientController.getInstance().getViewInterface().pickUsernameAndColor();
        }
    }
}
