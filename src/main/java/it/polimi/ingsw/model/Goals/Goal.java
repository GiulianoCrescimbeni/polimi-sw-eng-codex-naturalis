package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;

/**
 * Abstract class that represents the goal card
 */
public abstract class Goal {
    private int goalId;
    private int score;
    private CardType cardType;
    private Resource objectType;

    public Goal() {}

    public Goal(Resource objectType, CardType cardType, int score, int goalId) {
        this.objectType = objectType;
        this.cardType = cardType;
        this.score = score;
        this.goalId = goalId;
    }

    /**
     * @return the goal id
     */
    public int getGoalId() { return goalId; }

    /**
     * @return the score of the goal
     */
    public int getScore() { return score; }

    /**
     * @return the type of the goal card
     */
    public CardType getCardType() { return cardType; }

    /**
     * @return the object type of the goal card
     */
    public Resource getObjectType() { return objectType; }

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    public int check(Codex codex) {
        return 0;
    }
}
