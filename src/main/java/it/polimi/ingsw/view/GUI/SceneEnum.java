package it.polimi.ingsw.view.GUI;

public enum SceneEnum {

    MAIN_MENU("/polimi/ingsw/fxml/MainMenu.fxml"),
    CREATE_GAME_MENU("/polimi/ingsw/fxml/CreateGameMenu.fxml"),
    GAME_LIST_MENU("/polimi/ingsw/fxml/GameListMenu.fxml");
    private final String value;


    SceneEnum(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
