package it.polimi.ingsw.model.Data;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Angle;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AngleMapDeserializer {

    @SerializedName("position")
    private AnglePos position;
    @SerializedName("resource")
    private Resource resource;

    public AnglePos getPosition() {return this.position;}

    public Resource getResource() {return  this.resource;}

}
