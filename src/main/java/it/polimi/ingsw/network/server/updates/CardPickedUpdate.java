package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;

/**
 * Update for the new {@link Card} picked for the {@link it.polimi.ingsw.model.Player.Player}
 */
public class CardPickedUpdate extends Update implements Serializable {
    private Card cardPlaced;
    private Card cardPicked;

    /**
     * @return the {@link Card} placed from the {@link it.polimi.ingsw.model.Player.Player}
     */
    public Card getCardPlaced() { return cardPlaced; }

    /**
     * @param cardPlaced the {@link Card} placed from the {@link it.polimi.ingsw.model.Player.Player}
     */
    public void setCardPlaced(Card cardPlaced) { this.cardPlaced = cardPlaced; }

    /**
     * @return the {@link Card} picked from the {@link it.polimi.ingsw.model.Player.Player}
     */
    public Card getCardPicked() { return cardPicked; }

    /**
     * @param cardPicked the {@link Card} picked from the {@link it.polimi.ingsw.model.Player.Player}
     */
    public void setCardPicked(Card cardPicked) { this.cardPicked = cardPicked; }

    /**
     * Remove the {@link Card} played and pick the {@link Card} picked
     */
    @Override
    public void execute() {
        ClientController.getInstance().removeCardPlayed(getCardPlaced());
        ClientController.getInstance().addCardPicked(getCardPicked());
    }
}
