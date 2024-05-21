package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.updates.Update;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIClientHandlerImplementation extends UnicastRemoteObject implements RMIClientHandler {

    public RMIClientHandlerImplementation() throws RemoteException {
        super();
    }

    @Override
    public void sendUpdate(Update update) throws RemoteException {
        update.execute();
    }
}
