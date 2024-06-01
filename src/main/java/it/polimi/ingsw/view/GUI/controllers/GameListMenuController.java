package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.client.commands.RefreshAvailableGamesCommand;
import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.GUI.SceneEnum;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.ArrayList;

public class GameListMenuController extends ViewController {
    @FXML
    private ListView<VBox> gameListView;
    private ArrayList<SerializedGame> games;

    public void populateGamesList(ArrayList<SerializedGame> games) {
        this.games = games;
        for (SerializedGame game : this.games) {
            VBox vbox = new VBox();
            Text gameIdText = new Text("Id partita: " + game.getGameID() + " - ");
            Text playersText = new Text(game.getCurrentPlayers() + "/" + game.getMaxPlayers());
            playersText.setStyle("-fx-fill: blue; -fx-font-family: 'Luminari'; -fx-font-size: 20;");
            gameIdText.setStyle("-fx-font-family: 'Luminari'; -fx-font-size: 20;");
            TextFlow textFlow = new TextFlow(gameIdText, playersText);
            vbox.setAlignment(Pos.CENTER);
            vbox.getChildren().addAll(textFlow);
            vbox.setAlignment(Pos.CENTER);
            gameListView.getItems().add(vbox);
        }
    }

    @FXML
    private void handleJoinButton() {
        int selectedIndex = gameListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            SerializedGame selectedGame = games.get(selectedIndex);
            int gameId = selectedGame.getGameID();
            ClientController.getInstance().JoinGame(gameId);
        } else {
            System.out.println("No game selected");
        }
    }

    @FXML
    public void refresh() {
        try {
            RefreshAvailableGamesCommand cmd = new RefreshAvailableGamesCommand();
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void back() throws IOException {
        ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.MAIN_MENU);
    }
}