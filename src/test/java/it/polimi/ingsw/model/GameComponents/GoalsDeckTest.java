package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Goals.LGoal;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class GoalsDeckTest extends TestCase {

    private Goal goal1 = new LGoal();
    private Goal goal2 = new LGoal();
    private Goal goal3 = new LGoal();
    private Stack<Goal> goalStack = new Stack<Goal>();
    GoalsDeck testDeck = new GoalsDeck(3, goalStack);

    @BeforeEach
    public void setup() {
        goalStack.add(goal1);
        goalStack.add(goal2);
        goalStack.add(goal3);
    }

    @Test
    public void testGetNumOfGoals() {
        assertEquals(3, testDeck.getNumOfGoals() );
    }

    @Test
    public void testGetGoals() {
        assertEquals(goalStack, testDeck.getGoals());
    }

    @Test
    public void testGetGoal() {
        Goal goalPicked = testDeck.getGoal();
        assertEquals(2, testDeck.getNumOfGoals());
        assertEquals(goalPicked, goal3);
        goalStack.add(goal3);
    }

    @Test
    public void testAddGoal() {
        Goal goalToAdd = new LGoal();
        testDeck.addGoal(goalToAdd);
        assertEquals(4, testDeck.getNumOfGoals());
        assertEquals(goalToAdd, goalStack.pop());
    }

    @Test
    public void testGoalsShuffle() {
        Stack<Goal> stackToShuffle = new Stack<Goal>();
        stackToShuffle.addAll(goalStack);
        testDeck.goalsShuffle();
        assertNotEquals(goalStack, stackToShuffle);
        assertEquals(goalStack.size(), stackToShuffle.size());
    }
}