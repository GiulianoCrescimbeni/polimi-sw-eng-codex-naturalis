package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.GameComponents.Codex;

public class DifferentObjectGoal extends Goal {
    @Override
    public int check(Codex codex) {
        return Math.min(Math.min(codex.getNumOfResources().get(4), codex.getNumOfResources().get(5)), codex.getNumOfResources().get(6));
    }
}
