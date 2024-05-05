package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Enumerations.Color;

import java.io.Serializable;

public class LoginCommand extends Command implements Serializable {

    private Color chosen;

    public LoginCommand(String nickname, Color colorChosen) {
        super(nickname);
        this.chosen = colorChosen;
    }

    @Override
    public void execute(Controller gameController) {
        gameController.addPlayer(getNickname(), chosen);
        System.out.println("[LOGIN] Player \"" + getNickname() + "\", with color:\"" + chosen.toString() + "\" added to game: " + gameController.getModel().getGameId());
    }
}
