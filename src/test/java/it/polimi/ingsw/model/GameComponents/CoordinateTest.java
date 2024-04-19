package it.polimi.ingsw.model.GameComponents;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class CoordinateTest extends TestCase {

    int x1 = 10;
    int y1 = 10;
    int x2 = 20;
    int y2 = 20;

    Coordinate toTest = new Coordinate(x1, y1);
    Coordinate other = new Coordinate(x2, y2);
    Coordinate same = new Coordinate(x1, y1);

    @Test
    public void testGetX() {
        assertEquals(x1, toTest.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(y1, toTest.getY());
    }

    @Test
    public void testEquals() {
        assertFalse(toTest.equals(other));
        assertTrue(toTest.equals(same));
        assertTrue(same.equals(toTest));
    }
}