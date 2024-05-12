package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class CardTest extends TestCase {

    Angle a1 = new Angle(null, false, null, null);
    Angle a2 = new Angle(null, false, null, null);
    Angle a3 = new Angle(null, false, null, null);
    Angle a4 = new Angle(null, false, null, null);

    Map<AnglePos, Angle> angles = new HashMap<AnglePos, Angle>();
    Card testCard = new Card(1, angles, CardType.ANIMAL, false, 1, false, false);

    @BeforeEach
    public void setup() {
        angles.put(AnglePos.UL, a1);
        angles.put(AnglePos.UR, a2);
        angles.put(AnglePos.DL, a3);
        angles.put(AnglePos.DR, a4);
    }

    @Test
    public void testGetCardID() {
        assertEquals(1, testCard.getCardID());
    }

    @Test
    public void testGetAngles() {
        assertEquals(angles, testCard.getAnglesMap());
    }

    @Test
    public void testGetAngle() {
        assertEquals(a1, testCard.getAngle(AnglePos.UL));
        assertEquals(a2, testCard.getAngle(AnglePos.UR));
        assertEquals(a3, testCard.getAngle(AnglePos.DL));
        assertEquals(a4, testCard.getAngle(AnglePos.DR));
    }

    @Test
    public void testGetCardType() {
        assertEquals(CardType.ANIMAL, testCard.getCardType());
    }

    @Test
    public void testIsTurned() {
        assertFalse(testCard.isTurned());
    }

    @Test
    public void testGetCardScore() {
        assertEquals(1, testCard.getCardScore());
    }

    @Test
    public void testIsLUsed() {
        assertFalse(testCard.isLUsed());
    }

    @Test
    public void testIsDUsed() {
        assertFalse(testCard.isDUsed());
    }

    @Test
    public void testSetAngles() {
        Angle a5 = new Angle(null, false, null, null);
        Angle a6 = new Angle(null, false, null, null);
        Angle a7 = new Angle(null, false, null, null);
        Angle a8 = new Angle(null, false, null, null);
        Map<AnglePos, Angle> mapTest = new HashMap<>();
        mapTest.put(AnglePos.UL, a5);
        mapTest.put(AnglePos.UR, a6);
        mapTest.put(AnglePos.DL, a7);
        mapTest.put(AnglePos.DR, a8);
        testCard.setAnglesMap(mapTest);
        assertEquals(a5, testCard.getAngle(AnglePos.UL));
        assertEquals(a6, testCard.getAngle(AnglePos.UR));
        assertEquals(a7, testCard.getAngle(AnglePos.DL));
        assertEquals(a8, testCard.getAngle(AnglePos.DR));
    }

    @Test
    public void testTurn() {
        testCard.turn();
        assertTrue(testCard.isTurned());
    }

    @Test
    public void testSetlUsed() {
        testCard.setlUsed();
        assertTrue(testCard.isLUsed());
    }

    @Test
    public void testSetdUsed() {
        testCard.setdUsed();
        assertTrue(testCard.isDUsed());
    }
}