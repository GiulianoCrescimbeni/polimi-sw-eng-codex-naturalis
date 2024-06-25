package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

public class CodexInspectorController{

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

    private void placeCardsOnScene() {
        Map<Coordinate, Card> codex = ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(playerToInspect)).getCards();
        for (Map.Entry<Coordinate, Card> entry : codex.entrySet()) {
            Coordinate coordinates = entry.getKey();
            Card card = entry.getValue();
            if (coordinates.equals(new Coordinate(80, 80))) {
                placeCard(coordinates, card, 683, 266);
            } else {
                placeCardRelativeToCenter(coordinates, card);
            }
        }
    }

    private void placeCardRelativeToCenter(Coordinate cardCoordinates, Card card) {
        Coordinate centralCoordinates = new Coordinate(80, 80);

        int offsetX = cardCoordinates.getX() - centralCoordinates.getX();
        int offsetY = cardCoordinates.getY() - centralCoordinates.getY();

        double targetX = placedCards.get(new Coordinate(80, 80)).getLayoutX() + offsetX * 109;
        double targetY = placedCards.get(new Coordinate(80, 80)).getLayoutY() + offsetY * -56;

        placeCard(cardCoordinates, card, targetX, targetY);
    }

    private void placeCard(Coordinate cardCoordinates, Card card, double x, double y) {
        ImageView cardImageView = new ImageView();
        if(!ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(playerToInspect)).getCard(cardCoordinates).isTurned()) {
            setCardImage(cardImageView, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(playerToInspect)).getCard(cardCoordinates).getCardID() +".png");
        } else {
            setCardImage(cardImageView, "/polimi/ingsw/Cards/Backs/"+ ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(playerToInspect)).getCard(cardCoordinates).getCardID() +".png");
        }

        cardImageView.setScaleX(0.7);
        cardImageView.setScaleY(0.7);
        cardImageView.setFitWidth(200);
        cardImageView.setFitHeight(133);

        cardImageView.setLayoutX(x);
        cardImageView.setLayoutY(y);

        anchor.getChildren().add(cardImageView);
        placedCards.put(cardCoordinates, cardImageView);
    }

    private void setCardImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.out.println("Image not found: " + imagePath);
        }
    }

    private void updateLimits() {

        double imageWidth = background.getImage().getWidth();
        double imageHeight = background.getImage().getHeight();

        minX = 0;
        minY = 0;
        maxX = imageWidth;
        maxY = imageHeight;
    }

    public void setPlayerToInspect(String username) {
        this.playerToInspect = username;
        playerInspected.setText(playerToInspect);
        placeCardsOnScene();
    }

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

    @FXML
    public void handleMousePressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

}
