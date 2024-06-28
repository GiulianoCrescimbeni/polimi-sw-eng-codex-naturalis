package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import it.polimi.ingsw.view.TUI.View;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

/**
 * The update that carries the game data when a new game has started
 */
public class GameDataUpdate extends Update implements Serializable {
    private Map<Player, Codex> codexMap;
    private ArrayList<Card> cardToPick;
    private ArrayList<Card> goldCardToPick;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private ArrayList<Goal> commonGoals;
    public GameDataUpdate() {}

    /**
     * @return the codex map
     */
    public Map<Player, Codex> getCodexMap() {
        return codexMap;
    }

    /**
     * @param codexMap the codex map
     */
    public void setCodexMap(Map<Player, Codex> codexMap) {
        this.codexMap = codexMap;
    }

    /**
     * @return the array of card to pick
     */
    public ArrayList<Card> getCardToPick() {
        return cardToPick;
    }

    /**
     * @param cardToPick the array of card to pick
     */
    public void setCardToPick(ArrayList<Card> cardToPick) {
        this.cardToPick = cardToPick;
    }

    /**
     * @return the array of gold card to pick
     */
    public ArrayList<Card> getGoldCardToPick() {
        return goldCardToPick;
    }

    /**
     * @param goldCardToPick the array of gold card to pick
     */
    public void setGoldCardToPick(ArrayList<Card> goldCardToPick) {
        this.goldCardToPick = goldCardToPick;
    }

    /**
     * @return the arraylist of players
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players set the arraylist of players
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @param currentPlayer the current player
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the arraylist of common goals
     */
    public ArrayList<Goal> getCommonGoals() {
        return commonGoals;
    }

    /**
     * @param commonGoals the arraylist of common goals
     */
    public void setCommonGoals(ArrayList<Goal> commonGoals) {
        this.commonGoals = commonGoals;
    }

    /**
     * Update the game data when the game start
     * @throws IOException
     */
    @Override
    public void execute() throws IOException {
        ClientController.getInstance().updateGameData(getCodexMap(), getCardToPick(), getGoldCardToPick(), getPlayers(), getCurrentPlayer(), getCommonGoals());
        if(ClientController.getInstance().getViewInterface().getClass() == View.getInstance().getClass()) {
            View.getInstance().start();
        } else {
            ClientController.getInstance().getViewInterface().selectInitialCardSide();
        }
    }
}
