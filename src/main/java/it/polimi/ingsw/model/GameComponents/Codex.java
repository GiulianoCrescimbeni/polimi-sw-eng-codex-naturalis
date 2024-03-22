package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Interfaces.CodexInterface;

import java.util.ArrayList;

public class Codex implements CodexInterface {

    private InitialCard initialCard;
    private int score;
    private GoalsDeck goalsToPick;
    private Goal personalGoal;
    private ArrayList<Integer> numOfResources;
    private ArrayList<Card> cards;

    /**
     * Constructor
     * @param initialCard the {@link InitialCard} of the codex
     * @param goalsToPick the {@link GoalsDeck} of goals to pick the personal goal from
     * @param personalGoal the {@link Goal} that is personal for each codex
     * @param numOfResources the number of resources contained in the codex (from 0 to 7 where 0 represent the plant and 7 the jar like the {@link it.polimi.ingsw.model.Enumerations.Resource})
     * @param cards an arraylist of {@link Card} that are placed in the codex
     */
    public Codex(InitialCard initialCard, GoalsDeck goalsToPick, Goal personalGoal, ArrayList<Integer> numOfResources, ArrayList<Card> cards) {
        this.initialCard = initialCard;
        this.goalsToPick = goalsToPick;
        this.personalGoal = personalGoal;
        this.numOfResources = numOfResources;
        this.cards = cards;
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
     * @return the arraylist of cards that are placed in the codex
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * @param card the card to be added in the arraylist of cards
     */
    public void addCard(Card card) {
        this.cards.add(card);
    }

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
