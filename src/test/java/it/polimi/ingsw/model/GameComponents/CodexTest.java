package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.Goals.*;
import it.polimi.ingsw.model.GameComponents.Deck;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CodexTest extends TestCase {

    InitialCard initialCard = new InitialCard(12345, null, CardType.PLANT, false, 15, false, false, null);
    int score = 12;
    ArrayList<Goal> goalsToPick = new ArrayList<Goal>();
    ArrayList<Goal> settedGoalsToPick = new ArrayList<Goal>();
    Goal personalGoal = new DiagonalGoal();
    Map<Resource, Integer> numOfResources = new HashMap<Resource, Integer>();
    Map<Coordinate, Card> cards = new HashMap<Coordinate, Card>();
    Deck cardsDeck = new Deck(5);

    Card card1 = new Card(1, null, CardType.INSECT, false, 1, false, false);
    Card card2 = new Card(2, null, CardType.PLANT, false, 1, false, false);
    Card card3 = new Card(3, null, CardType.FUNGI, false, 1, false, false);

    Card toPlace = new Card(4, null, CardType.ANIMAL, false, 1, false, false);
    GoldCard goldCardToPlace = new GoldCard(5, null, CardType.FUNGI, false, 2, false, false, null);

    Coordinate c = new Coordinate(80, 80);
    Coordinate c1 = new Coordinate(79, 80);
    Coordinate c2 = new Coordinate(81, 80);
    Coordinate c3 = new Coordinate(82, 80);

    Resource res1 = Resource.FUNGI;
    Resource res2 = Resource.JAR;
    Resource res3 = Resource.INSECT;

    Codex toTest = new Codex(initialCard, goalsToPick, personalGoal);

    @BeforeEach
    public void setup() {
        goalsToPick.add(new LGoal());
        goalsToPick.add(new EqualsObjectGoal());
        goalsToPick.add(new DifferentObjectGoal());

        settedGoalsToPick.add(new LGoal());

        numOfResources.put(res1, new Integer(1));
        numOfResources.put(res2, new Integer(3));
        numOfResources.put(res3, new Integer(4));

        cards.put(c1, card1);
        cards.put(c2, card2);
        cards.put(c3, card3);
    }

    @Test
    public void testGetInitialCard() {
        assertEquals(initialCard, toTest.getInitialCard());
    }

    @Test
    public void testGetScore() {
        assertEquals(0, toTest.getScore());
    }

    @Test
    public void testGetGoalsToPick() {
        assertEquals(goalsToPick, toTest.getGoalsToPick());
    }

    @Test
    public void testGetPersonalGoal() {
        assertEquals(personalGoal, toTest.getPersonalGoal());
    }

    @Test
    public void testGetNumOfResources() {
        assertEquals(1, toTest.getNumOfResources(Resource.FUNGI));
        assertEquals(3, toTest.getNumOfResources(Resource.JAR));
        assertEquals(4, toTest.getNumOfResources(Resource.INSECT));
    }

    @Test
    public void testGetCards() {
        assertEquals(cards, toTest.getCards());
    }

    @Test
    public void testGetCard() {
        assertEquals(toTest.getCard(c1), card1);
        assertEquals(toTest.getCard(c2), card2);
        assertEquals(toTest.getCard(c3), card3);
    }

    @Test
    public void testGetCardsDeck() {
        assertEquals(cardsDeck, toTest.getCardsDeck());
    }

    @Test
    public void testSetGoalsToPick() {
        toTest.setGoalsToPick(settedGoalsToPick);
        assertEquals(settedGoalsToPick, toTest.getGoalsToPick());
    }

    @Test
    public void testIncrementScore() {
        toTest.incrementScore(3);
        assertEquals(3, toTest.getScore());
    }

    @Test
    public void testIncrementNumOfResources() {
        toTest.incrementNumOfResources(res1, 1);
        toTest.incrementNumOfResources(res2, 1);
        toTest.incrementNumOfResources(res3, 1);

        assertEquals(2, toTest.getNumOfResources(res1));
        assertEquals(4, toTest.getNumOfResources(res2));
        assertEquals(5, toTest.getNumOfResources(res3));
    }

    @Test
    public void testDecreaseNumOfResources() {
        toTest.decreaseNumOfResources(res1, 1);
        toTest.decreaseNumOfResources(res2, 1);
        toTest.decreaseNumOfResources(res3, 1);

        assertEquals(0, toTest.getNumOfResources(res1));
        assertEquals(2, toTest.getNumOfResources(res2));
        assertEquals(3, toTest.getNumOfResources(res3));
    }

    @Test
    public void testPickPersonalGoal() {
        assertEquals(personalGoal, toTest.getPersonalGoal());
    }

    @Test
    public void testPlaceCard() throws IllegalCoordinatesException, IllegalCardPlacementException {
        assertFalse(toTest.getCards().containsValue(toPlace));
        toTest.placeCard(new Coordinate(84, 80), toPlace);
        assertTrue(toTest.getCards().containsValue(toPlace));
    }

    @Test
    public void testPlaceGoldCard() throws IllegalCoordinatesException, IllegalCardPlacementException {
        assertFalse(toTest.getCards().containsValue(goldCardToPlace));
        toTest.placeGoldCard(new Coordinate(85, 80), goldCardToPlace);
        assertTrue(toTest.getCards().containsValue(goldCardToPlace));
    }

    @Test
    public void testSetInitialCard() {
        toTest.setInitialCard(initialCard);
        assertEquals(initialCard, toTest.getInitialCard());
        assertEquals(initialCard, toTest.getCard(c));
        assertTrue(toTest.getCards().containsValue(initialCard));
    }
}