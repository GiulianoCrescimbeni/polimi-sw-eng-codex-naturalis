package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;

public class CardPickedUpdate extends Update implements Serializable {
    private Card cardPlaced;
    private Card cardPicked;

    public Card getCardPlaced() { return cardPlaced; }

    public void setCardPlaced(Card cardPlaced) { this.cardPlaced = cardPlaced; }

    public Card getCardPicked() { return cardPicked; }

    public void setCardPicked(Card cardPicked) { this.cardPicked = cardPicked; }

    @Override
    public void execute() {
        ClientController.getInstance().removeCardPlayed(getCardPlaced());
        ClientController.getInstance().addCardPicked(getCardPicked());
    }
}
