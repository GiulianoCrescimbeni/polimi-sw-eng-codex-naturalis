package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.GameComponents.Codex;

/**
 * Class that represents the different object goal card
 */
public class DifferentObjectGoal extends Goal {

    //Goal Card: 3 different object
    //Takes the min number from an ArrayList of Resources:
    // pos 4: SCROLL;
    // pos 5: FEATHER;
    // pos 6: JAR;

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        return Math.min(Math.min( codex.getNumOfResources().get(4),
                                      codex.getNumOfResources().get(5)),
                                      codex.getNumOfResources().get(6));
    }
}