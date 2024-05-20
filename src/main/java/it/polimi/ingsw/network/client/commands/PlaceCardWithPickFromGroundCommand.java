package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.CardPickedUpdate;
import it.polimi.ingsw.network.server.updates.PlaceCardUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class PlaceCardWithPickFromGroundCommand extends Command implements Serializable {
    private Coordinate coordinate;
    private Card cardPlaced;
    private Card cardPicked;

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
     * @return the card picked from the player
     */
    public Card getCardPicked() {
        return cardPicked;
    }

    /**
     * @param cardPicked the card picked from the player
     */
    public void setCardPicked(Card cardPicked) {
        this.cardPicked = cardPicked;
    }

    /**
     * Execute the placement of the card
     * @param gameController the {@link Controller} of the game where the message needs to get executed
     * @return the {@link Update} with the result of the placement
     */
    @Override
    public Update execute(Controller gameController) {
        PlaceCardUpdate update = new PlaceCardUpdate();
        try {
            PlayerHand playerHand = gameController.getCurrentPlayer().getPlayerHand();
            update.setNickname(gameController.getCurrentPlayer().getNickname());
            gameController.playWithPickFromGround(getCoordinate(), getCardPlaced(), getCardPlaced());
            update.setPlacedCorrectly(true);
            update.setCard(getCardPlaced());
            update.setCoordinate(getCoordinate());
            update.setCurrentPlayer(gameController.getCurrentPlayer());
            GamesManager.getInstance().broadcast(gameController.getModel().getGameID(), update);
            CardPickedUpdate cardPickedUpdate = new CardPickedUpdate();
            cardPickedUpdate.setPlayerHand(playerHand);
            return cardPickedUpdate;
        } catch (IllegalCoordinatesException | IllegalCardPlacementException e) {
            update.setMessage(e.getMessage());
            update.setPlacedCorrectly(false);
            return update;
        }
    }
}