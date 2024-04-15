package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.GameComponents.GoldCard;
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

    /**
     * Return colors available
     */
    public ArrayList<Color> getAvailableColors() {
        return model.getAvailableColors();

    }

    /**
     * Add player and set his color
     */
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

    /**
     * The current player play the turn and then pick a card from the ground
     */
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

    /**
     * The current player play the turn and then pick a card from the top of the deck
     */
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

    /**
     * Set the game's status at "Last Turn" for each player
     */
    private void setFinalTurn(){
        this.model.setGameStatus(GameStatus.LAST_TURN);
    }

    /**
     * Set the winning player and the status game at END
     */
    private void endGame(){
        int maxScore = 0;
        Player winningPlayer = null;

        for(int i=0; i<this.model.getPlayers().size(); i++){
            Codex playerCodex = this.model.getGameTable().getCodex(this.model.getPlayers().get(i));
            int personal_score = playerCodex.getPersonalGoal().check(playerCodex) * playerCodex.getPersonalGoal().getScore();
            int common_score_0 = this.model.getGameTable().getCommonGoals().get(0).check(playerCodex) * this.model.getGameTable().getCommonGoals().get(0).getScore();
            int common_score_1 = this.model.getGameTable().getCommonGoals().get(1).check(playerCodex) * this.model.getGameTable().getCommonGoals().get(1).getScore();

            playerCodex.incrementScore(common_score_0 + common_score_1 + personal_score);

            if(playerCodex.getScore() > maxScore) {
                maxScore = playerCodex.getScore();
                winningPlayer = this.model.getPlayers().get(i);
            }
        }

        this.model.setWinner(winningPlayer);
        this.model.setGameStatus(GameStatus.ENDED);
    }

}
