package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Enumerations.Color;

/**
 * Player's Class
 */
public class Player {
    private int gameID;
    private Color color;
    private PlayerHand playerHand;

    /**
     * Constructor
     * @param gameID the ID of the game
     * @param color color chosen from the player
     */
    public Player(int gameID, Color color) {
        this.gameID = gameID;
        this.color = color;
    }

    /**
     * @return the ID of the game
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * @return the color of the player
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return the {@link PlayerHand} of the player
     */
    public PlayerHand getPlayerHand() {
        return playerHand;
    }

    /**
     * @param color set the color of the player
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
