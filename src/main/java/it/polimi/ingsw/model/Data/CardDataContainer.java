package it.polimi.ingsw.model.Data;

import com.google.gson.annotations.SerializedName;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.GameComponents.Angle;
import it.polimi.ingsw.model.GameComponents.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class to deserialize card data from the JSON
 */
public class CardDataContainer {
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

    public Card deserialize() {
        deserializeAnglesMap();
        Card cardToReturn = new Card(cardID, anglesMap, cardType, isTurned, cardScore, lUsed, dUsed);
        for (AnglePos ap : anglesMap.keySet()) {
            anglesMap.get(ap).setCard(cardToReturn);
        }
        return cardToReturn;
    }

    private void deserializeAnglesMap() {

        this.anglesMap = new HashMap<AnglePos, Angle>();

        for (AngleMapDeserializer a : anglesMapDeserializer) {
            anglesMap.put(a.getPosition(), new Angle(a.getResource(), false, null, null));
        }
        anglesMapDeserializer.clear();
        anglesMapDeserializer = null;
    }
}
