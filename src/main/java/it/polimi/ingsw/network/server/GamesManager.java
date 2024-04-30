package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.handler.RMIClientHandler;
import it.polimi.ingsw.network.server.handler.SocketClientHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GamesManager {
    private Map<Integer, Controller> controllers = new HashMap<>();
    private Map<ClientHandler, Integer> connections = new HashMap<>();

    public GamesManager() {}

    public int setController() {

        Random r = new Random();
        int id = r.nextInt();

        if (id < 0){
            id = id * -1;
        }

        boolean exists = controllers.containsKey(id);

        while(exists) {
            id = r.nextInt();

            if (id < 0){
                id = id * -1;
            }

            exists = controllers.containsKey(id);
        }

        Game gameModel = new Game(id);
        Controller gameController = new Controller(gameModel);
        controllers.put(id, gameController);

        return id;
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

    /**
     *
     * @param clientHandler The client handler that received the command
     * @param command The command to be executed
     */

    public void handleCommand(ClientHandler clientHandler, Command command) {

        Integer gameId = connections.get(clientHandler);

        if (command instanceof LoginCommand && gameId == null) {
            System.out.println("[GAMES MANAGER] Game ID is null, creating a new game");

            if (clientHandler instanceof SocketClientHandler) {
                SocketClientHandler handler = (SocketClientHandler) clientHandler;
                handler.interruptThread();
            } else if (clientHandler instanceof RMIClientHandler) {
                RMIClientHandler handler = (RMIClientHandler) clientHandler;
            }

            gameId = setController();
            setConnection(clientHandler, gameId);

        }

        System.out.println("[GAMES MANAGER] New game created with id: " + gameId);

        Controller gameController = controllers.get(gameId);
        command.setGameController(gameController);
        command.execute();
    }
}
