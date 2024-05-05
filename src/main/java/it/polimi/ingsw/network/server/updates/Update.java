package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.io.Serializable;

public abstract class Update implements Serializable {

    public Update() {}

    /**
     * This method has to be overwrtitten
     * It defines the behaviour of the update
     */
    public void execute(ClientController clientController) {}

}
