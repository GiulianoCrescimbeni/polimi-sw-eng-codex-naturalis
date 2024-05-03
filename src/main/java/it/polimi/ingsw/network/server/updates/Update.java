package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.io.Serializable;

public abstract class Update implements Serializable {

    private ClientHandler handler;
    private Object data;

    /**
     *
     * @param handler The client handler used as target to send data
     * @param data The data to send
     */
    public Update(ClientHandler handler, Object data) {
        this.handler = handler;
        this.data = data;
    }

    /**
     *
     * @return The client handler
     */
    public ClientHandler getHandler() {
        return this.handler;
    }

    /**
     *
     * @return The data of the update
     */
    public Object getData() {
        return this.data;
    }

    /**
     * This method has to be overwrtitten
     * It defines the behaviour of the update
     */
    public void execute() {}

}
