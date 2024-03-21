package it.polimi.ingsw.model.Interfaces;

import it.polimi.ingsw.model.GameComponents.Codex;
import it.polimi.ingsw.model.GameComponents.Deck;
import it.polimi.ingsw.model.GameComponents.GoalsDeck;
import it.polimi.ingsw.model.Goals.Goal;
import it.polimi.ingsw.model.Player.Player;

import java.util.ArrayList;

public interface GameTableInterface {

    public Codex getCodex(Player player);
    public Deck getInitialCardDeck();
    public GoalsDeck getGoalsCardDeck();
    public Deck getCardDeck();
    public Deck getGoldCardDeck();
    public Deck getCardToPick();
    public Deck getGoldCardToPick();
    public GoalsDeck getCommonGoals();
}
