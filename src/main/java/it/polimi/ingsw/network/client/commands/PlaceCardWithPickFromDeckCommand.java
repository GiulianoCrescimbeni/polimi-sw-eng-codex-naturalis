package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.CardPickedUpdate;
import it.polimi.ingsw.network.server.updates.PlaceCardUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class PlaceCardWithPickFromDeckCommand extends Command implements Serializable {
    private Coordinate coordinate;
    private Card cardPlaced;
    private int deckIndex;

    /**
     * @return the coordinate of the card
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * @param coordinate the coordinate of the card
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @return the card to place in the codex
     */
    public Card getCardPlaced() {
        return cardPlaced;
    }

    /**
     * @param cardPlaced the card to place in the codex
     */
    public void setCardPlaced(Card cardPlaced) {
        this.cardPlaced = cardPlaced;
    }

    /**
     * @return the index of the deck (0: Resource Card Deck, 1: Gold Card Deck)
     */
    public int getDeckIndex() {
        return deckIndex;
    }

    /**
     * @param deckIndex the index of the deck (0: Resource Card Deck, 1: Gold Card Deck)
     */
    public void setDeckIndex(int deckIndex) {
        this.deckIndex = deckIndex;
    }

    /**
     * Execute the placement of the card
     * @param gameController the {@link Controller} of the game where the message needs to get executed
     * @return the {@link Update} with the result of the placement
     */
    @Override
    public Update execute(Controller gameController) {
        try {
            gameController.playWithPickFromDeck(getCoordinate(), getCardPlaced(), getDeckIndex());
            PlaceCardUpdate update = new PlaceCardUpdate();
            update.setNickname(gameController.getCurrentPlayer().getNickname());
            update.setPlacedCorrectly(true);
            update.setCard(getCardPlaced());
            update.setCoordinate(getCoordinate());
            GamesManager.getInstance().broadcast(gameController.getModel().getGameId(), update);
            CardPickedUpdate cardPickedUpdate = new CardPickedUpdate();
            //TODO: Implementare il set della carta cambiando la funzione con return della carta pescata
            //cardPickedUpdate.setCardPicked();
            return cardPickedUpdate;
        } catch (IllegalCoordinatesException | IllegalCardPlacementException e) {
            PlaceCardUpdate update = new PlaceCardUpdate();
            update.setMessage(e.getMessage());
            update.setPlacedCorrectly(false);
            return update;
        }
    }
}
