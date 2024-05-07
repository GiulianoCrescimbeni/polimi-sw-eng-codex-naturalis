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
import it.polimi.ingsw.network.server.updates.AvailableColorsUpdate;
import it.polimi.ingsw.network.server.updates.LoginUpdate;
import it.polimi.ingsw.network.server.updates.SelectPlayerNumberUpdate;
import it.polimi.ingsw.network.server.updates.Update;
import it.polimi.ingsw.view.Messages;
import it.polimi.ingsw.view.TextColor;

import java.util.ArrayList;

public class Controller {
    private Game model;

    /**
     * Controller
     * @param model the {@link Game} for the controller
     */
    public Controller(Game model) {
        this.model = model;
    }

    /**
     * Check if the game is started
     * @return true if the game is running
     */
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

    public AvailableColorsUpdate getAvailableColorsUpdate() {
        return new AvailableColorsUpdate(model.getAvailableColors());
    }

    /**
     *
     * @return The instance of the game model
     */
    public Game getModel() {
        return this.model;
    }

    /**
     * Add a new player to the game
     */
    public Update addPlayer(String nickname, Color color) {
        if(model.getPlayerByNickname(nickname) != null) {
            LoginUpdate loginUpdate = new LoginUpdate(Messages.getInstance().getErrorMessage("Nickname already in use, pick a new one"), false);
            return loginUpdate;
        } else if(!model.getAvailableColors().contains(color)) {
            LoginUpdate loginUpdate = new LoginUpdate(Messages.getInstance().getErrorMessage("Color already in use, pick a new one"), false);
            return loginUpdate;
        } else {
            ArrayList<Card> cards = new ArrayList<>();
            PlayerHand ph = new PlayerHand(cards);
            Player p = new Player(nickname, ph);
            model.removeAvailableColor(color);
            p.setColor(color);
            model.addPlayer(p);
            System.out.println(TextColor.BRIGHT_BLUE + "[LOGIN]" + TextColor.RESET + " Player \"\u001B[35m" + nickname + "\u001B[0m\", with color:\"" + color.toString() + "\" added to game: \u001B[94m" + model.getGameId() + "\u001B[0m");

            if (model.getPlayers().size() == 1) {
                SelectPlayerNumberUpdate selectUpdate = new SelectPlayerNumberUpdate(null);
                return selectUpdate;
            } else {
                LoginUpdate loginUpdate = new LoginUpdate(Messages.getInstance().getInfoMessage("Logged in, waiting for player to start"), true);
                return loginUpdate;
            }

        }
    }

    /**
     * The current player play the turn and then pick a card from the ground
     */
    public void playWithPickFromGround(Coordinate coordinate, Card cardPlayed, Card cardPicked) {
        Player currentPlayer = model.getTable().getCurrentPlayer();
        ArrayList<Player> players = model.getPlayers();
        if(model.getGameStatus() == GameStatus.RUNNING) {
            for (Player p : players) {
                if (model.getGameTable().getCodex(p).getScore() >= 20) {
                    model.setGameStatus(GameStatus.LAST_TURN);
                }
            }
        }

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

        if(model.getGameStatus() == GameStatus.LAST_TURN && currentPlayer.equals(players.get(players.size() - 1))) {
            model.setGameStatus(GameStatus.ENDED);
            endGame();
        }
    }

    /**
     * The current player play the turn and then pick a card from the top of the deck
     */
    public void playWithPickFromDeck(Coordinate coordinate, Card cardPlayed, int deckIndex) {
        Player currentPlayer = model.getTable().getCurrentPlayer();
        ArrayList<Player> players = model.getPlayers();
        if(model.getGameStatus() == GameStatus.RUNNING) {
            for (Player p : players) {
                if (model.getGameTable().getCodex(p).getScore() >= 20) {
                    model.setGameStatus(GameStatus.LAST_TURN);
                }
            }
        }

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

        if(model.getGameStatus() == GameStatus.LAST_TURN && currentPlayer.equals(players.get(players.size() - 1))) {
            model.setGameStatus(GameStatus.ENDED);
            endGame();
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

    public Update selectMaxPlayers(int maxPlayers) {
        model.setMaxPlayers(maxPlayers);
        SelectPlayerNumberUpdate update = new SelectPlayerNumberUpdate(maxPlayers);
        return update;
    }

}
