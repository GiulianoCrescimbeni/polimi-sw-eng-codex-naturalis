package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.handler.RMIClientHandler;
import it.polimi.ingsw.network.server.handler.SocketClientHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GamesManager {
    private Map<Integer, Controller> controllers = new HashMap<>();
    private Map<ClientHandler, Integer> connections = new HashMap<>();
    private Integer lastGameId;
    private int lastGameNumOfPartecipants = 0;

    private static GamesManager instance;

    private GamesManager() {}

    public static GamesManager getInstance() {
        if (instance == null) instance = new GamesManager();
        return instance;
    }

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

    public void addConnection(ClientHandler clientHandler) throws IOException {

        Integer gameId = null;

        if (lastGameId == null) {
            System.out.println("[GAMES MANAGER] Last game ID is null, creating a new game");

            if (clientHandler instanceof SocketClientHandler) {
                SocketClientHandler handler = (SocketClientHandler) clientHandler;
                //handler.interruptThread();
            } else if (clientHandler instanceof RMIClientHandler) {
                RMIClientHandler handler = (RMIClientHandler) clientHandler;
            }

            gameId = setController();
            setConnection(clientHandler, gameId);

            System.out.println("[GAMES MANAGER] New game created with id: " + gameId);

            Controller gameController = controllers.get(gameId);

            lastGameId = gameId;
            lastGameNumOfPartecipants = 1;

        } else if (lastGameId != null && lastGameNumOfPartecipants < 4) {

            if (clientHandler instanceof SocketClientHandler) {
                SocketClientHandler handler = (SocketClientHandler) clientHandler;
                //handler.interruptThread();
            } else if (clientHandler instanceof RMIClientHandler) {
                RMIClientHandler handler = (RMIClientHandler) clientHandler;
            }

            gameId = lastGameId;
            setConnection(clientHandler, gameId);

            Controller gameController = controllers.get(gameId);

            lastGameNumOfPartecipants++;

        } else if (lastGameId != null && lastGameNumOfPartecipants == 4) {

            System.out.println("[GAMES MANAGER] Last game is full, creating a new game");

            if (clientHandler instanceof SocketClientHandler) {
                SocketClientHandler handler = (SocketClientHandler) clientHandler;
                //handler.interruptThread();
            } else if (clientHandler instanceof RMIClientHandler) {
                RMIClientHandler handler = (RMIClientHandler) clientHandler;
            }

            gameId = setController();
            setConnection(clientHandler, gameId);

            System.out.println("[GAMES MANAGER] New game created with id: " + gameId);

            Controller gameController = controllers.get(gameId);

            lastGameId = gameId;
            lastGameNumOfPartecipants = 1;

        }

        Controller c = getController(gameId);
        clientHandler.sendUpdate(c.getAvailableColorsUpdate());


    }

    /**
     *
     * @param clientHandler The client handler that received the command
     * @param command The command to be executed
     */
    public void handleCommand(ClientHandler clientHandler, Command command) {
        Integer gameId = connections.get(clientHandler);
        command.execute(controllers.get(gameId));
    }
}
