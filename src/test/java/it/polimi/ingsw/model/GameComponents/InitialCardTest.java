package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.Resource;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static it.polimi.ingsw.model.Enumerations.AnglePos.*;

public class InitialCardTest extends TestCase {

    ArrayList<Resource> backResources = new ArrayList<Resource>();
    private InitialCard testCard = new InitialCard(1, null, null, false, 0, false, false, backResources);
    Angle a1 = new Angle(null, false, null, null);
    Angle a2 = new Angle(null, false, null, null);
    Angle a3 = new Angle(null, false, null, null);
    Angle a4 = new Angle(null, false, null, null);

    Map<AnglePos,Angle> m1 = new HashMap<>();


    @BeforeEach
    public void setup() {
        backResources.add(Resource.PLANT);
        backResources.add(Resource.INSECT);
        backResources.add(Resource.FUNGI);
        backResources.add(Resource.ANIMAL);
        m1.put(UL,a1);
        m1.put(UR,a2);
        m1.put(DL,a3);
        m1.put(DR,a4);

    }

    @Test
    public void testGetBackResources() {
        assertEquals(backResources, testCard.getBackResources());
    }
    @Test
    public void testsetBackAngles(){
        testCard.setBackAngles(m1);
        assertEquals(this.m1,testCard.getBackAngles());

    }

}