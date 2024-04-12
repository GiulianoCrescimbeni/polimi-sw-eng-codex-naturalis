package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.InitialCard;
import java.util.ArrayList;
import java.util.Map;
import  it.polimi.ingsw.model.GameComponents.Coordinate;

public interface CodexInterface {

    public InitialCard getInitialCard();

    public ArrayList<Integer> getNumOfResources();
    public Map<Coordinate, Card> getCards();
    public void addCard(Coordinate coordinate, Card card);

}
