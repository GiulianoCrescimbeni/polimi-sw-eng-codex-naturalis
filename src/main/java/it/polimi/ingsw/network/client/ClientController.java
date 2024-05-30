package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.GameComponents.GoldCard;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.network.client.commands.LoginCommand;
import it.polimi.ingsw.network.client.commands.PlaceCardWithPickFromDeckCommand;
import it.polimi.ingsw.network.client.commands.PlaceCardWithPickFromGroundCommand;
import it.polimi.ingsw.network.client.commands.SelectPersonalGoalCommand;
import it.polimi.ingsw.view.TUI.View;
import it.polimi.ingsw.view.ViewInterface;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class ClientController {
    private static ClientController instance;
    private ViewInterface viewInterface;
    private ArrayList<Color> availableColors = new ArrayList<Color>();
    private ArrayList<Goal> goalsToPick = new ArrayList<>();
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

    private ClientController() {}

    public static ClientController getInstance() {
        if (instance == null) instance = new ClientController();
        return instance;
    }

    public ViewInterface getViewInterface() {
        return viewInterface;
    }

    public void setViewInterface(ViewInterface viewInterface) {
        this.viewInterface = viewInterface;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Goal getPersonalGoal() { return personalGoal; }

    public void setPersonalGoal(Goal personalGoal) {
        this.personalGoal = personalGoal;
    }

    public void updateAvailableColors(ArrayList<Color> availableColors) {
        this.availableColors = availableColors;
    }

    public ArrayList<Color> getAvailableColors() {
        return availableColors;
    }

    public ArrayList<Goal> getGoalsToPick() {
        return goalsToPick;
    }

    public void updateGoalsToPick(ArrayList<Goal> goalsToPick) {
        this.goalsToPick = goalsToPick;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Map<Player, Codex> getCodexMap() {
        return codexMap;
    }

    public void setCodexMap(Map<Player, Codex> codexMap) {
        this.codexMap = codexMap;
    }

    public ArrayList<Card> getCardToPick() {
        return cardToPick;
    }

    public void setCardToPick(ArrayList<Card> cardToPick) {
        this.cardToPick = cardToPick;
    }

    public ArrayList<Card> getGoldCardToPick() {
        return goldCardToPick;
    }

    public void setGoldCardToPick(ArrayList<Card> goldCardToPick) {
        this.goldCardToPick = goldCardToPick;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public ArrayList<Goal> getCommonGoals() {
        return commonGoals;
    }

    public void setCommonGoals(ArrayList<Goal> commonGoals) {
        this.commonGoals = commonGoals;
    }

    public PlayerHand getPlayerHand() {
        return players.stream().filter(player ->  player.getNickname().equals(this.getUsername())).findFirst().map(Player::getPlayerHand).orElse(null);
    }

    public void removeCardPlayed(Card cardPlayed) {
        Objects.requireNonNull(players.stream().filter(player -> player.getNickname().equals(this.getUsername())).findFirst().orElse(null)).getPlayerHand().removeCard(cardPlayed);
    }

    public void addCardPicked(Card cardPicked) {
        Objects.requireNonNull(players.stream().filter(player -> player.getNickname().equals(this.getUsername())).findFirst().orElse(null)).getPlayerHand().addCard(cardPicked);
    }

    public void updateGameData(Map<Player, Codex> codexMap, ArrayList<Card> cardToPick, ArrayList<Card> goldCardToPick, ArrayList<Player> players, Player currentPlayer, ArrayList<Goal> commonGoals) {
        gameStatus = GameStatus.RUNNING;
        setCodexMap(codexMap);
        setCardToPick(cardToPick);
        setGoldCardToPick(goldCardToPick);
        setPlayers(players);
        setCurrentPlayer(currentPlayer);
        setCommonGoals(commonGoals);
    }

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

    public void selectPersonalGoal(Goal goal) {
        SelectPersonalGoalCommand selectPersonalGoalCommand = new SelectPersonalGoalCommand(this.username, goal);
        try {
            ClientSR.getInstance().sendCommand(selectPersonalGoalCommand);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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

    public void updateTurn(String username, Coordinate coordinate, Card cardPlaced, Player currentPlayer, Card cardPicked, Card newGroundCard) {
        ClientController.getInstance().setCurrentPlayer(currentPlayer);
        Player player = getPlayerByUsername(username);
        Codex codex = ClientController.getInstance().getCodexMap().get(player);
        try {
            codex.placeCard(coordinate, cardPlaced);
        } catch (IllegalCardPlacementException | IllegalCoordinatesException e) {
            e.printStackTrace();
        }

        if(cardPicked != null && newGroundCard != null) {
            if(cardPicked.getClass() == Card.class) {
                getCardToPick().remove(cardPicked);
            } else if(cardPicked.getClass() == GoldCard.class) {
                getGoldCardToPick().remove(cardPicked);
            }
            if(newGroundCard.getClass() == Card.class) {
                getCardToPick().add(newGroundCard);
            } else if(newGroundCard.getClass() == GoldCard.class) {
                getGoldCardToPick().remove(newGroundCard);
            }
        }
    }

    public Player getPlayerByUsername(String nickname) {
        return (Player) getPlayers().stream().filter(player ->  player.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    public synchronized void addMessage(String message) {

        if (this.messageHistory == null) messageHistory = new ArrayList<String>();

        this.messageHistory.add(message);
    }

    public ArrayList<String> getMessages() {
        return this.messageHistory;
    }
}
