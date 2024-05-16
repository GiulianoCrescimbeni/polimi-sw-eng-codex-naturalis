package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.view.TUI.TextColor;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

/**
 * Class that represent the L shaped goal
 */
public class LGoal extends Goal implements Serializable {

    public LGoal(Resource objectType, CardType cardType, int score, int goalId) {
        super(objectType, cardType, score, goalId);
    }

    /**
     * Function to check the goal completion
     * @param codex the codex of the player to verify from
     * @return the number of times that the goal has been completed
     */
    @Override
    public int check(Codex codex) {
        int numOfCompletion = 0;
        switch (this.getCardType()){
            case INSECT:
                for (Coordinate i : codex.getCards().keySet()) {
                    Coordinate j = new Coordinate(i.getX(), i.getY() - 2);
                    if (  codex.getCard(j)!=null && codex.getCard(i).getAngle(AnglePos.UL).getAttached() !=null && !codex.getCard(i).isLUsed() && !codex.getCard(j).isLUsed() && !codex.getCard(i).getAngle(AnglePos.UL).getAttached().getCard().isLUsed() && codex.getCard(i).getAngle(AnglePos.UL).getAttached().getCard().getCardType() == CardType.ANIMAL
                            && codex.getCard(i).getCardType() == codex.getCard(j).getCardType()) {
                        numOfCompletion = numOfCompletion + 1;
                        codex.getCard(i).setlUsed();
                        codex.getCard(j).setlUsed();
                        codex.getCard(i).getAngle(AnglePos.UL).getAttached().getCard().setlUsed();
                    }
                }
                break;
            case ANIMAL:
                for (Coordinate i : codex.getCards().keySet()) {
                    Coordinate j = new Coordinate(i.getX(), i.getY() - 2);
                    if (  codex.getCard(j)!=null && codex.getCard(i).getAngle(AnglePos.UR).getAttached() !=null && !codex.getCard(i).isLUsed() && !codex.getCard(j).isLUsed() && !codex.getCard(i).getAngle(AnglePos.UR).getAttached().getCard().isLUsed() && codex.getCard(i).getAngle(AnglePos.UR).getAttached().getCard().getCardType() == CardType.FUNGI
                            && codex.getCard(i).getCardType() == codex.getCard(j).getCardType()) {
                        numOfCompletion = numOfCompletion + 1;
                        codex.getCard(i).setlUsed();
                        codex.getCard(j).setlUsed();
                        codex.getCard(i).getAngle(AnglePos.UR).getAttached().getCard().setlUsed();
                    }
                }
                break;


            case PLANT:
                for (Coordinate i : codex.getCards().keySet()) {
                    Coordinate j = new Coordinate(i.getX(), i.getY() + 2);
                    if (  codex.getCard(j)!=null && codex.getCard(i).getAngle(AnglePos.DL).getAttached() !=null && !codex.getCard(i).isLUsed() && !codex.getCard(j).isLUsed() && !codex.getCard(i).getAngle(AnglePos.DL).getAttached().getCard().isLUsed() && codex.getCard(i).getAngle(AnglePos.DL).getAttached().getCard().getCardType() == CardType.INSECT
                            && codex.getCard(i).getCardType() == codex.getCard(j).getCardType()) {
                        numOfCompletion = numOfCompletion + 1;
                        codex.getCard(i).setlUsed();
                        codex.getCard(j).setlUsed();
                        codex.getCard(i).getAngle(AnglePos.DL).getAttached().getCard().setlUsed();
                    }
                }
                break;

            case FUNGI:
                for (Coordinate i : codex.getCards().keySet()) {
                    Coordinate j = new Coordinate(i.getX(), i.getY() + 2);

                    if (  codex.getCard(j)!=null && codex.getCard(i).getAngle(AnglePos.DR).getAttached() !=null && !codex.getCard(i).isLUsed() && !codex.getCard(j).isLUsed() && !codex.getCard(i).getAngle(AnglePos.DR).getAttached().getCard().isLUsed() && codex.getCard(i).getAngle(AnglePos.DR).getAttached().getCard().getCardType() == CardType.PLANT
                            && codex.getCard(i).getCardType() == codex.getCard(j).getCardType()) {
                        numOfCompletion = numOfCompletion + 1;
                        codex.getCard(i).setlUsed();
                        codex.getCard(j).setlUsed();
                        codex.getCard(i).getAngle(AnglePos.DR).getAttached().getCard().setlUsed();
                    }
                }
                break;

            case FIRST_CARD:
                return 0;

        }
        return numOfCompletion;
    }

    @Override
    public void draw() {
        if(this.getCardType().equals(CardType.FUNGI)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             ┌─────────┐                     "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             │         │                     "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             │         │                     "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             └─────────┘                     "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             ┌─────────┐                     "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             │         │                     "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             │       "+ View.getInstance().getReverseCardTypeColor(getCardType()) +"┌─"+ View.getInstance().getCardTypeColor(getCardType()) +"┼"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"───────┐             "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"             └───────"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"┼"+ View.getInstance().getCardTypeColor(getCardType()) +"─┘       "+ View.getInstance().getReverseCardTypeColor(getCardType()) +"│             "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"                     │         │             "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"                     └─────────┘             "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.PLANT)){
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    ┌─────────┐              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    │         │              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    │         │              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    └─────────┘              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    ┌─────────┐              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    │         │              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            ┌───────"+ View.getInstance().getCardTypeColor(getCardType()) +"┼"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"─┐       "+ View.getInstance().getCardTypeColor(getCardType()) +"│              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            │       "+ View.getInstance().getCardTypeColor(getCardType()) +"└─"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"┼"+ View.getInstance().getCardTypeColor(getCardType()) +"───────┘              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            │         │                      "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            └─────────┘                      "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.ANIMAL)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"                      ┌─────────┐            "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"                      │         │            "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              ┌───────"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"┼"+ View.getInstance().getCardTypeColor(getCardType()) +"─┐       "+ View.getInstance().getReverseCardTypeColor(getCardType()) +"│            "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              │       "+ View.getInstance().getReverseCardTypeColor(getCardType()) +"└─"+ View.getInstance().getCardTypeColor(getCardType()) +"┼"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"───────┘            "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              │         │                    "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              └─────────┘                    "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              ┌─────────┐                    "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              │         │                    "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              │         │                    "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"              └─────────┘                    "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        } else if(this.getCardType().equals(CardType.INSECT)) {
            System.out.println(View.getInstance().getCardTypeColor(getCardType()) +
                            " ┌─────────────────────────────────────────────┐\n" +
                            "   │                                             │\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            ┌─────────┐                      "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            │         │                      "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            │       "+ View.getInstance().getCardTypeColor(getCardType()) +"┌─"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"┼"+ View.getInstance().getCardTypeColor(getCardType()) +"───────┐              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"            └───────"+ View.getInstance().getCardTypeColor(getCardType()) +"┼"+ View.getInstance().getReverseCardTypeColor(getCardType()) +"─┘       "+ View.getInstance().getCardTypeColor(getCardType()) +"│              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    │         │              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    └─────────┘              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    ┌─────────┐              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    │         │              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    │         │              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │"+ View.getInstance().getCardTypeColor(getCardType()) +"                    └─────────┘              "+ View.getInstance().getCardTypeColor(getCardType()) +"│\n" +
                            "   │                                             │\n" +
                            "   └─────────────────────────────────────────────┘" + TextColor.RESET
            );
        }

    }
}



