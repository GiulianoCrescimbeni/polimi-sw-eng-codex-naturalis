package it.polimi.ingsw.model.Data;

import org.junit.jupiter.api.Test;

class CardsLoaderTest {

    SerializedGame serializedGame = new SerializedGame(0, 0, 0);
    @Test
    void loadCards() {
        CardsLoader.getInstance().loadCards();
        serializedGame.getMaxPlayers();
        serializedGame.getGameID();
        serializedGame.getCurrentPlayers();
    }

    @Test
    void loadGoldCards() {
        CardsLoader.getInstance().loadGoldCards();
    }

    @Test
    void loadObjectivesCards() {
        CardsLoader.getInstance().loadObjectivesCards();
    }

    @Test
    void loadInitialCards() {
        CardsLoader.getInstance().loadInitialCards();
    }
}