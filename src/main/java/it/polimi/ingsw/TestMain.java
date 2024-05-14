package it.polimi.ingsw;

import it.polimi.ingsw.model.Data.CardsLoader;
import it.polimi.ingsw.model.GameComponents.Deck;

public class TestMain {

    public static void main(String[] args) {
        Deck d = new Deck();
        d.buildInitialCardsDeck();
        System.out.println("" + d.getNumOfCards());
    }

}
