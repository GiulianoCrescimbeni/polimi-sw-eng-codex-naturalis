package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;

import java.util.ArrayList;

public class AngleGoldCard extends GoldCard {
    public AngleGoldCard(int cardID, ArrayList<Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed, ArrayList<Resource> playCondition) {
        super(cardID, angles, cardType, isTurned, cardScore, lUsed, dUsed, playCondition);
    }
}
