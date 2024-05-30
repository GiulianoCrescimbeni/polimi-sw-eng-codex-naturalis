package it.polimi.ingsw.view.GUI;

public enum SceneEnum {

    MAINMENU("/polimi/ingsw/fxml/MainMenu.fxml");

    private final String value;


    SceneEnum(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
