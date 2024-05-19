package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.GameDataUpdate;
import it.polimi.ingsw.network.server.updates.PersonalGoalUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class SelectPersonalGoalCommand extends Command implements Serializable {
    private Goal personalGoalChoosen;

    /**
     * Constructor
     * @param nickname the nickname of the {@link it.polimi.ingsw.model.Player.Player} that is logging in the game
     * @param personalGoal the {@link Goal} chosen from the player
     */
    public SelectPersonalGoalCommand(String nickname, Goal personalGoal) {
        super(nickname);
        this.personalGoalChoosen = personalGoal;
    }

    /**
     * @return the personal goal choosen
     */
    public Goal getPersonalGoalChoosen() {
        return personalGoalChoosen;
    }

    /**
     * @param personalGoalChoosen the personal goal choosen
     */
    public void setPersonalGoalChoosen(Goal personalGoalChoosen) {
        this.personalGoalChoosen = personalGoalChoosen;
    }
    @Override
    public Update execute(Controller gameController) {
        gameController.pickPersonalGoal(this.getNickname(), this.getPersonalGoalChoosen());
        gameController.playerReady();
        Goal personalGoal = gameController.getModel().getTable().getCodex(gameController.getModel().getPlayerByNickname(this.getNickname())).getPersonalGoal();
        PersonalGoalUpdate personalGoalUpdate = new PersonalGoalUpdate();
        personalGoalUpdate.setPersonalGoal(personalGoal);
        if(gameController.getReadyPlayer() == gameController.getMaxPlayers()) {
            gameController.getModel().startGame();
            GameDataUpdate gameDataUpdate = getGameDataUpdate(gameController);
            GamesManager.getInstance().broadcast(gameController.getModel().getGameID(), gameDataUpdate);
        }
        return personalGoalUpdate;
    }

    private static GameDataUpdate getGameDataUpdate(Controller gameController) {
        Map<Player, Codex> codexMap = gameController.getModel().getGameTable().getCodexMap();
        ArrayList<Card> cardToPick = gameController.getModel().getTable().getCardToPick();
        ArrayList<Card> goldCardToPick = gameController.getModel().getTable().getGoldCardToPick();
        ArrayList<Player> players = gameController.getPlayers();
        Player currentPlayer = gameController.getCurrentPlayer();
        ArrayList<Goal> commonGoals = gameController.getModel().getTable().getCommonGoals();
        GameDataUpdate gameDataUpdate = new GameDataUpdate();
        gameDataUpdate.setCodexMap(codexMap);
        gameDataUpdate.setCardToPick(cardToPick);
        gameDataUpdate.setGoldCardToPick(goldCardToPick);
        gameDataUpdate.setPlayers(players);
        gameDataUpdate.setCurrentPlayer(currentPlayer);
        gameDataUpdate.setCommonGoals(commonGoals);
        return gameDataUpdate;
    }
}
