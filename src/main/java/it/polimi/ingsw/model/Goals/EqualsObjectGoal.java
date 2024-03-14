package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.GameComponents.Codex;

public class EqualsObjectGoal extends Goal {
    @Override
    public int check(Codex codex) {
        switch (getObjectType()) {
            case SCROLL: return codex.getNumOfResources().get(4);
            case FEATHER: return codex.getNumOfResources().get(5);
            case JAR: return codex.getNumOfResources().get(6);
            //TODO Aggiungere gestione eccezione
            default: return 0;
        }
    }
}
