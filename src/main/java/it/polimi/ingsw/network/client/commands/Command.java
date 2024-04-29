package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;

public abstract class Command {
    private String nickname;
    private Controller gameController;

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
