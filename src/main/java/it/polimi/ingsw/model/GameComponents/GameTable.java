package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Player.Player;

import java.util.Map;

public class GameTable {

    private Map<Player, Codex> codexMap;
    private Deck initialCardDeck;
    private GoalsDeck goalsDeck;
    private Deck cardDeck;
    private Deck goldCardDeck;
    private Deck cardToPick;
    private Deck goldCardToPick;
    private GoalsDeck commonGoals;
    private int maxCoordinateX;
    private int maxCoordinateY;
    private int minCoordinateX;
    private int minCoordinateY;

    /**
     * Constructor
     * @param codexMap map that maps each player to his {@link Codex}
     * @param initialCardDeck deck of {@link InitialCard}
     * @param goalsDeck deck of {@link it.polimi.ingsw.model.Goals.Goal}
     * @param cardDeck deck of {@link Card}
     * @param goldCardDeck deck of {@link GoldCard}
     * @param cardToPick deck of {@link Card} that the player can take
     * @param goldCardToPick deck of {@link GoldCard} that the player can take
     * @param maxCoordinateX is the max X coordinate of {@link Codex}
     * @param maxCoordinateY is the max Y coordinate of {@link Codex}
     * @param minCoordinateX is the min X coordinate of {@link Codex}
     * @param minCoordinateY is the min Y coordinate of {@link Codex}
     */
    public GameTable(Map<Player, Codex> codexMap, Deck initialCardDeck, GoalsDeck goalsDeck, Deck cardDeck, Deck goldCardDeck, Deck cardToPick, Deck goldCardToPick, int maxCoordinateX, int maxCoordinateY, int minCoordinateX, int minCoordinateY) {
        this.codexMap = codexMap;
        this.initialCardDeck = initialCardDeck;
        this.goalsDeck = goalsDeck;
        this.cardDeck = cardDeck;
        this.goldCardDeck = goldCardDeck;
        this.cardToPick = cardToPick;
        this.goldCardToPick = goldCardToPick;
        this.maxCoordinateX = maxCoordinateX;
        this.maxCoordinateY = maxCoordinateY;
        this.minCoordinateX = minCoordinateX;
        this.minCoordinateY = minCoordinateY;
    }

    /**
     * @param player player to get the codex from
     * @return the codex of the player
     */
    public Codex getCodex(Player player) { return this.codexMap.get(player); }

    /**
     * @return the initial card deck
     */
    public Deck getInitialCardDeck() { return initialCardDeck; }

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
    public Deck getCardToPick() { return cardToPick; }

    /**
     * @return the deck of gold cards that the player can pick
     */
    public Deck getGoldCardToPick() { return goldCardToPick; }

    /**
     * @return the deck of goals
     */
    public GoalsDeck getGoalsDeck() { return this.goalsDeck; }

    /**
     * @return the deck of common goals
     */
    public GoalsDeck getCommonGoals() { return this.commonGoals; }

    /**
     * @return the max X coordinate of the codex
     */
    public int getMaxCoordinateX() { return this.maxCoordinateX; }

    /**
     * @return the max Y coordinate of the codex
     */
    public int getMaxCoordinateY() { return this.maxCoordinateY; }

    /**
     * @return the min X coordinate of the codex
     */
    public int getMinCoordinateX() { return minCoordinateX; }

    /**
     * @return the min Y coordinate of the codex
     */
    public int getMinCoordinateY() { return minCoordinateY; }
}
