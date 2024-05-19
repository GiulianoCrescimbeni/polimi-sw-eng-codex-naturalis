package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.client.commands.Command;
import it.polimi.ingsw.network.client.commands.CreateMatchCommand;
import it.polimi.ingsw.network.client.commands.JoinMatchCommand;
import it.polimi.ingsw.network.server.handler.ClientHandler;
import it.polimi.ingsw.network.server.updates.AvailableMatchesUpdate;
import it.polimi.ingsw.network.server.updates.Update;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.TextColor;

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
    private ArrayList<ClientHandler> waitingToJoin = new ArrayList<ClientHandler>();
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
        gameModel.initGame();
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
     * @param clientHandler The client handler added to the waiting list
     * @throws IOException
     */
    public void addConnectionToWaitingList(ClientHandler clientHandler) throws IOException {
        ArrayList<SerializedGame> availableMatches = new ArrayList<SerializedGame>();

        for (int gameId : controllers.keySet()) {
            SerializedGame sg = new SerializedGame(gameId, controllers.get(gameId).getModel().getPlayers().size(), controllers.get(gameId).getModel().getMaxPlayers());
            availableMatches.add(sg);
        }

        waitingToJoin.add(clientHandler);

        AvailableMatchesUpdate up = new AvailableMatchesUpdate(availableMatches, false, null);
        clientHandler.sendUpdate(up);
    }

    /**
     * Creates a match
     * @param clientHandler The client added to the match
     * @param cmd The command with the data to create the match
     * @throws IOException
     */
    public void createMatch(ClientHandler clientHandler, CreateMatchCommand cmd) throws IOException {
        waitingToJoin.remove(clientHandler);
        System.out.println(TextColor.BRIGHT_BLUE + "[GAMES MANAGER]" + TextColor.RESET + " Creating a new game");

        int gameId = setController();
        setConnection(clientHandler, gameId);

        getController(gameId).selectMaxPlayers(cmd.getMaxPlayers());

        System.out.println(TextColor.BRIGHT_BLUE + "[GAMES MANAGER]" + TextColor.RESET + " New game created with id: " + TextColor.BLUE + gameId + TextColor.RESET);
        System.out.println(TextColor.BRIGHT_BLUE + "[GAMES MANAGER]" + TextColor.RESET + " Max players of game: " + TextColor.BLUE + gameId + TextColor.RESET + " set to: " + TextColor.BRIGHT_YELLOW + cmd.getMaxPlayers() + TextColor.RESET);
        System.out.println(TextColor.BRIGHT_BLUE + "[GAMES MANAGER]" + TextColor.RESET + " Client added to game with id: " + TextColor.BLUE + gameId + TextColor.RESET);
        clientHandler.sendUpdate(getController(gameId).getAvailableColorsUpdate());
    }

    /**
     * Adds a client to a specified match
     * @param clientHandler The client added to the match
     * @param command The command with the data to add the client to the match
     * @throws IOException
     */
    public void joinMatch(ClientHandler clientHandler, JoinMatchCommand command) throws IOException {
        int gameId = command.getGameId();
        if (getController(gameId).getModel().isFull()) {

            ArrayList<SerializedGame> availableMatches = new ArrayList<SerializedGame>();

            for (int id : controllers.keySet()) {
                SerializedGame sg = new SerializedGame(id, controllers.get(id).getModel().getPlayers().size(), controllers.get(id).getModel().getMaxPlayers());
                availableMatches.add(sg);
            }

            AvailableMatchesUpdate up = new AvailableMatchesUpdate(availableMatches, true, Messages.getInstance().getErrorMessage("The match you're trying to join is full. Try another match"));
            clientHandler.sendUpdate(up);
        } else {

            setConnection(clientHandler, gameId);
            waitingToJoin.remove(clientHandler);
            System.out.println(TextColor.BRIGHT_BLUE + "[GAMES MANAGER]" + TextColor.RESET + " Client added to game with id: " + TextColor.BLUE + gameId + TextColor.RESET);

            clientHandler.sendUpdate(getController(gameId).getAvailableColorsUpdate());
        }
    }

    /**
     * Function to handle a command recieved from the client
     * @param clientHandler The client handler that received the command
     * @param command The command to be executed
     */
    public void handleCommand(ClientHandler clientHandler, Command command) throws IOException {
        Integer gameId;
        synchronized(connections) {
            gameId = connections.get(clientHandler);
        }
        Update update = command.execute(controllers.get(gameId));
        clientHandler.sendUpdate(update);
    }

    /**
     * Function to broadcast an update to each client in the specified game
     * @param gameId the id of the game
     * @param update the update to send
     */
    public synchronized void broadcast(int gameId, Update update) {
        connections.entrySet()
                .stream()
                .filter(c -> c.getValue() == gameId)
                .forEach(c -> {
                    try {
                        c.getKey().sendUpdate(update);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
