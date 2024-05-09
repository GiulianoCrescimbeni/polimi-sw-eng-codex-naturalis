package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.TextColor;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

public class SelectPlayerNumberUpdate extends Update implements Serializable {

    private Integer maxPlayers;

    public SelectPlayerNumberUpdate(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    @Override
    public void execute(ClientController clientController) {
        if (maxPlayers == null) {
            View.getInstance().selectPlayersNumber();
        } else {
            Messages.getInstance().info("Number of maximum players set to: " + TextColor.BRIGHT_YELLOW + maxPlayers + TextColor.RESET);
        }
    }

}
