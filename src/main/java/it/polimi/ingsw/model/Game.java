package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.GameTable;
import it.polimi.ingsw.model.Interfaces.GameInterface;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Class that represents the game model
 */
public class Game implements GameInterface {

    private int gameID;
    private ArrayList<Player> players;
    private ArrayList<Color> availableColors;
    private GameStatus gameStatus;
    private Player winner;
    private GameTable table;
    private int maxPlayers;

    /**
     * Constructor
     * @param gameID The ID of the game
     * @param players an arraylist of {@link Player} of the game
     * @param availableColors an arraylist of possible {@link Color} that the player can pick
     * @param gameStatus the {@link GameStatus} of the game
     * @param winner the winner of the game
     * @param table the {@link GameTable} linked to the game
     */
    public Game(int gameID, ArrayList<Player> players, ArrayList<Color> availableColors, GameStatus gameStatus, Player winner, GameTable table, int maxPlayers) {
        this.gameID = gameID;
        this.players = players;
        this.availableColors = availableColors;
        this.gameStatus = gameStatus;
        this.winner = winner;
        this.table = table;
        this.maxPlayers = maxPlayers;
    }

    /**
     * Constructor
     */
    public Game() {}

    /**
     *
     * @param gameId The ID for the game
     *               This constructor fills automatically all the private fields of this class
     *               Sets the Game Status to "WAITING_TO_START"
     */
    public Game(int gameId) {
        this.gameID = gameId;
        this.players = new ArrayList<Player>();
        this.availableColors = new ArrayList<Color>();

        this.availableColors.add(Color.BLUE);
        this.availableColors.add(Color.YELLOW);
        this.availableColors.add(Color.GREEN);
        this.availableColors.add(Color.RED);

        this.gameStatus = GameStatus.WAITING_TO_START;
        this.table = new GameTable();
    }

    /**
     * @return the game id
     */
    public int getGameID() { return gameID; }

    /**
     * @return the players of the game
     */
    public ArrayList<Player> getPlayers() { return players; }

    /**
     * Get a {@link Player} by its nickname
     * @param nickname the nickname of the player
     * @return the player with that nickname
     */
    public Player getPlayerByNickname(String nickname) {
        return (Player) players.stream().filter(player ->  player.getNickname().equals(nickname)).findFirst().orElse(null);
    }

    /**
     * @return the colors available for players
     */
    public ArrayList<Color> getAvailableColors() { return availableColors; }

    /**
     * @return the game status
     */
    public GameStatus getGameStatus() { return gameStatus; }

    /**
     * @return the winner of the game
     */
    public Player getWinner() { return winner; }

    /**
     * @return the {@link it.polimi.ingsw.model.GameComponents.GameTable} table of the game
     */
    @Override
    public GameTable getGameTable() {
        return table;
    }

    /**
     * @return the game table
     */
    public GameTable getTable() { return table; }

    /**
     * Add a player to the game
     * @param nickname the nickname of the player
     * @param color the color of the player
     */
    public void addPlayer(String nickname, Color color) {
        ArrayList<Card> cards = new ArrayList<>();
        PlayerHand ph = new PlayerHand(cards);
        Player p = new Player(nickname, ph);
        p.setColor(color);

        this.removeAvailableColor(color);
        this.players.add(p);
        this.table.playerHandBuild(p);
        this.table.pickInitialCard(p);
        this.table.extractPersonalGoal(p);
    }

    /**
     * Remove a color after being picked by a player
     * @param color the color picked by the player
     */
    public void removeAvailableColor(Color color) { this.availableColors.remove(color); }

    /**
     * Set the game status
     * @param status the game status
     */
    public void setGameStatus(GameStatus status) {
        this.gameStatus = status;
    }

    /**
     * Set the winner of the game
     * @param winner the player that won the game
     */
    public void setWinner(Player winner) {
        if (this.players.contains(winner)) {
            this.winner = winner;
        }
    }

    /**
     * @return return the max player of the game
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     *
     * @return true if the game is full
     */
    public boolean isFull() {
        if (this.players.size() == this.maxPlayers) {
            return true;
        } else return false;
    }

    /**
     * Create the game
     */
    public void initGame() {
        this.table = new GameTable();
        this.table.setGameModel(this);
        this.table.initialCardDeckBuild();
        this.table.goalsDeckBuild();
        this.table.cardDeckBuild();
        this.table.goldCardDeckBuild();
        this.table.codexBuild();
        this.table.groundBuild();
        this.table.commonGoalsExtraction();
    }

    public void startGame() {
        this.setGameStatus(GameStatus.RUNNING);
        selectPlayerOrdering();
    }

    /**
     * Create the order of the players
     */
    private void selectPlayerOrdering(){
        Collections.shuffle(players);
        this.table.setCurrentPlayer(players.get(0));
    }

    /**
     * Select the next player
     */
    public void switchCurrentPlayer(){
        int lastIndex = players.size() - 1;
        int currIndex = players.indexOf( this.table.getCurrentPlayer() );

        if(  currIndex == lastIndex ){
            this.table.setCurrentPlayer(players.get(0));
        }
        else{
            this.table.setCurrentPlayer(players.get(currIndex++));
        }
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
}
