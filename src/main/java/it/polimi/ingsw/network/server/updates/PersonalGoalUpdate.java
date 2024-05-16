package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;

import java.io.Serializable;

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
    @Override
    public void execute() {
        ClientController.getInstance().setPersonalGoal(this.personalGoal);
        Messages.getInstance().info("Personal goal choosen, waiting for other players!");
    }
}
