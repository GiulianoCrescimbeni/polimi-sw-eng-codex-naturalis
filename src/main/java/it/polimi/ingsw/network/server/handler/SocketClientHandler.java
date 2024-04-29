package it.polimi.ingsw.network.server.handler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketClientHandler extends Thread implements ClientHandler {

    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public SocketClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;
        in = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    public void run() {
        try {
            while(!this.isInterrupted()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void interruptThread() {
        this.interrupt();
    }
}
