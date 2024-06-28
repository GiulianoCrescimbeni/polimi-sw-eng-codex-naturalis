package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.view.TUI.TextColor;

import java.io.Serializable;

/**
 * Class that represents the different object goal card
 */
public class DifferentObjectGoal extends Goal implements Serializable {

    /**
     * Constructor for the different object goal
     * @param objectType the type of the objective
     * @param cardType the type of the card
     * @param score the score of the goal
     * @param goalId the id of the goal
     */
    public DifferentObjectGoal(Resource objectType, CardType cardType, int score, int goalId) {
        super(objectType, cardType, score, goalId);
    }

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        return Math.min(Math.min(codex.getNumOfResources(Resource.SCROLL),
                codex.getNumOfResources(Resource.FEATHER)),
                codex.getNumOfResources(Resource.JAR));
    }

    @Override
    public void draw() {
        System.out.println(TextColor.BRIGHT_YELLOW +
                        " ┌─────────────────────────────────────────────┐\n" +
                        "   │                                             │\n" +
                        "   │                                             │\n" +
                        "   │       ,                      _________      │\n" +
                        "   │      \"\\\",          i==i     (        (@     │\n" +
                        "   │      \"=\\=\",      i======i    '________'|    │\n" +
                        "   │       \"=\\=\",     |      |      |       |    │\n" +
                        "   │        \"=\\=\",    |c     |      |       |    │\n" +
                        "   │         \"-\\-\"    |ccc   |    __)_______|    │\n" +
                        "   │            \\     |ccccc |   (         (@    │\n" +
                        "   │             `    `-====-'    '--------'     │\n" +
                        "   │                                             │\n" +
                        "   │                                             │\n" +
                        "   │                                             │\n" +
                        "   └─────────────────────────────────────────────┘" + TextColor.RESET
        );
    }
}
