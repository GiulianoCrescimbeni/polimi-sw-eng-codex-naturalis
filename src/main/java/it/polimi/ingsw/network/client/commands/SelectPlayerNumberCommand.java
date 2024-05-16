package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.server.updates.LoginUpdate;
import it.polimi.ingsw.network.server.updates.SelectPlayerNumberUpdate;
import it.polimi.ingsw.network.server.updates.Update;
import it.polimi.ingsw.view.TUI.Messages;

import java.io.Serializable;

public class SelectPlayerNumberCommand extends  Command implements Serializable  {

    private int maxPlayers;

    public SelectPlayerNumberCommand(String username, int maxPlayers) {
        super(username);
        this.maxPlayers = maxPlayers;
    }

    @Override
    public Update execute(Controller gameController) {
        Game model = gameController.getModel();
        gameController.selectMaxPlayers(maxPlayers);
        LoginUpdate loginUpdate = new LoginUpdate(Messages.getInstance().getInfoMessage("Max player number set to: "+maxPlayers+", extract your personal goal"), true);
        loginUpdate.setNickname(getNickname());
        loginUpdate.setPersonalGoalsToPick(model.getGameTable().getCodex(model.getPlayerByNickname(getNickname())).getGoalsToPick());
        return loginUpdate;
    }

}
