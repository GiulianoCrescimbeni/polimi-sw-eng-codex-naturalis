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
        System.out.println("[LOGIN] Player \"\u001B[35m" + getNickname() + "\u001B[0m\", with color:\"" + chosen.toString() + "\" added to game: \u001B[94m" + gameController.getModel().getGameId() + "\u001B[0m");
    }
}
