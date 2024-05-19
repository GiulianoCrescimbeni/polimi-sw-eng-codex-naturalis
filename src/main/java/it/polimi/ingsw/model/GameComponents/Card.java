package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Data.AngleMapDeserializer;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Interfaces.CardInteface;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that represents the card
 */
public class Card implements CardInteface,Serializable {

    private int cardID;
    private Map<AnglePos, Angle> anglesMap;
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
        this.anglesMap = angles;
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
    public Map<AnglePos, Angle> getAnglesMap() {
        return anglesMap;
    }

    public Angle getAngle(AnglePos anglePos) { return getAnglesMap().get(anglePos); }

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
     * @param anglesMap the angles of the card
     */
    public void setAnglesMap(Map<AnglePos, Angle> anglesMap) {
        this.anglesMap = anglesMap;
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
