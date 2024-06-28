package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameComponents.*;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.AvailableColorsUpdate;
import it.polimi.ingsw.network.server.updates.LoginUpdate;
import it.polimi.ingsw.network.server.updates.Update;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.TextColor;

import java.util.ArrayList;

/**
 * The Controller class handles the communication between the view and the model.
 * It processes input from the user and updates the game state accordingly.
 */
public class Controller {
    private Game model;

    /**
     * Constructs a Controller with the specified game model.
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
     * @return an arraylist of available colors
     */
    public ArrayList<Color> getAvailableColors() {
        return model.getAvailableColors();
    }

    /**
     * @return the update of available colors
     */
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
     * @return players of the game
     */
    public ArrayList<Player> getPlayers() {
        return this.model.getPlayers();
    }

    /**
     * @return the max number of players that can play in the game
     */
    public int getMaxPlayers() {
        return this.model.getMaxPlayers();
    }

    /**
     * @param maxPlayers the number of max player that can play in the game
     * @return an update with the maximum number of players
     */
    public void selectMaxPlayers(int maxPlayers) {
        model.setMaxPlayers(maxPlayers);
    }

    /**
     * @return the current player of the game
     */
    public Player getCurrentPlayer() {
        return model.getGameTable().getCurrentPlayer();
    }

    /**
     * Add a new player to the game
     */
    public Update addPlayer(String nickname, Color color) {
        if(model.getPlayerByNickname(nickname) != null) {
            return new LoginUpdate("Nickname already in use, pick a new one", false);
        } else if(!model.getAvailableColors().contains(color)) {
            return new LoginUpdate("Color already in use, pick a new one", false);
        } else {
            model.addPlayer(nickname, color);
            System.out.println(TextColor.BRIGHT_BLUE + "[LOGIN]" + TextColor.RESET + " Player \"\u001B[35m" + nickname + "\u001B[0m\", with color:\"" + color.toString() + "\" added to game: " + TextColor.BLUE + model.getGameID() + "\u001B[0m");

            LoginUpdate loginUpdate = new LoginUpdate(Messages.getInstance().getInfoMessage("Logged in, extract your personal goal"), true);
            loginUpdate.setNickname(nickname);
            loginUpdate.setPersonalGoalsToPick(model.getGameTable().getCodex(model.getPlayerByNickname(nickname)).getGoalsToPick());
            return loginUpdate;


        }
    }

    /**
     * Pick the personal goal for the player
     * @param goal the personal goal choosen
     */
    public void pickPersonalGoal(String nickname, Goal goal) {
        this.getModel().getTable().getCodex(getModel().getPlayerByNickname(nickname)).pickPersonalGoal(goal);
    }

    /**
     * Set the player to ready
     */
    public void playerReady() {
        this.getModel().incrementPlayersReady();
    }

    /**
     * @return the number of ready player
     */
    public int getReadyPlayer() {
        return this.getModel().getPlayersReady();
    }

    /**
     * The current player play the turn and then pick a card from the ground
     */
    public void playCard(Coordinate coordinate, Card cardPlayed) throws IllegalCoordinatesException, IllegalCardPlacementException {
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
            } else if(cardPlayed.getClass() == GoldCard.class || cardPlayed.getClass() == ResourceGoldCard.class || cardPlayed.getClass() == AngleGoldCard.class) {
                model.getGameTable().getCodex(currentPlayer).placeGoldCard(coordinate, (GoldCard) cardPlayed);
            }
            getCurrentPlayer().getPlayerHand().removeCard(cardPlayed);
        } catch (IllegalCoordinatesException e) {
            if(cardPlayed.isTurned())
                cardPlayed.turn();
            throw new IllegalCoordinatesException(e.getMessage());
        } catch (IllegalCardPlacementException e) {
            if(cardPlayed.isTurned())
                cardPlayed.turn();
            throw new IllegalCardPlacementException(e.getMessage());
        }

        if(model.getGameStatus() == GameStatus.LAST_TURN && currentPlayer.equals(players.get(players.size() - 1))) {
            model.setGameStatus(GameStatus.ENDED);
            endGame();
        }
    }

    /**
     * Pick a card from the ground
     * @param cardPicked the card picked by the player
     * @return the new card on the ground
     */
    public Card pickCardFromGround(Card cardPicked) {
        Card newGroundCard = model.getGameTable().pickCardFromGround(cardPicked);
        model.switchCurrentPlayer();
        return newGroundCard;
    }

    /**
     * Pick a card from the deck
     * @param deckIndex the deck specified by the player
     * @return the card picked from the player
     */
    public Card pickCardFromDeck(int deckIndex) {
        Card cardPicked = null;
        if(deckIndex == 0) {
            cardPicked = model.getGameTable().pickCardFromDeck();
        } else if(deckIndex == 1) {
            cardPicked = model.getGameTable().pickGoldCardFromDeck();
        }
        model.switchCurrentPlayer();
        return cardPicked;
    }

    /**
     * Set the game's status at "Last Turn" for each player
     */
    public void setFinalTurn(){
        this.model.setGameStatus(GameStatus.LAST_TURN);
    }

    /**
     * Set the winning player and the status game at END
     */
    public void endGame(){
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
        assert winningPlayer != null;
        GamesManager.getInstance().endMatch(this.model.getGameID(), winningPlayer.getNickname());
    }

}
