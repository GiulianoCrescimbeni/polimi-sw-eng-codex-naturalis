package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;

import java.util.ArrayList;

public class Card {

    private int cardID;
    private ArrayList<Angle> angles;
    private CardType cardType;
    private boolean isTurned;
    private int cardScore;
    private boolean lUsed;
    private boolean dUsed;

    public Card(int cardID, ArrayList<Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed) {
        this.cardID = cardID;
        this.angles = angles;
        this.cardType = cardType;
        this.isTurned = isTurned;
        this.cardScore = cardScore;
        this.lUsed = lUsed;
        this.dUsed = dUsed;
    }

    public int getCardID() {
        return this.cardID;
    }

    public ArrayList<Angle> getAngles() {
        return angles;
    }

    public CardType getCardType() {
        return cardType;
    }

    public boolean isTurned() {
        return this.isTurned;
    }

    public int getCardScore() {
        return cardScore;
    }

    public boolean isLUsed() {
        return this.lUsed;
    }

    public boolean isDUsed() {
        return this.dUsed;
    }

    public void setAngles(ArrayList<Angle> angles) {
        this.angles = angles;
    }

    public void turn() {
        if (this.isTurned) {
            this.isTurned = false;
        } else this.isTurned = true;
    }

    public void setlUsed() {
        this.lUsed = true;
    }

    public void setdUsed() {
        this.dUsed = true;
    }
}
