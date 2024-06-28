package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The interface for the {@link it.polimi.ingsw.model.GameComponents.Deck} game component
 */
public interface DeckInterface {

    /**
     * @return the number of cards in the deck
     */
    public int getNumOfCards();

    /**
     * @return the stack of {@link Card} in the deck
     */
    public Stack<Card> getCards();

}
