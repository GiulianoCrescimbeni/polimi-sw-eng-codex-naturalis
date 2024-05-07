package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.ClientController;

import java.util.Scanner;

public class LoginView {

    private static LoginView instance;

    private LoginView() {}

    public static LoginView getInstance() {
        if (instance == null) {
            instance = new LoginView();
        }
        return instance;
    }

    public void showColors() {
        System.out.println("Ecco una lista dei colori disponibili: ");
        for(int i = 0; i < ClientController.getInstance().getAvailableColors().size(); i++) {
            System.out.println(i + ": " + ClientController.getInstance().getAvailableColors().get(i).toString());
        }
        pickUsernameAndColor();
    }

    public void pickUsernameAndColor() {

        Scanner s = new Scanner(System.in);
        System.out.print("Inserisci il tuo username: ");

        String username = s.nextLine();


        if (ClientController.getInstance().getAvailableColors().size() > 1) {
            System.out.print("Inserisci il numero corrispondente al colore che vuoi scegliere: ");
            int colorIndex = s.nextInt();

            if (ClientController.getInstance().getAvailableColors().get(colorIndex) != null) {
                Color c = ClientController.getInstance().getAvailableColors().get(colorIndex);
                ClientController.getInstance().sendUsernameAndColor(username, c);
            }
        } else {
            Color c = ClientController.getInstance().getAvailableColors().get(0);
            ClientController.getInstance().sendUsernameAndColor(username, c);
        }




    }

}
