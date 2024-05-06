package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;

/**
 * The interface that all client handlers needs to implement
 */
public interface ClientHandler {

    /**
     * Function that sends an {@link Update} to the Client
     * @param update the update to send
     * @throws IOException
     */
    public void sendUpdate(Update update) throws IOException;

}
