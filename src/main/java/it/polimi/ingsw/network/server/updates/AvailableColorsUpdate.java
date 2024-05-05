package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.handler.ClientHandler;

import java.io.Serializable;
import java.util.ArrayList;

public class AvailableColorsUpdate extends Update implements Serializable {

    private ArrayList<Color> availableColors;

    /**
     * @param availableColors The available colors to send to client
     */
    public AvailableColorsUpdate(ArrayList<Color> availableColors) {
        this.availableColors = availableColors;
    }

    @Override
    public void execute(ClientController clientController) {
        clientController.updateAvailableColors(availableColors);
    }

}
