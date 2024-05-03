package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;

import java.io.Serializable;

public class LoginCommand extends Command implements Serializable {

    @Override
    public void execute(Controller gameController) {
        gameController.addPlayer(getNickname());
        System.out.println("[LOGIN] Player \"" + getNickname() + "\" added to game with id: " + gameController.getModel().getGameId());
    }
}
