package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Enumerations.Color;

import java.util.ArrayList;

public class ClientController {

    private ArrayList<Color> availableColors = new ArrayList<Color>();

    public ClientController() {}

    public void updateAvailableColors(ArrayList<Color> availableColors) {
        this.availableColors = availableColors;
    }

}
