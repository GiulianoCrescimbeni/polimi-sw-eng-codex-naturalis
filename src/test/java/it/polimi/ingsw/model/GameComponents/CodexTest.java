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
    ArrayList<Goal> goalsToPick = new ArrayList<Goal>();
    ArrayList<Goal> settedGoalsToPick = new ArrayList<Goal>();
    ArrayList<Resource> playCondition = new ArrayList<Resource>();

    Goal personalGoal = new DiagonalGoal(null, null, 0, 1);
    Map<Resource, Integer> numOfResources = new HashMap<Resource, Integer>();
    Map<Coordinate, Card> cards = new LinkedHashMap<>();
    Map<AnglePos, Angle> initialCardAngles = new HashMap<>();
    Map<AnglePos, Angle> cardAngles = new HashMap<>();
    Map<AnglePos, Angle> goldCardAngles = new HashMap<>();

    Card card1 = new Card(1, null, CardType.INSECT, false, 1, false, false);
    Card card2 = new Card(2, null, CardType.PLANT, false, 1, false, false);
    Card card3 = new Card(3, null, CardType.FUNGI, false, 1, false, false);
    Card cardToPlace = new Card(4, null, CardType.ANIMAL, false, 1, false, false);
    GoldCard goldCardToPlace = new GoldCard(5, null, CardType.FUNGI, false, 2, false, false, playCondition);

    Angle initialCardAngle1 = new Angle(Resource.INSECT, false, null, cardToPlace);
    Angle initialCardAngle2 = new Angle(null, false, null, cardToPlace);
    Angle initialCardAngle3 = new Angle(null, false, null, cardToPlace);
    Angle initialCardAngle4 = new Angle(null, false, null, cardToPlace);
    Angle cardAngle1 = new Angle(Resource.BLANK, false, null, cardToPlace);
    Angle cardAngle2 = new Angle(null, false, null, cardToPlace);
    Angle cardAngle3 = new Angle(null, false, null, cardToPlace);
    Angle cardAngle4 = new Angle(Resource.PLANT, false, null, cardToPlace);
    Angle goldCardAngle1 = new Angle(Resource.FUNGI, false, null, goldCardToPlace);
    Angle goldCardAngle2 = new Angle(null, false, null, goldCardToPlace);
    Angle goldCardAngle3 = new Angle(null, false, null, goldCardToPlace);
    Angle goldCardAngle4 = new Angle(Resource.PLANT, false, null, goldCardToPlace);
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
        goalsToPick.add(new LGoal(null, null, 0, 1));
        goalsToPick.add(new EqualsObjectGoal(null, null, 0, 1));
        goalsToPick.add(new DifferentObjectGoal(null, null, 0, 1));

        settedGoalsToPick.add(new LGoal(null, null, 0, 1));

        numOfResources.put(res1, 1);
        numOfResources.put(res2, 3);
        numOfResources.put(res3, 4);
        numOfResources.put(res4, 2);

        cards.put(c1, card1);
        cards.put(c2, card2);
        cards.put(c3, card3);
        cards.put(c, initialCard);

        initialCardAngles.put(AnglePos.UL, initialCardAngle1);
        initialCardAngles.put(AnglePos.UR, initialCardAngle2);
        initialCardAngles.put(AnglePos.DL, initialCardAngle3);
        initialCardAngles.put(AnglePos.DR, initialCardAngle4);
        initialCard.setAnglesMap(initialCardAngles);

        cardAngles.put(AnglePos.UL, cardAngle1);
        cardAngles.put(AnglePos.UR, cardAngle2);
        cardAngles.put(AnglePos.DL, cardAngle3);
        cardAngles.put(AnglePos.DR, cardAngle4);
        cardToPlace.setAnglesMap(cardAngles);

        goldCardAngles.put(AnglePos.UL, goldCardAngle1);
        goldCardAngles.put(AnglePos.UR, goldCardAngle2);
        goldCardAngles.put(AnglePos.DL, goldCardAngle3);
        goldCardAngles.put(AnglePos.DR, goldCardAngle4);
        goldCardToPlace.setAnglesMap(goldCardAngles);

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
        assertFalse(toTest.getCards().containsValue(cardToPlace));
        toTest.placeCard(new Coordinate(79, 81), cardToPlace);
        assertEquals(3, toTest.getNumOfResources(Resource.INSECT));
        assertEquals(1, toTest.getNumOfResources(Resource.FUNGI));
        assertEquals(3, toTest.getNumOfResources(Resource.PLANT));
        assertTrue(toTest.getCards().containsValue(cardToPlace));
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