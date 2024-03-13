package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;
import java.util.Map;

public class GameTable {

    private Map<Player, Codex> codexMap;
    private ArrayList<InitialCard> initialCardDeck;
    /* TODO Abilitare il codice dopo l'aggiunta dei Goal
    private ArrayList<Goal> goalsDeck;
    */
    private ArrayList<Card> cardDeck;
    private ArrayList<GoldCard> goldCardDeck;
    private ArrayList<Card> cardToPick;
    private ArrayList<GoldCard> goldCardToPick;
    /* TODO Abilitare il codice dopo l'aggiunta dei Goal
    private ArrayList<Goal> commonGoals;
    */

    public GameTable(Map<Player, Codex> codexMap, ArrayList<InitialCard> initialCardDeck, /*ArrayList<Goal> goalsDeck, */ ArrayList<Card> cardDeck, ArrayList<GoldCard> goldCardDeck, ArrayList<Card> cardToPick, ArrayList<GoldCard> goldCardToPick/*, ArrayList<Goal> commonGoals*/) {
        this.codexMap = codexMap;
        this.initialCardDeck = initialCardDeck;
        this.cardDeck = cardDeck;
        this.goldCardDeck = goldCardDeck;
        this.cardToPick = cardToPick;
        this.goldCardToPick = goldCardToPick;
        /* TODO Abilitare il codice dopo l'aggiunta dei Goal
        this.goalsDeck = goalsDeck;
        this.commonGoals = commonGoals;
        */
    }

    public Codex getCodex(Player player) {
        return this.codexMap.get(player);
    }

    public ArrayList<InitialCard> getInitialCardDeck() {
        return initialCardDeck;
    }

    public ArrayList<Card> getCardDeck() {
        return cardDeck;
    }

    public ArrayList<GoldCard> getGoldCardDeck() {
        return goldCardDeck;
    }

    public ArrayList<Card> getCardToPick() {
        return cardToPick;
    }

    public ArrayList<GoldCard> getGoldCardToPick() {
        return goldCardToPick;
    }

    public void removeFromCard(int position) {
        this.cardDeck.remove(position);
    }

    public void removeFromGoldCard(int position) {
        this.goldCardDeck.remove(position);
    }

    public void removeFromCardToPick(int position) {
        this.cardToPick.remove(position);
    }

    public void removeFromGoldCardToPick(int position) {
        this.goldCardToPick.remove(position);
    }

    public void addCardToPick(Card toAdd) {
        this.cardToPick.add(toAdd);
    }

    public void addGoldCardToPick(GoldCard toAdd) {
        this.goldCardToPick.add(toAdd);
    }

    /* TODO Abilitare i metodi dopo l'aggiunta dei Goal
    public ArrayList<Goal> getGoalsDeck() {
        return this.goalsDeck;
    }

    public ArrayList<Goal> getCommonGoals() {
        return this.commonGoals;
    }

    public void removeFromGoals(int position) {
        this.goalsDeck.remove(position);
    }
    */

}
