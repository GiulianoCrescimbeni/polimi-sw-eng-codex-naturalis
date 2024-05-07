package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.network.client.ClientController;

import java.util.Scanner;

public class View {

    private static View instance;

    private Scanner s = new Scanner(System.in);

    private View() {}

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

    public void showColors() {
        Messages.getInstance().info("Here's a list of the available colors: ");
        for(int i = 0; i < ClientController.getInstance().getAvailableColors().size(); i++) {
            System.out.println(i + ": " + ClientController.getInstance().getAvailableColors().get(i).toString());
        }
        pickUsernameAndColor();
    }

    public void pickUsernameAndColor() {

        //Scanner s = new Scanner(System.in);
        Messages.getInstance().input("Insert your username: ");

        String username = s.nextLine();

        if (ClientController.getInstance().getAvailableColors().size() > 1) {
            Messages.getInstance().input("Insert the number representing the color you would like to choose: ");
            int colorIndex = s.nextInt();

            if (ClientController.getInstance().getAvailableColors().get(colorIndex) != null) {
                Color c = ClientController.getInstance().getAvailableColors().get(colorIndex);
                ClientController.getInstance().sendUsernameAndColor(username, c);
            }
        } else {
            Color c = ClientController.getInstance().getAvailableColors().get(0);
            ClientController.getInstance().sendUsernameAndColor(username, c);
        }

        s.nextLine();
    }

    public void selectPlayersNumber() {

        int maxPlayers = 0;

        while (maxPlayers < 2 || maxPlayers > 4) {
            Messages.getInstance().input("Select the maximum number of players that can join the game (from " + TextColor.BRIGHT_YELLOW + "2" + TextColor.RESET + " to " + TextColor.BRIGHT_YELLOW + "4" + TextColor.RESET + "): ");
            maxPlayers = s.nextInt();

            if (maxPlayers < 2 || maxPlayers > 4) {
                Messages.getInstance().error("The inserted number must be between " + TextColor.BRIGHT_RED + "2" + TextColor.RESET + " and " + TextColor.BRIGHT_RED + "4" + TextColor.RESET);
            }

        }

        ClientController.getInstance().sendMaxPlayers(maxPlayers);
        s.nextLine();
    }



}
