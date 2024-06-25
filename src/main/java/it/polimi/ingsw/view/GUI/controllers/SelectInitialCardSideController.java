package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.network.client.ClientController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class SelectInitialCardSideController extends ViewController {

    @FXML
    ImageView FrontSide;
    @FXML
    ImageView BackSide;

    public void initialize() {
        setCardImage(FrontSide, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(ClientController.getInstance().getUsername())).getCards().get(new Coordinate(80,80)).getCardID() +".png");
        setCardImage(BackSide, "/polimi/ingsw/Cards/Backs/"+ ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(ClientController.getInstance().getUsername())).getCards().get(new Coordinate(80,80)).getCardID() +".png");
        addHoverEffect(FrontSide);
        addHoverEffect(BackSide);
    }

    private void setCardImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.out.println("Image not found: " + imagePath);
        }
    }

    private void addHoverEffect(ImageView imageView) {
        imageView.setOnMouseEntered(event -> {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, #edb009, 10, 0, 0, 5);");
        });

        imageView.setOnMouseExited(event -> {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 5);");
        });
    }

    @FXML
    public void handleFrontSide(MouseEvent event) throws IOException {
        ClientController.getInstance().selectInitialCardSide(0);
    }

    @FXML
    public void handleBackSide(MouseEvent event) throws IOException {
        ClientController.getInstance().selectInitialCardSide(1);
    }

}
