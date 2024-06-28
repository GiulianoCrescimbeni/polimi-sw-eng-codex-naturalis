package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourcesGoalTest {
@Test
    void checktestFUNGI(){

    Goal testGoal = new ResourcesGoal(null, CardType.FUNGI,3,0);

    Map<Resource, Integer> MapNR = new HashMap<>();
    MapNR.put(Resource.FUNGI, 6);
    MapNR.put(Resource.INSECT, 0);
    MapNR.put(Resource.PLANT, 0);
    MapNR.put(Resource.ANIMAL, 0);
    MapNR.put(Resource.FEATHER, 0);
    MapNR.put(Resource.SCROLL, 0);
    MapNR.put(Resource.JAR, 0);
    Codex COD = new Codex(null, testGoal, MapNR, null);

    testGoal.draw();
    assertEquals(2, testGoal.check(COD));
}
    @Test
    void checktestANIMAL(){

        Goal testGoal = new ResourcesGoal(null, CardType.ANIMAL,3,0);

        Map<Resource, Integer> MapNR = new HashMap<>();
        MapNR.put(Resource.FUNGI, 0);
        MapNR.put(Resource.INSECT, 0);
        MapNR.put(Resource.PLANT, 0);
        MapNR.put(Resource.ANIMAL, 6);
        MapNR.put(Resource.FEATHER, 0);
        MapNR.put(Resource.SCROLL, 0);
        MapNR.put(Resource.JAR, 0);
        Codex COD = new Codex(null, testGoal, MapNR, null);

        testGoal.draw();
        assertEquals(2, testGoal.check(COD));
    }
    @Test
    void checktestINSECT(){

        Goal testGoal = new ResourcesGoal(null, CardType.INSECT,3,0);

        Map<Resource, Integer> MapNR = new HashMap<>();
        MapNR.put(Resource.FUNGI, 0);
        MapNR.put(Resource.INSECT, 9);
        MapNR.put(Resource.PLANT, 0);
        MapNR.put(Resource.ANIMAL, 0);
        MapNR.put(Resource.FEATHER, 0);
        MapNR.put(Resource.SCROLL, 0);
        MapNR.put(Resource.JAR, 0);
        Codex COD = new Codex(null, testGoal, MapNR, null);

        testGoal.draw();
        assertEquals(3, testGoal.check(COD));
    }
    @Test
    void checktestPLANT(){

        Goal testGoal = new ResourcesGoal(null, CardType.PLANT,3,0);

        Map<Resource, Integer> MapNR = new HashMap<>();
        MapNR.put(Resource.FUNGI, 0);
        MapNR.put(Resource.INSECT, 0);
        MapNR.put(Resource.PLANT, 3);
        MapNR.put(Resource.ANIMAL, 0);
        MapNR.put(Resource.FEATHER, 0);
        MapNR.put(Resource.SCROLL, 0);
        MapNR.put(Resource.JAR, 0);
        Codex COD = new Codex(null, testGoal, MapNR, null);

        testGoal.draw();
        assertEquals(1, testGoal.check(COD));
    }
//
}

