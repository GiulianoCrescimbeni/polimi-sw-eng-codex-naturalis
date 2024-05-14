package it.polimi.ingsw.model.Data;

import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.AngleGoldCard;
import it.polimi.ingsw.model.GameComponents.GoldCard;
import it.polimi.ingsw.model.GameComponents.ResourceGoldCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GoldCardDataContainer {

    @SerializedName("cardID")
    private int cardID;
    private Map<AnglePos, Angle> anglesMap;
    @SerializedName("cardType")
    private CardType cardType;
    @SerializedName("isTurned")
    private boolean isTurned;
    @SerializedName("cardScore")
    private int cardScore;
    @SerializedName("lUsed")
    private boolean lUsed;
    @SerializedName("dUsed")
    private boolean dUsed;
    @SerializedName("angles")
    private ArrayList<AngleMapDeserializer> anglesMapDeserializer;
    @SerializedName("playCondition")
    private ArrayList<PlayContidionDataContainer> playCondition;
    private ArrayList<Resource> playConditionsArray;
    @SerializedName("scoringType")
    private String scoringType;

    public GoldCard deserialize() {

        deserializeAnglesMap();
        deserializePlayConditions();

        switch (scoringType) {
            case "PLANT":
            case "ANIMAL":
            case "FUNGI":
            case "INSECT":
            case "SCROLL":
            case "FEATHER":
            case "JAR":
            case "BLANK":
                ResourceGoldCard resToReturn = new ResourceGoldCard(cardID, anglesMap, cardType, isTurned, cardScore, lUsed, dUsed, playConditionsArray, Resource.getFromName(scoringType));

                for (AnglePos ap : anglesMap.keySet()) {
                    anglesMap.get(ap).setCard(resToReturn);
                }

                return resToReturn;
            case "ANGLE":
                AngleGoldCard angleToReturn = new AngleGoldCard(cardID, anglesMap, cardType, isTurned, cardScore, lUsed, dUsed, playConditionsArray);

                for (AnglePos ap : anglesMap.keySet()) {
                    anglesMap.get(ap).setCard(angleToReturn);
                }

                return angleToReturn;
            case "STATIC":
                GoldCard goldToReturn = new GoldCard(cardID, anglesMap, cardType, isTurned, cardScore, lUsed, dUsed, playConditionsArray);

                for (AnglePos ap : anglesMap.keySet()) {
                    anglesMap.get(ap).setCard(goldToReturn);
                }

                return goldToReturn;
        }
        return null;
    }

    private void deserializeAnglesMap() {

        this.anglesMap = new HashMap<AnglePos, Angle>();

        for (AngleMapDeserializer a : anglesMapDeserializer) {
            anglesMap.put(a.getPosition(), new Angle(a.getResource(), false, null, null));
        }
        anglesMapDeserializer.clear();
        anglesMapDeserializer = null;
    }

    private void deserializePlayConditions() {
        playConditionsArray = new ArrayList<Resource>();

        for (PlayContidionDataContainer pd : playCondition) {
            playConditionsArray.add(pd.getResource());
        }

        playCondition.clear();
        playCondition = null;
    }

}
