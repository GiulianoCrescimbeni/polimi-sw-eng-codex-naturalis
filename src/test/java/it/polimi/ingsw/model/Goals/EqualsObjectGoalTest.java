package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Codex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EqualsObjectGoalTest {

    private Codex codex;
    private EqualsObjectGoal scrollGoal;
    private EqualsObjectGoal featherGoal;
    private EqualsObjectGoal jarGoal;

    @BeforeEach
    public void setUp() {
        Map<Resource, Integer> resources = new HashMap<>();
        resources.put(Resource.SCROLL, 0);
        resources.put(Resource.FEATHER, 0);
        resources.put(Resource.JAR, 0);

        codex = new Codex(null, null, resources, null);
        scrollGoal = new EqualsObjectGoal(Resource.SCROLL, null, 10, 1);
        featherGoal = new EqualsObjectGoal(Resource.FEATHER, null, 10, 2);
        jarGoal = new EqualsObjectGoal(Resource.JAR, null, 10, 3);
    }

    @Test
    public void testCheckScrollGoal() {
        codex.incrementNumOfResources(Resource.SCROLL, 4);
        scrollGoal.draw();
        assertEquals(2, scrollGoal.check(codex));

        codex.incrementNumOfResources(Resource.SCROLL, 2);
        assertEquals(3, scrollGoal.check(codex));

        codex.decreaseNumOfResources(Resource.SCROLL, 6);
        assertEquals(0, scrollGoal.check(codex));
    }

    @Test
    public void testCheckFeatherGoal() {
        codex.incrementNumOfResources(Resource.FEATHER, 6);
        featherGoal.draw();
        assertEquals(3, featherGoal.check(codex));

        codex.incrementNumOfResources(Resource.FEATHER, 4);
        assertEquals(5, featherGoal.check(codex));

        codex.decreaseNumOfResources(Resource.FEATHER, 10);
        assertEquals(0, featherGoal.check(codex));
    }

    @Test
    public void testCheckJarGoal() {
        codex.incrementNumOfResources(Resource.JAR, 8);
        jarGoal.draw();
        assertEquals(4, jarGoal.check(codex));

        codex.incrementNumOfResources(Resource.JAR, 2);
        assertEquals(5, jarGoal.check(codex));

        codex.decreaseNumOfResources(Resource.JAR, 10);
        assertEquals(0, jarGoal.check(codex));
    }
}