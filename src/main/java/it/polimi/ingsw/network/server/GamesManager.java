package it.polimi.ingsw.network.server;

import com.sun.security.ntlm.Client;
import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.util.HashMap;
import java.util.Map;

public class GamesManager {
    private Map<Integer, Controller> controllers = new HashMap<>();
    private Map<ClientHandler, Integer> connections = new HashMap<>();

    public GamesManager() {}

    public void setController(Integer gameId) {
        Controller gameController = new Controller();
        controllers.put(gameId, gameController);
    }

    public Controller getController(Integer gameId) {
        return controllers.get(gameId);
    }

    public void setConnection(ClientHandler newClient, Integer gameId) {
        connections.put(newClient, gameId);
    }

    public Integer getGameId(ClientHandler clientHandler) {
        return connections.get(clientHandler);
    }

    public void handleCommand(ClientHandler clientHandler, Command command) {

    }
}
