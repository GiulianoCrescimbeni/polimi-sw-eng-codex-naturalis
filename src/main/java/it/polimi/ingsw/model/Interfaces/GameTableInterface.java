package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Card;
import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Deck;
import it.polimi.ingsw.model.GameComponents.GoalsDeck;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The interface for the {@link it.polimi.ingsw.model.GameComponents.GameTable} game component
 */
public interface GameTableInterface {
    /**
     * @param player player to get the codex from
     * @return the codex of the player
     */
    public Codex getCodex(Player player);
    /**
     * @return the initial card deck
     */
    public Deck getInitialCardDeck();
    /**
     * @return the GoalsDeck
     */
    public GoalsDeck getGoalsCardDeck();
    /**
     * @return the card deck
     */
    public Deck getCardDeck();
    /**
     * @return the gold card deck
     */
    public Deck getGoldCardDeck();
    /**
     * @return the deck of cards that the player can pick
     */
    public ArrayList<Card> getCardToPick();
    /**
     * @return the deck of gold cards that the player can pick
     */
    public ArrayList<Card> getGoldCardToPick();
    /**
     * @return the deck of common goals
     */
    public ArrayList<Goal> getCommonGoals();
}
