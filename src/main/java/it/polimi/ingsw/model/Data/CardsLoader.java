package it.polimi.ingsw.model.Data;

import com.google.gson.Gson;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.GoldCard;
import it.polimi.ingsw.model.GameComponents.InitialCard;
import it.polimi.ingsw.model.Goals.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CardsLoader {

    private static CardsLoader instance;

    private CardsLoader() {}

    public static CardsLoader getInstance() {
        if (instance == null) instance = new CardsLoader();
        return instance;
    }

    public ArrayList<Card> loadCards() {
        String filePath = "/polimi/ingsw/JSON/CardsData.json";

        try (InputStream InputReader = getClass().getResourceAsStream(filePath)) {
            InputStreamReader reader = new InputStreamReader(InputReader);
            Gson gson = new Gson();

            CardDataContainer[] cards = gson.fromJson(reader, CardDataContainer[].class);

            ArrayList<Card> toReturn = new ArrayList<Card>();

            for (CardDataContainer card : cards) {
                toReturn.add(card.deserialize());

            }

            return toReturn;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<GoldCard> loadGoldCards() {
        String filePath = "/polimi/ingsw/JSON/GoldCardsData.json";

        try (InputStream InputReader = getClass().getResourceAsStream(filePath)) {
            InputStreamReader reader = new InputStreamReader(InputReader);
            Gson gson = new Gson();

            GoldCardDataContainer[] goldCardsContainers = gson.fromJson(reader, GoldCardDataContainer[].class);

            ArrayList<GoldCard> toReturn = new ArrayList<GoldCard>();

            for (GoldCardDataContainer gcd : goldCardsContainers) {
                toReturn.add(gcd.deserialize());
            }

            return toReturn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Goal> loadObjectivesCards() {
        ArrayList<Goal> toReturn = new ArrayList<Goal>();

        toReturn.add(new DiagonalGoal(null, CardType.FUNGI, 2, 87));
        toReturn.add(new DiagonalGoal(null, CardType.PLANT, 2, 88));
        toReturn.add(new DiagonalGoal(null, CardType.ANIMAL, 2, 89));
        toReturn.add(new DiagonalGoal(null, CardType.INSECT, 2, 90));
        toReturn.add(new LGoal(null, CardType.FUNGI, 3, 91));
        toReturn.add(new LGoal(null, CardType.PLANT, 3, 92));
        toReturn.add(new LGoal(null, CardType.ANIMAL, 3, 93));
        toReturn.add(new LGoal(null, CardType.INSECT, 3, 94));
        toReturn.add(new ResourcesGoal(Resource.FUNGI, CardType.FUNGI, 2, 95));
        toReturn.add(new ResourcesGoal(Resource.PLANT, CardType.PLANT, 2, 96));
        toReturn.add(new ResourcesGoal(Resource.ANIMAL, CardType.ANIMAL, 2, 97));
        toReturn.add(new ResourcesGoal(Resource.INSECT, CardType.INSECT, 2, 98));
        toReturn.add(new DifferentObjectGoal(null, null, 3, 99));
        toReturn.add(new EqualsObjectGoal(Resource.SCROLL, null, 2, 100));
        toReturn.add(new EqualsObjectGoal(Resource.JAR, null, 2, 101));
        toReturn.add(new EqualsObjectGoal(Resource.FEATHER, null, 2, 102));


        return toReturn;
    }

    public ArrayList<InitialCard> loadInitialCards() {
        String filePath = "/polimi/ingsw/JSON/InitialCardsData.json";

        try (InputStream InputReader = getClass().getResourceAsStream(filePath)) {
            InputStreamReader reader = new InputStreamReader(InputReader);
            Gson gson = new Gson();

            InitialCardDataContainer[] initialCardsContainers = gson.fromJson(reader, InitialCardDataContainer[].class);

            ArrayList<InitialCard> toReturn = new ArrayList<InitialCard>();

            for (InitialCardDataContainer icd : initialCardsContainers) {
                toReturn.add(icd.deserialize());
            }

            return toReturn;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
