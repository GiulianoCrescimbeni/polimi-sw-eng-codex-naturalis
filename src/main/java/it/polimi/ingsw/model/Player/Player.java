package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Enumerations.Color;

public class Player {
    int gameID;
    Color color;
    PlayerDeck playerDeck;

    public Player(int gameID, Color color) {
        this.gameID = gameID;
        this.color = color;
    }

    public int getGameID() {
        return gameID;
    }

    public Color getColor() {
        return color;
    }

    public PlayerDeck getPlayerDeck() {
        return playerDeck;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
