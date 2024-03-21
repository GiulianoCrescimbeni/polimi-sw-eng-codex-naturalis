package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Goals.Goal;

import java.util.ArrayList;

public interface GoalDeckInterface {

    public int getNumOfGoals();
    public ArrayList<Goal> getGoals();
    public Goal pickRandomGoal();


}
