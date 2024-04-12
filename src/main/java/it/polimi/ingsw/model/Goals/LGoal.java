package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;

/**
 * Class that represent the L shaped goal
 */
public class LGoal extends Goal {
    @Override
    public int check(Codex codex) {
        int numOfCompletion = 0;
        switch (this.getCardType()){
            case INSECT:
                for (Coordinate i : codex.getCards().keySet()) {
                    Coordinate j = new Coordinate(i.getX(), i.getY() - 1);
                    if (codex.getCard(i).isLUsed()==false && codex.getCard(j).isLUsed()==false && codex.getCard(i).getAngle(AnglePos.UL).getAttached().getCard().isLUsed() == false && codex.getCard(i).getAngle(AnglePos.UL).getAttached().getCard().getCardType()== CardType.ANIMAL
                            && codex.getCard(i).getCardType()==codex.getCard(j).getCardType()){
                        numOfCompletion++;
                        codex.getCard(i).setlUsed();
                        codex.getCard(j).setlUsed();
                        codex.getCard(i).getAngle(AnglePos.UL).getAttached().getCard().setlUsed();
                    }
                }
                break;
            case ANIMAL:
                for (Coordinate z : codex.getCards().keySet()) {
                    Coordinate v = new Coordinate(z.getX(), z.getY() - 1);
                    if (codex.getCard(z).isLUsed() == false && codex.getCard(v).isLUsed() == false && codex.getCard(z).getAngle(AnglePos.UR).getAttached().getCard().isLUsed() == false && codex.getCard(z).getAngle(AnglePos.UL).getAttached().getCard().getCardType() == CardType.FUNGI
                            && codex.getCard(z).getCardType() == codex.getCard(v).getCardType()) {
                        numOfCompletion++;
                        codex.getCard(z).setlUsed();
                        codex.getCard(z).setlUsed();
                        codex.getCard(v).getAngle(AnglePos.UR).getAttached().getCard().setlUsed();
                    }
                }
                break;


            case PLANT:
                for (Coordinate a : codex.getCards().keySet()) {
                    Coordinate b = new Coordinate(a.getX(), a.getY() + 1);
                    if (codex.getCard(a).isLUsed() == false && codex.getCard(b).isLUsed() == false && codex.getCard(a).getAngle(AnglePos.DL).getAttached().getCard().isLUsed() == false && codex.getCard(a).getAngle(AnglePos.DL).getAttached().getCard().getCardType() == CardType.INSECT
                            && codex.getCard(a).getCardType() == codex.getCard(b).getCardType()) {
                        numOfCompletion++;
                        codex.getCard(a).setlUsed();
                        codex.getCard(a).setlUsed();
                        codex.getCard(b).getAngle(AnglePos.DL).getAttached().getCard().setlUsed();
                    }
                }
                break;

            case FUNGI:
                for (Coordinate c : codex.getCards().keySet()) {
                    Coordinate d = new Coordinate(c.getX(), c.getY() + 1);
                    if (codex.getCard(c).isLUsed() == false && codex.getCard(d).isLUsed() == false && codex.getCard(c).getAngle(AnglePos.DR).getAttached().getCard().isLUsed() == false && codex.getCard(c).getAngle(AnglePos.DR).getAttached().getCard().getCardType() == CardType.PLANT
                            && codex.getCard(c).getCardType() == codex.getCard(d).getCardType()) {
                        numOfCompletion++;
                        codex.getCard(c).setlUsed();
                        codex.getCard(c).setlUsed();
                        codex.getCard(d).getAngle(AnglePos.DR).getAttached().getCard().setlUsed();
                    }
                }
                break;

            default: return 0;
        }
        return numOfCompletion;
    }}

