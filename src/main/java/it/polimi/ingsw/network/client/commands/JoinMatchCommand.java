package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.network.server.updates.Update;
import it.polimi.ingsw.controller.Controller;
import java.io.Serializable;

public class JoinMatchCommand extends Command implements Serializable {

    private int gameId;

    public JoinMatchCommand(int gameId) {
        this.gameId = gameId;
    }

    public int getGameId() {
        return this.gameId;
    }
}
