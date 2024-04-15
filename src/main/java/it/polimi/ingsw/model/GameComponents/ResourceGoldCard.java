package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class that represent the gold card with the resource score type
 */
public class ResourceGoldCard extends GoldCard {

    private Resource resourceType;

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
    public ResourceGoldCard(int cardID, Map<AnglePos, Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed, ArrayList<Resource> playCondition, Resource resourceType) {
        super(cardID, angles, cardType, isTurned, cardScore, lUsed, dUsed, playCondition);
        this.resourceType = resourceType;
    }

    /**
     * @return the resource for the score calculation
     */
    public Resource getResourceType() {
        return resourceType;
    }
}
