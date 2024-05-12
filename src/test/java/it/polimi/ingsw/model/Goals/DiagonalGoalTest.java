package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class DiagonalGoalTest extends TestCase {

    Map<Coordinate, Card> cards = new HashMap<>();

    Codex codex = new Codex(null, null, null, cards);

    /**
     *  Basic test for the diagonal goal Animal
     */
    @Test
    public void testCheckANIMAL() {

        DiagonalGoal goal = new DiagonalGoal(null, CardType.ANIMAL, 0, 0);


        Card card1 = new Card(0, null, CardType.ANIMAL, false, 0, false, false),
                card2 = new Card(0, null, CardType.ANIMAL, false, 0, false, false),
                card3 = new Card(0, null, CardType.ANIMAL, false, 0, false, false);

        Coordinate  coordinate1 = new Coordinate(20, 18),
                coordinate2 = new Coordinate(21, 19),
                coordinate3 = new Coordinate(22, 20);

        cards.put(coordinate1, card1);
        cards.put(coordinate2, card2);
        cards.put(coordinate3, card3);


        Angle angle1_card1 = new Angle(null, false, null, card1),
                angle2_card1 = new Angle(null, true, null, card1),
                angle3_card1 = new Angle(null, false, null, card1),
                angle4_card1 = new Angle(null, false, null, card1),

                angle1_card2 = new Angle(null, false, null, card2),
                angle2_card2 = new Angle(null, true, null, card2),
                angle3_card2 = new Angle(null, false, null, card2),
                angle4_card2 = new Angle(null, false, null, card2),

                angle1_card3 = new Angle(null, false, null, card3),
                angle2_card3 = new Angle(null, false, null, card3),
                angle3_card3 = new Angle(null, false, null, card3),
                angle4_card3 = new Angle(null, false, null, card3);

        angle2_card1.setAttached(angle4_card2);
        angle4_card2.setAttached(angle2_card1);
        angle2_card2.setAttached(angle4_card3);
        angle4_card3.setAttached(angle2_card2);

        Map<AnglePos, Angle> angles1 = new HashMap<>(),
                angles2 = new HashMap<>(),
                angles3 = new HashMap<>();

        angles1.put(AnglePos.UL, angle1_card1);
        angles1.put(AnglePos.UR, angle2_card1);
        angles1.put(AnglePos.DR, angle3_card1);
        angles1.put(AnglePos.DL, angle4_card1);

        angles2.put(AnglePos.UL, angle1_card2);
        angles2.put(AnglePos.UR, angle2_card2);
        angles2.put(AnglePos.DR, angle3_card2);
        angles2.put(AnglePos.DL, angle4_card2);

        angles3.put(AnglePos.UL, angle1_card3);
        angles3.put(AnglePos.UR, angle2_card3);
        angles3.put(AnglePos.DR, angle3_card3);
        angles3.put(AnglePos.DL, angle4_card3);

        card1.setAnglesMap(angles1);
        card2.setAnglesMap(angles2);
        card3.setAnglesMap(angles3);

        assertEquals(1, goal.check(codex));
    }

    /**
     *  Basic test for the diagonal goal Plant
     */
    @Test
    public void testCheckPLANT() {

        DiagonalGoal goal = new DiagonalGoal(null, CardType.PLANT, 0, 0);


        Card card1 = new Card(0, null, CardType.PLANT, false, 0, false, false),
                card2 = new Card(0, null, CardType.PLANT, false, 0, false, false),
                card3 = new Card(0, null, CardType.PLANT, false, 0, false, false);

        Coordinate  coordinate1 = new Coordinate(74, 90),
                coordinate2 = new Coordinate(73, 91),
                coordinate3 = new Coordinate(72, 92);

        cards.put(coordinate1, card1);
        cards.put(coordinate2, card2);
        cards.put(coordinate3, card3);


        Angle angle1_card1 = new Angle(null, true, null, card1),
                angle2_card1 = new Angle(null, false, null, card1),
                angle3_card1 = new Angle(null, false, null, card1),
                angle4_card1 = new Angle(null, false, null, card1),

                angle1_card2 = new Angle(null, true, null, card2),
                angle2_card2 = new Angle(null, false, null, card2),
                angle3_card2 = new Angle(null, false, null, card2),
                angle4_card2 = new Angle(null, false, null, card2),

                angle1_card3 = new Angle(null, false, null, card3),
                angle2_card3 = new Angle(null, false, null, card3),
                angle3_card3 = new Angle(null, false, null, card3),
                angle4_card3 = new Angle(null, false, null, card3);

        angle1_card1.setAttached(angle3_card2);
        angle3_card2.setAttached(angle1_card1);
        angle1_card2.setAttached(angle3_card3);
        angle3_card3.setAttached(angle1_card2);

        Map<AnglePos, Angle> angles1 = new HashMap<>(),
                angles2 = new HashMap<>(),
                angles3 = new HashMap<>();

        angles1.put(AnglePos.UL, angle1_card1);
        angles1.put(AnglePos.UR, angle2_card1);
        angles1.put(AnglePos.DR, angle3_card1);
        angles1.put(AnglePos.DL, angle4_card1);

        angles2.put(AnglePos.UL, angle1_card2);
        angles2.put(AnglePos.UR, angle2_card2);
        angles2.put(AnglePos.DR, angle3_card2);
        angles2.put(AnglePos.DL, angle4_card2);

        angles3.put(AnglePos.UL, angle1_card3);
        angles3.put(AnglePos.UR, angle2_card3);
        angles3.put(AnglePos.DR, angle3_card3);
        angles3.put(AnglePos.DL, angle4_card3);

        card1.setAnglesMap(angles1);
        card2.setAnglesMap(angles2);
        card3.setAnglesMap(angles3);

        assertEquals(1, goal.check(codex));
    }

    /**
     *  Basic test for the diagonal goal Fungi
     */
    @Test
    public void testCheckFUNGI() {

        DiagonalGoal goal = new DiagonalGoal(null, CardType.FUNGI, 0, 0);


        Card card1 = new Card(0, null, CardType.FUNGI, false, 0, false, false),
                card2 = new Card(0, null, CardType.FUNGI, false, 0, false, false),
                card3 = new Card(0, null, CardType.FUNGI, false, 0, false, false);

        Coordinate  coordinate1 = new Coordinate(7, 74),
                coordinate2 = new Coordinate(8, 75),
                coordinate3 = new Coordinate(9, 76);

        cards.put(coordinate1, card1);
        cards.put(coordinate2, card2);
        cards.put(coordinate3, card3);


        Angle angle1_card1 = new Angle(null, false, null, card1),
                angle2_card1 = new Angle(null, true, null, card1),
                angle3_card1 = new Angle(null, false, null, card1),
                angle4_card1 = new Angle(null, false, null, card1),

                angle1_card2 = new Angle(null, false, null, card2),
                angle2_card2 = new Angle(null, true, null, card2),
                angle3_card2 = new Angle(null, false, null, card2),
                angle4_card2 = new Angle(null, false, null, card2),

                angle1_card3 = new Angle(null, false, null, card3),
                angle2_card3 = new Angle(null, false, null, card3),
                angle3_card3 = new Angle(null, false, null, card3),
                angle4_card3 = new Angle(null, false, null, card3);

        angle2_card1.setAttached(angle4_card2);
        angle4_card2.setAttached(angle2_card1);
        angle2_card2.setAttached(angle4_card3);
        angle4_card3.setAttached(angle2_card2);

        Map<AnglePos, Angle> angles1 = new HashMap<>(),
                angles2 = new HashMap<>(),
                angles3 = new HashMap<>();

        angles1.put(AnglePos.UL, angle1_card1);
        angles1.put(AnglePos.UR, angle2_card1);
        angles1.put(AnglePos.DR, angle3_card1);
        angles1.put(AnglePos.DL, angle4_card1);

        angles2.put(AnglePos.UL, angle1_card2);
        angles2.put(AnglePos.UR, angle2_card2);
        angles2.put(AnglePos.DR, angle3_card2);
        angles2.put(AnglePos.DL, angle4_card2);

        angles3.put(AnglePos.UL, angle1_card3);
        angles3.put(AnglePos.UR, angle2_card3);
        angles3.put(AnglePos.DR, angle3_card3);
        angles3.put(AnglePos.DL, angle4_card3);

        card1.setAnglesMap(angles1);
        card2.setAnglesMap(angles2);
        card3.setAnglesMap(angles3);

        assertEquals(1, goal.check(codex));
    }

    /**
     *  Basic test for the diagonal goal Insect
     */
    @Test
    public void testCheckINSECT() {

        DiagonalGoal goal = new DiagonalGoal(null, CardType.INSECT, 0, 0);


        Card card1 = new Card(0, null, CardType.INSECT, false, 0, false, false),
                card2 = new Card(0, null, CardType.INSECT, false, 0, false, false),
                card3 = new Card(0, null, CardType.INSECT, false, 0, false, false);

        Coordinate  coordinate1 = new Coordinate(27, 57),
                coordinate2 = new Coordinate(26, 58),
                coordinate3 = new Coordinate(25, 59);

        cards.put(coordinate1, card1);
        cards.put(coordinate2, card2);
        cards.put(coordinate3, card3);


        Angle angle1_card1 = new Angle(null, true, null, card1),
                angle2_card1 = new Angle(null, false, null, card1),
                angle3_card1 = new Angle(null, false, null, card1),
                angle4_card1 = new Angle(null, false, null, card1),

                angle1_card2 = new Angle(null, true, null, card2),
                angle2_card2 = new Angle(null, false, null, card2),
                angle3_card2 = new Angle(null, false, null, card2),
                angle4_card2 = new Angle(null, false, null, card2),

                angle1_card3 = new Angle(null, false, null, card3),
                angle2_card3 = new Angle(null, false, null, card3),
                angle3_card3 = new Angle(null, false, null, card3),
                angle4_card3 = new Angle(null, false, null, card3);

        angle1_card1.setAttached(angle3_card2);
        angle3_card2.setAttached(angle1_card1);
        angle1_card2.setAttached(angle3_card3);
        angle3_card3.setAttached(angle1_card2);

        Map<AnglePos, Angle> angles1 = new HashMap<>(),
                angles2 = new HashMap<>(),
                angles3 = new HashMap<>();

        angles1.put(AnglePos.UL, angle1_card1);
        angles1.put(AnglePos.UR, angle2_card1);
        angles1.put(AnglePos.DR, angle3_card1);
        angles1.put(AnglePos.DL, angle4_card1);

        angles2.put(AnglePos.UL, angle1_card2);
        angles2.put(AnglePos.UR, angle2_card2);
        angles2.put(AnglePos.DR, angle3_card2);
        angles2.put(AnglePos.DL, angle4_card2);

        angles3.put(AnglePos.UL, angle1_card3);
        angles3.put(AnglePos.UR, angle2_card3);
        angles3.put(AnglePos.DR, angle3_card3);
        angles3.put(AnglePos.DL, angle4_card3);

        card1.setAnglesMap(angles1);
        card2.setAnglesMap(angles2);
        card3.setAnglesMap(angles3);

        assertEquals(1, goal.check(codex));
    }



    /**
     * Diagonal of 2 Animal, 1 Insect and 1 Animal
     */
    @Test
    public void testCheck_2_1_1() {

        DiagonalGoal goal = new DiagonalGoal(null, CardType.ANIMAL, 0, 0);


        Card card1 = new Card(0, null, CardType.ANIMAL, false, 0, false, false),
                card2 = new Card(0, null, CardType.ANIMAL, false, 0, false, false),
                card3 = new Card(0, null, CardType.INSECT, false, 0, false, false),
                card4 = new Card(0, null, CardType.ANIMAL, false, 0, false, false);

        Coordinate  coordinate1 = new Coordinate(20, 18),
                coordinate2 = new Coordinate(21, 19),
                coordinate3 = new Coordinate(22, 20),
                coordinate4 = new Coordinate(23, 21);

        cards.put(coordinate1, card1);
        cards.put(coordinate2, card2);
        cards.put(coordinate3, card3);
        cards.put(coordinate4, card4);


        Angle angle1_card1 = new Angle(null, false, null, card1),
                angle2_card1 = new Angle(null, true, null, card1),
                angle3_card1 = new Angle(null, false, null, card1),
                angle4_card1 = new Angle(null, false, null, card1),

                angle1_card2 = new Angle(null, false, null, card2),
                angle2_card2 = new Angle(null, true, null, card2),
                angle3_card2 = new Angle(null, false, null, card2),
                angle4_card2 = new Angle(null, false, null, card2),

                angle1_card3 = new Angle(null, false, null, card3),
                angle2_card3 = new Angle(null, true, null, card3),
                angle3_card3 = new Angle(null, false, null, card3),
                angle4_card3 = new Angle(null, false, null, card3),

                angle1_card4 = new Angle(null, false, null, card4),
                angle2_card4 = new Angle(null, false, null, card4),
                angle3_card4 = new Angle(null, false, null, card4),
                angle4_card4 = new Angle(null, false, null, card4);

        angle2_card1.setAttached(angle4_card2);
        angle4_card2.setAttached(angle2_card1);
        angle2_card2.setAttached(angle4_card3);
        angle4_card3.setAttached(angle2_card2);
        angle2_card3.setAttached(angle4_card4);
        angle4_card4.setAttached(angle2_card3);

        Map<AnglePos, Angle> angles1 = new HashMap<>(),
                angles2 = new HashMap<>(),
                angles3 = new HashMap<>(),
                angles4 = new HashMap<>();

        angles1.put(AnglePos.UL, angle1_card1);
        angles1.put(AnglePos.UR, angle2_card1);
        angles1.put(AnglePos.DR, angle3_card1);
        angles1.put(AnglePos.DL, angle4_card1);

        angles2.put(AnglePos.UL, angle1_card2);
        angles2.put(AnglePos.UR, angle2_card2);
        angles2.put(AnglePos.DR, angle3_card2);
        angles2.put(AnglePos.DL, angle4_card2);

        angles3.put(AnglePos.UL, angle1_card3);
        angles3.put(AnglePos.UR, angle2_card3);
        angles3.put(AnglePos.DR, angle3_card3);
        angles3.put(AnglePos.DL, angle4_card3);

        angles4.put(AnglePos.UL, angle1_card4);
        angles4.put(AnglePos.UR, angle2_card4);
        angles4.put(AnglePos.DR, angle3_card4);
        angles4.put(AnglePos.DL, angle4_card4);


        card1.setAnglesMap(angles1);
        card2.setAnglesMap(angles2);
        card3.setAnglesMap(angles3);
        card4.setAnglesMap(angles4);

        assertEquals(0, goal.check(codex));
    }
}