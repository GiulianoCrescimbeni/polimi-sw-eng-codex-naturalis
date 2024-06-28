package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.ChatMessageUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

/**
 * Chat message command for sending a message
 */
public class ChatMessageCommand extends Command implements Serializable {

    private String message;
    private String receiver;
    private String sender;

    /**
     * Constructor
     * @param message the string of the message
     * @param receiver the reciever of the message
     * @param sender the sender of the message
     */
    public ChatMessageCommand(String message, String receiver, String sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    /**
     * @return the string of the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @return the reciever of the message
     */
    public String getReceiver() {
        return this.receiver;
    }

    /**
     * @return the sender of the message
     */
    public String getSender() {
        return this.sender;
    }

    /**
     * Broadcast the message into a {@link ChatMessageUpdate} to all client
     * @param gameController the {@link Controller} of the game where the message needs to get executed
     * @return
     */
    @Override
    public Update execute(Controller gameController) {
        GamesManager.getInstance().broadcast(gameController.getModel().getGameID(), new ChatMessageUpdate(getMessage(), getReceiver(), getSender()));
        return null;
    }

}
