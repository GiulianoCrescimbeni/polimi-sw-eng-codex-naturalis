package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.client.commands.LoginCommand;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost",25565);

            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());

            System.out.print("Inserisci il tuo nickname: ");
            Scanner scanner = new Scanner(System.in);
            String nickname = scanner.nextLine();

            LoginCommand lgCmd = new LoginCommand();
            lgCmd.setNickname(nickname);

            out.writeObject(lgCmd);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
