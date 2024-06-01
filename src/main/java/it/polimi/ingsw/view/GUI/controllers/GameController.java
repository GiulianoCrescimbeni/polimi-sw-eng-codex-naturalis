package it.polimi.ingsw.view.GUI.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class GameController {


    Stage stage;
    private double mouseX;
    private double mouseY;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img10;

    @FXML
    private ImageView background;

    @FXML
    private ImageView centre;

    @FXML
    private Button plateauButton;


    @FXML
    public void handlePlateau(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Plateau.fxml"));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setTitle("Score");
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);

           popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.initOwner(stage);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private boolean isValidPosition(double x, double y) {
        //TODO
        return false;
    }


    @FXML
    public void handleMousePressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }


    @FXML
    public void handleDrag(MouseEvent event) {
        double offsetX = event.getSceneX() - mouseX;
        double offsetY = event.getSceneY() - mouseY;

        img1.setLayoutX(img1.getLayoutX() + offsetX);
        img1.setLayoutY(img1.getLayoutY() + offsetY);

        // Aggiorna le coordinate del mouse
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();

        img1.toFront();
    }

    @FXML
    public void handleDrag10(MouseEvent event) {
        double offsetX = event.getSceneX() - mouseX;
        double offsetY = event.getSceneY() - mouseY;

        img10.setLayoutX(img10.getLayoutX() + offsetX);
        img10.setLayoutY(img10.getLayoutY() + offsetY);

        mouseX = event.getSceneX();
        mouseY = event.getSceneY();

        img10.toFront();
    }

    @FXML
    public void handleRelease10(MouseEvent event) {
        anchor.getChildren().add(img10);
        img10.setLayoutX(img10.getLayoutX() - anchor.getLayoutX());
        img10.setLayoutY(img10.getLayoutY() - anchor.getLayoutY());
    }



    private double minX;
    private double maxX;
    private double minY;
    private double maxY;

    @FXML
    public void initialize() {
        updateLimits();
        background.imageProperty().addListener((observable, oldValue, newValue) -> updateLimits());
    }
    private void updateLimits() {

        double imageWidth = background.getImage().getWidth();
        double imageHeight = background.getImage().getHeight();

        minX = 0;
        minY = 0;
        maxX = imageWidth;
        maxY = imageHeight;
    }

    @FXML
    public void handleBackground(MouseEvent event) {
        double offsetX = event.getSceneX() - mouseX;
        double offsetY = event.getSceneY() - mouseY;

        // Calcolo le nuove coordinate
        double newLayoutX = background.getLayoutX() + offsetX;
        double newLayoutY = background.getLayoutY() + offsetY;

        // Verifico che le nuove coordinate siano nei limiti
        if (newLayoutX >= minX && newLayoutX <= maxX) {
            background.setLayoutX(newLayoutX);
            centre.setLayoutX(newLayoutX);
        }

        if (newLayoutY >= minY && newLayoutY <= maxY) {
            background.setLayoutY(newLayoutY);
            centre.setLayoutY(newLayoutY);
        }

        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }




}