package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.Resource;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class InitialCardTest extends TestCase {

    ArrayList<Resource> backResources = new ArrayList<Resource>();
    private InitialCard testCard = new InitialCard(1, null, null, false, 0, false, false, backResources);

    @BeforeEach
    public void setup() {
        backResources.add(Resource.PLANT);
        backResources.add(Resource.INSECT);
        backResources.add(Resource.FUNGI);
        backResources.add(Resource.ANIMAL);
    }

    @Test
    public void testGetBackResources() {
        assertEquals(backResources, testCard.getBackResources());
    }
}