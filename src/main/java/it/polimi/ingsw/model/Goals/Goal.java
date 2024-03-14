package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;

public abstract class Goal {
    private int goalId;
    private int score;
    private CardType cardType;
    private Resource objectType;

    public int getGoalId() {
        return goalId;
    }

    public int getScore() {
        return score;
    }

    public CardType getCardType() {
        return cardType;
    }

    public Resource getObjectType() {
        return objectType;
    }

    public int check(Codex codex) {
        return 0;
    }
}
