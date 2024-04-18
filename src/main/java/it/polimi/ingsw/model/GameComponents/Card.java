package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Interfaces.CardInteface;

import java.util.ArrayList;
import java.util.Map;

/**
 * Class that represents the card
 */
public class Card implements CardInteface {

    private int cardID;
    private Map<AnglePos, Angle> angles;
    private CardType cardType;
    private boolean isTurned;
    private int cardScore;
    private boolean lUsed;
    private boolean dUsed;

    /**
     * Constructor
     * @param cardID id of the card
     * @param angles map of <{@link AnglePos}, {@link Angle}> for the card
     * @param cardType {@link CardType} of the card
     * @param isTurned parameter that indicates if it is turned or not
     * @param cardScore the score of the card
     * @param lUsed parameter that indicates if the card has been used for an {@link it.polimi.ingsw.model.Goals.LGoal}
     * @param dUsed parameter that indicates if the card has been used for an {@link it.polimi.ingsw.model.Goals.DiagonalGoal}
     */
    public Card(int cardID, Map<AnglePos, Angle> angles, CardType cardType, boolean isTurned, int cardScore, boolean lUsed, boolean dUsed) {
        this.cardID = cardID;
        this.angles = angles;
        this.cardType = cardType;
        this.isTurned = isTurned;
        this.cardScore = cardScore;
        this.lUsed = lUsed;
        this.dUsed = dUsed;
    }

    /**
     * Constructor
     */
    public Card() {}

    /**
     * @return the card ID
     */
    public int getCardID() {
        return this.cardID;
    }

    /**
     * @return the angles of the card
     */
    public Map<AnglePos, Angle> getAngles() {
        return angles;
    }

    public Angle getAngle(AnglePos anglePos) { return getAngles().get(anglePos); }

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
     * @param angles the angles of the card
     */
    public void setAngles(Map<AnglePos, Angle> angles) {
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

}
