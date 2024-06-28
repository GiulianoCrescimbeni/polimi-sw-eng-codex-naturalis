package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.Messages;

import java.io.Serializable;

/**
 * End game update
 */
public class EndGameUpdate extends Update implements Serializable {

    String winner;

    /**
     * @return the winner of the game
     */
    public String getWinner() {
        return winner;
    }

    /**
     * @param winner the winner of the game
     */
    public void setWinner(String winner) {
        this.winner = winner;
    }

    /**
     * Show the win or loose screen
     */
    @Override
    public void execute() {
        if(getWinner() == null) {
            System.out.print("\n");
            Messages.getInstance().info("Game ended");
            Messages.getInstance().info("Closing");
            System.exit(0);
        } else {
            if(getWinner().equals(ClientController.getInstance().getUsername())) {
                ClientController.getInstance().getViewInterface().winScreen();
            } else {
                ClientController.getInstance().getViewInterface().looseScreen();
            }
        }
    }
}
