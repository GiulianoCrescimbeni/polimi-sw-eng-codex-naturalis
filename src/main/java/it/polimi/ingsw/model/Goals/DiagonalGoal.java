package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.GameComponents.Codex;

/**
 * Class that represent the diagonal shaped goal card
 */
public class DiagonalGoal extends Goal {
    @Override
    public int check(Codex codex){ return 0; };
}
