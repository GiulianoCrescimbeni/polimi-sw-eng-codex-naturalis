package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import java.io.Serializable;

/**
 * The update of the initial card side chosen
 */
public class InitialCardSideUpdate extends Update implements Serializable {
    String nickname;
    boolean isTurned;
    public InitialCardSideUpdate() {
        super();
    }

    /**
     * @param nickname the nickname of the player
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the nickname of the player
     */
    public String getNickname() {
        return this.nickname;
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

    /**
     * Update the new card side
     */
    public void execute() {
        if(isTurned) {
            ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(getNickname())).turnInitialCard();
        }

        if(ClientController.getInstance().getUsername().equals(getNickname())) {
            ClientController.getInstance().setInitialSideChoosen();
            ClientController.getInstance().getViewInterface().updateInfo(null, false);
        }
    }
}
