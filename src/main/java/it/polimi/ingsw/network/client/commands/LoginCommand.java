package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

/**
 * Class that represent the Login message sent when a client tries to log in into a game
 */
public class LoginCommand extends Command implements Serializable {

    private Color chosen;

    /**
     * Constructor
     * @param nickname the nickname of the {@link it.polimi.ingsw.model.Player.Player} that is logging in the game
     * @param colorChosen the {@link Color} chosen from the player
     */
    public LoginCommand(String nickname, Color colorChosen) {
        super(nickname);
        this.chosen = colorChosen;
    }

    /**
     * Execute the login by adding the player
     * @param gameController the {@link Controller} of the game where the message needs to get executed
     * @return the {@link Update} with the result of the login
     */
    @Override
    public Update execute(Controller gameController) {
        return gameController.addPlayer(getNickname(), chosen);
    }
}
