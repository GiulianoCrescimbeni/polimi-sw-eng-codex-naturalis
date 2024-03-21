package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;

public interface DeckInterface {

    public int getNumOfCards();
    public ArrayList<Card> getCards();
    public Card pickRandomCard();
    public Card pickCard(int pos);

}
