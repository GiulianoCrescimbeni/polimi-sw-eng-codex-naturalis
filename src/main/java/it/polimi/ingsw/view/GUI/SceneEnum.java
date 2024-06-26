package it.polimi.ingsw.view.GUI;

public enum SceneEnum {

    MAIN_MENU("/polimi/ingsw/fxml/MainMenu.fxml"),
    CREATE_GAME_MENU("/polimi/ingsw/fxml/CreateGameMenu.fxml"),
    GAME_LIST_MENU("/polimi/ingsw/fxml/GameListMenu.fxml"),
    LOGIN_MENU("/polimi/ingsw/fxml/LoginMenu.fxml"),
    INITIAL_CARD_SIDE_SELECTION_MENU("/polimi/ingsw/fxml/SelectInitialCardSide.fxml"),
    PERSONAL_GOAL_SELECTION_MENU("/polimi/ingsw/fxml/PersonalGoalSelectionMenu.fxml"),
    WAITING_ROOM("/polimi/ingsw/fxml/WaitingRoom.fxml"),
    CODEX("/polimi/ingsw/fxml/Codex.fxml"),
    CHAT("/polimi/ingsw/fxml/ChatView.fxml"),
    PLATEAU("/polimi/ingsw/fxml/Plateau.fxml"),
    INSPECT_CODEX("/polimi/ingsw/fxml/Inspect.fxml"),
    ERROR("/polimi/ingsw/fxml/Error.fxml");
    private final String value;


    SceneEnum(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
