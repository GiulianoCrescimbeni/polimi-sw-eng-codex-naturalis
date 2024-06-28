package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;

import java.io.Serializable;

/**
 * The update after picking a personal goal
 */
public class PersonalGoalUpdate extends Update implements Serializable {
    private Goal personalGoal;
    public PersonalGoalUpdate() {
        super();
    }

    /**
     * @return the personal goal of the player
     */
    public Goal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * @param personalGoal the personal goal of the player
     */
    public void setPersonalGoal(Goal personalGoal) {
        this.personalGoal = personalGoal;
    }

    /**
     * Update the personal goal chosen
     */
    @Override
    public void execute() {
        ClientController.getInstance().setPersonalGoal(this.personalGoal);
        if(ClientController.getInstance().getGameStatus() != GameStatus.RUNNING) {
            ClientController.getInstance().getViewInterface().waitingRoom();
        }
    }
}
