package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;

/**
 * Class that represents the equals object goal
 */
public class EqualsObjectGoal extends Goal {

    //Goal Card: 2 equals object
    //Takes num of couple of object:

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        switch (getObjectType()) {
            case SCROLL:    return codex.getNumOfResources(Resource.SCROLL) / 2;
            case FEATHER:   return codex.getNumOfResources(Resource.FEATHER) / 2;
            case JAR:       return codex.getNumOfResources(Resource.JAR) / 2;
            //TODO Aggiungere gestione eccezione
            default: return 0;
        }
    }
}
