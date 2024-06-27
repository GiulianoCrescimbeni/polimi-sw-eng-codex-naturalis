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

    Coordinate co1 = new Coordinate(40,40);
    Coordinate co2 = new Coordinate(39,41);
    Coordinate co3 = new Coordinate(39,43);
    Coordinate co4 = new Coordinate(40,44);
    Coordinate co5 = new Coordinate(39,45);
    Coordinate co6 = new Coordinate(39,47);

    Map<AnglePos, Angle> Map1 = new HashMap<>();
    Map<AnglePos, Angle> Map2 = new HashMap<>();
    Map<AnglePos, Angle> Map3 = new HashMap<>();
    Map<AnglePos, Angle> Map4 = new HashMap<>();
    Map<AnglePos, Angle> Map5 = new HashMap<>();
    Map<AnglePos, Angle> Map6 = new HashMap<>();

    Map<Coordinate, Card> MapCC = new HashMap<>();

    Card c1 = new Card(1, Map1,CardType.FUNGI, false , 0,false,false);
    Card c2 = new Card(2, Map2, CardType.FUNGI, false, 0, false, false);
    Card c3 = new Card(3, Map3, CardType.FUNGI, false, 0, false, false);
    Card c4 = new Card(4, Map4,CardType.FUNGI, false , 0,false,false);
    Card c5 = new Card(5, Map5, CardType.FUNGI, false, 0, false, false);
    Card c6 = new Card(6, Map6, CardType.FUNGI, false, 0, false, false);

    Angle a1 = new Angle(null, true, null, c1);
    Angle a1_2 = new Angle(null, false, null, c1);
    Angle a1_3 = new Angle(null, false, null, c1);
    Angle a1_4 = new Angle(null, false, null, c1);

    Angle a2 = new Angle(null, false, null, c2);
    Angle a2_2 = new Angle(null, false, null, c2);
    Angle a2_3 = new Angle(null, false, null, c2);
    Angle a2_4 = new Angle(null, false, null, c2);

    Angle a3 = new Angle(null, false, null, c3);
    Angle a3_2 = new Angle(null, false, null, c3);
    Angle a3_3 = new Angle(null, false, null, c3);
    Angle a3_4 = new Angle(null, false, null, c3);

    Angle a4 = new Angle(null, false, null, c4);
    Angle a4_2 = new Angle(null, false, null, c4);
    Angle a4_3 = new Angle(null, false, null, c4);
    Angle a4_4 = new Angle(null, false, null, c4);

    Angle a5 = new Angle(null, false, null, c5);
    Angle a5_2 = new Angle(null, false, null, c5);
    Angle a5_3 = new Angle(null, false, null, c5);
    Angle a5_4 = new Angle(null, false, null, c5);

    Angle a6 = new Angle(null, false, null, c6);
    Angle a6_2 = new Angle(null, false, null, c6);
    Angle a6_3 = new Angle(null, false, null, c6);
    Angle a6_4 = new Angle(null, false, null, c6);

    a1_3.setAttached(a2);
    a2.setAttached(a1_3);
    a3_3.setAttached(a4_2);
    a4_2.setAttached(a3_3);
    a5.setAttached(a4_4);
    a4_4.setAttached(a5);

    Map1.put(AnglePos.DR, a1);
    Map1.put(AnglePos.DL, a1_2);
    Map1.put(AnglePos.UR, a1_3);
    Map1.put(AnglePos.UL, a1_4);

    Map2.put(AnglePos.DR, a2);
    Map2.put(AnglePos.DL, a2_2);
    Map2.put(AnglePos.UR, a2_3);
    Map2.put(AnglePos.UL, a2_4);

    Map3.put(AnglePos.DR,a3);
    Map3.put(AnglePos.DL,a3_2);
    Map3.put(AnglePos.UR,a3_3);
    Map3.put(AnglePos.UL,a3_4);

    Map4.put(AnglePos.DR,a4);
    Map4.put(AnglePos.DL,a4_2);
    Map4.put(AnglePos.UR,a4_3);
    Map4.put(AnglePos.UL,a4_4);

    Map5.put(AnglePos.DR,a5);
    Map5.put(AnglePos.DL,a5_2);
    Map5.put(AnglePos.UR,a5_3);
    Map5.put(AnglePos.UL,a5_4);

    Map6.put(AnglePos.DR,a6);
    Map6.put(AnglePos.DL,a6_2);
    Map6.put(AnglePos.UR,a6_3);
    Map6.put(AnglePos.UL,a6_4);

    MapCC.put(co1,c1);
    MapCC.put(co2,c2);
    MapCC.put(co3,c3);

    Map<Resource, Integer> MapNR = new HashMap<>();
    Codex COD = new Codex(null, testGoal, MapNR, MapCC);

    MapCC.put(co5,c5);
    MapCC.put(co4,c4);
    MapCC.put(co6,c6);

    testGoal.draw();
    assertEquals(2, testGoal.check(COD));
}
    @Test
    void checktestANIMAL(){

        Goal testGoal = new ResourcesGoal(null, CardType.ANIMAL,3,0);

        Coordinate co1 = new Coordinate(40,40);
        Coordinate co2 = new Coordinate(39,41);
        Coordinate co3 = new Coordinate(39,43);
        Coordinate co4 = new Coordinate(40,44);
        Coordinate co5 = new Coordinate(39,45);
        Coordinate co6 = new Coordinate(39,47);

        Map<AnglePos, Angle> Map1 = new HashMap<>();
        Map<AnglePos, Angle> Map2 = new HashMap<>();
        Map<AnglePos, Angle> Map3 = new HashMap<>();
        Map<AnglePos, Angle> Map4 = new HashMap<>();
        Map<AnglePos, Angle> Map5 = new HashMap<>();
        Map<AnglePos, Angle> Map6 = new HashMap<>();

        Map<Coordinate, Card> MapCC = new HashMap<>();

        Card c1 = new Card(1, Map1,CardType.ANIMAL, false , 0,false,false);
        Card c2 = new Card(2, Map2, CardType.ANIMAL, false, 0, false, false);
        Card c3 = new Card(3, Map3, CardType.ANIMAL, false, 0, false, false);
        Card c4 = new Card(4, Map4,CardType.ANIMAL, false , 0,false,false);
        Card c5 = new Card(5, Map5, CardType.ANIMAL, false, 0, false, false);
        Card c6 = new Card(6, Map6, CardType.ANIMAL, false, 0, false, false);

        Angle a1 = new Angle(null, true, null, c1);
        Angle a1_2 = new Angle(null, false, null, c1);
        Angle a1_3 = new Angle(null, false, null, c1);
        Angle a1_4 = new Angle(null, false, null, c1);

        Angle a2 = new Angle(null, false, null, c2);
        Angle a2_2 = new Angle(null, false, null, c2);
        Angle a2_3 = new Angle(null, false, null, c2);
        Angle a2_4 = new Angle(null, false, null, c2);

        Angle a3 = new Angle(null, false, null, c3);
        Angle a3_2 = new Angle(null, false, null, c3);
        Angle a3_3 = new Angle(null, false, null, c3);
        Angle a3_4 = new Angle(null, false, null, c3);

        Angle a4 = new Angle(null, false, null, c4);
        Angle a4_2 = new Angle(null, false, null, c4);
        Angle a4_3 = new Angle(null, false, null, c4);
        Angle a4_4 = new Angle(null, false, null, c4);

        Angle a5 = new Angle(null, false, null, c5);
        Angle a5_2 = new Angle(null, false, null, c5);
        Angle a5_3 = new Angle(null, false, null, c5);
        Angle a5_4 = new Angle(null, false, null, c5);

        Angle a6 = new Angle(null, false, null, c6);
        Angle a6_2 = new Angle(null, false, null, c6);
        Angle a6_3 = new Angle(null, false, null, c6);
        Angle a6_4 = new Angle(null, false, null, c6);

        a1_3.setAttached(a2);
        a2.setAttached(a1_3);
        a3_3.setAttached(a4_2);
        a4_2.setAttached(a3_3);
        a5.setAttached(a4_4);
        a4_4.setAttached(a5);

        Map1.put(AnglePos.DR, a1);
        Map1.put(AnglePos.DL, a1_2);
        Map1.put(AnglePos.UR, a1_3);
        Map1.put(AnglePos.UL, a1_4);

        Map2.put(AnglePos.DR, a2);
        Map2.put(AnglePos.DL, a2_2);
        Map2.put(AnglePos.UR, a2_3);
        Map2.put(AnglePos.UL, a2_4);

        Map3.put(AnglePos.DR,a3);
        Map3.put(AnglePos.DL,a3_2);
        Map3.put(AnglePos.UR,a3_3);
        Map3.put(AnglePos.UL,a3_4);

        Map4.put(AnglePos.DR,a4);
        Map4.put(AnglePos.DL,a4_2);
        Map4.put(AnglePos.UR,a4_3);
        Map4.put(AnglePos.UL,a4_4);

        Map5.put(AnglePos.DR,a5);
        Map5.put(AnglePos.DL,a5_2);
        Map5.put(AnglePos.UR,a5_3);
        Map5.put(AnglePos.UL,a5_4);

        Map6.put(AnglePos.DR,a6);
        Map6.put(AnglePos.DL,a6_2);
        Map6.put(AnglePos.UR,a6_3);
        Map6.put(AnglePos.UL,a6_4);

        MapCC.put(co1,c1);
        MapCC.put(co2,c2);
        MapCC.put(co3,c3);

        Codex COD = new Codex(null, testGoal, null, MapCC);

        MapCC.put(co5,c5);
        MapCC.put(co4,c4);
        MapCC.put(co6,c6);

        testGoal.draw();
        assertEquals(2, testGoal.check(COD));
    }
    @Test
    void checktestINSECT(){

        Goal testGoal = new ResourcesGoal(null, CardType.INSECT,3,0);

        Coordinate co1 = new Coordinate(40,40);
        Coordinate co2 = new Coordinate(39,41);
        Coordinate co3 = new Coordinate(39,43);
        Coordinate co4 = new Coordinate(40,44);
        Coordinate co5 = new Coordinate(39,45);
        Coordinate co6 = new Coordinate(39,47);

        Map<AnglePos, Angle> Map1 = new HashMap<>();
        Map<AnglePos, Angle> Map2 = new HashMap<>();
        Map<AnglePos, Angle> Map3 = new HashMap<>();
        Map<AnglePos, Angle> Map4 = new HashMap<>();
        Map<AnglePos, Angle> Map5 = new HashMap<>();
        Map<AnglePos, Angle> Map6 = new HashMap<>();

        Map<Coordinate, Card> MapCC = new HashMap<>();

        Card c1 = new Card(1, Map1,CardType.INSECT, false , 0,false,false);
        Card c2 = new Card(2, Map2, CardType.INSECT, false, 0, false, false);
        Card c3 = new Card(3, Map3, CardType.INSECT, false, 0, false, false);
        Card c4 = new Card(4, Map4,CardType.INSECT, false , 0,false,false);
        Card c5 = new Card(5, Map5, CardType.INSECT, false, 0, false, false);
        Card c6 = new Card(6, Map6, CardType.INSECT, false, 0, false, false);

        Angle a1 = new Angle(null, true, null, c1);
        Angle a1_2 = new Angle(null, false, null, c1);
        Angle a1_3 = new Angle(null, false, null, c1);
        Angle a1_4 = new Angle(null, false, null, c1);

        Angle a2 = new Angle(null, false, null, c2);
        Angle a2_2 = new Angle(null, false, null, c2);
        Angle a2_3 = new Angle(null, false, null, c2);
        Angle a2_4 = new Angle(null, false, null, c2);

        Angle a3 = new Angle(null, false, null, c3);
        Angle a3_2 = new Angle(null, false, null, c3);
        Angle a3_3 = new Angle(null, false, null, c3);
        Angle a3_4 = new Angle(null, false, null, c3);

        Angle a4 = new Angle(null, false, null, c4);
        Angle a4_2 = new Angle(null, false, null, c4);
        Angle a4_3 = new Angle(null, false, null, c4);
        Angle a4_4 = new Angle(null, false, null, c4);

        Angle a5 = new Angle(null, false, null, c5);
        Angle a5_2 = new Angle(null, false, null, c5);
        Angle a5_3 = new Angle(null, false, null, c5);
        Angle a5_4 = new Angle(null, false, null, c5);

        Angle a6 = new Angle(null, false, null, c6);
        Angle a6_2 = new Angle(null, false, null, c6);
        Angle a6_3 = new Angle(null, false, null, c6);
        Angle a6_4 = new Angle(null, false, null, c6);

        a1_3.setAttached(a2);
        a2.setAttached(a1_3);
        a3_3.setAttached(a4_2);
        a4_2.setAttached(a3_3);
        a5.setAttached(a4_4);
        a4_4.setAttached(a5);

        Map1.put(AnglePos.DR, a1);
        Map1.put(AnglePos.DL, a1_2);
        Map1.put(AnglePos.UR, a1_3);
        Map1.put(AnglePos.UL, a1_4);

        Map2.put(AnglePos.DR, a2);
        Map2.put(AnglePos.DL, a2_2);
        Map2.put(AnglePos.UR, a2_3);
        Map2.put(AnglePos.UL, a2_4);

        Map3.put(AnglePos.DR,a3);
        Map3.put(AnglePos.DL,a3_2);
        Map3.put(AnglePos.UR,a3_3);
        Map3.put(AnglePos.UL,a3_4);

        Map4.put(AnglePos.DR,a4);
        Map4.put(AnglePos.DL,a4_2);
        Map4.put(AnglePos.UR,a4_3);
        Map4.put(AnglePos.UL,a4_4);

        Map5.put(AnglePos.DR,a5);
        Map5.put(AnglePos.DL,a5_2);
        Map5.put(AnglePos.UR,a5_3);
        Map5.put(AnglePos.UL,a5_4);

        Map6.put(AnglePos.DR,a6);
        Map6.put(AnglePos.DL,a6_2);
        Map6.put(AnglePos.UR,a6_3);
        Map6.put(AnglePos.UL,a6_4);

        MapCC.put(co1,c1);
        MapCC.put(co2,c2);
        MapCC.put(co3,c3);

        Codex COD = new Codex(null, testGoal, null, MapCC);

        MapCC.put(co5,c5);
        MapCC.put(co4,c4);
        MapCC.put(co6,c6);

        testGoal.draw();
        assertEquals(2, testGoal.check(COD));
    }
    @Test
    void checktestPLANT(){

        Goal testGoal = new ResourcesGoal(null, CardType.PLANT,3,0);

        Coordinate co1 = new Coordinate(40,40);
        Coordinate co2 = new Coordinate(39,41);
        Coordinate co3 = new Coordinate(39,43);
        Coordinate co4 = new Coordinate(40,44);
        Coordinate co5 = new Coordinate(39,45);
        Coordinate co6 = new Coordinate(39,47);

        Map<AnglePos, Angle> Map1 = new HashMap<>();
        Map<AnglePos, Angle> Map2 = new HashMap<>();
        Map<AnglePos, Angle> Map3 = new HashMap<>();
        Map<AnglePos, Angle> Map4 = new HashMap<>();
        Map<AnglePos, Angle> Map5 = new HashMap<>();
        Map<AnglePos, Angle> Map6 = new HashMap<>();

        Map<Coordinate, Card> MapCC = new HashMap<>();

        Card c1 = new Card(1, Map1,CardType.PLANT, false , 0,false,false);
        Card c2 = new Card(2, Map2, CardType.PLANT, false, 0, false, false);
        Card c3 = new Card(3, Map3, CardType.PLANT, false, 0, false, false);
        Card c4 = new Card(4, Map4,CardType.PLANT, false , 0,false,false);
        Card c5 = new Card(5, Map5, CardType.PLANT, false, 0, false, false);
        Card c6 = new Card(6, Map6, CardType.PLANT, false, 0, false, false);

        Angle a1 = new Angle(null, true, null, c1);
        Angle a1_2 = new Angle(null, false, null, c1);
        Angle a1_3 = new Angle(null, false, null, c1);
        Angle a1_4 = new Angle(null, false, null, c1);

        Angle a2 = new Angle(null, false, null, c2);
        Angle a2_2 = new Angle(null, false, null, c2);
        Angle a2_3 = new Angle(null, false, null, c2);
        Angle a2_4 = new Angle(null, false, null, c2);

        Angle a3 = new Angle(null, false, null, c3);
        Angle a3_2 = new Angle(null, false, null, c3);
        Angle a3_3 = new Angle(null, false, null, c3);
        Angle a3_4 = new Angle(null, false, null, c3);

        Angle a4 = new Angle(null, false, null, c4);
        Angle a4_2 = new Angle(null, false, null, c4);
        Angle a4_3 = new Angle(null, false, null, c4);
        Angle a4_4 = new Angle(null, false, null, c4);

        Angle a5 = new Angle(null, false, null, c5);
        Angle a5_2 = new Angle(null, false, null, c5);
        Angle a5_3 = new Angle(null, false, null, c5);
        Angle a5_4 = new Angle(null, false, null, c5);

        Angle a6 = new Angle(null, false, null, c6);
        Angle a6_2 = new Angle(null, false, null, c6);
        Angle a6_3 = new Angle(null, false, null, c6);
        Angle a6_4 = new Angle(null, false, null, c6);

        a1_3.setAttached(a2);
        a2.setAttached(a1_3);
        a3_3.setAttached(a4_2);
        a4_2.setAttached(a3_3);
        a5.setAttached(a4_4);
        a4_4.setAttached(a5);

        Map1.put(AnglePos.DR, a1);
        Map1.put(AnglePos.DL, a1_2);
        Map1.put(AnglePos.UR, a1_3);
        Map1.put(AnglePos.UL, a1_4);

        Map2.put(AnglePos.DR, a2);
        Map2.put(AnglePos.DL, a2_2);
        Map2.put(AnglePos.UR, a2_3);
        Map2.put(AnglePos.UL, a2_4);

        Map3.put(AnglePos.DR,a3);
        Map3.put(AnglePos.DL,a3_2);
        Map3.put(AnglePos.UR,a3_3);
        Map3.put(AnglePos.UL,a3_4);

        Map4.put(AnglePos.DR,a4);
        Map4.put(AnglePos.DL,a4_2);
        Map4.put(AnglePos.UR,a4_3);
        Map4.put(AnglePos.UL,a4_4);

        Map5.put(AnglePos.DR,a5);
        Map5.put(AnglePos.DL,a5_2);
        Map5.put(AnglePos.UR,a5_3);
        Map5.put(AnglePos.UL,a5_4);

        Map6.put(AnglePos.DR,a6);
        Map6.put(AnglePos.DL,a6_2);
        Map6.put(AnglePos.UR,a6_3);
        Map6.put(AnglePos.UL,a6_4);

        MapCC.put(co1,c1);
        MapCC.put(co2,c2);
        MapCC.put(co3,c3);

        Codex COD = new Codex(null, testGoal, null, MapCC);

        MapCC.put(co5,c5);
        MapCC.put(co4,c4);
        MapCC.put(co6,c6);

        testGoal.draw();
        assertEquals(2, testGoal.check(COD));
    }

}

