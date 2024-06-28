package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Goals.Goal;
import java.util.Stack;

/**
 * The interface for the {@link it.polimi.ingsw.model.GameComponents.GoalsDeck} game component
 */
public interface GoalDeckInterface {

    /**
     * @return the number of goals
     */
    public int getNumOfGoals();
    /**
     * @return stack of goals in the deck
     */
    public Stack<Goal> getGoals();
}
