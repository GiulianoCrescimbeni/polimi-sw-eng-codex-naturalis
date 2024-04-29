package it.polimi.ingsw.network.client.commands;

public class LoginCommand extends Command{

    @Override
    public void execute() {
        getGameController().addPlayer(getNickname());
    }
}
