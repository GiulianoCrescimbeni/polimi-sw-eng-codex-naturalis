package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AngleTest extends TestCase {

    private Resource resource = Resource.FUNGI;
    private boolean hidden = false;
    private Angle attached = new Angle(Resource.FEATHER, false, null, null);
    private Card card = new Card(12345, null, CardType.PLANT, false, 12, false, false);
    private Map<AnglePos, Angle> angleMap = new HashMap<AnglePos, Angle>();
    private Angle toTest = new Angle(resource, hidden, null, card);

    @BeforeEach
    public void setup() {
        angleMap.put(AnglePos.UR, new Angle(resource, false, attached, card));
        card.setAngles(angleMap);
    }

    @Test
    @DisplayName("Test Angle Get Resource")
    public void testGetResource() {
        assertEquals(Resource.FUNGI, toTest.getResource());
    }

    @Test
    public void testIsHidden() {
        assertEquals(false, toTest.isHidden());
    }

    @Test
    public void testIsAttached() {
        assertEquals(false, toTest.isAttached());
    }

    @Test
    public void testGetAttached() {
        assertEquals(null, toTest.getAttached());
    }

    @Test
    public void testGetCard() {
        assertEquals(card, toTest.getCard());
    }

    @Test
    public void testSetHidden() {
        toTest.setHidden();
        assertEquals(true, toTest.isHidden());
    }

    @Test
    public void testSetAttached() {
        toTest.setAttached(attached);
        assertEquals(attached, toTest.getAttached());
        assertEquals(true, toTest.isAttached());
    }
}