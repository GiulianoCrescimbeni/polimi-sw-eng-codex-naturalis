package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.GUI.GUIApplication;
import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.View;
import javafx.application.Application;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainClient{

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {

        boolean socket = true;
        View.getInstance().showTitleScreen();

        Scanner s = new Scanner(System.in);
        Messages.getInstance().input("Insert 1 for socket or 2 for RMI: ");
        String input = s.nextLine();

        while (!isNumeric(input) || Integer.parseInt(input) > 2 || Integer.parseInt(input) < 1) {
            Messages.getInstance().error("Wrong input format");
            Messages.getInstance().input("Insert 1 for socket or 2 for RMI: ");
            input = s.nextLine();
        }

        int selection = Integer.parseInt(input);

        if (selection == 2) {
            socket = false;
        }
        //s.nextLine();

        Messages.getInstance().input("Insert the server address: ");
        String host = s.nextLine();

        Messages.getInstance().input("Insert 1 for TUI or 2 for GUI: ");
        input = s.nextLine();

        while (!isNumeric(input) || Integer.parseInt(input) > 2 || Integer.parseInt(input) < 1) {
            Messages.getInstance().error("Wrong input format");
            Messages.getInstance().input("Insert 1 for TUI or 2 for GUI: ");
            input = s.nextLine();
        }

        selection = Integer.parseInt(input);

        try {

            if (socket) {
                ClientSR.getInstance().startSR(host, 25565, socket);
            } else {
                ClientSR.getInstance().startSR(host, 25566, socket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if(selection == 1) {
            ClientController.getInstance().setViewInterface(View.getInstance());
            View.getInstance().joinOrCreateMatch();
        } else if (selection == 2) {
            Application.launch(GUIApplication.class);
        }
        while (true);
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
