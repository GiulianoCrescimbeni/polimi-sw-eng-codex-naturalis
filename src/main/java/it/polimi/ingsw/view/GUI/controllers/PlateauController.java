package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.network.client.ClientController;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.util.HashMap;
import java.util.Map;


public class PlateauController {

    @FXML
    private AnchorPane anchor;

    private Map<Player, ImageView> tokens = new HashMap<>();

    @FXML
    public void initialize() {
        initializeTokens();
    }

    private void initializeTokens() {
        for (Player player : ClientController.getInstance().getPlayers()) {
            ImageView token = getToken(player.getColor());
            token.setFitWidth(70);
            token.setFitHeight(72);
            tokens.put(player, token);
            setCoordinate(ClientController.getInstance().getCodexMap().get(player).getScore(), token);
            anchor.getChildren().add(token);
        }
    }

    private ImageView getToken(Color color) {
        switch (color) {
            case Yellow:    return new ImageView("src/main/resources/placeholder_yellow.png");
            case Blue:      return new ImageView("src/main/resources/placeholder_blue.png");
            case Red:       return new ImageView("src/main/resources/placeholder_red.png");
            case Green:     return new ImageView("src/main/resources/placeholder_green.png");
            default:        return null;
        }
    }

    private void setCoordinate(int score, ImageView token) {
        switch (score) {
            case 0:
                token.setLayoutX(58);
                token.setLayoutY(616);
                break;
            case 1:
                token.setLayoutX(140);
                token.setLayoutY(616);
                break;
            case 2:
                token.setLayoutX(223);
                token.setLayoutY(616);
                break;
            case 3:
                token.setLayoutX(265);
                token.setLayoutY(539);
            case 4:
                token.setLayoutX(182);
                token.setLayoutY(539);
                break;
            case 5:
                token.setLayoutX(99);
                token.setLayoutY(539);
            case 6:
                token.setLayoutX(17);
                token.setLayoutY(539);
                break;
            case 7:
                token.setLayoutX(17);
                token.setLayoutY(464);
                break;
            case 8:
                token.setLayoutX(99);
                token.setLayoutY(464);
                break;
            case 9:
                token.setLayoutX(182);
                token.setLayoutY(464);
                break;
            case 10:
                token.setLayoutX(265);
                token.setLayoutY(464);
                break;
            case 11:
                token.setLayoutX(265);
                token.setLayoutY(389);
                break;
            case 12:
                token.setLayoutX(182);
                token.setLayoutY(389);
                break;
            case 13:
                token.setLayoutX(99);
                token.setLayoutY(389);
                break;
            case 14:
                token.setLayoutX(17);
                token.setLayoutY(389);
                break;
            case 15:
                token.setLayoutX(17);
                token.setLayoutY(314);
                break;
            case 16:
                token.setLayoutX(99);
                token.setLayoutY(314);
                break;
            case 17:
                token.setLayoutX(182);
                token.setLayoutY(314);
                break;
            case 18:
                token.setLayoutX(265);
                token.setLayoutY(314);
                break;
            case 19:
                token.setLayoutX(265);
                token.setLayoutY(238);
                break;
            case 20:
                token.setLayoutX(140);
                token.setLayoutY(202);
                break;
            case 21:
                token.setLayoutX(17);
                token.setLayoutY(238);
                break;
            case 22:
                token.setLayoutX(17);
                token.setLayoutY(163);
                break;
            case 23:
                token.setLayoutX(17);
                token.setLayoutY(88);
                break;
            case 24:
                token.setLayoutX(65);
                token.setLayoutY(26);
                break;
            case 25:
                token.setLayoutX(140);
                token.setLayoutY(12);
                break;
            case 26:
                token.setLayoutX(217);
                token.setLayoutY(26);
                break;
            case 27:
                token.setLayoutX(265);
                token.setLayoutY(88);
                break;
            case 28:
                token.setLayoutX(265);
                token.setLayoutY(163);
                break;
            case 29:
                token.setLayoutX(140);
                token.setLayoutY(104);
                break;
        }
    }
}

