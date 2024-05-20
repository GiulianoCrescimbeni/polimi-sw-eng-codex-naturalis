package it.polimi.ingsw.model.Data;

import java.io.Serializable;

public class SerializedGame implements Serializable {

    private int gameID;
    private int currentPlayers;
    private int maxPlayers;

    public SerializedGame(int gameID, int currentPlayers, int maxPlayers) {
        this.gameID = gameID;
        this.currentPlayers = currentPlayers;
        this.maxPlayers = maxPlayers;
    }

    public int getGameID() {
        return gameID;
    }

    public int getCurrentPlayers() {
        return currentPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }
}