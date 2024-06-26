package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.GoldCard;
import it.polimi.ingsw.model.GameComponents.InitialCard;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import it.polimi.ingsw.view.TUI.View;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.nio.channels.ClosedByInterruptException;
import java.util.HashMap;
import java.util.Map;


public class GameController extends ViewController {

    private double mouseX;
    private double mouseY;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ImageView background;

    @FXML
    private ImageView Hand1;

    @FXML
    private ImageView Hand2;

    @FXML
    private ImageView Hand3;

    @FXML
    private ImageView ResourceDeck;

    @FXML
    private ImageView GoldDeck;

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
    private ImageView previewImageView;

    @FXML
    private ComboBox<String> codexSelection;

    private double initialX;
    private double initialY;
    private double initialLayoutX;
    private double initialLayoutY;
    private double minX;
    private double maxX;
    private double minY;
    private double maxY;
    private HashMap<Coordinate, ImageView> placedCards;
    private Boolean isPlacing;
    private Coordinate placedCoordinate;
    private Card placedCard;
    private Boolean turnCard;
    private Boolean isPicking;

    @FXML
    public void initialize() {
        updateLimits();
        background.imageProperty().addListener((observable, oldValue, newValue) -> updateLimits());
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
        makeDraggable(Hand1);
        makeDraggable(Hand2);
        makeDraggable(Hand3);
        addHoverEffect(GroundCard1, ClientController.getInstance().getCardToPick().get(0));
        addHoverEffect(GroundCard2, ClientController.getInstance().getCardToPick().get(1));
        addHoverEffect(GroundGoldCard1, ClientController.getInstance().getGoldCardToPick().get(0));
        addHoverEffect(GroundGoldCard2, ClientController.getInstance().getGoldCardToPick().get(1));
        printCurrentPlayer();
        turnCard = false;

        previewImageView.setVisible(false);
        previewImageView.setOpacity(0.5);

        placedCards = new HashMap<>();
        placeCardsOnScene();

        setupCodexes();

        isPlacing = false;
        isPicking = false;
        if(ClientController.getInstance().isMyTurn())
            isPlacing = true;

    }

    @FXML
    public void handleMousePressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    private void setupCodexes() {
        for (Player p : ClientController.getInstance().getPlayers()) {
            if (!p.getNickname().equals(ClientController.getInstance().getUsername())) {
                codexSelection.getItems().add(p.getNickname());
            }
        }
    }

    private void setGoalShadow(ImageView goalImage, Card card) {
        switch (card.getCardType()) {
            case FUNGI -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, red, 10, 0, 0, 5);");
            case ANIMAL -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, lightblue, 10, 0, 0, 5);");
            case PLANT -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, green, 10, 0, 0, 5);");
            case INSECT -> goalImage.setStyle("-fx-effect: dropshadow(three-pass-box, purple, 10, 0, 0, 5);");
        }
    }

    private void addHoverEffect(ImageView imageView, Card card) {
        imageView.setOnMouseEntered(event -> {
            if(isPicking) {
                setGoalShadow(imageView, card);
            }
        });

        imageView.setOnMouseExited(event -> {
            if(isPicking) {
                imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 5);");
            }
        });
    }

    @FXML
    private void addResourceDeckHoverEffect(MouseEvent mouseEvent) {
        if(isPicking)
            ResourceDeck.setStyle("-fx-effect: dropshadow(three-pass-box, WHITE, 10, 0, 0, 5);");
    }

    @FXML
    private void removeResourceDeckHoverEffect(MouseEvent mouseEvent) {
        if(isPicking)
            ResourceDeck.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 5);");
    }

    @FXML
    private void addGoldDeckHoverEffect(MouseEvent mouseEvent) {
        if(isPicking)
            GoldDeck.setStyle("-fx-effect: dropshadow(three-pass-box, WHITE, 10, 0, 0, 5);");
    }

    @FXML
    private void removeGoldDeckHoverEffect(MouseEvent mouseEvent) {
        if(isPicking)
            GoldDeck.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0, 0, 0, 0.5), 10, 0, 0, 5);");
    }

    private void makeDraggable(ImageView imageView) {
        imageView.setOnMousePressed(event -> {
            if(isPlacing) {
                initialX = event.getSceneX() - imageView.getLayoutX();
                initialY = event.getSceneY() - imageView.getLayoutY();
                initialLayoutX = imageView.getLayoutX();
                initialLayoutY = imageView.getLayoutY();
                imageView.toFront();
            }
        });

        imageView.setOnMouseDragged(event -> {
            if(isPlacing) {
                double newX = event.getSceneX() - initialX;
                double newY = event.getSceneY() - initialY;
                imageView.setLayoutX(newX);
                imageView.setLayoutY(newY);

                Coordinate overlappingCard = getOverlappingCard(newX, newY, imageView.getFitWidth(), imageView.getFitHeight());
                if (overlappingCard != null) {
                    showPreview(newX, newY, imageView, overlappingCard);
                } else {
                    previewImageView.setVisible(false);
                }
            }
        });

        imageView.setOnMouseReleased(event -> {
            if(isPlacing) {
                double dropX = imageView.getLayoutX();
                double dropY = imageView.getLayoutY();

                Coordinate overlappingCard = getOverlappingCard(dropX, dropY, imageView.getFitWidth(), imageView.getFitHeight());
                if (overlappingCard != null) {
                    snapToCorner(imageView, dropX, dropY, overlappingCard);
                    isPlacing = false;
                    isPicking = true;
                } else {
                    imageView.setLayoutX(initialLayoutX);
                    imageView.setLayoutY(initialLayoutY);
                }
                previewImageView.setVisible(false);
            }
        });
    }

    private void placeCardsOnScene() {
        Map<Coordinate, Card> codex = ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayerByUsername(ClientController.getInstance().getUsername())).getCards();
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

        anchor.getChildren().add(cardImageView);
        placedCards.put(cardCoordinates, cardImageView);
    }

    private Coordinate getOverlappingCard(double dropX, double dropY, double cardWidth, double cardHeight) {
        for (Map.Entry<Coordinate, ImageView> entry : placedCards.entrySet()) {
            Coordinate coordinates = entry.getKey();
            ImageView imageView = entry.getValue();

            double centralX = imageView.getLayoutX();
            double centralY = imageView.getLayoutY();
            double centralWidth = imageView.getFitWidth();
            double centralHeight = imageView.getFitHeight();

            boolean isOverlappingHorizontally = (dropX + cardWidth > centralX) && (dropX < centralX + centralWidth);
            boolean isOverlappingVertically = (dropY + cardHeight > centralY) && (dropY < centralY + centralHeight);

            boolean isInTopLeftCorner = dropX < centralX && dropY < centralY;
            boolean isInTopRightCorner = dropX > centralX + centralWidth / 2 && dropY < centralY;
            boolean isInBottomLeftCorner = dropX < centralX && dropY > centralY + centralHeight / 2;
            boolean isInBottomRightCorner = dropX > centralX + centralWidth / 2 && dropY > centralY + centralHeight / 2;

            if ((isInTopLeftCorner || isInTopRightCorner || isInBottomLeftCorner || isInBottomRightCorner) &&
                    isOverlappingHorizontally && isOverlappingVertically) {
                return coordinates;
            }
        }
        return null;
    }

    private void snapToCorner(ImageView imageView, double dropX, double dropY, Coordinate overlappingCard) {
        double[] renderCoordinates = calculateRenderCoordinates(dropX, dropY, imageView.getFitWidth(), imageView.getFitHeight(), overlappingCard);
        int[] targetCoordinates = calculateTargetCoordinates(renderCoordinates[0], renderCoordinates[1]);

        imageView.setLayoutX(renderCoordinates[0]);
        imageView.setLayoutY(renderCoordinates[1]);

        int targetCoordinateX = targetCoordinates[0];
        int targetCoordinateY = targetCoordinates[1];
        placedCoordinate = new Coordinate(targetCoordinateX, targetCoordinateY);
        if(imageView == Hand1) {
            placedCard = ClientController.getInstance().getCurrentPlayer().getPlayerHand().getCards().get(0);
        } else if(imageView == Hand2) {
            placedCard = ClientController.getInstance().getCurrentPlayer().getPlayerHand().getCards().get(1);
        } else if(imageView == Hand3) {
            placedCard = ClientController.getInstance().getCurrentPlayer().getPlayerHand().getCards().get(2);
        }
        placedCards.put(new Coordinate(targetCoordinates[0], targetCoordinates[1]), imageView);
    }

    private void showPreview(double dropX, double dropY, ImageView imageView, Coordinate overlappingCard) {
        double[] renderCoordinates = calculateRenderCoordinates(dropX, dropY, imageView.getFitWidth(), imageView.getFitHeight(), overlappingCard);
        previewImageView.setLayoutX(renderCoordinates[0]);
        previewImageView.setLayoutY(renderCoordinates[1]);
        previewImageView.setFitWidth(imageView.getFitWidth());
        previewImageView.setFitHeight(imageView.getFitHeight());
        previewImageView.setVisible(true);
    }

    private double[] calculateRenderCoordinates(double dropX, double dropY, double cardWidth, double cardHeight, Coordinate overlappingCard) {
        ImageView centralCard = placedCards.get(overlappingCard);

        double centralX = centralCard.getLayoutX();
        double centralY = centralCard.getLayoutY();
        double centralWidth = centralCard.getFitWidth();
        double centralHeight = centralCard.getFitHeight();

        double targetX = centralX;
        double targetY = centralY;

        if (dropX > centralX + centralWidth / 2 && dropY < centralY + centralHeight / 2) {
            // Top-right corner
            targetX = centralX + 109;
            targetY = centralY - 56;
        } else if (dropX < centralX + centralWidth / 2 && dropY < centralY + centralHeight / 2) {
            // Top-left corner
            targetX = centralX - 109;
            targetY = centralY - 56;
        } else if (dropX < centralX + centralWidth / 2 && dropY > centralY + centralHeight / 2) {
            // Bottom-left corner
            targetX = centralX - 109;
            targetY = centralY + 56;
        } else if (dropX > centralX + centralWidth / 2 && dropY > centralY + centralHeight / 2) {
            // Bottom-right corner
            targetX = centralX + 109;
            targetY = centralY + 56;
        }

        return new double[]{targetX, targetY};
    }

    private int[] calculateTargetCoordinates(double dropX, double dropY) {
        return new int[]{(int) (((dropX - placedCards.get(new Coordinate(80, 80)).getLayoutX())/109)+80), (int) (((dropY - placedCards.get(new Coordinate(80, 80)).getLayoutY())/-56)+80)};
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

    @FXML
    public void handleTurnCard(MouseEvent event) {
        if(!turnCard) {
            loadBack(Hand1, ClientController.getInstance().getPlayerHand().getCards().get(0));
            loadBack(Hand2, ClientController.getInstance().getPlayerHand().getCards().get(1));
            loadBack(Hand3, ClientController.getInstance().getPlayerHand().getCards().get(2));
            turnCard = true;
        } else {
            setCardImage(Hand1, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPlayerHand().getCards().get(0).getCardID() +".png");
            setCardImage(Hand2, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPlayerHand().getCards().get(1).getCardID() +".png");
            setCardImage(Hand3, "/polimi/ingsw/Cards/Fronts/"+ ClientController.getInstance().getPlayerHand().getCards().get(2).getCardID() +".png");
            turnCard = false;
        }
    }

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

    @FXML
    public void handleResourceCardDeck(MouseEvent event) {
        if(isPicking) {
            if(turnCard)
                placedCard.turn();
            ClientController.getInstance().playWithPickFromDeck(placedCoordinate, placedCard, 0);
        }
    }

    @FXML
    public void handleGoldCardDeck(MouseEvent event) {
        if(isPicking) {
            if(turnCard)
                placedCard.turn();
            ClientController.getInstance().playWithPickFromDeck(placedCoordinate, placedCard, 1);
        }
    }

    @FXML
    public void handleGroundCard1(MouseEvent event) {
        if(isPicking) {
            if(turnCard)
                placedCard.turn();
            ClientController.getInstance().playWithPickFromGround(placedCoordinate, placedCard, ClientController.getInstance().getCardToPick().get(0));
        }
    }

    @FXML
    public void handleGroundCard2(MouseEvent event) {
        if(isPicking) {
            if(turnCard)
                placedCard.turn();
            ClientController.getInstance().playWithPickFromGround(placedCoordinate, placedCard, ClientController.getInstance().getCardToPick().get(1));
        }
    }

    @FXML
    public void handleGroundGoldCard1(MouseEvent event) {
        if(isPicking) {
            if(turnCard)
                placedCard.turn();
            ClientController.getInstance().playWithPickFromGround(placedCoordinate, placedCard, ClientController.getInstance().getGoldCardToPick().get(0));
        }
    }

    @FXML
    public void handleGroundGoldCard2(MouseEvent event) {
        if(isPicking) {
            if(turnCard)
                placedCard.turn();
            ClientController.getInstance().playWithPickFromGround(placedCoordinate, placedCard, ClientController.getInstance().getGoldCardToPick().get(1));
        }
    }

    @FXML
    public void handleInspectCodex(ActionEvent event) {
        if (codexSelection.getValue() == null) return;

        try {
            GUIApplication viewInterface = (GUIApplication) ClientController.getInstance().getViewInterface();
            viewInterface.openPopup(SceneEnum.INSPECT_CODEX);
            CodexInspectorController.getInstance().setPlayerToInspect(codexSelection.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handlePlateau(ActionEvent event) {
        try {
            GUIApplication viewInterface = (GUIApplication) ClientController.getInstance().getViewInterface();
            viewInterface.openPopup(SceneEnum.PLATEAU);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleChat(ActionEvent event) {
        try {
            GUIApplication viewInterface = (GUIApplication) ClientController.getInstance().getViewInterface();
            viewInterface.openPopup(SceneEnum.CHAT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}