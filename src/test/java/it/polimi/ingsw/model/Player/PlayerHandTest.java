package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.GameComponents.Card;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PlayerHandTest extends TestCase {

    Card card1;
    Card card2;
    Card card3;
    Card card4;
    ArrayList<Card> cards = new ArrayList<>();
    PlayerHand playerHand = new PlayerHand(cards);

    @BeforeEach
    public void setUp() {
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
    }

    @Test
    public void testGetCards() {
        assertEquals(cards, playerHand.getCards());
    }

    @Test
    public void testAddCard() {
        playerHand.addCard(card4);
        assertEquals(4, playerHand.getCards().size());
        cards.remove(card4);
    }

    @Test
    public void testRemoveCard() {
        playerHand.removeCard(card1);
        assertEquals(2, playerHand.getCards().size());
        playerHand.addCard(card1);
    }
}