package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Interfaces.GoalDeckInterface;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for goals deck
 */
public class GoalsDeck implements GoalDeckInterface {
    private int numOfGoals;
    private ArrayList<Goal> goals;

    /**
     * Constructor
     * @param numOfGoals the number of goals in the deck
     */
    public GoalsDeck(int numOfGoals) {
        this.numOfGoals = numOfGoals;
    }

    /**
     * Constructor
     * @param numOfGoals the number of goals in the deck
     * @param goals the arraylist of goals in the deck
     */
    public GoalsDeck(int numOfGoals, ArrayList<Goal> goals) {
        this.numOfGoals = numOfGoals;
        this.goals = goals;
    }

    /**
     * @return the number of goals
     */
    public int getNumOfGoals() { return numOfGoals; }

    /**
     * @return arraylist of goals in the deck
     */
    public ArrayList<Goal> getGoals() { return goals; }

    /**
     * Pick a random {@link Goal} from the deck and remove it
     * @return goal picked from the deck
     */
    public Goal pickRandomGoal() {
        Random r = new Random();
        int goalToPick = r.nextInt(numOfGoals + 1);
        Goal goalPicked = goals.get(goalToPick);
        goals.remove(goalToPick);
        return goalPicked;
    }

    /**
     * Add a {@link Goal} in the deck
     * @param goal the goal to add in the deck
     */
    public void addGoal(Goal goal) {
        goals.add(goal);
    }
}
