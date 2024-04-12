package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Interfaces.DeckInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Class for cards deck
 */
public class Deck implements DeckInterface {
    private int numOfCards;
    private static Stack<Card> cards;

    /**
     * Constructor
     * @param numOfCards the number of cards in the deck
     */
    public Deck(int numOfCards) {
        this.numOfCards = numOfCards;
    }

    /**
     * Constructor
     * @param numOfCards the number of cards in the deck
     * @param cards the stack of cards of the deck
     */
    public Deck(int numOfCards, Stack<Card> cards) {
        this.numOfCards = numOfCards;
        this.cards = cards;
    }

    /**
     * @return the number of cards in the deck
     */
    public int getNumOfCards() { return numOfCards; }

    /**
     * @return stack of cards in the deck
     */
    public Stack<Card> getCards() { return cards; }

    /**
     * Add a {@link Card} in the deck
     * @param card the card to add in the deck
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    /**
     * Take the card at the top of the deck
     */
    public static Card pickCard() {
        Card popped = Deck.cards.pop();
        return popped;
    }

    /**
     * Verify if there are cards in the deck
     */
    public boolean containsCard(Card card) {
        return cards.contains(card);
    }

    /**
     * Shuffle the resources deck
     */
    public void deckShuffle(){
        Collections.shuffle(cards);
    }
}
