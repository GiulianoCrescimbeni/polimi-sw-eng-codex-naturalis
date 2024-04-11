package it.polimi.ingsw.model.GameComponents;

import it.polimi.ingsw.model.Interfaces.GameTableInterface;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

public class GameTable implements GameTableInterface {

    private Map<Player, Codex> codexMap;
    private Deck initialCardDeck;
    private GoalsDeck goalsDeck;
    private Deck cardDeck;
    private Deck goldCardDeck;
    private Deck cardToPick;
    private Deck goldCardToPick;
    private GoalsDeck commonGoals;

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
    public GameTable(Map<Player, Codex> codexMap, Deck initialCardDeck, GoalsDeck goalsDeck, Deck cardDeck, Deck goldCardDeck, Deck cardToPick, Deck goldCardToPick) {
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


    public void pickGoldCardFromGround(){
        while (true) {
            System.out.println("Enter the card to pick (0 or 1): ");
            Scanner sc = new Scanner(System.in);
            int pos = sc.nextInt();
            if (pos == 0 || pos == 1){
            Card picked = goldCardToPick.pickCard(pos);
            PlayerHand.addCard(picked);
            break;}
            else{
            System.out.println(" Unvalid input ");}

        }


    }
    public void pickCardFromDeck(){
        Card picked = cardDeck.pickCard();
        PlayerHand.addCard(picked);

    }

    public void pickGoldCardFromDeck(){
       Card picked = goldCardDeck.pickCard();
        PlayerHand.addCard(picked);



    }

}


