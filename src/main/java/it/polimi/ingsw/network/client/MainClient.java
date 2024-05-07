package it.polimi.ingsw.network.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainClient{

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {
        try {
            ClientSR.getInstance().startSR("localhost", 25565);
            while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
