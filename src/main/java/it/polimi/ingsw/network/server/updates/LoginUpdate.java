package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.io.Serializable;

public class LoginUpdate extends Update implements Serializable {

    public LoginUpdate() {}

    @Override
    public void execute(ClientController clientController) {
    }
}
