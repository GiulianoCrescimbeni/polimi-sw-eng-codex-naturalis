package it.polimi.ingsw.network.server.RMI;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.handler.RMIClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIServerInterface extends Remote {

    /**
     * Register a new client
     * @param clientHandler the {@link ClientHandler}
     * @throws RemoteException
     */
    void registerClient(RMIClientHandler clientHandler) throws RemoteException;
    /**
     * Receive a new command
     * @param clientHandler the {@link ClientHandler} that sent the {@link Command}
     * @param command the {@link Command} sent by the {@link ClientHandler}
     * @throws RemoteException
     */
    void receiveCommand(RMIClientHandler clientHandler, Command command) throws RemoteException;
    /**
     * Receive a pong back from the client
     * @param clientHandler the {@link ClientHandler} that sent the pong
     */
    void receviePong(ClientHandler clientHandler) throws RemoteException;

}
