package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;

/**
 * Class that represents the equals resource goal
 */
public class ResourcesGoal extends Goal {

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        switch (getCardType()) {
            case PLANT:     return codex.getNumOfResources(Resource.PLANT) / 3;
            case ANIMAL:    return codex.getNumOfResources(Resource.ANIMAL) / 3;
            case FUNGI:     return codex.getNumOfResources(Resource.FUNGI) / 3;
            case INSECT:    return codex.getNumOfResources(Resource.INSECT) / 3;
            //TODO Gestire eccezione
            default: return 0;
        }
    }
}
