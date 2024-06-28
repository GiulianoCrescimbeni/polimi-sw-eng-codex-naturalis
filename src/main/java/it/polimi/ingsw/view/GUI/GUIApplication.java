package it.polimi.ingsw.view.GUI;

import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.GUI.controllers.ChatViewController;
import it.polimi.ingsw.view.GUI.controllers.ErrorViewController;
import it.polimi.ingsw.view.GUI.controllers.GameListMenuController;
import it.polimi.ingsw.view.GUI.controllers.ViewController;
import it.polimi.ingsw.view.TUI.View;
import it.polimi.ingsw.view.ViewInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Class that represents the GUI View
 */
public class GUIApplication extends Application implements ViewInterface {
    private Stage mainStage, popUpStage;
    private StackPane root;

    /**
     * Start the GUI
     * @param stage the {@link Stage} to start from
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        ClientController.getInstance().setViewInterface(this);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/polimi/ingsw/fxml/MainMenu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        mainStage = stage;
        mainStage.setScene(scene);
        this.mainStage.setTitle("Codex Naturalis");
        mainStage.show();
        mainStage.setOnCloseRequest(event -> { System.exit(0); });
        root = new StackPane();
    }

    /**
     * Set up the main stage
     * @param sceneName the name of the scene that you want to load in the main stage
     * @return the {@link ViewController} of that {@link Stage}
     * @throws IOException
     */
    public ViewController setMainScene(SceneEnum sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName.value()));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        this.mainStage.setScene(scene);
        return loader.getController();
    }

    /**
     * Open a new popup
     * @param sceneName the name of the scene that you want to load in the popup
     * @return the {@link ViewController} of that {@link Stage}
     * @throws IOException
     */
    public ViewController openPopup(SceneEnum sceneName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sceneName.value()));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        popUpStage = new Stage();
        popUpStage.setTitle("Info");
        popUpStage.setResizable(false);
        popUpStage.setScene(scene);
        popUpStage.setX(mainStage.getX() + (mainStage.getWidth() - scene.getWidth()) * 0.5);
        popUpStage.setY(mainStage.getY() + (mainStage.getHeight() - scene.getHeight()) * 0.5);
        popUpStage.initModality(Modality.APPLICATION_MODAL);
        popUpStage.initOwner(mainStage);
        popUpStage.show();
        return loader.getController();
    }

    /**
     * Load the select available match screen
     * @param availableMatches the ArrayList of {@link SerializedGame} representing all the available matches
     * @param error
     * @throws IOException
     */
    public void selectAvailableMatch(ArrayList<SerializedGame> availableMatches, String error) {
        Platform.runLater(() -> {
                    try {
                        GameListMenuController controller = (GameListMenuController) ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.GAME_LIST_MENU);
                        controller.populateGamesList(availableMatches);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    /**
     * Load the screen to pick username and color
     */
    public void pickUsernameAndColor() {
        Platform.runLater(() -> {
            try {
                ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.LOGIN_MENU);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Load the screen for selecting a personal {@link it.polimi.ingsw.model.Goals.Goal}
     */
    public void selectPersonalGoal() {
        Platform.runLater(() -> {
            try {
                ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.PERSONAL_GOAL_SELECTION_MENU);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Load the screen for the waiting room
     */
    public void waitingRoom() {
        Platform.runLater(() -> {
            try {
                ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.WAITING_ROOM);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Load the screen for selecting an {@link it.polimi.ingsw.model.GameComponents.InitialCard} side
     * @throws IOException
     */
    public void selectInitialCardSide() {
        Platform.runLater(() -> {
            try {
                ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.INITIAL_CARD_SIDE_SELECTION_MENU);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Update the screen after a new turn
     * @param message a message in case of error
     * @param clear true if is needed to clear the screen (Only for TUI)
     */
    public void updateInfo(String message, boolean clear) {
        Platform.runLater(() -> {
            try {
                if(message != null) {
                    showError(message);
                }
                ((GUIApplication) ClientController.getInstance().getViewInterface()).setMainScene(SceneEnum.CODEX);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Load the screen for the chat
     * @param error
     */
    public void updateChatView(String error) {
        Platform.runLater(() -> {
            if (ChatViewController.getInstance() == null) return;
            ChatViewController.getInstance().updateMessageHistory();
        });
    }

    /**
     * Show an error message
     * @param error the string of the error message
     */
    public void showError(String error) {
        Platform.runLater(() -> {
            try {
                ViewController viewController = ((GUIApplication) ClientController.getInstance().getViewInterface()).openPopup(SceneEnum.ERROR);
                ((ErrorViewController) viewController).setErrorMessage(error);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Show the winning screen
     */
    public void winScreen() {
        Platform.runLater(() -> {
            try {
                ((GUIApplication) ClientController.getInstance().getViewInterface()).openPopup(SceneEnum.WINNING_SCREEN);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Show the loosing screen
     */
    public void looseScreen() {
        Platform.runLater(() -> {
            try {
                ((GUIApplication) ClientController.getInstance().getViewInterface()).openPopup(SceneEnum.LOOSING_SCREEN);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
