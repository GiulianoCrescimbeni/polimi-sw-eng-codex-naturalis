package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Interfaces.DeckInterface;

import java.util.ArrayList;
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
     * Pick a random {@link Card} from the deck and remove it
     * @return card picked from the deck
     */
    public Card pickRandomCard() {
        Random r = new Random();
        int cardToPick = r.nextInt(numOfCards + 1);
        Card cardPicked = cards.get(cardToPick);
        cards.remove(cardToPick);
        return cardPicked;
    }

    /**
     * Pick a specific {@link Card} from the deck, identified by its position in it, and remove it
     * @param pos the position of the card in the deck
     * @return the card picked
     */
    public Card pickCard(int pos) {
        Card cardPicked = cards.get(pos);
        cards.remove(pos);
        return cardPicked;
    }

    /**
     * Add a {@link Card} in the deck
     * @param card the card to add in the deck
     */
    public void addCard(Card card) {
        cards.add(card);
    }

    public static Card pickCard() {
        Card popped = Deck.cards.pop();
        return popped;
    }

}
