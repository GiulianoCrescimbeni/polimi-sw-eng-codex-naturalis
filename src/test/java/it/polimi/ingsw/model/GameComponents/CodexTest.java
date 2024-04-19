package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.Goals.*;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CodexTest extends TestCase {

    InitialCard initialCard = new InitialCard(12345, null, CardType.PLANT, false, 15, false, false, null);
    int score = 12;
    ArrayList<Goal> goalsToPick = new ArrayList<Goal>();
    ArrayList<Goal> settedGoalsToPick = new ArrayList<Goal>();
    ArrayList<Resource> playCondition = new ArrayList<Resource>();

    Goal personalGoal = new DiagonalGoal();
    Map<Resource, Integer> numOfResources = new HashMap<Resource, Integer>();
    Map<Coordinate, Card> cards = new LinkedHashMap<>();
    Map<AnglePos, Angle> iAngles = new HashMap<>();
    Map<AnglePos, Angle> angles = new HashMap<>();
    Map<AnglePos, Angle> anglesGold = new HashMap<>();


    Card card1 = new Card(1, null, CardType.INSECT, false, 1, false, false);
    Card card2 = new Card(2, null, CardType.PLANT, false, 1, false, false);
    Card card3 = new Card(3, null, CardType.FUNGI, false, 1, false, false);
    Card toPlace = new Card(4, null, CardType.ANIMAL, false, 1, false, false);
    GoldCard goldCardToPlace = new GoldCard(5, null, CardType.FUNGI, false, 2, false, false, playCondition);

    Angle i1 = new Angle(Resource.INSECT, false, null, toPlace);
    Angle i2 = new Angle(null, false, null, toPlace);
    Angle i3 = new Angle(null, false, null, toPlace);
    Angle i4 = new Angle(null, false, null, toPlace);
    Angle a1 = new Angle(Resource.FUNGI, false, null, toPlace);
    Angle a2 = new Angle(null, false, null, toPlace);
    Angle a3 = new Angle(null, false, null, toPlace);
    Angle a4 = new Angle(Resource.PLANT, false, null, toPlace);
    Angle b1 = new Angle(Resource.FUNGI, false, null, goldCardToPlace);
    Angle b2 = new Angle(null, false, null, goldCardToPlace);
    Angle b3 = new Angle(null, false, null, goldCardToPlace);
    Angle b4 = new Angle(Resource.PLANT, false, null, goldCardToPlace);
    Coordinate c = new Coordinate(80, 80);
    Coordinate c1 = new Coordinate(79, 80);
    Coordinate c2 = new Coordinate(85, 79);
    Coordinate c3 = new Coordinate(82, 80);

    Resource res1 = Resource.FUNGI;
    Resource res2 = Resource.ANIMAL;
    Resource res3 = Resource.INSECT;
    Resource res4 = Resource.PLANT;
    Codex toTest = new Codex(goalsToPick, personalGoal, numOfResources, cards);

    @BeforeEach
    public void setup() {
        goalsToPick.add(new LGoal());
        goalsToPick.add(new EqualsObjectGoal());
        goalsToPick.add(new DifferentObjectGoal());

        settedGoalsToPick.add(new LGoal());

        numOfResources.put(res1, new Integer(1));
        numOfResources.put(res2, new Integer(3));
        numOfResources.put(res3, new Integer(4));
        numOfResources.put(res4, new Integer(2));

        cards.put(c1, card1);
        cards.put(c2, card2);
        cards.put(c3, card3);
        cards.put(c, initialCard);

        iAngles.put(AnglePos.UL, i1);
        iAngles.put(AnglePos.UR, i2);
        iAngles.put(AnglePos.DL, i3);
        iAngles.put(AnglePos.DR, i4);
        initialCard.setAngles(iAngles);

        angles.put(AnglePos.UL, a1);
        angles.put(AnglePos.UR, a2);
        angles.put(AnglePos.DL, a3);
        angles.put(AnglePos.DR, a4);
        toPlace.setAngles(angles);

        anglesGold.put(AnglePos.UL, b1);
        anglesGold.put(AnglePos.UR, b2);
        anglesGold.put(AnglePos.DL, b3);
        anglesGold.put(AnglePos.DR, b4);
        goldCardToPlace.setAngles(anglesGold);

        playCondition.add(Resource.ANIMAL);
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
        assertEquals(3, toTest.getNumOfResources(Resource.ANIMAL));
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
        assertEquals(4, toTest.getNumOfResources(Resource.INSECT));
        assertEquals(1, toTest.getNumOfResources(Resource.FUNGI));
        assertEquals(2, toTest.getNumOfResources(Resource.PLANT));
        assertFalse(toTest.getCards().containsValue(toPlace));
        toTest.placeCard(new Coordinate(79, 81), toPlace);
        assertEquals(3, toTest.getNumOfResources(Resource.INSECT));
        assertEquals(2, toTest.getNumOfResources(Resource.FUNGI));
        assertEquals(3, toTest.getNumOfResources(Resource.PLANT));
        assertTrue(toTest.getCards().containsValue(toPlace));
    }

    @Test
    public void testPlaceGoldCard() throws IllegalCoordinatesException, IllegalCardPlacementException {
        assertEquals(4, toTest.getNumOfResources(Resource.INSECT));
        assertEquals(1, toTest.getNumOfResources(Resource.FUNGI));
        assertEquals(2, toTest.getNumOfResources(Resource.PLANT));
        assertEquals(0, toTest.getScore());
        assertFalse(toTest.getCards().containsValue(goldCardToPlace));
        toTest.placeGoldCard(new Coordinate(79, 81), goldCardToPlace);
        assertEquals(3, toTest.getNumOfResources(Resource.INSECT));
        assertEquals(2, toTest.getNumOfResources(Resource.FUNGI));
        assertEquals(3, toTest.getNumOfResources(Resource.PLANT));
        assertEquals(2, toTest.getScore());
        assertTrue(toTest.getCards().containsValue(goldCardToPlace));
    }

    @Test
    public void testSetInitialCard() {
        toTest.setInitialCard(initialCard);
        assertEquals(initialCard, toTest.getCard(c));
        assertTrue(toTest.getCards().containsValue(initialCard));
    }
}