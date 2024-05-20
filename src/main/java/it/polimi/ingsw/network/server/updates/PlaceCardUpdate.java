package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

public class PlaceCardUpdate extends Update implements Serializable {
    private String nickname;
    private boolean placedCorrectly;
    private String message;
    private Card cardPlaced;
    private Coordinate coordinate;
    private Player currentPlayer;

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

    @Override
    public void execute() {
        if(!isPlacedCorrectly()) {
            View.getInstance().updateChatView(getMessage());
        } else {
            ClientController.getInstance().updateTurn(getNickname(), getCoordinate(), getCardPlaced(), getCurrentPlayer());
            View.getInstance().updateInfo(null, true);
        }
    }
}
