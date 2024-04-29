package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.Resource;
import junit.framework.TestCase;

public class ResourceGoldCardTest extends TestCase {

    ResourceGoldCard testCard = new ResourceGoldCard(1, null, null, false, 0, false, false, null, Resource.FUNGI);
    public void testGetResourceType() {
        assertEquals(Resource.FUNGI, testCard.getResourceType());
    }
}