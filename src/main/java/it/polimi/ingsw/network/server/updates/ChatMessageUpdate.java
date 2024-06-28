package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.TextColor;


import java.io.Serializable;

/**
 * The update for a new chat message
 */
public class ChatMessageUpdate extends Update implements Serializable {

    private String message;
    private String receiver;
    private String sender;

    /**
     * Constructor
     * @param message the new chat message
     * @param receiver the receiver
     * @param sender the sender
     */
    public ChatMessageUpdate(String message, String receiver, String sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    /**
     * @return the new message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @return the receiver
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * @return the sender
     */
    public String getSender() { return sender; }

    /**
     * Update the chat message history for the new chat message
     */
    @Override
    public void execute() {
        if (getReceiver().equals(ClientController.getInstance().getUsername()) || getSender().equals(ClientController.getInstance().getUsername()) || getReceiver().equals("public")) {

            String toAdd = "";

            if (receiver.equals("public")) {
                toAdd = Messages.getInstance().getMessage(TextColor.BRIGHT_BLUE + "[" + getSender() + "]: " + TextColor.RESET + message, false);
            } else if (receiver.equals(ClientController.getInstance().getUsername())) {
                toAdd = Messages.getInstance().getMessage(TextColor.BRIGHT_BLUE + "[" + getSender() + "]: " + TextColor.RESET + message, true);
            } else if (sender.equals(ClientController.getInstance().getUsername()) && !receiver.equals("public")) {
                toAdd = Messages.getInstance().getMessage(TextColor.BRIGHT_BLUE + "[You to " + getReceiver() + "]: " + TextColor.RESET + message, true);
            }
            ClientController.getInstance().addMessage(toAdd);
            ClientController.getInstance().getViewInterface().updateChatView("");
        }
    }
}
