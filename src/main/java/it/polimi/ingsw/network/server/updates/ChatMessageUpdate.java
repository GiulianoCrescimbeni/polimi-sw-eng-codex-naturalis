package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.TextColor;


import java.io.Serializable;

public class ChatMessageUpdate extends Update implements Serializable {

    private String message;
    private String receiver;
    private String sender;

    public ChatMessageUpdate(String message, String receiver, String sender) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getSender() { return sender; }

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
        }
    }
}
