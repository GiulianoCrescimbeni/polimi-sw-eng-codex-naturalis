package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.GoldCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The update of the new card placed
 */
public class PlaceCardUpdate extends Update implements Serializable {
    private String nickname;
    private boolean placedCorrectly;
    private String message;
    private Card cardPlaced;
    private Coordinate coordinate;
    private Player currentPlayer;
    private Card cardPicked;
    private Card newGroundCard;

    /**
     * @return the nickname of the last current {@link Player}
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname of the last current {@link Player}
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return true if the card is placed correctly
     */
    public boolean isPlacedCorrectly() {
        return placedCorrectly;
    }

    /**
     * @param placedCorrectly set if the card is placed correctly
     */
    public void setPlacedCorrectly(boolean placedCorrectly) {
        this.placedCorrectly = placedCorrectly;
    }

    /**
     * @return the message in case of error
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message in case of error
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the new {@link Card} placed
     */
    public Card getCardPlaced() {
        return cardPlaced;
    }

    /**
     * @param card new {@link Card} placed
     */
    public void setCard(Card card) {
        this.cardPlaced = card;
    }

    /**
     * @return the {@link Coordinate} of the {@link Card} placed
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the {@link Coordinate} of the {@link Card} placed
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return the new current {@link Player}
     */
    public Player getCurrentPlayer() { return currentPlayer; }

    /**
     * @param currentPlayer set the new current {@link Player}
     */
    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }

    /**
     * @return the new {@link Card} picked
     */
    public Card getCardPicked() { return cardPicked; }

    /**
     * @param cardPicked the new {@link Card} picked
     */
    public void setCardPicked(Card cardPicked) { this.cardPicked = cardPicked; }

    /**
     * @return the new ground {@link Card}
     */
    public Card getNewGroundCard() { return newGroundCard; }

    /**
     * @param newGroundCard the new ground {@link Card}
     */
    public void setNewGroundCard(Card newGroundCard) { this.newGroundCard = newGroundCard; }

    /**
     * Update data to place the {@link Card}
     */
    @Override
    public void execute() {
        if(!isPlacedCorrectly()) {
            ClientController.getInstance().getViewInterface().updateInfo(getMessage(), false);
        } else {
            ClientController.getInstance().updateTurn(getNickname(), getCoordinate(), getCardPlaced(), getCurrentPlayer(), getCardPicked(), getNewGroundCard());
            if(ClientController.getInstance().isInitialSideChoosen())
                ClientController.getInstance().getViewInterface().updateInfo(null, true);
        }
    }
}
