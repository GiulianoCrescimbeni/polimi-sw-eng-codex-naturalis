package it.polimi.ingsw.network.server.RMI;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.client.commands.CreateMatchCommand;
import it.polimi.ingsw.network.client.commands.JoinMatchCommand;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.handler.RMIClientHandler;
import it.polimi.ingsw.view.TUI.TextColor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer extends UnicastRemoteObject implements RMIServerInterface {


    private GamesManager manager;

    private int port;

    public RMIServer(int port) throws RemoteException {
        super();
        this.port = port;
    }

    public void startRMI() {
        try {
            RMIServerInterface stub = this;
            Registry registry = LocateRegistry.createRegistry(this.port);
            registry.rebind("RMIServer", stub);

            manager = GamesManager.getInstance();

            System.out.println(TextColor.BRIGHT_BLUE + "[RMI SERVER] " + TextColor.RESET + " RMI Server" + TextColor.GREEN + " Ready");
        } catch (Exception e) {
            System.out.println(TextColor.RED + "[RMI SERVER] " + TextColor.RESET + "Error while starting RMI Server");
            e.printStackTrace();
        }
    }

    @Override
    public void registerClient(RMIClientHandler clientHandler) throws RemoteException {
        try {
            manager.addConnectionToWaitingList(clientHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receiveCommand(RMIClientHandler clientHandler, Command command) throws RemoteException {
        try {
            if (command instanceof CreateMatchCommand) {
                GamesManager.getInstance().createMatch(clientHandler, (CreateMatchCommand) command);
            } else if (command instanceof JoinMatchCommand) {
                GamesManager.getInstance().joinMatch(clientHandler, (JoinMatchCommand) command);
            } else {
                GamesManager.getInstance().handleCommand(clientHandler, command);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void receviePong(ClientHandler clientHandler) {
        GamesManager.getInstance().pinged.remove(clientHandler);
    }
}
