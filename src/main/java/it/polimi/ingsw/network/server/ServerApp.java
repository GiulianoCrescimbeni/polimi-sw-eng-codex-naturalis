package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.server.handler.SocketClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerApp extends Thread {

    private ServerSocket socketServer;

    private ArrayList<SocketClientHandler> socHandlers;

    private GamesManager manager;

    public void startSocket(int port) throws IOException {
        try {
            socketServer = new ServerSocket(port);
            socHandlers = new ArrayList<>();
            manager = new GamesManager();
            this.start();
            System.out.println("[SOCKET SERVER] Server Socket Ready");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[ERROR] STARTING SOCKET SERVER");
        }
    }

    public void run() {
        try {
            while (!this.isInterrupted()) {
                socHandlers.add(new SocketClientHandler(socketServer.accept()));
                socHandlers.get(socHandlers.size() - 1).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
