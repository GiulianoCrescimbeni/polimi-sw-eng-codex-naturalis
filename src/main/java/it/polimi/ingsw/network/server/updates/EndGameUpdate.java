package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.view.TUI.Messages;

import java.io.Serializable;

public class EndGameUpdate extends Update implements Serializable {

    @Override
    public void execute() {
        Messages.getInstance().info("Game ended");
        Messages.getInstance().info("Closing");
        System.exit(0);
    }

}
