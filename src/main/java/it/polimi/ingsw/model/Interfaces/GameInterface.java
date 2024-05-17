package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.GameComponents.GameTable;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public interface GameInterface {

    public int getGameID();
    public ArrayList<Player> getPlayers();
    public GameStatus getGameStatus();
    public Player getWinner();
    public GameTable getGameTable();
    public void addPlayer(String nickname, Color color);
    public void removeAvailableColor(Color color);
    public void setGameStatus(GameStatus status);
    public void setWinner(Player player);

}
