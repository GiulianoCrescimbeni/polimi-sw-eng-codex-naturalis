package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Data.SerializedGame;

import java.io.IOException;
import java.util.ArrayList;

public interface ViewInterface {
    void selectAvailableMatch(ArrayList<SerializedGame> availableMatches, String error) throws IOException;
    void pickUsernameAndColor();
    void selectPersonalGoal();
    void waitingRoom();
    void selectInitialCardSide() throws IOException;
    void updateInfo(String message, boolean clear);
    void updateChatView(String error);
    void showError(String error);
    void winScreen();
    void looseScreen();
}