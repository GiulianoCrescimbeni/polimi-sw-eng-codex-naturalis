package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.view.TUI.TextColor;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

/**
 * Class that represents the equals resource goal
 */
public class ResourcesGoal extends Goal implements Serializable {

    /**
     * Constructor for the diagonal goad
     * @param objectType the type of the objective
     * @param cardType the type of the card
     * @param score the score of the goal
     * @param goalId the id of the goal
     */
    public ResourcesGoal(Resource objectType, CardType cardType, int score, int goalId) {
        super(objectType, cardType, score, goalId);
    }

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        switch (getCardType()) {
            case PLANT:     return codex.getNumOfResources(Resource.PLANT) / 3;
            case ANIMAL:    return codex.getNumOfResources(Resource.ANIMAL) / 3;
            case FUNGI:     return codex.getNumOfResources(Resource.FUNGI) / 3;
            case INSECT:    return codex.getNumOfResources(Resource.INSECT) / 3;
            default: return 0;
        }
    }

    @Override
    public void draw() {
        if(this.getCardType().equals(CardType.FUNGI)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐ \n" +
                            "   │                                             │ \n" +
                            "   │                                             │ \n" +
                            "   │                                             │ \n" +
                            "   │                                             │ \n" +
                            "   │      .-\"\"\"-.      .-\"\"\"-.      .-\"\"\"-.      │ \n" +
                            "   │     /* * * *\\    /* * * *\\    /* * * *\\     │ \n" +
                            "   │    :_.-:`:-._;  :_.-:`:-._;  :_.-:`:-._;    │ \n" +
                            "   │        (_)          (_)          (_)        │ \n" +
                            "   │        (_)          (_)          (_)        │ \n" +
                            "   │                                             │ \n" +
                            "   │                                             │ \n" +
                            "   │                                             │ \n" +
                            "   │                                             │ \n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.PLANT)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType())+
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │         |            |            |         │\n" +
                            "   │       .'|'.        .'|'.        .'|'.       │\n" +
                            "   │      /.'|\\ \\      /.'|\\ \\      /.'|\\ \\      │\n" +
                            "   │      | /|'.|      | /|'.|      | /|'.|      │\n" +
                            "   │       \\ |\\/        \\ |\\/        \\ |\\/       │\n" +
                            "   │        \\|/          \\|/          \\|/        │\n" +
                            "   │         `            `            `         │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.ANIMAL)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType())+
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │      |\\__/|       |\\__/|       |\\__/|       │\n" +
                            "   │      /     \\      /     \\      /     \\      │\n" +
                            "   │     /_.~ ~,_\\    /_.~ ~,_\\    /_.~ ~,_\\     │\n" +
                            "   │        \\@/          \\@/          \\@/        │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.INSECT)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │       ,   ,        ,   ,        ,   ,       │\n" +
                            "   │      { \\w/ }      { \\w/ }      { \\w/ }      │\n" +
                            "   │       `>!<`        `>!<`        `>!<`       │\n" +
                            "   │       (/^\\)        (/^\\)        (/^\\)       │\n" +
                            "   │       '   '        '   '        '   '       │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        }
    }
}
