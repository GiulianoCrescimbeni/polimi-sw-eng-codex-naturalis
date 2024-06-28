package it.polimi.ingsw.network.client.commands;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.GamesManager;
import it.polimi.ingsw.network.server.updates.InitialCardSideUpdate;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

/**
 * Class for selecting the side of the initial card
 */
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

    /**
     * Select the side
     * @param clientController the {@link Controller} of the game where the message needs to get executed
     * @return a {@link InitialCardSideUpdate}
     */
    @Override
    public Update execute(Controller clientController) {
        InitialCardSideUpdate initialCardSideUpdate = new InitialCardSideUpdate();
        initialCardSideUpdate.setNickname(getNickname());
        if(isTurned()) {
            clientController.getModel().getTable().getCodex(clientController.getModel().getPlayerByNickname(getNickname())).getCard(new Coordinate(80,80)).turn();
            initialCardSideUpdate.setTurned();
            GamesManager.getInstance().broadcast(clientController.getModel().getGameID(), initialCardSideUpdate);
            return null;
        } else {
            return initialCardSideUpdate;
        }
    }
}
