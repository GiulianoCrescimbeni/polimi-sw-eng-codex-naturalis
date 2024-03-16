package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;

import java.util.ArrayList;

/**
 * Class that represent the GoldCard
 */
public class GoldCard extends Card {

    private ArrayList<Resource> playCondition;

    /**
     * Constructor
     * @param cardID id of the card
     * @param angles arraylist of {@link Angle} for the card
     * @param cardType {@link CardType} of the card
     * @param isTurned parameter that indicates if it is turned or not
     * @param cardScore the score of the card
     * @param lUsed parameter that indicates if the card has been used for an {@link it.polimi.ingsw.model.Goals.LGoal}
     * @param dUsed parameter that indicates if the card has been used for an {@link it.polimi.ingsw.model.Goals.DiagonalGoal}
     */
    public GoldCard(int cardID, ArrayList<Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed, ArrayList<Resource> playCondition) {
        super(cardID, angles, cardType, isTurned, cardScore, lUsed, dUsed);
        this.playCondition = playCondition;
    }

    /**
     * @return the resources that the codex need to have to play the card
     */
    public ArrayList<Resource> getPlayCondition() { return playCondition; }
}
