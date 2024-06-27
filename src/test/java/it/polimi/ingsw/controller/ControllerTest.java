package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Coordinate;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.GameComponents.GameTable;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Goals.LGoal;
import it.polimi.ingsw.model.Player.Player;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class ControllerTest extends TestCase {

    private int gameID;
    private ArrayList<Player> players;
    private ArrayList<Color> availableColors;
    private GameStatus gameStatus;
    private Player winner;
    private GameTable table;
    private int maxPlayers;
    private int playersReady;
    private Game model;

    private Controller toTest;

    @BeforeEach
    public void setup() {
        gameID = 123456;
        players = new ArrayList<Player>();
        availableColors = new ArrayList<Color>();
        gameStatus = GameStatus.RUNNING;
        winner = null;
        HashMap<Player, Codex> map = new HashMap<Player, Codex>();

        table = new GameTable(map, null, null, null, null, null, null);
        maxPlayers = 4;
        playersReady = 0;

        Player p1 = new Player("1", null);
        Player p2 = new Player("2", null);
        Player p3 = new Player("3", null);
        Player p4 = new Player("4", null);

        map.put(p1, new Codex(new ArrayList<Goal>(), new LGoal(Resource.ANIMAL, CardType.ANIMAL, 1,1), null, null));

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        availableColors.add(Color.Blue);
        availableColors.add(Color.Red);
        availableColors.add(Color.Yellow);
        availableColors.add(Color.Green);

        model = new Game(gameID, players, availableColors, gameStatus, winner, table, maxPlayers);

        toTest = new Controller(model);
    }

    @Test
    public void isGameStartedTest() {
        assertTrue(toTest.isGameStarted());
        toTest = new Controller(new Game(gameID, players, availableColors, GameStatus.WAITING_TO_START, winner, table, maxPlayers));
        assertFalse(toTest.isGameStarted());
    }

    @Test
    public void getAvailableColorsTest() {
        assertEquals(availableColors, toTest.getAvailableColors());
    }

    @Test
    public void getAvailableColorsUpdateTest() {
        assertTrue(toTest.getAvailableColorsUpdate() != null);
    }

    @Test
    public void getModelTest() {
        assertEquals(toTest.getModel(), model);
    }

    @Test
    public void getPlayersTest() {
        assertEquals(toTest.getPlayers(), players);
    }

    @Test
    public void getMaxPlayersTest() {
        assertEquals(toTest.getMaxPlayers(), maxPlayers);
    }

    @Test
    public void selectMaxPlayersTest() {
        toTest.selectMaxPlayers(2);
        assertEquals(model.getMaxPlayers(), 2);
    }

    @Test
    public void getCurrentPlayerTest() {
        model.startGame();
        assertNotNull(toTest.getCurrentPlayer());
    }

    @Test
    public void addPlayerTest() {
        assertNotNull(toTest.addPlayer("Test", Color.Red));
    }

    @Test
    public void pickPersonalGoalTest() {
        toTest.pickPersonalGoal("Test", new LGoal(Resource.ANIMAL, CardType.ANIMAL, 2, 2));
    }

    @Test
    public void playerReadyTest() {
        toTest.playerReady();
        assertEquals(toTest.getReadyPlayer(), playersReady+1);
    }

    @Test
    public void getReadyPlayerTest() {
        assertEquals(toTest.getReadyPlayer(), playersReady);
    }

    @Test
    public void playCardTest() throws IllegalCoordinatesException, IllegalCardPlacementException {
        toTest.playCard(new Coordinate(81, 81), new Card(1, null, CardType.ANIMAL, false, 10, false, false));
    }

    @Test
    public void pickCardFromGroundTest() {
        toTest.pickCardFromGround(new Card(1, null, CardType.ANIMAL, false, 10, false, false));
    }

    @Test
    public void pickCardFromDeckTest() {
        toTest.pickCardFromDeck(0);
    }

    @Test
    public void setFinaleTurnTest() {
        toTest.setFinalTurn();
        assertEquals(GameStatus.LAST_TURN, toTest.getModel().getGameStatus());
    }

    @Test
    public void endGameTest() {
        toTest.endGame();
        assertEquals(GameStatus.ENDED, toTest.getModel().getGameStatus());
    }
}
