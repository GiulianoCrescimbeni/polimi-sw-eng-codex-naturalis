package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.GameComponents.*;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.network.client.commands.*;
import it.polimi.ingsw.network.server.ping.Pong;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.View;
import it.polimi.ingsw.view.ViewInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 * Class for the controller of the client
 */
public class ClientController {
    private static ClientController instance;
    private ViewInterface viewInterface;
    private ArrayList<Color> availableColors = new ArrayList<Color>();
    private ArrayList<Goal> goalsToPick = new ArrayList<>();
    private boolean initialSideChoosen;
    private String username;
    private Color color;
    private Goal personalGoal;
    private GameStatus gameStatus;
    private Map<Player, Codex> codexMap;
    private ArrayList<Card> cardToPick;
    private ArrayList<Card> goldCardToPick;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private ArrayList<Goal> commonGoals;
    private ArrayList<String> messageHistory;

    /**
     * Constructor
     */
    private ClientController() {}

    public static ClientController getInstance() {
        if (instance == null) instance = new ClientController();
        return instance;
    }

    /**
     * @return the {@link ViewInterface} of the client
     */
    public ViewInterface getViewInterface() {
        return viewInterface;
    }

    /**
     * @param viewInterface the {@link ViewInterface} of the client
     */
    public void setViewInterface(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    /**
     * @return the username of the player
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username of the player
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the {@link Color} of the player
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color of the player
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return the personal {@link Goal} of the player
     */
    public Goal getPersonalGoal() { return personalGoal; }

    /**
     * @param personalGoal the personal {@link Goal} of the player
     */
    public void setPersonalGoal(Goal personalGoal) {
        this.personalGoal = personalGoal;
    }

    /**
     * @param availableColors the ArrayList of available {@link Color}
     */
    public void updateAvailableColors(ArrayList<Color> availableColors) {
        this.availableColors = availableColors;
    }

    /**
     * @return the ArrayList of available {@link Color}
     */
    public ArrayList<Color> getAvailableColors() {
        return availableColors;
    }

    /**
     * @return the ArrayList of {@link Goal} to pick from
     */
    public ArrayList<Goal> getGoalsToPick() {
        return goalsToPick;
    }

    /**
     * @param goalsToPick the ArrayList of {@link Goal} to pick from
     */
    public void updateGoalsToPick(ArrayList<Goal> goalsToPick) {
        this.goalsToPick = goalsToPick;
    }

    /**
     * @return true if the side of the initial card is already chosen
     */
    public boolean isInitialSideChoosen() {
        return initialSideChoosen;
    }

    /**
     * set if the initial side of the card is already chosen
     */
    public void setInitialSideChoosen() {
        this.initialSideChoosen = true;
    }

    /**
     * @return the {@link GameStatus} of the game
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * @param gameStatus the {@link GameStatus} of the game
     */
    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    /**
     * @return the map of {@link Codex} foreach player
     */
    public Map<Player, Codex> getCodexMap() {
        return codexMap;
    }

    /**
     * @param codexMap the map of {@link Codex} foreach player
     */
    public void setCodexMap(Map<Player, Codex> codexMap) {
        this.codexMap = codexMap;
    }

    /**
     * @return the ArrayList of {@link Card} to pick from
     */
    public ArrayList<Card> getCardToPick() {
        return cardToPick;
    }

    /**
     * @param cardToPick the ArrayList of {@link Card} to pick from
     */
    public void setCardToPick(ArrayList<Card> cardToPick) {
        this.cardToPick = cardToPick;
    }

    /**
     * @return the ArrayList of {@link GoldCard} to pick from
     */
    public ArrayList<Card> getGoldCardToPick() {
        return goldCardToPick;
    }

    /**
     * @param goldCardToPick the ArrayList of {@link GoldCard} to pick from
     */
    public void setGoldCardToPick(ArrayList<Card> goldCardToPick) {
        this.goldCardToPick = goldCardToPick;
    }

    /**
     * @return the ArrayList of {@link Player} of the game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the ArrayList of {@link Player} of the game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * @return the current {@link Player}
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the current {@link Player}
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return an ArrayList of common {@link Goal}
     */
    public ArrayList<Goal> getCommonGoals() {
        return commonGoals;
    }

    /**
     * @param commonGoals an ArrayList of common {@link Goal}
     */
    public void setCommonGoals(ArrayList<Goal> commonGoals) {
        this.commonGoals = commonGoals;
    }

    /**
     * @return the {@link PlayerHand} of the player
     */
    public PlayerHand getPlayerHand() {
        return players.stream().filter(player ->  player.getNickname().equals(this.getUsername())).findFirst().map(Player::getPlayerHand).orElse(null);
    }

    /**
     * Remove the card player from the {@link PlayerHand}
     * @param cardPlayed the {@link Card} played from the player
     */
    public void removeCardPlayed(Card cardPlayed) {
        Objects.requireNonNull(players.stream().filter(player -> player.getNickname().equals(this.getUsername())).findFirst().orElse(null)).getPlayerHand().removeCard(cardPlayed);
    }

    /**
     * Add the card picked to the {@link PlayerHand}
     * @param cardPicked the {@link Card} picked from the player
     */
    public void addCardPicked(Card cardPicked) {
        Objects.requireNonNull(players.stream().filter(player -> player.getNickname().equals(this.getUsername())).findFirst().orElse(null)).getPlayerHand().addCard(cardPicked);
    }

    /**
     * Join a game
     * @param gameId the id of the game
     */
    public void JoinGame(int gameId) {
        JoinMatchCommand cmd = new JoinMatchCommand(gameId);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return true if is the turn of this client controller
     */
    public boolean isMyTurn() {
        if(ClientController.getInstance().getUsername().equals(ClientController.getInstance().getCurrentPlayer().getNickname())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Update the game data after joining a match
     * @param codexMap
     * @param cardToPick
     * @param goldCardToPick
     * @param players
     * @param currentPlayer
     * @param commonGoals
     */
    public void updateGameData(Map<Player, Codex> codexMap, ArrayList<Card> cardToPick, ArrayList<Card> goldCardToPick, ArrayList<Player> players, Player currentPlayer, ArrayList<Goal> commonGoals) {
        gameStatus = GameStatus.RUNNING;
        setCodexMap(codexMap);
        setCardToPick(cardToPick);
        setGoldCardToPick(goldCardToPick);
        setPlayers(players);
        setCurrentPlayer(currentPlayer);
        setCommonGoals(commonGoals);
    }

    /**
     * Send the username and the color to join the match
     * @param username the username of the player
     * @param color the {@link Color} of the player
     */
    public void sendUsernameAndColor(String username, Color color) {
        setUsername(username);
        setColor(color);
        LoginCommand lgcmd = new LoginCommand(username, color);
        try {
            ClientSR.getInstance().sendCommand(lgcmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Select the side of the initial card
     * @param side the side of the {@link InitialCard}
     * @throws IOException
     */
    public void selectInitialCardSide(int side) throws IOException {
        SelectInitialCardSideCommand selectInitialCardSideCommand;
        if(side == 0) {
            selectInitialCardSideCommand = new SelectInitialCardSideCommand(ClientController.getInstance().getUsername(), false);
        } else {
            selectInitialCardSideCommand = new SelectInitialCardSideCommand(ClientController.getInstance().getUsername(), true);
        }
        ClientSR.getInstance().sendCommand(selectInitialCardSideCommand);

    }

    /**
     * Select the personal goal for the player
     * @param goal the {@link Goal} chosen
     */
    public void selectPersonalGoal(Goal goal) {
        SelectPersonalGoalCommand selectPersonalGoalCommand = new SelectPersonalGoalCommand(this.username, goal);
        try {
            ClientSR.getInstance().sendCommand(selectPersonalGoalCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Play a card picking from the ground
     * @param coordinate the {@link Coordinate} of the card that has been played
     * @param cardPlaced the {@link Card} played
     * @param cardPicked the {@link Card} picked from the ground
     */
    public void playWithPickFromGround(Coordinate coordinate, Card cardPlaced, Card cardPicked) {
        PlaceCardWithPickFromGroundCommand command = new PlaceCardWithPickFromGroundCommand();
        command.setNickname(ClientController.getInstance().getUsername());
        command.setCoordinate(coordinate);
        command.setCardPlaced(cardPlaced);
        command.setCardPicked(cardPicked);
        try {
            ClientSR.getInstance().sendCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Play a card picking from the deck
     * @param coordinate the {@link Coordinate} of the card that has been played
     * @param cardPlaced the {@link Card} played
     * @param deckIndex the index of the {@link Deck} (0 for ResourceDeck, 1 for GoldDeck)
     */
    public void playWithPickFromDeck(Coordinate coordinate, Card cardPlaced, int deckIndex) {
        PlaceCardWithPickFromDeckCommand command = new PlaceCardWithPickFromDeckCommand();
        command.setNickname(ClientController.getInstance().getUsername());
        command.setCoordinate(coordinate);
        command.setCardPlaced(cardPlaced);
        command.setDeckIndex(deckIndex);
        try {
            ClientSR.getInstance().sendCommand(command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the turn
     * @param username the username of the last current {@link Player}
     * @param coordinate the {@link Coordinate} of the new {@link Card} placed
     * @param cardPlaced the new {@link Card} placed
     * @param currentPlayer the new current {@link Player}
     * @param cardPicked the {@link Card} picked from the last current {@link Player}
     * @param newGroundCard the new ground {@link Card}(If the last current {@link Player} picked from ground)
     */
    public void updateTurn(String username, Coordinate coordinate, Card cardPlaced, Player currentPlayer, Card cardPicked, Card newGroundCard) {
        ClientController.getInstance().setCurrentPlayer(currentPlayer);
        Player player = getPlayerByUsername(username);
        Codex codex = ClientController.getInstance().getCodexMap().get(player);
        if(cardPlaced.getClass() == Card.class) {
            try {
                codex.placeCard(coordinate, cardPlaced);
            } catch (IllegalCardPlacementException | IllegalCoordinatesException e) {
                e.printStackTrace();
            }
        } else if(cardPlaced.getClass() == GoldCard.class || cardPlaced.getClass() == ResourceGoldCard.class || cardPlaced.getClass() == AngleGoldCard.class){
            try {
                codex.placeGoldCard(coordinate, (GoldCard) cardPlaced);
            } catch (IllegalCardPlacementException | IllegalCoordinatesException e) {
                e.printStackTrace();
            }
        }


        if(cardPicked != null && newGroundCard != null) {
            if(cardPicked.getClass() == Card.class) {
                getCardToPick().remove(cardPicked);
            } else if(cardPicked.getClass() == GoldCard.class || cardPicked.getClass() == ResourceGoldCard.class || cardPicked.getClass() == AngleGoldCard.class) {
                getGoldCardToPick().remove(cardPicked);
            }
            if(newGroundCard.getClass() == Card.class) {
                getCardToPick().add(newGroundCard);
            } else if(newGroundCard.getClass() == GoldCard.class || newGroundCard.getClass() == ResourceGoldCard.class || newGroundCard.getClass() == AngleGoldCard.class) {
                getGoldCardToPick().add(newGroundCard);
            }
        }
    }

    /**
     * @param nickname the nickname of the player
     * @return Return the {@link Player} with the specified username
     */
    public Player getPlayerByUsername(String nickname) {
        return (Player) getPlayers().stream().filter(player ->  player.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    /**
     * Add a message to the message history
     * @param message the message to be added
     */
    public synchronized void addMessage(String message) {

        if (this.messageHistory == null) messageHistory = new ArrayList<String>();
        if (message == null || message.equals("")) return;

        this.messageHistory.add(message);
    }

    /**
     * @return an ArrayList of messages from the message history
     */
    public ArrayList<String> getMessages() {
        return this.messageHistory;
    }

    /**
     * Send a public message to all
     * @param args
     */
    public void sendPublicMessage(String[] args) {
        String[] message = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            message[i - 1] = args[i];
        }

        String toSend = String.join(" ", message);

        ChatMessageCommand cmd = new ChatMessageCommand(toSend, "public", getUsername());

        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Send a private message to a specified client
     * @param args
     */
    public void sendPrivateMessage(String[] args) {
        String[] message = new String[args.length - 1];
        for (int i = 1; i < args.length; i++) {
            message[i - 1] = args[i];
        }

        String toSend = String.join(" ", message);

        ChatMessageCommand cmd = new ChatMessageCommand(toSend, args[0], ClientController.getInstance().getUsername());

        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Send a pong to the server for activity checking
     */
    public void sendPong() {
        Pong pong = new Pong();

        try {
            ClientSR.getInstance().sendCommand(pong);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
