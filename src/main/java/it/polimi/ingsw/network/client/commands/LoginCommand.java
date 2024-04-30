package it.polimi.ingsw.network.client.commands;

import java.io.Serializable;

public class LoginCommand extends Command implements Serializable {

    @Override
    public void execute() {
        getGameController().addPlayer(getNickname());
        System.out.println("[LOGIN] Player added with name:\"" + getNickname() + "\"");
    }
}