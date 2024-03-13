package it.polimi.ingsw.model.Player;

import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;

public class PlayerDeck {

    private ArrayList<Card> cards;

    public PlayerDeck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }

}
