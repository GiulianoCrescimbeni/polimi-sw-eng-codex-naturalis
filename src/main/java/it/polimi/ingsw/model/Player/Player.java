package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.GameComponents.Coordinate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Player's Class
 */
public class Player implements Serializable {

    private String nickname;
    private Color color;
    private PlayerHand playerHand;

    /**
     * Constructor
     * @param nickname the nickname of the player
     * @param playerHand the {@link PlayerHand} of the player
     */
    public Player(String nickname, PlayerHand playerHand) {
        this.nickname = nickname;
        this.playerHand = playerHand;
    }

    /**
     * @return the nickname of the player
     */
    public String getNickname() {
        return nickname;
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
     * @param playerHand the player hand
     */
    public void setPlayerHand(PlayerHand playerHand) { this.playerHand = playerHand; }

    /**
     * @param color set the color of the player
     */
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player that = (Player) o;
        return this.nickname.equals(that.nickname);
    }
}
