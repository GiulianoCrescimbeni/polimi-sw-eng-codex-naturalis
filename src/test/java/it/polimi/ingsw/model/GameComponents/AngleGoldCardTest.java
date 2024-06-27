package it.polimi.ingsw.model.GameComponents;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class AngleGoldCardTest extends TestCase {

    AngleGoldCard testCard = new AngleGoldCard(1, null, null, false, 1, false, false, null);
    @Test
    public void testAngleCoveredFunctions() {
        assertEquals(0, testCard.getNumOfAnglesCovered());
        testCard.addAngleCovered();
        assertEquals(1, testCard.getNumOfAnglesCovered());
        testCard.addAngleCovered();
        testCard.addAngleCovered();
        assertEquals(3, testCard.getNumOfAnglesCovered());
    }
    @Test
    public void testsetNumOfAnglesCovered(){
        testCard.setNumOfAnglesCovered(3);
        assertEquals(3, testCard.getNumOfAnglesCovered());

    }
}