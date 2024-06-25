package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import java.io.Serializable;

public class InitialCardSideUpdate extends Update implements Serializable {
    boolean isTurned;
    public InitialCardSideUpdate() {
        super();
    }

    /**
     * @return true if the card is turned
     */
    public boolean isTurned() {
        return this.isTurned;
    }

    /**
     * Set the card to turned
     */
    public void setTurned() {this.isTurned = true;}

    public void execute() {
        if(isTurned) {
            ClientController.getInstance().turnInitialCard();
        }
        ClientController.getInstance().setInitialSideChoosen();
        ClientController.getInstance().getViewInterface().updateInfo(null, false);
    }
}
