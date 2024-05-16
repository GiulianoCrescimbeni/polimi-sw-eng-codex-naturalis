package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;

import java.io.Serializable;

public class PlaceCardUpdate extends Update implements Serializable {
    private String nickname;
    private boolean placedCorrectly;
    private String message;
    private Card card;
    private Coordinate coordinate;

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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    //TODO: Execute del comando
}
