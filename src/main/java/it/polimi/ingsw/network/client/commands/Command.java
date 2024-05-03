package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;

import java.io.Serializable;

public abstract class Command  implements Serializable {
    private String nickname;

    public Command(String nickname) {
        this.nickname = nickname;
    }

    public Command() {}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void execute(Controller gameController) {}
}
