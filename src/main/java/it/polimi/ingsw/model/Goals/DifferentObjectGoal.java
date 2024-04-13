package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;

/**
 * Class that represents the different object goal card
 */
public class DifferentObjectGoal extends Goal {

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        return Math.min(Math.min(codex.getNumOfResources(Resource.SCROLL),
                codex.getNumOfResources(Resource.FEATHER)),
                codex.getNumOfResources(Resource.JAR));
    }
}
