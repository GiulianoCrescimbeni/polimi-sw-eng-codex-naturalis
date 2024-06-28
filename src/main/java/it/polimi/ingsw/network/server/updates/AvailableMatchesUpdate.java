package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.TUI.View;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents the update containing all the available matches
 */
public class AvailableMatchesUpdate extends Update implements Serializable {

    private ArrayList<SerializedGame> availableMatches;
    private String message;

    /**
     * Constructor
     * @param availableMatches the ArrayList of {@link SerializedGame} of all available matches
     * @param message
     */
    public AvailableMatchesUpdate(ArrayList<SerializedGame> availableMatches, String message) {
        this.availableMatches = availableMatches;
        this.message = message;
    }

    /**
     * Update the available matches
     * @throws IOException
     */
    @Override
    public void execute() throws IOException {
        ClientController.getInstance().getViewInterface().selectAvailableMatch(availableMatches, message);
    }

}
