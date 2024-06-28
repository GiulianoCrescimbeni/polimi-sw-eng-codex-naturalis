package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.GameComponents.Angle;

import java.util.ArrayList;
import java.util.Map;

/**
 * The interface for the {@link it.polimi.ingsw.model.GameComponents.Card} game component
 */
public interface CardInteface {

    /**
     * @return the card ID
     */
    public int getCardID();

    /**
     * @return the angles of the card
     */
    public Map<AnglePos,Angle> getAnglesMap();

    /**
     * @return the type of the card
     */
    public CardType getCardType();

    /**
     * @return true if the card is turned, false if not
     */
    public boolean isTurned();

    /**
     * @return the score of the card
     */
    public int getCardScore();

}