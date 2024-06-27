package it.polimi.ingsw.model.GameComponents;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeckTest extends TestCase {

    private Card card1 = new Card();
    private Card card2 = new Card();
    private Card card3 = new Card();
    private Stack<Card> cardStack = new Stack<Card>();
    Deck testDeck = new Deck(3, cardStack);

    @BeforeEach
    public void setup() {
        cardStack.push(card1);
        cardStack.push(card2);
        cardStack.push(card3);
    }

    @Test
    public void testGetNumOfCards() {
        assertEquals(3, testDeck.getNumOfCards());
    }

    @Test
    public void testGetCards() {
        assertEquals(cardStack, testDeck.getCards());
    }

    @Test
    public void testAddCard() {
        Card cardToAdd = new Card();
        testDeck.addCard(cardToAdd);
        assertEquals(4, testDeck.getNumOfCards());
        assertEquals(cardToAdd, cardStack.pop());
    }

    @Test
    public void testPickCard() {
        Card cardPicked = testDeck.pickCard();
        assertEquals(2, testDeck.getNumOfCards());
        assertEquals(card3, cardPicked);
        cardStack.add(card3);
    }

    @Test
    public void testContainsCard() {
        assertTrue(testDeck.containsCard(card1));
        assertTrue(testDeck.containsCard(card2));
        assertTrue(testDeck.containsCard(card3));
    }

    @Test
    public void testDeckShuffle() {
        Stack<Card> stackToShuffle = new Stack<Card>();
        stackToShuffle.addAll(cardStack);
        testDeck.deckShuffle();
        /*assertNotEquals(cardStack.pop(),stackToShuffle.pop());
        assertNotEquals(cardStack.pop(),stackToShuffle.pop());
        assertNotEquals(cardStack.pop(),stackToShuffle.pop());*/
        assertEquals(cardStack.size(), stackToShuffle.size());
    }
}