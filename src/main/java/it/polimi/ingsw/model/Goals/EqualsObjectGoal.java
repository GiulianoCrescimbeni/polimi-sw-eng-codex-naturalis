package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.view.TUI.TextColor;

import java.io.Serializable;

/**
 * Class that represents the equals object goal
 */
public class EqualsObjectGoal extends Goal implements Serializable {

    /**
     * Constructor for the equals object goal
     * @param objectType the type of the objective
     * @param cardType the type of the card
     * @param score the score of the goal
     * @param goalId the id of the goal
     */
    public EqualsObjectGoal(Resource objectType, CardType cardType, int score, int goalId) {
        super(objectType, cardType, score, goalId);
    }

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        switch (getObjectType()) {
            case SCROLL:    return codex.getNumOfResources(Resource.SCROLL) / 2;
            case FEATHER:   return codex.getNumOfResources(Resource.FEATHER) / 2;
            case JAR:       return codex.getNumOfResources(Resource.JAR) / 2;
            default: return 0;
        }
    }

    public void draw() {
        if(this.getObjectType().equals(Resource.FEATHER)) {
            System.out.println(TextColor.BRIGHT_YELLOW +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │            ,             ,                  │\n" +
                            "   │           \"\\\",          \"\\\",                │\n" +
                            "   │           \"=\\=\",        \"=\\=\",              │\n" +
                            "   │            \"=\\=\",        \"=\\=\",             │\n" +
                            "   │             \"=\\=\",        \"=\\=\",            │\n" +
                            "   │              \"-\\-\"         \"-\\-\"            │\n" +
                            "   │                 \\             \\             │\n" +
                            "   │                  `             `            │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getObjectType().equals(Resource.SCROLL)) {
            System.out.println(TextColor.BRIGHT_YELLOW +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │          _________      _________           │\n" +
                            "   │         (        (@    (        (@          │\n" +
                            "   │          '________'|    '________'|         │\n" +
                            "   │            |       |      |       |         │\n" +
                            "   │            |       |      |       |         │\n" +
                            "   │          __)_______|    __)_______|         │\n" +
                            "   │         (         (@   (         (@         │\n" +
                            "   │          '--------'     '--------'          │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getObjectType().equals(Resource.JAR)) {
            System.out.println(TextColor.BRIGHT_YELLOW +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │             i==i           i==i             │\n" +
                            "   │           i======i       i======i           │\n" +
                            "   │           |      |       |      |           │\n" +
                            "   │           |c     |       |c     |           │\n" +
                            "   │           |ccc   |       |ccc   |           │\n" +
                            "   │           |ccccc |       |ccccc |           │\n" +
                            "   │           `-====-'       `-====-'           │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        }
    }
}
