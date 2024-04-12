package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Interfaces.GameTableInterface;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.model.Game;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

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
    private Game gameController;

    /**
     * Constructor
     * @param codexMap map that maps each player to his {@link Codex}
     * @param initialCardDeck deck of {@link InitialCard}
     * @param goalsDeck deck of {@link it.polimi.ingsw.model.Goals.Goal}
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
     * @param player player to get the codex from
     * @return the codex of the player
     */
    public Codex getCodex(Player player) { return this.codexMap.get(player); }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * @return the initial card deck
     */
    public Deck getInitialCardDeck() { return initialCardDeck; }

    @Override
    public GoalsDeck getGoalsCardDeck() {
        return this.goalsDeck;
    }

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
     * set the current player
     */
    public void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }
    public void pickCardFromDeck(){
        Card picked = cardDeck.pickCard();
        PlayerHand.addCard(picked);

    }

    public void pickGoldCardFromDeck(){
       Card picked = goldCardDeck.pickCard();
        PlayerHand.addCard(picked);
    }

    public void gameBoardBuild() {
        //Per ogni giocatore presente nel game controller crea un nuovo codex vuoto e lo aggiunge alla mappa (giocatore -> codex)
        for(Player p : gameController.getPlayers()) {
            Codex c = new Codex();
            codexMap.put(p, c);
        }

    }

    public void pickInitialCard() {
        //Per ogni giocatore presente nella codex map aggiunge una carta iniziale al suo codex alle coordinate (80, 80)
        for (Player p : codexMap.keySet()) {
            InitialCard toAdd = (InitialCard) initialCardDeck.pickCardFromStack();
            Codex codex = codexMap.get(p);
            codex.setInitialCard(toAdd);
        }
    }

    public void pickCardFromGround(Card card) {
        //Controlla da quale dei due array la carte è stata presa, la aggiunge al deck del current player.
        //Successivamente la rimpiazza nell'array da cui è stata scelta con una nuova carta dai deck
        if (cardToPick.contains(card)) {
            codexMap.get(currentPlayer).getCardsDeck().addCard(card);
            cardToPick.remove(card);
            cardToPick.add(cardDeck.pickCardFromStack());
        } else if (goldCardToPick.contains(card)) {
            codexMap.get(currentPlayer).getCardsDeck().addCard(card);
            goldCardToPick.remove(card);
            goldCardToPick.add(goldCardDeck.pickCardFromStack());
        }
    }



}


