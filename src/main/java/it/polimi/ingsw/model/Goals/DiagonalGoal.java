package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.view.TUI.TextColor;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

/**
 * Class that represent the diagonal shaped goal card
 */
public class DiagonalGoal extends Goal implements Serializable {

    /**
     * Constructor for the diagonal goal
     * @param objectType the type of the objective
     * @param cardType the type of the card
     * @param score the score of the goal
     * @param goalId the id of the goal
     */
    public DiagonalGoal(Resource objectType, CardType cardType, int score, int goalId) {
        super(objectType, cardType, score, goalId);
    }

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex){
        int numOfCompletition = 0;

        switch(this.getCardType()){

            case INSECT:
                for (Coordinate i : codex.getCards().keySet()) {
                    Card cardUL = null;

                    if (codex.getCard(i) != null && !codex.getCard(i).isDUsed() && codex.getCard(i).getCardType() == CardType.INSECT) {
                        cardUL = codex.getCard(i);

                        while(cardUL.getAngle(AnglePos.UL).getAttached() != null && cardUL.getAngle(AnglePos.UL).getAttached().getCard().getCardType()== CardType.INSECT && !cardUL.getAngle(AnglePos.UL).getAttached().getCard().isDUsed())
                            cardUL = cardUL.getAngle(AnglePos.UL).getAttached().getCard();

                        if(cardUL.getAngle(AnglePos.DR).getAttached() != null && cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached() != null) {

                            if (!cardUL.getAngle(AnglePos.DR).getAttached().getCard().isDUsed() &&
                                    cardUL.getAngle(AnglePos.DR).getAttached().getCard().getCardType() == CardType.INSECT &&
                                    cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached().getCard().getCardType() == CardType.INSECT &&
                                    !cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached().getCard().isDUsed()){

                                numOfCompletition++;
                                cardUL.setdUsed();
                                cardUL.getAngle(AnglePos.DR).getAttached().getCard().setdUsed();
                                cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached().getCard().setdUsed();
                            }
                        }

                    }
                }

                break;

            case FUNGI:
                for (Coordinate i : codex.getCards().keySet()) {
                    Card cardUL = null;

                    if (codex.getCard(i) != null && !codex.getCard(i).isDUsed() && codex.getCard(i).getCardType() == CardType.FUNGI) {
                        cardUL = codex.getCard(i);

                        while(cardUL.getAngle(AnglePos.UR).getAttached() != null && cardUL.getAngle(AnglePos.UR).getAttached().getCard().getCardType() == CardType.FUNGI && !cardUL.getAngle(AnglePos.UR).getAttached().getCard().isDUsed())
                            cardUL = cardUL.getAngle(AnglePos.UR).getAttached().getCard();

                        if(cardUL.getAngle(AnglePos.DL).getAttached() != null && cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached() != null) {

                            if (!cardUL.getAngle(AnglePos.DL).getAttached().getCard().isDUsed() &&
                                    cardUL.getAngle(AnglePos.DL).getAttached().getCard().getCardType() == CardType.FUNGI &&
                                    cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached().getCard().getCardType() == CardType.FUNGI &&
                                    !cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached().getCard().isDUsed()) {

                                numOfCompletition++;
                                cardUL.setdUsed();
                                cardUL.getAngle(AnglePos.DL).getAttached().getCard().setdUsed();
                                cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached().getCard().setdUsed();
                            }
                        }
                    }
                }

                break;


            case PLANT:
                for (Coordinate i : codex.getCards().keySet()) {
                    Card cardUL = null;

                    if (codex.getCard(i) != null && !codex.getCard(i).isDUsed() && codex.getCard(i).getCardType() == CardType.PLANT) {
                        cardUL = codex.getCard(i);

                        while(cardUL.getAngle(AnglePos.UL).getAttached() != null && cardUL.getAngle(AnglePos.UL).getAttached().getCard().getCardType()== CardType.PLANT && !cardUL.getAngle(AnglePos.UL).getAttached().getCard().isDUsed())
                            cardUL = cardUL.getAngle(AnglePos.UL).getAttached().getCard();

                        if(cardUL.getAngle(AnglePos.DR).getAttached() != null && cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached() != null) {

                            if (!cardUL.getAngle(AnglePos.DR).getAttached().getCard().isDUsed() &&
                                    cardUL.getAngle(AnglePos.DR).getAttached().getCard().getCardType() == CardType.PLANT &&
                                    cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached().getCard().getCardType() == CardType.PLANT &&
                                    !cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached().getCard().isDUsed()) {

                                numOfCompletition++;
                                cardUL.setdUsed();
                                cardUL.getAngle(AnglePos.DR).getAttached().getCard().setdUsed();
                                cardUL.getAngle(AnglePos.DR).getAttached().getCard().getAngle(AnglePos.DR).getAttached().getCard().setdUsed();
                            }
                        }
                    }
                }
                break;


            case ANIMAL:
                for (Coordinate i : codex.getCards().keySet()) {
                    Card cardUL = null;

                    if (codex.getCard(i) != null && !codex.getCard(i).isDUsed() && codex.getCard(i).getCardType() == CardType.ANIMAL) {
                        cardUL = codex.getCard(i);

                        while(cardUL.getAngle(AnglePos.UR).getAttached() != null && cardUL.getAngle(AnglePos.UR).getAttached().getCard().getCardType()== CardType.ANIMAL && !cardUL.getAngle(AnglePos.UR).getAttached().getCard().isDUsed())
                            cardUL = cardUL.getAngle(AnglePos.UR).getAttached().getCard();

                        if(cardUL.getAngle(AnglePos.DL).getAttached() != null && cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached() != null) {

                            if (!cardUL.getAngle(AnglePos.DL).getAttached().getCard().isDUsed() &&
                                    cardUL.getAngle(AnglePos.DL).getAttached().getCard().getCardType() == CardType.ANIMAL &&
                                    cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached().getCard().getCardType() == CardType.ANIMAL &&
                                    !cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached().getCard().isDUsed()) {

                                numOfCompletition++;
                                cardUL.setdUsed();
                                cardUL.getAngle(AnglePos.DL).getAttached().getCard().setdUsed();
                                cardUL.getAngle(AnglePos.DL).getAttached().getCard().getAngle(AnglePos.DL).getAttached().getCard().setdUsed();
                            }
                        }
                    }
                }
                break;

            default: return (0);
        }
        return numOfCompletition;
    }
    @Override
    public void draw() {
        if(this.getCardType().equals(CardType.FUNGI) || this.getCardType().equals(CardType.ANIMAL)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                         ┌───────────┐       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                         │           │       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                ┌────────┼──┐        │       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                │        │  │        │       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       ┌────────┼──┐     └──┼────────┘       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       │        │  │        │                "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       │        └──┼────────┘                "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       │           │                         "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       └───────────┘                         "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.INSECT) || this.getCardType().equals(CardType.PLANT)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       ┌───────────┐                         "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       │           │                         "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       │        ┌──┼────────┐                "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       │        │  │        │                "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"       └────────┼──┘     ┌──┼────────┐       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                │        │  │        │       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                └────────┼──┘        │       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                         │           │       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                         └───────────┘       "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │                                             │\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        }
    }
}