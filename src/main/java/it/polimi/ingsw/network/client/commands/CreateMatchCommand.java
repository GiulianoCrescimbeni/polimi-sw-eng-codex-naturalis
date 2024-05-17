package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class CreateMatchCommand extends Command implements Serializable {

    private int maxPlayers;

    public CreateMatchCommand(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    @Override
    public Update execute(Controller gameController) {
        return null;
    }

}
