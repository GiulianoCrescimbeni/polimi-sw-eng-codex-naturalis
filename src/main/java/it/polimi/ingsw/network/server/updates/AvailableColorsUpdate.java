package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * The class represents the {@link Update} sent from the server after a first connection
 */
public class AvailableColorsUpdate extends Update implements Serializable {

    private ArrayList<Color> availableColors;

    /**
     * @param availableColors The available colors to send to client
     */
    public AvailableColorsUpdate(ArrayList<Color> availableColors) {
        this.availableColors = availableColors;
    }

    /**
     * Execute the Update on the client showing available colors to choose from
     */
    @Override
    public void execute() {
        ClientController.getInstance().updateAvailableColors(availableColors);
    }

}
