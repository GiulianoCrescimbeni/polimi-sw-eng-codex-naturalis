package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

/**
 * Create match command to create a new game
 */
public class CreateMatchCommand extends Command implements Serializable {

    private int maxPlayers;

    /**
     * Constructor
     * @param maxPlayers the number of max players
     */
    public CreateMatchCommand(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * @return the number of max players
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * Don't do anything, the {@link it.polimi.ingsw.network.server.GamesManager} will provide the request
     * @param gameController the {@link Controller} of the game where the message needs to get executed
     * @return
     */
    @Override
    public Update execute(Controller gameController) {
        return null;
    }

}
