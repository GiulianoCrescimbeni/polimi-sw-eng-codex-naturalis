package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;

import java.io.Serializable;

public class CardPickedUpdate extends Update implements Serializable {
    private Card cardPicked;

    public Card getCardPicked() {
        return cardPicked;
    }

    public void setCardPicked(Card cardPicked) {
        this.cardPicked = cardPicked;
    }

    //TODO: Execute
}
