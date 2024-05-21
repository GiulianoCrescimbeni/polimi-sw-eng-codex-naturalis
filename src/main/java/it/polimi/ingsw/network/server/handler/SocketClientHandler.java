package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.client.commands.CreateMatchCommand;
import it.polimi.ingsw.network.client.commands.JoinMatchCommand;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Class that represent the handler of a client connected with Socket technology
 */
public class SocketClientHandler extends Thread implements ClientHandler {

    private Socket clientSocket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    /**
     * Constructor
     * @param socket the {@link Socket} of the client
     * @throws IOException
     */
    public SocketClientHandler(Socket socket) throws IOException {
        this.clientSocket = socket;

        this.out = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    /**
     * @return the {@link ObjectOutputStream} of the connection
     */
    public ObjectOutputStream getOutputStream() {
        return this.out;
    }

    /**
     * @return the {@link ObjectInputStream} of the connection
     */
    public ObjectInputStream getInputStream() {
        return this.in;
    }

    /**
     * Send an update to the client
     * @param toSend the update to send
     * @throws IOException
     */
    @Override
    public void sendUpdate(Update toSend) throws IOException {
        out.writeObject(toSend);
    }

    /**
     * Handle commands sent by the client
     */
    public void run() {
        try {

            this.in = new ObjectInputStream(clientSocket.getInputStream());

            while(!this.isInterrupted()) {
                Command c = (Command) in.readObject();
                //System.out.println("[SOCKET HANDLER] Received Command, type: " + c.getClass().toString());

                if (c instanceof CreateMatchCommand) {
                    GamesManager.getInstance().createMatch(this, (CreateMatchCommand) c);
                } else if (c instanceof JoinMatchCommand) {
                    GamesManager.getInstance().joinMatch(this, (JoinMatchCommand) c);
                } else {
                    GamesManager.getInstance().handleCommand(this, c);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Interrupt the thread
     */
    public void interruptThread() {
        this.interrupt();
    }
}
