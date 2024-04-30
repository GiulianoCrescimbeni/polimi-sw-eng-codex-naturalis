package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.network.server.GamesManager;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientHandler extends Thread implements ClientHandler {

    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private GamesManager gamesManager;

    public SocketClientHandler(Socket socket, GamesManager gamesManager) throws IOException {
        this.clientSocket = socket;
        this.in = new ObjectInputStream(clientSocket.getInputStream());
        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
        this.gamesManager = gamesManager;
    }

    public void run() {
        try {
            while(!this.isInterrupted()) {
                Command c = (Command) in.readObject();
                System.out.println("[SOCKET HANDLER] Received Command, type: " + c.getClass().toString());
                gamesManager.handleCommand(this, c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void interruptThread() {
        this.interrupt();
    }
}
