package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Goals.DiagonalGoal;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Goals.LGoal;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class GameTableTest extends TestCase {

    Map<Player, Codex> codexMap = new HashMap<Player, Codex>();
    Deck initialCardDeck = new Deck(5, new Stack<Card>());
    GoalsDeck goalsDeck = new GoalsDeck(5, new Stack<Goal>());
    Deck cardDeck = new Deck(4, new Stack<Card>());
    Deck goldCardDeck = new Deck(3, new Stack<Card>());
    ArrayList<Card> cardToPick = new ArrayList<Card>();
    ArrayList<Card> goldCardToPick = new ArrayList<Card>();
    ArrayList<Goal> commonGoals = new ArrayList<Goal>();
    Player currentPlayer = new Player("CurrentPlayer", null);

    ArrayList<Player> players = new ArrayList<Player>();
    Game gameModel = new Game(123, players, null, null, null, null, 4);

    GameTable toTest = new GameTable(codexMap, initialCardDeck, goalsDeck, cardDeck, goldCardDeck, cardToPick, goldCardToPick);

    @Test
    public void testGetCodex() {
        currentPlayer.setColor(Color.Red);

        Player p1 = new Player("1", null);
        p1.setColor(Color.Blue);
        Codex c1 = new Codex();

        Player p2 = new Player("2", null);
        p2.setColor(Color.Green);
        Codex c2 = new Codex();

        codexMap.put(p1, c1);
        codexMap.put(p2, c2);

        assertEquals(c1, toTest.getCodex(p1));
        assertNotSame(c1, toTest.getCodex(p2));
    }

    @Test
    public void testGetCurrentPlayer() {
        assertNull(toTest.getCurrentPlayer());
    }

    @Test
    public void testGetInitialCardDeck() {
        assertEquals(initialCardDeck, toTest.getInitialCardDeck());
    }

    @Test
    public void testGetGoalsCardDeck() {
        assertEquals(goalsDeck, toTest.getGoalsCardDeck());
    }

    @Test
    public void testGetCardDeck() {
        assertEquals(cardDeck, toTest.getCardDeck());
    }

    @Test
    public void testGetGoldCardDeck() {
        assertEquals(goldCardDeck, toTest.getGoldCardDeck());
    }

    @Test
    public void testGetCardToPick() {
        assertEquals(cardToPick, toTest.getCardToPick());
    }

    @Test
    public void testGetGoldCardToPick() {
        assertEquals(goldCardToPick, toTest.getGoldCardToPick());
    }

    @Test
    public void testGetGoalsDeck() {
        assertEquals(goalsDeck, toTest.getGoalsDeck());
    }

    @Test
    public void testGetCommonGoals() {
        assertNotNull(toTest.getCommonGoals());
        assertEquals(0, toTest.getCommonGoals().size());
    }

    @Test
    public void testSetCurrentPlayer() {
        assertNull(toTest.getCurrentPlayer());
        toTest.setCurrentPlayer(currentPlayer);
        assertEquals(currentPlayer, toTest.getCurrentPlayer());
    }

    @Test
    public void testGetGameModel() {
        assertNull(toTest.getGameModel());
    }

    @Test
    public void testSetGameModel() {
        toTest.setGameModel(gameModel);
        assertEquals(gameModel, toTest.getGameModel());
    }

    @Test
    public void testPickCardFromGround() {

        Player p = new Player("Player", new PlayerHand(new ArrayList<Card>()));
        p.setColor(Color.Red);
        Card toPick = new Card();
        Card filler = new Card();
        cardToPick.add(toPick);
        cardDeck.addCard(filler);

        toTest.setCurrentPlayer(p);
        toTest.pickCardFromGround(toPick);

        assertEquals(toPick, p.getPlayerHand().getCards().get(0));

        GoldCard goldToPick = new GoldCard(1, null, CardType.FUNGI, false, 1, false, false, null);
        GoldCard goldFiller = new GoldCard(2, null, CardType.FUNGI, false, 1, false, false, null);
        goldCardToPick.add(goldToPick);
        goldCardDeck.addCard(goldFiller);

        toTest.pickCardFromGround(goldToPick);

        assertEquals(goldToPick, p.getPlayerHand().getCards().get(1));
    }

    @Test
    public void testPickCardFromDeck() {
        Card toPickFromDeck = new Card();
        cardDeck.addCard(toPickFromDeck);

        Player p = new Player("Player", new PlayerHand(new ArrayList<Card>()));
        p.setColor(Color.Red);

        toTest.setCurrentPlayer(p);
        toTest.pickCardFromDeck();

        assertEquals(toPickFromDeck, p.getPlayerHand().getCards().get(0));
    }

    @Test
    public void testPickGoldCardFromDeck() {

        GoldCard toPickFromGoldDeck = new GoldCard(2, null, CardType.INSECT, false, 10, false, false, null);
        goldCardDeck.addCard(toPickFromGoldDeck);

        Player p = new Player("Player", new PlayerHand(new ArrayList<Card>()));
        p.setColor(Color.Red);

        toTest.setCurrentPlayer(p);
        toTest.pickGoldCardFromDeck();

        assertEquals(toPickFromGoldDeck, p.getPlayerHand().getCards().get(0));
    }

    @Test
    public void testPlayerHandBuild() {
        Player p1 = new Player("1", new PlayerHand(new ArrayList<Card>()));

        players.add(p1);

        toTest.setGameModel(gameModel);

        Card c1 = new Card();
        Card c2 = new Card();
        GoldCard c3 = new GoldCard(2, null, CardType.INSECT, false, 10, false, false, null);

        cardDeck.addCard(c1);
        cardDeck.addCard(c2);
        goldCardDeck.addCard(c3);

        toTest.playerHandBuild(p1);

        assertEquals(3, p1.getPlayerHand().getCards().size());
        assertEquals(c2, p1.getPlayerHand().getCards().get(0));
        assertEquals(c1, p1.getPlayerHand().getCards().get(1));
        assertEquals(c3, p1.getPlayerHand().getCards().get(2));
    }

    @Test
    public void testGroundBuild() {

        Card c1 = new Card();
        Card c2 = new Card();

        GoldCard g1 = new GoldCard(2, null, CardType.INSECT, false, 10, false, false, null);
        GoldCard g2 = new GoldCard(3, null, CardType.ANIMAL, false, 10, false, false, null);

        cardDeck.addCard(c1);
        cardDeck.addCard(c2);

        goldCardDeck.addCard(g1);
        goldCardDeck.addCard(g2);

        toTest.groundBuild();

        assertEquals(2, cardToPick.size());
        assertEquals(c2, cardToPick.get(0));
        assertEquals(c1, cardToPick.get(1));

        assertEquals(2, goldCardToPick.size());
        assertEquals(g2, goldCardToPick.get(0));
        assertEquals(g1, goldCardToPick.get(1));
    }

    /* FIXME: Test non eseguiti sulle funzioni commentate nella classe GameTable
    @Test
    public void testInitialCardDeckBuild() {
    }

    @Test
    public void testGoalsDeckBuild() {
    }

    @Test
    public void testCardDeckBuild() {
    }

    @Test
    public void testGoldCardDeckBuild() {
    }
     */

    @Test
    public void testPickInitialCard() {
        Player p1 = new Player("1", new PlayerHand(new ArrayList<Card>()));

        InitialCard ic = new InitialCard(3, null, CardType.ANIMAL, false, 10, false, false, null);
        initialCardDeck.addCard(ic);
        toTest.pickInitialCard(p1);

        Codex cx = codexMap.get(p1);
        assertNotNull(cx);
        assertEquals(ic, cx.getCard(new Coordinate(80, 80)));
    }

    @Test
    public void testExtractPersonalGoal() {

        Player p1 = new Player("1", new PlayerHand(new ArrayList<Card>()));
        Codex cx = new Codex(new HashMap<Coordinate, Card>());

        codexMap.put(p1, cx);

        Goal g1 = new LGoal(null, null, 1, 1);
        Goal g2 = new DiagonalGoal(null, null, 1, 1);

        goalsDeck.addGoal(g1);
        goalsDeck.addGoal(g2);

        toTest.extractPersonalGoal(p1);

        assertEquals(2, codexMap.get(p1).getGoalsToPick().size());
        assertEquals(g2, codexMap.get(p1).getGoalsToPick().get(0));
        assertEquals(g1, codexMap.get(p1).getGoalsToPick().get(1));

    }

    @Test
    public void testCommonGoalsExtraction() {
        Goal g1 = new LGoal(null, null, 1, 1);
        Goal g2 = new DiagonalGoal(null, null, 1, 1);

        goalsDeck.addGoal(g1);
        goalsDeck.addGoal(g2);

        toTest.commonGoalsExtraction();

        assertEquals(2, toTest.getCommonGoals().size());
        assertEquals(g2, toTest.getCommonGoals().get(0));
        assertEquals(g1, toTest.getCommonGoals().get(1));
    }
}