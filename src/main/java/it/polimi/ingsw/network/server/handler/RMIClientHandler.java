package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import it.polimi.ingsw.network.client.commands.Command;

/**
 * Class that represent the handler of a client connected with RMI technology
 */
public interface RMIClientHandler extends Remote, ClientHandler {
    /**
     * Function that sends an {@link Update} to the Client
     * @param update the update to send
     * @throws IOException
     */
    @Override
    void sendUpdate(Update update) throws IOException;

    void receivePing() throws IOException;
}
