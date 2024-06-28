package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.GameComponents.GameTable;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

/**
 * The interface for the {@link it.polimi.ingsw.model.Game} model
 */
public interface GameInterface {
    /**
     * @return the game id
     */
    public int getGameID();
    /**
     * @return the players of the game
     */
    public ArrayList<Player> getPlayers();
    /**
     * @return the game status
     */
    public GameStatus getGameStatus();
    /**
     * @return the winner of the game
     */
    public Player getWinner();
    /**
     * @return the {@link it.polimi.ingsw.model.GameComponents.GameTable} table of the game
     */
    public GameTable getGameTable();
    /**
     * Add a player to the game
     * @param nickname the nickname of the player
     * @param color the color of the player
     */
    public void addPlayer(String nickname, Color color);
    /**
     * Remove a color after being picked by a player
     * @param color the color picked by the player
     */
    public void removeAvailableColor(Color color);
    /**
     * Set the game status
     * @param status the game status
     */
    public void setGameStatus(GameStatus status);
    /**
     * Set the winner of the game
     * @param player the player that won the game
     */
    public void setWinner(Player player);

}
