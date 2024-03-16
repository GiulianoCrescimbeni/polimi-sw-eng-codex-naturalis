package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Goals.Goal;

import java.util.ArrayList;

public class Codex {

    private InitialCard initialCard;
    private int score;
    private GoalsDeck goalsToPick;
    private Goal personalGoal;
    private ArrayList<Integer> numOfResources;

    /**
     * Constructor
     * @param initialCard the {@link InitialCard} of the codex
     * @param numOfResources the number of resources contained in the codex (from 0 to 7 where 0 represent the plant and 7 the jar like the {@link it.polimi.ingsw.model.Enumerations.Resource})
     */
    public Codex(InitialCard initialCard, /*ArrayList<Goal> goalsToPick, Goal personalGoal,*/ ArrayList<Integer> numOfResources) {
        this.initialCard = initialCard;
        this.goalsToPick = goalsToPick;
        this.personalGoal = personalGoal;
        this.numOfResources = numOfResources;
    }

    /**
     * @return the intial card of the deck
     */
    public InitialCard getInitialCard() { return this.initialCard; }

    /**
     * @return the score of the codex
     */
    public int getScore() { return this.score; }

    /**
     * @return the deck of the goals to pick
     */
    public GoalsDeck getGoalsToPick() { return this.goalsToPick; }

    /**
     * @return the personal goal of the codex
     */
    public Goal getPersonalGoal() { return this.personalGoal; }

    /**
     * @return the arraylist for the number of resources
     */
    public ArrayList<Integer> getNumOfResources() { return this.numOfResources; }

    /**
     * Set the score of the codex
     * @param score the new score for the codex
     */
    public void setScore(int score) { this.score = score; }

    /**
     * Set the number of resources
     * @param numOfResources arraylist for the number of resources
     */
    public void setNumOfResources(ArrayList<Integer> numOfResources) { this.numOfResources = numOfResources; }

    /**
     * Set the personal goal for the deck
     * @param personalGoal the personal goal for the codex
     */
    public void setPersonalGoal(Goal personalGoal) {
        this.personalGoal = personalGoal;
    }
}
