package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;

import java.util.ArrayList;

public class ResourceGoldCard extends GoldCard {

    private Resource resourceType;
    public ResourceGoldCard(int cardID, ArrayList<Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed, ArrayList<Resource> playCondition, Resource resourceType) {
        super(cardID, angles, cardType, isTurned, cardScore, lUsed, dUsed, playCondition);
        this.resourceType = resourceType;
    }

    public Resource getResourceType() {
        return resourceType;
    }
}
