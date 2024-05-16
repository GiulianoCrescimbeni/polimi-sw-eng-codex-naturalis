package it.polimi.ingsw.network.server;

import it.polimi.ingsw.view.TUI.View;

import java.io.IOException;

public class MainServer {

    public static void main(String[] args) throws IOException {
        ServerApp server = new ServerApp();
        server.startSocket(25565);
    }

}
