package it.polimi.ingsw;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestGame extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TestGame(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestGame.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
