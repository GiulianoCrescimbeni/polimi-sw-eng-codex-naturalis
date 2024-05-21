package it.polimi.ingsw.network.server;

import it.polimi.ingsw.view.TUI.View;

import java.io.IOException;
import java.util.Scanner;

public class MainServer {

    public static void main(String[] args) throws IOException {

        ServerApp server = new ServerApp();
        server.startSocket(25565);

        RMIServer rmiserver = new RMIServer(25566);
        rmiserver.startRMI();
        }


    }


