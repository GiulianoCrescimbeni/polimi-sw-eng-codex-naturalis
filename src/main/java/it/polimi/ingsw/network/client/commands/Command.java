package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;

import java.io.Serializable;

public abstract class Command  implements Serializable {
    private String nickname;
    private Controller gameController;

    public Command(String nickname, Controller gameController) {
        this.nickname = nickname;
        this.gameController = gameController;
    }

    public Command() {}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Controller getGameController() {
        return gameController;
    }

    public void setGameController(Controller gameController) {
        this.gameController = gameController;
    }

    public void execute() {}
}
