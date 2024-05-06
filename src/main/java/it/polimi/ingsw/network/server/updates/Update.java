package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.io.Serializable;

/**
 * Update Class
 * This class represents all the messages sent from the server to the client for updates on the game status and errors
 */
public abstract class Update implements Serializable {

    /**
     * Constructor
     */
    public Update() {}

    /**
     * This method has to be overwrtitten
     * It defines the behaviour of the update
     */
    public void execute(ClientController clientController) {}

}
