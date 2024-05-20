package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;

public class CardPickedUpdate extends Update implements Serializable {
    private PlayerHand playerHand;

    public PlayerHand getPlayerHand() { return this.playerHand; }

    public void setPlayerHand(PlayerHand playerHand) {
        this.playerHand = playerHand;
    }

    @Override
    public void execute() {
        ClientController.getInstance().setPlayerHand(getPlayerHand());
    }
}
