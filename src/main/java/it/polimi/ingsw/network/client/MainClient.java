package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.commands.LoginCommand;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",25565);

            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            LoginCommand lgCmd = new LoginCommand();
            lgCmd.setNickname("Java");

            out.writeObject(lgCmd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
