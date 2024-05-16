package it.polimi.ingsw.network.client;

import it.polimi.ingsw.view.TUI.View;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainClient{

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {
        try {
            ClientSR.getInstance().startSR("localhost", 25565);
            View.getInstance().showTitleScreen();
            while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
