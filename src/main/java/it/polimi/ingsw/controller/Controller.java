package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.GameComponents.GoldCard;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;

import java.util.ArrayList;

public class Controller {
    private Game model;
    public boolean isGameStarted(){
       if ( model.getGameStatus() == GameStatus.RUNNING )
           return true;
       else
           return false;
    }

    public ArrayList<Color> getAvailableColors() {
        return model.getAvailableColors();

    }

    public void addPlayer(String nickname, Color color) {
        if (model.getPlayers().size()<4){
            model.removeAvailableColor(color);
            PlayerHand ph = new PlayerHand();
            Player p = new Player(nickname, color, ph);
            model.addPlayer(p);
        }
        if (model.getPlayers().size() == 4) {
            model.initGame();
        }
    }
    public void playWithPickFromGround(Coordinate coordinate, Card cardPlayed, Card cardPicked) {
        Player currentPlayer = model.getTable().getCurrentPlayer();
        try {
            if(cardPlayed.getClass() == Card.class) {
                model.getGameTable().getCodex(currentPlayer).placeCard(coordinate, cardPlayed);
            } else if(cardPlayed.getClass() == GoldCard.class) {
                model.getGameTable().getCodex(currentPlayer).placeGoldCard(coordinate, (GoldCard) cardPlayed);
            }
        } catch (IllegalCoordinatesException | IllegalCardPlacementException e) {
            throw new RuntimeException(e);
        }
        model.getGameTable().pickCardFromGround(cardPicked);
    }

    public void playWithPickFromDeck(Coordinate coordinate, Card cardPlayed, int deckIndex) {
        Player currentPlayer = model.getTable().getCurrentPlayer();
        try {
            if(cardPlayed.getClass() == Card.class) {
                model.getGameTable().getCodex(currentPlayer).placeCard(coordinate, cardPlayed);
            } else if(cardPlayed.getClass() == GoldCard.class) {
                model.getGameTable().getCodex(currentPlayer).placeGoldCard(coordinate, (GoldCard) cardPlayed);
            }
        } catch (IllegalCoordinatesException | IllegalCardPlacementException e) {
            throw new RuntimeException(e);
        }
        if(deckIndex == 0) {
            model.getGameTable().pickCardFromDeck();
        } else if(deckIndex == 1) {
            model.getGameTable().pickGoldCardFromDeck();
        }
    }

}
