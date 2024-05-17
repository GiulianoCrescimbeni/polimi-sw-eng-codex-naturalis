package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

/**
 * Command Class
 * This class represents all the messages sent from the client to the server
 */
public abstract class Command  implements Serializable {
    private String nickname;

    /**
     * Constructor
     * @param nickname the nickname that sent the message
     */
    public Command(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Constructor
     */
    public Command() {}

    /**
     * @return the nickname of the sender
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname of the sender
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Execute the message
     * @param gameController the {@link Controller} of the game where the message needs to get executed
     * @return an {@link Update} to be sent back to the client
     */
    public Update execute(Controller gameController) {
        return null;
    }
}
