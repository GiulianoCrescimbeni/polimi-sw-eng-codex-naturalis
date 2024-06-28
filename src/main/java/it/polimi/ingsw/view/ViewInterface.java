package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Data.SerializedGame;

import java.io.IOException;
import java.util.ArrayList;

public interface ViewInterface {
    /**
     * Load the select available match screen
     * @param availableMatches the ArrayList of {@link SerializedGame} representing all the available matches
     * @param error
     * @throws IOException
     */
    void selectAvailableMatch(ArrayList<SerializedGame> availableMatches, String error) throws IOException;

    /**
     * Load the screen to pick username and color
     */
    void pickUsernameAndColor();

    /**
     * Load the screen for selecting a personal {@link it.polimi.ingsw.model.Goals.Goal}
     */
    void selectPersonalGoal();

    /**
     * Load the screen for the waiting room
     */
    void waitingRoom();

    /**
     * Load the screen for selecting an {@link it.polimi.ingsw.model.GameComponents.InitialCard} side
     * @throws IOException
     */
    void selectInitialCardSide() throws IOException;

    /**
     * Update the screen after a new turn
     * @param message a message in case of error
     * @param clear true if is needed to clear the screen (Only for TUI)
     */
    void updateInfo(String message, boolean clear);

    /**
     * Load the screen for the chat
     * @param error
     */
    void updateChatView(String error);

    /**
     * Show an error message
     * @param error the string of the error message
     */
    void showError(String error);

    /**
     * Show the winning screen
     */
    void winScreen();

    /**
     * Show the loosing screen
     */
    void looseScreen();
}