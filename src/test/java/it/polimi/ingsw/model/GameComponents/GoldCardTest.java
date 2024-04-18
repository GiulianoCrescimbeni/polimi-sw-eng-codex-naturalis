package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.Resource;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GoldCardTest extends TestCase {

    ArrayList<Resource> playCondition = new ArrayList<>();
    GoldCard testCard = new GoldCard(1, null, null, false, 0, false, false, playCondition);

    @BeforeEach
    public void setup() {
        playCondition.add(Resource.ANIMAL);
        playCondition.add(Resource.FUNGI);
        playCondition.add(Resource.INSECT);
        playCondition.add(Resource.PLANT);
    }

    @Test
    public void testGetPlayCondition() {
        assertEquals(playCondition, testCard.getPlayCondition());
    }
}