package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Data.SerializedGame;

import java.util.ArrayList;

public interface ViewInterface {
    void selectAvailableMatch(ArrayList<SerializedGame> availableMatches, String error);
    void joinOrCreateMatch(ArrayList<SerializedGame> availableMatches);
    void pickUsernameAndColor();
    void selectPersonalGoal();
    void updateInfo(String message, boolean clear);
    void updateChatView(String error);
}