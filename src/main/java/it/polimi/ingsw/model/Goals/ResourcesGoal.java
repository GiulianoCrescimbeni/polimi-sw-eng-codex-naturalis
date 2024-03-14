package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.GameComponents.Codex;

public class ResourcesGoal extends Goal {
    @Override
    public int check(Codex codex) {
        switch (getCardType()) {
            case PLANT: return codex.getNumOfResources().get(0) / 3;
            case ANIMAL: return codex.getNumOfResources().get(1) / 3;
            case FUNGI: return codex.getNumOfResources().get(2) / 3;
            case INSECT: return codex.getNumOfResources().get(3) / 3;
            //TODO Gestire eccezione
            default: return 0;
        }
    }
}
