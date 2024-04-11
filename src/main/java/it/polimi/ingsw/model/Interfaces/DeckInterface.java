package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;
import java.util.Stack;

public interface DeckInterface {

    public int getNumOfCards();
    public Stack<Card> getCards();
    public Card pickRandomCard();
    public Card pickCard(int pos);

}
