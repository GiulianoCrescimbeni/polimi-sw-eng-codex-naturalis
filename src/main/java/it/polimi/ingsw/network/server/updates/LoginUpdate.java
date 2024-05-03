package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.io.Serializable;

public class LoginUpdate extends Update implements Serializable {
    /**
     * @param handler The client handler used as target to send data
     * @param data    The data to send
     */
    public LoginUpdate(ClientHandler handler, Object data) {
        super(handler, data);
    }
}
