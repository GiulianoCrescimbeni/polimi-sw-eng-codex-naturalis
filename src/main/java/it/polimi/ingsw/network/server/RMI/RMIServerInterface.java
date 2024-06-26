package it.polimi.ingsw.network.server.RMI;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.handler.RMIClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {

    void registerClient(RMIClientHandler clientHandler) throws RemoteException;
    void receiveCommand(RMIClientHandler clientHandler, Command command) throws RemoteException;
    void receviePong(ClientHandler clientHandler) throws RemoteException;

}
