package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;
import java.util.ArrayList;

public class AvailableMatchesUpdate extends Update implements Serializable {

    private ArrayList<SerializedGame> availableMatches;
    boolean select = false;
    private String message;

    public AvailableMatchesUpdate(ArrayList<SerializedGame> availableMatches, boolean select, String message) {
        this.availableMatches = availableMatches;
        this.select = select;
        this.message = message;
    }

    @Override
    public void execute() {
        if (select) {
            View.getInstance().selectAvailableMatch(availableMatches, message);
        } else {
            View.getInstance().joinOrCreateMatch(availableMatches);
        }

    }

}
