package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.ChatMessageUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class ChatMessageCommand extends Command implements Serializable {

    private String message;
    private String receiver;
    private String sender;

    public ChatMessageCommand(String message, String receiver, String sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getMessage() {
        return this.message;
    }

    public String getReceiver() {
        return this.receiver;
    }

    public String getSender() {
        return this.sender;
    }

    @Override
    public Update execute(Controller gameController) {
        GamesManager.getInstance().broadcast(gameController.getModel().getGameID(), new ChatMessageUpdate(getMessage(), getReceiver(), getSender()));
        return null;
    }

}
