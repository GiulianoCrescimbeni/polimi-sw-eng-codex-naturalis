package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.network.client.ClientController;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.InputStream;

/**
 * The PersonalGoalSelectionMenuController class handles the interface for selecting personal goals in the GUI
 */
public class PersonalGoalSelectionMenuController extends ViewController {
    @FXML
    private ImageView goal1;

    @FXML
    private ImageView goal2;

    @FXML
    public void initialize() {
        setGoalImage(goal1, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getGoalsToPick().get(0).getGoalId() +".png");
        setGoalImage(goal2, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getGoalsToPick().get(1).getGoalId() +".png");
        goal1.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleCardClick(event, ClientController.getInstance().getGoalsToPick().get(0)));
        goal2.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handleCardClick(event, ClientController.getInstance().getGoalsToPick().get(1)));
        addHoverEffect(goal1, ClientController.getInstance().getGoalsToPick().get(0));
        addHoverEffect(goal2, ClientController.getInstance().getGoalsToPick().get(1));
    }

    /**
     * Sets the shadow effect on the goal card image based on the goal type
     * @param goalImage the ImageView of the goal card
     * @param goal the goal associated with the card
     */
    private void setGoalShadow(ImageView goalImage, Goal goal) {
        switch (goal.getCardType()) {
            case FUNGI -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 5);");
            case ANIMAL -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, lightblue, 10, 0, 0, 5);");
            case PLANT -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, green, 10, 0, 0, 5);");
            case INSECT -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, purple, 10, 0, 0, 5);");
            case null -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, #edb009, 10, 0, 0, 5);");
            case FIRST_CARD -> {}
        }
    }

    /**
     * Adds a hover effect to the goal card image
     * @param imageView the ImageView of the goal card
     * @param goal the goal associated with the card
     */
    private void addHoverEffect(ImageView imageView, Goal goal) {
        imageView.setOnMouseEntered(event -> {
            setGoalShadow(imageView, goal);
        });

        imageView.setOnMouseExited(event -> {
            imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 5);");
        });
    }

    /**
     * Sets the goal image on the specified ImageView
     * @param imageView the ImageView to set the image on
     * @param imagePath the path to the image file
     */
    private void setGoalImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.out.println("Image not found: " + imagePath);
        }
    }

    /**
     * Handles the click event on a goal card
     * @param event the mouse event
     * @param goal the goal associated with the clicked card
     */
    private void handleCardClick(MouseEvent event, Goal goal) {
        ClientController.getInstance().selectPersonalGoal(goal);
    }
}
