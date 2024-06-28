package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Enumerations.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameComponents.*;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCardPlacementException;
import it.polimi.ingsw.model.GameComponents.Exceptions.IllegalCoordinatesException;
import it.polimi.ingsw.model.GameComponents.GameTable;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Goals.LGoal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

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
    private ArrayList<Card> cardToPick;
    private Controller toTest;

    @BeforeEach
    public void setup() {
        gameID = 123456;
        players = new ArrayList<Player>();
        availableColors = new ArrayList<Color>();
        gameStatus = GameStatus.RUNNING;
        winner = null;
        cardToPick = new ArrayList<Card>();
        cardToPick.add(new Card(1,new HashMap<>(),CardType.ANIMAL,false,1,false,false));

        Card card1 = new Card(1,new HashMap<>(),CardType.ANIMAL,false,1,false,false);
        Card card2 = new Card(1,new HashMap<>(),CardType.ANIMAL,false,1,false,false);
        Card card3 = new Card(1,new HashMap<>(),CardType.ANIMAL,false,1,false,false);
        Stack<Card> cardStack = new Stack<Card>();
        cardStack.push(card1);
        cardStack.push(card2);
        cardStack.push(card3);

        Deck testDeck = new Deck(3, cardStack);
        Stack GoalCard = new Stack<Goal>();
        GoalCard.add(card3);
        GoalsDeck testGoldDeck = new GoalsDeck(3, GoalCard);

        HashMap<Player, Codex> codexHashMap = new HashMap<Player, Codex>();

        table = new GameTable(codexHashMap, testDeck, testGoldDeck,testDeck, testDeck, cardToPick, null);
        maxPlayers = 4;
        playersReady = 0;

        HashMap<AnglePos, Angle> anglesMap = new HashMap<AnglePos, Angle>();

        Card card = new Card(1, anglesMap, CardType.FUNGI, false, 1, false, false);

        ArrayList<Card> hand = new ArrayList<Card>();

        for (int i = 0; i < 4; i++) {
            hand.add(card);
        }

        PlayerHand ph1 = new PlayerHand(hand);
        PlayerHand ph2 = new PlayerHand(hand);
        PlayerHand ph3 = new PlayerHand(hand);
        PlayerHand ph4 = new PlayerHand(hand);


        anglesMap.put(AnglePos.UR, new Angle(Resource.FUNGI, false, null, card));
        anglesMap.put(AnglePos.DR, new Angle(Resource.ANIMAL, false, null, card));
        anglesMap.put(AnglePos.UL, new Angle(Resource.PLANT, false, null, card));
        anglesMap.put(AnglePos.DL, new Angle(Resource.INSECT, false, null, card));


        Player p1 = new Player("1", ph1);
        Player p2 = new Player("2", ph2);
        Player p3 = new Player("3", ph3);
        Player p4 = new Player("4", ph4);

        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);

        availableColors.add(Color.Blue);
        availableColors.add(Color.Red);
        availableColors.add(Color.Yellow);
        availableColors.add(Color.Green);

        HashMap<Coordinate, Card> cardsMap1 = new HashMap<Coordinate, Card>();
        HashMap<Coordinate, Card> cardsMap2 = new HashMap<Coordinate, Card>();
        HashMap<Coordinate, Card> cardsMap3 = new HashMap<Coordinate, Card>();
        HashMap<Coordinate, Card> cardsMap4 = new HashMap<Coordinate, Card>();

        cardsMap1.put(new Coordinate(80, 80), new InitialCard(1, anglesMap, CardType.FIRST_CARD, false, 1, false, false, null));
        cardsMap2.put(new Coordinate(80, 80), new InitialCard(1, anglesMap, CardType.FIRST_CARD, false, 1, false, false, null));
        cardsMap3.put(new Coordinate(80, 80), new InitialCard(1, anglesMap, CardType.FIRST_CARD, false, 1, false, false, null));
        cardsMap4.put(new Coordinate(80, 80), new InitialCard(1, anglesMap, CardType.FIRST_CARD, false, 1, false, false, null));

        HashMap<Resource, Integer> numOfResources1 = new HashMap<Resource, Integer>();
        HashMap<Resource, Integer> numOfResources2 = new HashMap<Resource, Integer>();
        HashMap<Resource, Integer> numOfResources3 = new HashMap<Resource, Integer>();
        HashMap<Resource, Integer> numOfResources4 = new HashMap<Resource, Integer>();

        numOfResources1.put(Resource.FUNGI, 10);
        numOfResources1.put(Resource.PLANT, 10);
        numOfResources1.put(Resource.INSECT, 10);
        numOfResources1.put(Resource.SCROLL, 10);
        numOfResources1.put(Resource.FEATHER, 10);
        numOfResources1.put(Resource.ANIMAL, 10);
        numOfResources1.put(Resource.JAR, 10);

        numOfResources2.put(Resource.FUNGI, 10);
        numOfResources2.put(Resource.PLANT, 10);
        numOfResources2.put(Resource.INSECT, 10);
        numOfResources2.put(Resource.SCROLL, 10);
        numOfResources2.put(Resource.FEATHER, 10);
        numOfResources2.put(Resource.ANIMAL, 10);
        numOfResources2.put(Resource.JAR, 10);

        numOfResources3.put(Resource.FUNGI, 10);
        numOfResources3.put(Resource.PLANT, 10);
        numOfResources3.put(Resource.INSECT, 10);
        numOfResources3.put(Resource.SCROLL, 10);
        numOfResources3.put(Resource.FEATHER, 10);
        numOfResources3.put(Resource.ANIMAL, 10);
        numOfResources3.put(Resource.JAR, 10);

        numOfResources4.put(Resource.FUNGI, 10);
        numOfResources4.put(Resource.PLANT, 10);
        numOfResources4.put(Resource.INSECT, 10);
        numOfResources4.put(Resource.SCROLL, 10);
        numOfResources4.put(Resource.FEATHER, 10);
        numOfResources4.put(Resource.ANIMAL, 10);
        numOfResources4.put(Resource.JAR, 10);


        Codex codex1 = new Codex(new ArrayList<Goal>(), new LGoal(Resource.ANIMAL, CardType.FUNGI, 10, 1), numOfResources1, cardsMap1);
        Codex codex2 = new Codex(new ArrayList<Goal>(), new LGoal(Resource.ANIMAL, CardType.FUNGI, 10, 1), numOfResources2, cardsMap2);
        Codex codex3 = new Codex(new ArrayList<Goal>(), new LGoal(Resource.ANIMAL, CardType.FUNGI, 10, 1), numOfResources3, cardsMap3);
        Codex codex4 = new Codex(new ArrayList<Goal>(), new LGoal(Resource.ANIMAL, CardType.FUNGI, 10, 1), numOfResources4, cardsMap4);

        codexHashMap.put(p1, codex1);
        codexHashMap.put(p2, codex2);
        codexHashMap.put(p3, codex3);
        codexHashMap.put(p4, codex4);

        codex1.incrementScore(20);

        table.goalsDeckBuild();
        table.commonGoalsExtraction();

        table.initialCardDeckBuild();

        model = new Game(gameID, players, availableColors, gameStatus, winner, table, maxPlayers);
        Game model1 = new Game(gameID + 1);

        toTest = new Controller(model);
    }
    @Test
    public void addPlayerTest() {
        model.startGame();
        assertNotNull(toTest.addPlayer("Test", Color.Red));
        //toTest.addPlayer("Test",Color.Red);
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
        this.toTest.getModel().isFull();
        model.startGame();
        assertNotNull(toTest.getCurrentPlayer());
    }




    @Test
    public void pickPersonalGoalTest() {
        model.startGame();
        toTest.pickPersonalGoal("1", new LGoal(Resource.ANIMAL, CardType.ANIMAL, 2, 2));
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
        HashMap<AnglePos, Angle> anglesMap = new HashMap<AnglePos, Angle>();
        ArrayList<Resource> playConditions = new ArrayList<Resource>();

        Card toPlay = new Card(2, anglesMap, CardType.ANIMAL, false, 10, false, false);
        GoldCard goldToPlay = new GoldCard(2, anglesMap, CardType.FUNGI, false, 10, false, false, playConditions);

        anglesMap.put(AnglePos.UR, new Angle(Resource.FUNGI, false, null, toPlay));
        anglesMap.put(AnglePos.DR, new Angle(Resource.ANIMAL, false, null, toPlay));
        anglesMap.put(AnglePos.UL, new Angle(Resource.PLANT, false, null, toPlay));
        anglesMap.put(AnglePos.DL, new Angle(Resource.INSECT, false, null, toPlay));

        playConditions.add(Resource.ANIMAL);

        model.startGame();
        //toTest.playCard(new Coordinate(-8000, 80000), new Card(2, null, CardType.ANIMAL, true, 10, false, false));
        toTest.playCard(new Coordinate(81, 81), toPlay);
        toTest.playCard(new Coordinate(79, 79), goldToPlay);

        try {
            toTest.playCard(new Coordinate(79, 79), goldToPlay);
        } catch (Exception e) {}

        try {
            toTest.playCard(new Coordinate(75, 75), goldToPlay);
        } catch (Exception e) {}
        toTest.getModel().switchCurrentPlayer();
    }

    @Test
    public void pickCardFromGroundTest() {
        model.startGame();
        toTest.pickCardFromGround(new Card(1, null, CardType.ANIMAL, false, 10, false, false));
    }

    @Test
    public void pickCardFromDeckTest() {
        model.startGame();
        toTest.pickCardFromDeck(0);
        toTest.pickCardFromDeck(1);
    }

    @Test
    public void setFinaleTurnTest() {
        toTest.setFinalTurn();
        assertEquals(GameStatus.LAST_TURN, toTest.getModel().getGameStatus());
    }

    @Test
    public void endGameTest() {
        model.startGame();
        toTest.getModel().setWinner(null);
        toTest.getModel().getWinner();
        toTest.endGame();
        assertEquals(GameStatus.ENDED, toTest.getModel().getGameStatus());
    }

    @Test
    public void gameModelTest() {
        toTest.getModel().initGame();
    }
}
