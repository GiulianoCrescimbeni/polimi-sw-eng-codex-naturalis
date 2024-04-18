package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Interfaces.DeckInterface;

import java.util.Collections;
import java.util.Stack;

/**
 * Class for cards deck
 */
public class Deck implements DeckInterface {
    private int numOfCards;
    private Stack<Card> cards;

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
     * Constructor
     * @param numOfCards the number of cards in the deck
     * Sets the cards stack to null
     */
    public Deck(int numOfCards) {
        this.numOfCards = numOfCards;
    }

    /**
     * @return the number of cards in the deck
     */
    public int getNumOfCards() { return this.numOfCards; }

    /**
     * @return stack of cards in the deck
     */
    public Stack<Card> getCards() { return this.cards; }

    /**
     * Add a {@link Card} in the deck
     * @param card the card to add in the deck
     */
    public void addCard(Card card) {
        numOfCards++;
        this.cards.push(card);
    }

    /**
     * Take the card at the top of the deck
     */
    public Card pickCard() {
        numOfCards--;
        return this.cards.pop();
    }

    /**
     * Verify if there are cards in the deck
     */
    public boolean containsCard(Card card) {
        return this.cards.contains(card);
    }

    /**
     * Shuffle the resources deck
     */
    public void deckShuffle(){
        Collections.shuffle(cards);
    }
}
