package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;

/**
 * PlayerHand's class
 */
public class PlayerHand {

    private ArrayList<Card> cards;

    /**
     * @return cards in the player's hand
     */
    public ArrayList<Card> getCards() {
        return this.cards;
    }

    /**
     * Function to add a card to the player's hand
     * @param cardToAdd the card to add
     */
    public void addCard(Card cardToAdd) {
        cards.add(cardToAdd);
    }

    /**
     * Function to remove a card from the player's hand
     * @param cardToRemove the card to remove
     */
    public void removeCard(Card cardToRemove) {
        cards.remove(cardToRemove);
    }

}
