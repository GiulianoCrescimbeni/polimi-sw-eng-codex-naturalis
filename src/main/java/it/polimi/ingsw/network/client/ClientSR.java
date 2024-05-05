package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSR extends Thread {

    private Socket s;
    private String host;
    private int port;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    private static ClientSR instance;

    private ClientSR() {}

    public void startSR(String host, int port) {
        this.host = host;
        this.port = port;

        try{

            s = new Socket(host, port);

            out = new ObjectOutputStream(s.getOutputStream());

            this.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ClientSR getInstance() {
        if (instance == null) instance = new ClientSR();
        return instance;
    }

    public void sendCommand(Command command) throws IOException {
        out.writeObject(command);
    }

    public void run() {
        try {

            in = new ObjectInputStream(s.getInputStream());

            while(!this.isInterrupted()) {
                Update up = (Update) in.readObject();
                ClientManager.getInstance().handleUpdate(up);
            }

            in.close();
            out.close();
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
