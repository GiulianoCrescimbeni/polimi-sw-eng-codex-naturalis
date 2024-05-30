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

public class PlaceCardUpdate extends Update implements Serializable {
    private String nickname;
    private boolean placedCorrectly;
    private String message;
    private Card cardPlaced;
    private Coordinate coordinate;
    private Player currentPlayer;
    private Card cardPicked;
    private Card newGroundCard;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isPlacedCorrectly() {
        return placedCorrectly;
    }

    public void setPlacedCorrectly(boolean placedCorrectly) {
        this.placedCorrectly = placedCorrectly;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Card getCardPlaced() {
        return cardPlaced;
    }

    public void setCard(Card card) {
        this.cardPlaced = card;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Player getCurrentPlayer() { return currentPlayer; }

    public void setCurrentPlayer(Player currentPlayer) { this.currentPlayer = currentPlayer; }

    public Card getCardPicked() { return cardPicked; }

    public void setCardPicked(Card cardPicked) { this.cardPicked = cardPicked; }

    public Card getNewGroundCard() { return newGroundCard; }

    public void setNewGroundCard(Card newGroundCard) { this.newGroundCard = newGroundCard; }

    @Override
    public void execute() {
        if(!isPlacedCorrectly()) {
            ClientController.getInstance().getViewInterface().updateInfo(getMessage(), false);
        } else {
            ClientController.getInstance().updateTurn(getNickname(), getCoordinate(), getCardPlaced(), getCurrentPlayer(), getCardPicked(), getNewGroundCard());
            ClientController.getInstance().getViewInterface().updateInfo(null, true);
        }
    }
}
