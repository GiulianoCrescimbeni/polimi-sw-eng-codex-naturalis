package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Interfaces.CardInteface;

import java.util.ArrayList;

/**
 * Class that represent the card
 */
public class Card implements CardInteface {

    private int cardID;
    private ArrayList<Angle> angles;
    private CardType cardType;
    private boolean isTurned;
    private int cardScore;
    private boolean lUsed;
    private boolean dUsed;
    private int coordinateX;
    private int coordinateY;


    /**
     * Constructor
     * @param cardID id of the card
     * @param angles arraylist of {@link Angle} for the card
     * @param cardType {@link CardType} of the card
     * @param isTurned parameter that indicates if it is turned or not
     * @param cardScore the score of the card
     * @param lUsed parameter that indicates if the card has been used for an {@link it.polimi.ingsw.model.Goals.LGoal}
     * @param dUsed parameter that indicates if the card has been used for an {@link it.polimi.ingsw.model.Goals.DiagonalGoal}
     * @param coordinateX parameter that indicates the X coordinate of the card
     * @param coordinateY parameter that indicates the Y coordinate of the card
     */
    public Card(int cardID, ArrayList<Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed) {
        this.cardID = cardID;
        this.angles = angles;
        this.cardType = cardType;
        this.isTurned = isTurned;
        this.cardScore = cardScore;
        this.lUsed = lUsed;
        this.dUsed = dUsed;
    }

    /**
     * @return the card ID
     */
    public int getCardID() {
        return this.cardID;
    }

    /**
     * @return the angles of the card
     */
    public ArrayList<Angle> getAngles() {
        return angles;
    }

    /**
     * @return the type of the card
     */
    public CardType getCardType() {
        return cardType;
    }

    /**
     * @return true if the card is turned, false if not
     */
    public boolean isTurned() {
        return this.isTurned;
    }

    /**
     * @return the score of the card
     */
    public int getCardScore() {
        return cardScore;
    }

    /**
     * @return true if it has been used in a LGoal, false if not
     */
    public boolean isLUsed() {
        return this.lUsed;
    }

    /**
     * @return true if it has been used in a DiagonalGoal, false if not
     */
    public boolean isDUsed() {
        return this.dUsed;
    }

    /**
     * @return the X coordinate of the card
     */
    public int getCoordinateX() { return this.coordinateX; }

    /**
     * @return the Y coordinate of the card
     */
    public int getCoordinateY() { return this.coordinateY; }

    /**
     * @param angles the angles of the card
     */
    public void setAngles(ArrayList<Angle> angles) {
        this.angles = angles;
    }

    /**
     * Turn the card
     */
    public void turn() {
        if (this.isTurned) {
            this.isTurned = false;
        } else this.isTurned = true;
    }

    /**
     * Set LUsed to true
     */
    public void setlUsed() {
        this.lUsed = true;
    }

    /**
     * Set DUsed to true
     */
    public void setdUsed() {
        this.dUsed = true;
    }

    /**
     * Set the X coordinate of the card
     */
    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    /**
     * Set the Y coordinate of the card
     */
    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

}
