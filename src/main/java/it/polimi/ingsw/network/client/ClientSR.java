package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.RMI.RMIServerInterface;
import it.polimi.ingsw.network.server.handler.RMIClientHandlerImplementation;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientSR extends Thread {

    private Socket s;
    private String host;
    private int port;

    private boolean isSocket;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private RMIServerInterface server;
    private RMIClientHandlerImplementation clientHandler;

    private static ClientSR instance;

    private ClientSR() {}

    public synchronized void startSR(String host, int port, boolean isSocket) {
        this.isSocket = isSocket;
        this.host = host;
        this.port = port;

        if (isSocket) {

            try{

                s = new Socket(host, port);
                out = new ObjectOutputStream(s.getOutputStream());
                this.start();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                Registry registry = LocateRegistry.getRegistry(host, port);
                this.server = (RMIServerInterface) registry.lookup("RMIServer");
                //this.server = (RMIServerInterface) Naming.lookup("rmi://" + this.host + ":" + this.port + "/RMIServerInterface");

                this.clientHandler = new RMIClientHandlerImplementation();
                server.registerClient(clientHandler);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static ClientSR getInstance() {
        if (instance == null) instance = new ClientSR();
        return instance;
    }

    public void sendCommand(Command command) throws IOException {
        if (isSocket) {
            out.writeObject(command);
        } else {
            try {
                server.receiveCommand(this.clientHandler, command);
            } catch (Exception e) {
                System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
            }
        }


    }

    public void handleUpdate(Update update) {
        update.execute();
    }

    public void run() {
        try {

            in = new ObjectInputStream(s.getInputStream());

            while(!this.isInterrupted()) {
                Update up = (Update) in.readObject();
                up.execute();
            }

            in.close();
            out.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}