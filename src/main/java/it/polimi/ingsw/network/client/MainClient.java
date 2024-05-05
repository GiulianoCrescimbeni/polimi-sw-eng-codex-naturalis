package it.polimi.ingsw.network.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainClient{

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public static void main(String[] args) {
        try {
/*
            ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
            System.out.print("Inserisci il tuo nickname: ");
            Scanner scanner = new Scanner(System.in);
            String nickname = scanner.nextLine();

            LoginCommand lgCmd = new LoginCommand(nickname, null);

            out.writeObject(lgCmd);
*/
            ClientSR.getInstance().startSR("localhost", 25565);

            while (true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
