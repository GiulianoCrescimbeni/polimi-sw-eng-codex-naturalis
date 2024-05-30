module it.polimi.ingsw {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.rmi;
    requires com.google.gson;
    exports it.polimi.ingsw.view.GUI;
    opens it.polimi.ingsw.view.GUI to javafx.graphics;
    opens it.polimi.ingsw to javafx.fxml;
}