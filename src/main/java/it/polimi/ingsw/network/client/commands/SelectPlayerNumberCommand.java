package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.server.updates.SelectPlayerNumberUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class SelectPlayerNumberCommand extends  Command implements Serializable  {

    private int maxPlayers;

    public SelectPlayerNumberCommand(String username, int maxPlayers) {
        super(username);
        this.maxPlayers = maxPlayers;
    }

    @Override
    public Update execute(Controller gameController) {
        gameController.selectMaxPlayers(maxPlayers);
        return new SelectPlayerNumberUpdate(maxPlayers);
    }

}
