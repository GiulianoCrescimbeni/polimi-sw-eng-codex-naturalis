package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.handler.RMIClientHandler;
import it.polimi.ingsw.network.server.handler.SocketClientHandler;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.IOException;
import java.util.*;

/**
 * Class that manage all games and connections.
 * The main purpose of the class is to execute messages from clients
 * on the correct Game {@link Controller}.
 * The class is a Singleton, so it gets instantiated only one time
 */
public class GamesManager {
    private Map<Integer, Controller> controllers = new HashMap<>();
    private Map<ClientHandler, Integer> connections = new HashMap<>();
    private Integer lastGameId;
    private int lastGameNumOfPartecipants = 0;

    private static GamesManager instance;

    /**
     * Constructor
     */
    private GamesManager() {}

    /**
     * Instance getter
     * @return the instance of the class
     */
    public static GamesManager getInstance() {
        if (instance == null) instance = new GamesManager();
        return instance;
    }

    /**
     * Create a new {@link Game} and its {@link Controller}
     * @return a random generated id of that game
     */
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

    /**
     * @param gameId the id of the {@link Game}
     * @return the controller of that game
     */
    public Controller getController(Integer gameId) {
        return controllers.get(gameId);
    }

    /**
     * Link a new {@link ClientHandler} to a {@link Controller}
     * @param newClient the new client handler
     * @param gameId the id of the game
     */
    public void setConnection(ClientHandler newClient, Integer gameId) {
        connections.put(newClient, gameId);
    }

    /**
     * @param clientHandler
     * @return the id of the game linked to that client handler
     */
    public Integer getGameId(ClientHandler clientHandler) {
        return connections.get(clientHandler);
    }

    /**
     * Manage a new connection to the server
     * @param clientHandler the new client handler to handle
     * @throws IOException
     */
    public void addConnection(ClientHandler clientHandler) throws IOException {

        Integer gameId = null;

        if (lastGameId == null) {
            System.out.println("[GAMES MANAGER] Last game ID is null, creating a new game");

            gameId = setController();
            setConnection(clientHandler, gameId);

            System.out.println("[GAMES MANAGER] New game created with id: " + gameId);

            lastGameId = gameId;
            lastGameNumOfPartecipants = 1;

        } else if (lastGameId != null && lastGameNumOfPartecipants < 4) {

            gameId = lastGameId;
            setConnection(clientHandler, gameId);

            lastGameNumOfPartecipants++;

        } else if (lastGameId != null && lastGameNumOfPartecipants == 4) {

            System.out.println("[GAMES MANAGER] Last game is full, creating a new game");

            gameId = setController();
            setConnection(clientHandler, gameId);

            System.out.println("[GAMES MANAGER] New game created with id: " + gameId);

            lastGameId = gameId;
            lastGameNumOfPartecipants = 1;

        }

        Controller c = getController(gameId);
        clientHandler.sendUpdate(c.getAvailableColorsUpdate());

    }

    /**
     * Function to handle a command recieved from the client
     * @param clientHandler The client handler that received the command
     * @param command The command to be executed
     */
    public void handleCommand(ClientHandler clientHandler, Command command) throws IOException {
        Integer gameId = connections.get(clientHandler);
        Update update = command.execute(controllers.get(gameId));
        clientHandler.sendUpdate(update);
    }

    /**
     * Function to broadcast an update to each client in the game
     * @param gameId the id of the game
     * @param update the update to send
     */
    public void broadcast(int gameId, Update update) {
    }
}
