package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.server.RMI.RMIServer;
import it.polimi.ingsw.network.server.socket.SocketServer;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {

        SocketServer server = new SocketServer();
        server.startSocket(25565);

        RMIServer rmiserver = new RMIServer(25566);
        rmiserver.startRMI();
        }


    }


