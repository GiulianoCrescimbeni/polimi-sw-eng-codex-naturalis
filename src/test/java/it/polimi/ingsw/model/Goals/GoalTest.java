package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

public class GoalTest {
    @Test
    void getGoalIdCheck() {
        Goal testGoal = new LGoal(null, CardType.FUNGI, 1, 0);
        Goal testGoal1 = new DifferentObjectGoal(null, null, 2, 1);
        Goal testGoal2 = new EqualsObjectGoal(null, null, 3, 2);
        Goal testGoal3 = new DiagonalGoal(null, CardType.FUNGI, 3, 4);
        assertEquals(0, testGoal.getGoalId());
    }
    @Test
    void getScoreTest(){
        Goal testGoal1 = new DifferentObjectGoal(null, null, 2, 1);
        assertEquals(2,testGoal1.getScore());
    }
    @Test
    void  getCardType(){
        Goal testGoal3 = new DiagonalGoal(null, CardType.FUNGI, 3, 4);
        assertEquals(CardType.FUNGI,testGoal3.getCardType());
    }
    @Test
    void  getObjectType(){
        Goal testGoal3 = new DiagonalGoal(null, CardType.FUNGI, 3, 4);

        assertEquals(null,testGoal3.getObjectType());
    }

}
