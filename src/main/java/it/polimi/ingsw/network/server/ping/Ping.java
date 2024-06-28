package it.polimi.ingsw.network.server.ping;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.updates.Update;

import java.io.Serializable;

/**
 * The ping sent by the server
 */
public class Ping extends Update implements Serializable {

    @Override
    public void execute() {
        ClientController.getInstance().sendPong();
    }

}
