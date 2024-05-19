package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Interfaces.GameTableInterface;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameTable implements GameTableInterface {

    private Map<Player, Codex> codexMap;
    private Deck initialCardDeck;
    private GoalsDeck goalsDeck;
    private Deck cardDeck;
    private Deck goldCardDeck;
    private ArrayList<Card> cardToPick;
    private ArrayList<Card> goldCardToPick;
    private ArrayList<Goal> commonGoals;
    private Player currentPlayer;
    private Game gameModel;

    /**
     * Constructor
     * @param codexMap map that maps each player to his {@link Codex}
     * @param initialCardDeck deck of {@link InitialCard}
     * @param goalsDeck deck of {@link Goal}
     * @param cardDeck deck of {@link Card}
     * @param goldCardDeck deck of {@link GoldCard}
     * @param cardToPick deck of {@link Card} that the player can take
     * @param goldCardToPick deck of {@link GoldCard} that the player can take
     */
    public GameTable(Map<Player, Codex> codexMap, Deck initialCardDeck, GoalsDeck goalsDeck, Deck cardDeck, Deck goldCardDeck, ArrayList<Card> cardToPick, ArrayList<Card> goldCardToPick) {
        this.codexMap = codexMap;
        this.initialCardDeck = initialCardDeck;
        this.goalsDeck = goalsDeck;
        this.cardDeck = cardDeck;
        this.goldCardDeck = goldCardDeck;
        this.cardToPick = cardToPick;
        this.goldCardToPick = goldCardToPick;
        this.commonGoals = new ArrayList<Goal>();
    }

    /**
     * Constructor
     */
    public GameTable() {}

    /**
     * @param player player to get the codex from
     * @return the codex of the player
     */
    public Codex getCodex(Player player) { return this.codexMap.get(player); }

    /**
     * @return the codex map
     */
    public Map<Player, Codex> getCodexMap() {
        return codexMap;
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {return currentPlayer; }

    /**
     * @return the initial card deck
     */
    public Deck getInitialCardDeck() { return initialCardDeck; }

    /**
     * @return the GoalsDeck
     */
    @Override
    public GoalsDeck getGoalsCardDeck() { return this.goalsDeck; }

    /**
     * @return the card deck
     */
    public Deck getCardDeck() { return cardDeck; }

    /**
     * @return the gold card deck
     */
    public Deck getGoldCardDeck() { return goldCardDeck; }

    /**
     * @return the deck of cards that the player can pick
     */
    public ArrayList<Card> getCardToPick() { return cardToPick; }

    /**
     * @return the deck of gold cards that the player can pick
     */
    public ArrayList<Card> getGoldCardToPick() { return goldCardToPick; }

    /**
     * @return the deck of goals
     */
    public GoalsDeck getGoalsDeck() { return this.goalsDeck; }

    /**
     * @return the deck of common goals
     */
    public ArrayList<Goal> getCommonGoals() { return this.commonGoals; }

    /**
     * @param player the {@link Player} that will be the new current player
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    /**
     * @return the game model entity
     */
    public Game getGameModel() { return gameModel; }

    /**
     * @param gameModel
     */
    public void setGameModel(Game gameModel) { this.gameModel = gameModel; }

    /**
     * Pick a card from the ground
     * @param card the card that the player wants to pick
     */
    public void pickCardFromGround(Card card) {
        if (cardToPick.contains(card)) {
            currentPlayer.getPlayerHand().addCard(card);
            cardToPick.remove(card);
            cardToPick.add(cardDeck.pickCard());
        } else if (goldCardToPick.contains(card)) {
            currentPlayer.getPlayerHand().addCard(card);
            goldCardToPick.remove(card);
            goldCardToPick.add(goldCardDeck.pickCard());
        }
    }

    /**
     * Take the card on the top of the deck and add it to the player hand
     */
    public void pickCardFromDeck(){
        Card picked = cardDeck.pickCard();
        currentPlayer.getPlayerHand().addCard(picked);
    }

    /**
     * Take the card on the top of the gold deck and add it to the player hand
     */
    public void pickGoldCardFromDeck(){
        Card picked = goldCardDeck.pickCard();
        currentPlayer.getPlayerHand().addCard(picked);
    }

    /**
     * For each player, create an empty codex
     */
    public void codexBuild() {
        this.codexMap = new HashMap<>();
    }

    /**
     * Create the player hand
     */
    public void playerHandBuild(Player player) {
        player.getPlayerHand().addCard(cardDeck.pickCard());
        player.getPlayerHand().addCard(cardDeck.pickCard());
        player.getPlayerHand().addCard(goldCardDeck.pickCard());
    }

    /**
     * Function to build the ground
     */
    public void groundBuild() {
        cardToPick = new ArrayList<>();
        goldCardToPick = new ArrayList<>();
        this.cardToPick.add(cardDeck.pickCard());
        this.cardToPick.add(cardDeck.pickCard());
        this.goldCardToPick.add(goldCardDeck.pickCard());
        this.goldCardToPick.add(goldCardDeck.pickCard());
    }

    /**
     * Function to create the initial card deck
     */
    public void initialCardDeckBuild() {
        this.initialCardDeck = new Deck();
        this.initialCardDeck.buildInitialCardsDeck();
        this.initialCardDeck.deckShuffle();
    }

    /**
     * Function to create the goals deck
     */
    public void goalsDeckBuild() {
        this.goalsDeck = new GoalsDeck();
        this.goalsDeck.buildDeck();
        this.goalsDeck.goalsShuffle();
    }

    /**
     * Function to create the card deck
     */
    public void cardDeckBuild() {
        this.cardDeck = new Deck();
        this.cardDeck.buildDeck();
        this.cardDeck.deckShuffle();
    }

    /**
     * Function to create the gold card deck
     */
    public void goldCardDeckBuild() {
        this.goldCardDeck = new Deck();
        this.goldCardDeck.buildGoldCardsDeck();
        this.goldCardDeck.deckShuffle();
    }

    /**
     * Set the initial card for each player in the game
     */
    public void pickInitialCard(Player player) {
        InitialCard toAdd = (InitialCard) initialCardDeck.pickCard();
        Codex codex = new Codex();
        Map<Resource, Integer> resourcesMap = new HashMap<>();
        Map<Coordinate, Card> cardsMap = new HashMap<>();
        Coordinate coordinate = new Coordinate(80, 80);

        codex.setNumOfResources(resourcesMap);
        codex.setCards(cardsMap);
        codex.getCards().put(coordinate, toAdd);
        this.codexMap.put(player, codex);
    }

    /**
     * Extract personal goals for each player
     */
    public void extractPersonalGoal(Player p) {
        ArrayList<Goal> goalsToPick = new ArrayList<Goal>();
        goalsToPick.add(this.goalsDeck.getGoal());
        goalsToPick.add(this.goalsDeck.getGoal());
        codexMap.get(p).setGoalsToPick(goalsToPick);
    }

    /**
     * Take randomly the two common goals for all the players
     */
    public void commonGoalsExtraction(){
        this.commonGoals = new ArrayList<>();
        for(int i=0; i<2; i++) {
            this.commonGoals.add(goalsDeck.getGoal());
        }
    }

}
