package it.polimi.ingsw.model.Data;

import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.InitialCard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InitialCardDataContainer {

    @SerializedName("cardID")
    private int cardID;
    private Map<AnglePos, Angle> frontAnglesMap;
    private Map<AnglePos, Angle> backAnglesMap;
    private ArrayList<Resource> backResourcesArray;
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
    @SerializedName("backAngles")
    private ArrayList<AngleMapDeserializer> backAngles;
    @SerializedName("frontAngles")
    private ArrayList<AngleMapDeserializer> frontAngles;
    @SerializedName("backResources")
    private ArrayList<BackResourcesDataContainer> backResources;

    public InitialCard deserialize() {

        deserializeFrontAngles();
        deserializeBackAngles();
        deserializeBackResources();

        InitialCard toReturn = new InitialCard(cardID, frontAnglesMap, cardType, isTurned, cardScore, lUsed, dUsed, backResourcesArray);

        if (frontAngles != null) {
            for (AnglePos ap : frontAnglesMap.keySet()) {
                frontAnglesMap.get(ap).setCard(toReturn);
            }
        }

        for (AnglePos ap : backAnglesMap.keySet()) {
            backAnglesMap.get(ap).setCard(toReturn);
        }

        return toReturn;
    }

    private void deserializeFrontAngles() {

        if (frontAngles == null) return;

        frontAnglesMap = new HashMap<AnglePos, Angle>();

        for (AngleMapDeserializer a : frontAngles) {
            frontAnglesMap.put(a.getPosition(), new Angle(a.getResource(), false, null, null));
        }
        frontAngles.clear();
        frontAngles = null;
    }

    private void deserializeBackAngles() {

        backAnglesMap = new HashMap<AnglePos, Angle>();

        for (AngleMapDeserializer a : backAngles) {
            backAnglesMap.put(a.getPosition(), new Angle(a.getResource(), false, null, null));
        }
        backAngles.clear();
        backAngles = null;
    }

    private void deserializeBackResources() {
        backResourcesArray = new ArrayList<Resource>();
        for (BackResourcesDataContainer bc : backResources) {
            backResourcesArray.add(bc.getResource());
        }

        backResources.clear();
        backResources = null;
    }

}
