package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Interfaces.GameTableInterface;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Game;

import java.util.ArrayList;
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

    //TODO Riferimento al Game Controller per le funzioni. Da sostituire quando scrieremo il game controller manager.
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
        for(Player p : gameModel.getPlayers()) {
            Codex c = new Codex();
            codexMap.put(p, c);
        }
    }

    public void playerHandBuild() {
        for(Player p : gameModel.getPlayers()) {
            p.getPlayerHand().addCard(cardDeck.pickCard());
            p.getPlayerHand().addCard(cardDeck.pickCard());
            p.getPlayerHand().addCard(goldCardDeck.pickCard());
        }
    }

    /**
     * Function to build the ground
     */
    public void groundBuild() {
        this.cardToPick.add(cardDeck.pickCard());
        this.cardToPick.add(cardDeck.pickCard());
        this.goldCardToPick.add(goldCardDeck.pickCard());
        this.goldCardToPick.add(goldCardDeck.pickCard());
    }

    /**
     * Function to create the initial card deck
     */
    public void initialCardDeckBuild() {
        //this.initialCardDeck.deckBuild();
    }

    /**
     * Function to create the goals deck
     */
    public void goalsDeckBuild() {
        //this.goalsDeck.deckBuild();
    }

    /**
     * Function to create the card deck
     */
    public void cardDeckBuild() {
        //this.cardDeck.deckBuild();
    }

    /**
     * Function to create the gold card deck
     */
    public void goldCardDeckBuild() {
        //this.goldCardDeck.deckBuild();
    }

    /**
     * Set the initial card for each player in the game
     */
    public void pickInitialCard() {
        for (Player p : codexMap.keySet()) {
            InitialCard toAdd = (InitialCard) initialCardDeck.pickCard();
            Codex codex = codexMap.get(p);
            codex.setInitialCard(toAdd);
        }
    }

    /**
     * Extract personal goals for each player
     */
    public void extractPersonalGoal() {
        for (Player p : codexMap.keySet()) {
            ArrayList<Goal> goalsToPick = null;
            goalsToPick.add(this.goalsDeck.getGoal());
            goalsToPick.add(this.goalsDeck.getGoal());
            codexMap.get(p).setGoalsToPick(goalsToPick);
        }
    }

    /**
     * Take randomly the two common goals for all the players
     */
    public void commonGoalsExtraction(){
        for(int i=0; i<2; i++) {  this.commonGoals.add(goalsDeck.getGoal());   }
    }

}
