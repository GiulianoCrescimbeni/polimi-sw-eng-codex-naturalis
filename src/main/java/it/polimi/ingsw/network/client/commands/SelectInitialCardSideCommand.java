package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.network.server.updates.InitialCardSideUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

public class SelectInitialCardSideCommand extends Command implements Serializable {
    private boolean turned;
    /**
     * Constructor
     * @param nickname the nickname of the {@link it.polimi.ingsw.model.Player.Player} that is choosing the side
     * @param turned the side of the card
     */
    public SelectInitialCardSideCommand(String nickname, boolean turned) {
        super(nickname);
        this.turned = turned;
    }

    /**
     * @return true if the card is turned
     */
    public boolean isTurned() {
        return this.turned;
    }

    @Override
    public Update execute(Controller clientController) {
        InitialCardSideUpdate initialCardSideUpdate = new InitialCardSideUpdate();
        if(isTurned()) {
            clientController.getModel().getTable().getCodex(clientController.getModel().getPlayerByNickname(getNickname())).getCard(new Coordinate(80,80)).turn();
            initialCardSideUpdate.setTurned();
        }
        return initialCardSideUpdate;
    }
}
