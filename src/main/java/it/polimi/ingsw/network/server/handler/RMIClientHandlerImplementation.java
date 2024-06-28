package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * The implementation of the client handler
 */
public class RMIClientHandlerImplementation extends UnicastRemoteObject implements RMIClientHandler {

    /**
     * Constructor
     * @throws RemoteException
     */
    public RMIClientHandlerImplementation() throws RemoteException {
        super();
    }

    /**
     * Send execute the update received
     * @param update the update to execute
     * @throws IOException
     */
    @Override
    public void sendUpdate(Update update) throws IOException {
        update.execute();
    }

    /**
     * Receive a ping from the server
     * @throws IOException
     */
    @Override
    public void receivePing() throws IOException {
        ClientSR.getInstance().sendPong();
    }


}
