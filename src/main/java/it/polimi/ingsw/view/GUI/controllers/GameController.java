package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.network.client.ClientController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    private ImageView InitialCard;

    @FXML
    private ImageView Hand1;

    @FXML
    private ImageView Hand2;

    @FXML
    private ImageView Hand3;

    @FXML
    private ImageView GroundCard1;

    @FXML
    private ImageView GroundCard2;

    @FXML
    private ImageView GroundGoldCard1;

    @FXML
    private ImageView GroundGoldCard2;

    @FXML
    private ImageView CommonGoal1;

    @FXML
    private ImageView CommonGoal2;

    @FXML
    private ImageView PersonalGoal;

    @FXML
    private Text CurrentPlayerNameText;

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
        setCardImage(PersonalGoal, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(ClientController.getInstance().getUsername())).getCard(new Coordinate(80, 80)).getCardID() +".png");
        setCardImage(Hand1, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPlayerHand().getCards().get(0).getCardID() +".png");
        setCardImage(Hand2, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPlayerHand().getCards().get(1).getCardID() +".png");
        setCardImage(Hand3, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPlayerHand().getCards().get(2).getCardID() +".png");
        setCardImage(GroundCard1, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCardToPick().get(0).getCardID() +".png");
        setCardImage(GroundCard2, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCardToPick().get(1).getCardID() +".png");
        setCardImage(GroundGoldCard1, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getGoldCardToPick().get(0).getCardID() +".png");
        setCardImage(GroundGoldCard2, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getGoldCardToPick().get(1).getCardID() +".png");
        setCardImage(CommonGoal1, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCommonGoals().get(0).getGoalId() +".png");
        setCardImage(CommonGoal2, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCommonGoals().get(1).getGoalId() +".png");
        setCardImage(PersonalGoal, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPersonalGoal().getGoalId() +".png");
        printCurrentPlayer();
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
            InitialCard.setLayoutX(newLayoutX);
        }

        if (newLayoutY >= minY && newLayoutY <= maxY) {
            background.setLayoutY(newLayoutY);
            InitialCard.setLayoutY(newLayoutY);
        }

        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    private void setCardImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.out.println("Image not found: " + imagePath);
        }
    }

    private void printCurrentPlayer() {
        CurrentPlayerNameText.setText(ClientController.getInstance().getCurrentPlayer().getNickname());
        switch (ClientController.getInstance().getCurrentPlayer().getColor()) {
            case Color.Blue:
                CurrentPlayerNameText.setFill(javafx.scene.paint.Color.BLUE);
                break;
            case Color.Red:
                CurrentPlayerNameText.setFill(javafx.scene.paint.Color.RED);
                break;
            case Color.Yellow:
                CurrentPlayerNameText.setFill(javafx.scene.paint.Color.web("#edb009"));
                break;
            case Color.Green:
                CurrentPlayerNameText.setFill(javafx.scene.paint.Color.GREEN);
                break;
        }
    }

}