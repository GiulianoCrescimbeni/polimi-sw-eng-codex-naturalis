package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.GameComponents.Angle;

import java.util.ArrayList;

public interface CardInteface {

    public int getCardID();
    public ArrayList<Angle> getAngles();
    public CardType getCardType();
    public boolean isTurned();
    public int getCardScore();

}