package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.InitialCard;
import java.util.ArrayList;

/**
 * Interface for the {@link it.polimi.ingsw.model.GameComponents.Codex} game component
 */
public interface CodexInterface {

    /**
     * @return the arraylist for the number of resources
     */
    public int getNumOfResources(Resource resource);

    /**
     * @return the map of the cards
     */
    public Card getCard(Coordinate coordinate);

}
