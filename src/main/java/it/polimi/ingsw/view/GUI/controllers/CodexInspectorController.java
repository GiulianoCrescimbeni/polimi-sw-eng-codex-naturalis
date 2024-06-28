package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.GameComponents.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.View;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * The CodexInspectorController class manages the visual representation of the player's codex in the GUI.
 */
public class CodexInspectorController extends ViewController {

    @FXML
    private ImageView previewImageView;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView background;

    @FXML
    private Text playerInspected;


    private double mouseX;
    private double mouseY;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private HashMap<Coordinate, ImageView> placedCards;

    private String playerToInspect;

    private static CodexInspectorController instance;

    public static CodexInspectorController getInstance() {
        return instance;
    }

    @FXML
    public void initialize() {
        updateLimits();
        background.imageProperty().addListener((observable, oldValue, newValue) -> updateLimits());

        previewImageView.setVisible(false);
        previewImageView.setOpacity(0.5);

        placedCards = new HashMap<>();
        instance = this;
    }

    /**
     * Places the cards on the scene
     */
    private void placeCardsOnScene() {
        Map<Coordinate, Card> codex = ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(playerToInspect)).getCards();
        for (Map.Entry<Coordinate, Card> entry : codex.entrySet()) {
            Coordinate coordinates = entry.getKey();
            Card card = entry.getValue();
            if (coordinates.equals(new Coordinate(80, 80))) {
                placeCard(coordinates, card, 551, 328);
            } else {
                placeCardRelativeToCenter(coordinates, card);
            }
        }
    }

    /**
     * Places a card relative to the center
     * @param cardCoordinates the coordinates of the card
     * @param card the card to be placed
     */
    private void placeCardRelativeToCenter(Coordinate cardCoordinates, Card card) {
        Coordinate centralCoordinates = new Coordinate(80, 80);

        int offsetX = cardCoordinates.getX() - centralCoordinates.getX();
        int offsetY = cardCoordinates.getY() - centralCoordinates.getY();

        double targetX = placedCards.get(new Coordinate(80, 80)).getLayoutX() + offsetX * 109;
        double targetY = placedCards.get(new Coordinate(80, 80)).getLayoutY() + offsetY * -56;

        placeCard(cardCoordinates, card, targetX, targetY);
    }

    /**
     * Places a card at the specified location
     * @param cardCoordinates the coordinates of the card
     * @param card the card to be placed
     * @param x the x-coordinate for placement
     * @param y the y-coordinate for placement
     */
    private void placeCard(Coordinate cardCoordinates, Card card, double x, double y) {
        ImageView cardImageView = new ImageView();
        if(!card.isTurned()) {
            setCardImage(cardImageView, "/polimi/ingsw/Cards/Fronts/"+ card.getCardID() +".png");
        } else {
            loadBack(cardImageView, card);
        }

        cardImageView.setScaleX(0.7);
        cardImageView.setScaleY(0.7);
        cardImageView.setFitWidth(200);
        cardImageView.setFitHeight(133);

        cardImageView.setLayoutX(x);
        cardImageView.setLayoutY(y);

        placedCards.put(cardCoordinates, cardImageView);
        anchor.getChildren().add(placedCards.size(), cardImageView);
    }

    /**
     * Sets the image for a card
     * @param imageView the ImageView to set the image on
     * @param imagePath the path to the image file
     */
    private void setCardImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.out.println("Image not found: " + imagePath);
        }
    }

    /**
     * Loads the back image for a card
     * @param imageView the ImageView to set the image on
     * @param card the card whose back image is to be loaded
     */
    private void loadBack(ImageView imageView, Card card) {
        if(card instanceof GoldCard) {
            switch (card.getCardType()) {
                case ANIMAL -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/AnimalGold.png");
                case FUNGI -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/FungiGold.png");
                case PLANT -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/PlantGold.png");
                case INSECT -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/InsectGold.png");
            }
        } else if(card instanceof InitialCard) {
            setCardImage(imageView, "/polimi/ingsw/Cards/Backs/"+card.getCardID()+".png");
        } else {
            switch(card.getCardType()) {
                case ANIMAL -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/Animal.png");
                case FUNGI -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/Fungi.png");
                case PLANT -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/Plant.png");
                case INSECT -> setCardImage(imageView, "/polimi/ingsw/Cards/Backs/Insect.png");
            }
        }
    }

    /**
     * Updates the layout limits
     */
    private void updateLimits() {

        double imageWidth = background.getImage().getWidth();
        double imageHeight = background.getImage().getHeight();

        minX = 0;
        minY = 0;
        maxX = imageWidth;
        maxY = imageHeight;
    }

    /**
     * Sets the player to inspect
     * @param username the username of the player to inspect
     */
    public void setPlayerToInspect(String username) {
        Color playerColor = ClientController.getInstance().getPlayerByUsername(username).getColor();
        this.playerToInspect = username;
        playerInspected.setText(playerToInspect);
        switch(playerColor) {
            case Color.Blue:
                playerInspected.setFill(javafx.scene.paint.Color.BLUE);
                break;
            case Color.Red:
                playerInspected.setFill(javafx.scene.paint.Color.RED);
                break;
            case Color.Yellow:
                playerInspected.setFill(javafx.scene.paint.Color.web("#edb009"));
                break;
            case Color.Green:
                playerInspected.setFill(javafx.scene.paint.Color.GREEN);
                break;
        }
        placeCardsOnScene();
    }

    /**
     * Handles the background movement when dragged
     * @param event the mouse event triggered by dragging the background
     */
    @FXML
    public void handleBackground(MouseEvent event) {
        double offsetX = event.getSceneX() - mouseX;
        double offsetY = event.getSceneY() - mouseY;

        double newLayoutX = background.getLayoutX() + offsetX;
        double newLayoutY = background.getLayoutY() + offsetY;

        if (newLayoutX >= minX && newLayoutX <= maxX) {
            background.setLayoutX(newLayoutX);
            for(ImageView c : placedCards.values()) {
                c.setLayoutX(c.getLayoutX() + offsetX);
            }
        }

        if (newLayoutY >= minY && newLayoutY <= maxY) {
            background.setLayoutY(newLayoutY);
            for(ImageView c : placedCards.values()) {
                c.setLayoutY(c.getLayoutY() + offsetY);
            }
        }

        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }


    /**
     * Handles the mouse press event
     * @param event the mouse event triggered by pressing the mouse
     */
    @FXML
    public void handleMousePressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

}
