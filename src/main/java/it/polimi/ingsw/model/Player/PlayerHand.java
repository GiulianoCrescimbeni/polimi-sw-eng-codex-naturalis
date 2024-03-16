package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;

public class PlayerHand {

    private ArrayList<Card> cards;

    public PlayerHand(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

}
