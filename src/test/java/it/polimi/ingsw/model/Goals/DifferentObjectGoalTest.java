package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DifferentObjectGoalTest {

    Map<Coordinate, Card> cards;
    Map<Resource, Integer> resources;
    Codex codex;

    @BeforeEach
    public void setUp() {
        cards = new HashMap<>();
        resources = new HashMap<>();
        resources.put(Resource.SCROLL, 3);
        resources.put(Resource.FEATHER, 2);
        resources.put(Resource.JAR, 2);

        codex = new Codex(null, null, resources, cards);
    }

    @Test
    public void check() {
        DifferentObjectGoal goal = new DifferentObjectGoal(null, null, 0, 0);

        Card card1 = new Card(0, null, CardType.ANIMAL, false, 0, false, false),
                card2 = new Card(0, null, CardType.ANIMAL, false, 0, false, false),
                card3 = new Card(0, null, CardType.ANIMAL, false, 0, false, false);

        Coordinate coordinate1 = new Coordinate(20, 18),
                coordinate2 = new Coordinate(21, 19),
                coordinate3 = new Coordinate(22, 20);

        cards.put(coordinate1, card1);
        cards.put(coordinate2, card2);
        cards.put(coordinate3, card3);

        Angle angle1_card1 = new Angle(Resource.SCROLL, false, null, card1),
                angle2_card1 = new Angle(null, true, null, card1),
                angle3_card1 = new Angle(Resource.SCROLL, false, null, card1),
                angle4_card1 = new Angle(Resource.SCROLL, false, null, card1),

                angle1_card2 = new Angle(Resource.JAR, false, null, card2),
                angle2_card2 = new Angle(null, true, null, card2),
                angle3_card2 = new Angle(null, false, null, card2),
                angle4_card2 = new Angle(Resource.FEATHER, false, null, card2),

                angle1_card3 = new Angle(null, false, null, card3),
                angle2_card3 = new Angle(Resource.JAR, false, null, card3),
                angle3_card3 = new Angle(null, false, null, card3),
                angle4_card3 = new Angle(Resource.FEATHER, false, null, card3);

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

        assertEquals(2, goal.check(codex));
    }

    @Test
    public void draw() {
        DifferentObjectGoal goal = new DifferentObjectGoal(null, null, 0, 0);
        goal.draw();
    }
}