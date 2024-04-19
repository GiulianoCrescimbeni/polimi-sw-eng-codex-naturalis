package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.InitialCard;
import java.util.ArrayList;

public interface CodexInterface {

    public int getNumOfResources(Resource resource);

    public Card getCard(Coordinate coordinate);

}
