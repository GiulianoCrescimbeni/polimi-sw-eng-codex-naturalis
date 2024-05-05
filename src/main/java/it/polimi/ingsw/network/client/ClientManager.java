package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.server.updates.Update;

public class ClientManager {

    private static ClientManager instance;

    private ClientManager() {}

    public static ClientManager getInstance() {
        if (instance == null) {
            instance = new ClientManager();
        }
        return instance;
    }

    public void handleUpdate(Update update) {
        update.execute(ClientController.getInstance());
    }

}
