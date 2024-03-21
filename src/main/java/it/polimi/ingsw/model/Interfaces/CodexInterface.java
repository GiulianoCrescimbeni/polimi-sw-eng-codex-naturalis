package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.InitialCard;
import java.util.ArrayList;

public interface CodexInterface {

    public InitialCard getInitialCard();

    public ArrayList<Integer> getNumOfResources();
    public ArrayList<Card> getCards();
    public void addCard(Card card);

}
