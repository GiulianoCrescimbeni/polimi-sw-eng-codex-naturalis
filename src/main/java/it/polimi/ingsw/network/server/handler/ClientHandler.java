package it.polimi.ingsw.network.server.handler;

import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;

public interface ClientHandler {

    public void sendUpdate(Update update) throws IOException;

}
