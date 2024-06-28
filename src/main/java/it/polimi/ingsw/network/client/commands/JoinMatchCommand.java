package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.network.server.updates.Update;
import it.polimi.ingsw.controller.Controller;
import java.io.Serializable;

/**
 * Command for joining a new match
 */
public class JoinMatchCommand extends Command implements Serializable {

    private int gameId;

    /**
     * Constructor
     * @param gameId the game id of the match to join
     */
    public JoinMatchCommand(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the game id
     */
    public int getGameId() {
        return this.gameId;
    }
}
