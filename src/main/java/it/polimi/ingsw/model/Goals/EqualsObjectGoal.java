package it.polimi.ingsw.model.Goals;

import it.polimi.ingsw.model.GameComponents.Codex;

public class EqualsObjectGoal extends Goal {

    //Goal Card: 2 equals object
    //Takes num of couple of object:
    // pos 4: SCROLL;
    // pos 5: FEATHER;
    // pos 6: JAR;
    @Override
    public int check(Codex codex) {
        switch (getObjectType()) {
            case SCROLL:    return codex.getNumOfResources().get(4) / 2;
            case FEATHER:   return codex.getNumOfResources().get(5) / 2;
            case JAR:       return codex.getNumOfResources().get(6) / 2;
            //TODO Aggiungere gestione eccezione
            default: return 0;
        }
    }
}
