package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.TUI.Messages;
import it.polimi.ingsw.view.TUI.View;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class MainClient{

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {

        View.getInstance().showTitleScreen();

        Scanner s = new Scanner(System.in);
        Messages.getInstance().input("Insert 1 for socket or 2 for RMI: ");
        int selection = s.nextInt();

        s.nextLine();

        Messages.getInstance().input("Insert the server address: ");
        String host = s.nextLine();


        boolean socket = true;

        if (selection == 2) {
            socket = false;
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
