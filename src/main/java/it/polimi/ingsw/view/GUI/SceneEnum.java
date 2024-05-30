package it.polimi.ingsw.view.GUI;

public enum SceneEnum {

    MENU("/polimi/ingsw/fxml/menu.fxml"),
    MENU2("/polimi/ingsw/fxml/menu2.fxml");

    private final String value;


    SceneEnum(final String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
