package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.GameComponents.GameTable;
import it.polimi.ingsw.model.Player.Player;
import java.util.ArrayList;

public class Game {

    private int gameID;
    private ArrayList<Player> players;
    private ArrayList<Color> availableColors;
    private Player currentPlayer;
    private GameStatus gameStatus;
    private Player winner;
    private GameTable table;

    public Game(int gameID, ArrayList<Player> players, ArrayList<Color> availableColors, Player currentPlayer, GameStatus gameStatus, Player winner, GameTable table) {
        this.gameID = gameID;
        this.players = players;
        this.availableColors = availableColors;
        this.currentPlayer = currentPlayer;
        this.gameStatus = gameStatus;
        this.winner = winner;
        this.table = table;
    }

    public int getGameID() {
        return gameID;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Color> getAvailableColors() {
        return availableColors;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public GameTable getTable() {
        return table;
    }

    public void addPlayer(Player toAdd) {
        this.players.add(toAdd);
    }

    public void removeAvailableColor(int position) {
        this.availableColors.remove(position);
    }

    public void setCurrentPlayer(Player current) {
        if (players.contains(current)) {
            this.currentPlayer = current;
        }
    }

    public void setGameStatus(GameStatus status) {
        this.gameStatus = status;
    }

    public void setWinner(Player winner) {
        if (this.players.contains(winner)) {
            this.winner = winner;
        }
    }
}
