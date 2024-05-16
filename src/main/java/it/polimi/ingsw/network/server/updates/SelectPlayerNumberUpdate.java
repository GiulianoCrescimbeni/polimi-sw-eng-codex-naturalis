package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

public class SelectPlayerNumberUpdate extends Update implements Serializable {

    @Override
    public void execute() {
        View.getInstance().selectPlayersNumber();
    }

}

