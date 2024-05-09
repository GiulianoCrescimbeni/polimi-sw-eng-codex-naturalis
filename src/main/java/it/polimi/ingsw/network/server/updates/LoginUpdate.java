package it.polimi.ingsw.network.server.updates;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.view.TUI.View;

import java.io.Serializable;

/**
 * The class represents the {@link Update} sent from the server after a login attempt
 */
public class LoginUpdate extends Update implements Serializable {

    private boolean logged;
    private String message;

    /**
     * Constructor
     */
    public LoginUpdate() {}

    /**
     * Constructor
     * @param message the message of the update
     * @param logged a logged flag, True if the login went well
     */
    public LoginUpdate(String message, boolean logged) {
        this.message = message;
        this.logged = logged;
    }

    /**
     * Execute the Update on the client with the result of the login
     * @param clientController the controller of the client
     */
    @Override
    public void execute(ClientController clientController) {
        if(logged == false) {
            System.out.println(message);
            View.getInstance().pickUsernameAndColor();
        } else {
            System.out.println(message);
        }
    }
}
