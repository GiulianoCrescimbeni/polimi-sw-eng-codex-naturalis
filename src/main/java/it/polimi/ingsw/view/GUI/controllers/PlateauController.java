package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.View;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class PlateauController extends ViewController {

    @FXML
    private AnchorPane anchor;

    private Map<Player, ImageView> tokens = new HashMap<>();
    private Map<String, Integer> positionCount = new HashMap<>();

    @FXML
    public void initialize() {
        initializeTokens();
    }

    private void initializeTokens() {
        for (Player player : ClientController.getInstance().getPlayers()) {
            ImageView token = new ImageView();
            setTokenImage(token, getToken(player.getColor()));
            token.setFitWidth(70);
            token.setFitHeight(72);
            tokens.put(player, token);
            setCoordinate(ClientController.getInstance().getCodexMap().get(player).getScore(), token, player);
            anchor.getChildren().add(token);
        }
    }

    private String getToken(Color color) {
        switch (color) {
            case Yellow:    return new String("/placeholder_yellow.png");
            case Blue:      return new String("/placeholder_blue.png");
            case Red:       return new String("/placeholder_red.png");
            case Green:     return new String("/placeholder_green.png");
            default:        return null;
        }
    }

    private void setTokenImage(ImageView imageView, String imagePath) {
        try {
            Image image = new Image(getClass().getResource(imagePath).toExternalForm());
            imageView.setImage(image);
        } catch (NullPointerException e) {
            System.out.println("Image not found: " + imagePath);
        }
    }

    private void setCoordinate(int score, ImageView token, Player player) {
        double x = 0;
        double y = 0;

        switch (score) {
            case 0:
                x = 58;
                y = 616;
                break;
            case 1:
                x = 140;
                y = 616;
                break;
            case 2:
                x = 223;
                y = 616;
                break;
            case 3:
                x = 265;
                y = 539;
                break;
            case 4:
                x = 182;
                y = 539;
                break;
            case 5:
                x = 99;
                y = 539;
                break;
            case 6:
                x = 17;
                y = 539;
                break;
            case 7:
                x = 17;
                y = 464;
                break;
            case 8:
                x = 99;
                y = 464;
                break;
            case 9:
                x = 182;
                y = 464;
                break;
            case 10:
                x = 265;
                y = 464;
                break;
            case 11:
                x = 265;
                y = 389;
                break;
            case 12:
                x = 182;
                y = 389;
                break;
            case 13:
                x = 99;
                y = 389;
                break;
            case 14:
                x = 17;
                y = 389;
                break;
            case 15:
                x = 17;
                y = 314;
                break;
            case 16:
                x = 99;
                y = 314;
                break;
            case 17:
                x = 182;
                y = 314;
                break;
            case 18:
                x = 265;
                y = 314;
                break;
            case 19:
                x = 265;
                y = 238;
                break;
            case 20:
                x = 140;
                y = 202;
                break;
            case 21:
                x = 17;
                y = 238;
                break;
            case 22:
                x = 17;
                y = 163;
                break;
            case 23:
                x = 17;
                y = 88;
                break;
            case 24:
                x = 65;
                y = 26;
                break;
            case 25:
                x = 140;
                y = 12;
                break;
            case 26:
                x = 217;
                y = 26;
                break;
            case 27:
                x = 265;
                y = 88;
                break;
            case 28:
                x = 265;
                y = 163;
                break;
            case 29:
                x = 140;
                y = 104;
                break;
        }

        String positionKey = x + "," + y;
        int offset = positionCount.getOrDefault(positionKey, 0);

        token.setLayoutX(x);
        token.setLayoutY(y - offset * 7);

        positionCount.put(positionKey, offset + 1);
    }
}