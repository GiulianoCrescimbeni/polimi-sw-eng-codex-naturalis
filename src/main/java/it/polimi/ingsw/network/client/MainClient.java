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
        int selection = s.nextInt();
        if (selection == 2) {
            socket = false;
        }
        s.nextLine();

        Messages.getInstance().input("Insert the server address: ");
        String host = s.nextLine();

        Messages.getInstance().input("Insert 1 for TUI or 2 for GUI: ");
        selection = s.nextInt();

        if(selection == 1) {
            ClientController.getInstance().setViewInterface(View.getInstance());
        } else if (selection == 2) {
            Application.launch(GUIApplication.class);
        }

        try {

            if (socket) {
                ClientSR.getInstance().startSR(host, 25565, socket);
            } else {
                ClientSR.getInstance().startSR(host, 25566, socket);
            }

            while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
