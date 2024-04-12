package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Goals.Goal;
import java.util.Stack;

public interface GoalDeckInterface {

    public int getNumOfGoals();
    public Stack<Goal> getGoals();
}
