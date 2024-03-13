package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;

import java.util.ArrayList;

public class InitialCard extends Card {

    private ArrayList<Resource> backResources;

    public InitialCard(int cardID, ArrayList<Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed, ArrayList<Resource> backResources) {
        super(cardID, angles, cardType, isTurned, cardScore, lUsed, dUsed);
        this.backResources = backResources;
    }

    public ArrayList<Resource> getBackResources() {
        return this.backResources;
    }
}
