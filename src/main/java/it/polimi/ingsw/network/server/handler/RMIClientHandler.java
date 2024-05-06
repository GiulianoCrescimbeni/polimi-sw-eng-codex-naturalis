package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;

/**
 * Class that represent the handler of a client connected with RMI technology
 */
public class RMIClientHandler extends Thread implements ClientHandler {
    /**
     * Function that sends an {@link Update} to the Client
     * @param update the update to send
     * @throws IOException
     */
    @Override
    public void sendUpdate(Update update) throws IOException {

    }
}
