package it.polimi.ingsw.model.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.Card;

import javax.swing.text.Position;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class CardsLoader {

    private static CardsLoader instance;

    private CardsLoader() {}

    public static CardsLoader getInstance() {
        if (instance == null) instance = new CardsLoader();
        return instance;
    }

    public ArrayList<Card> loadCards() {
        String filePath = "src/main/resources/polimi/ingsw/JSON/CardsData.json";

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();

            Card[] cards = gson.fromJson(reader, Card[].class);

            for (Card card : cards) {
                card.deserializeAnglesMap();
            }

            return new ArrayList<Card>(Arrays.asList(cards));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
