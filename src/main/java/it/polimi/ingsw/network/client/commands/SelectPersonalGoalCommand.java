package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.network.server.updates.PersonalGoalUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class SelectPersonalGoalCommand extends Command implements Serializable {
    private Goal personalGoalChoosen;

    /**
     * Constructor
     * @param nickname the nickname of the {@link it.polimi.ingsw.model.Player.Player} that is logging in the game
     * @param personalGoal the {@link Goal} chosen from the player
     */
    public SelectPersonalGoalCommand(String nickname, Goal personalGoal) {
        super(nickname);
        this.personalGoalChoosen = personalGoal;
    }

    /**
     * @return the personal goal choosen
     */
    public Goal getPersonalGoalChoosen() {
        return personalGoalChoosen;
    }

    /**
     * @param personalGoalChoosen the personal goal choosen
     */
    public void setPersonalGoalChoosen(Goal personalGoalChoosen) {
        this.personalGoalChoosen = personalGoalChoosen;
    }
    @Override
    public Update execute(Controller gameController) {
        gameController.pickPersonalGoal(this.getNickname(), this.getPersonalGoalChoosen());
        Goal personalGoal = gameController.getModel().getTable().getCodex(gameController.getModel().getPlayerByNickname(this.getNickname())).getPersonalGoal();
        PersonalGoalUpdate personalGoalUpdate = new PersonalGoalUpdate();
        personalGoalUpdate.setPersonalGoal(personalGoal);
        return personalGoalUpdate;
    }
}
