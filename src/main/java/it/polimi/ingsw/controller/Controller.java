package it.polimi.ingsw.controller;

import apple.laf.JRSUIConstants;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.GameStatus;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;

import java.util.ArrayList;

public class Controller {
    private Game model;

    public boolean isGameStarted(){
       if ( model.getGameStatus() == GameStatus.RUNNING )
           return true;
       else
           return false;
    }

    public ArrayList<Color> getAvailableColors() {
        return model.getAvailableColors();

    }

    public void addPlayer(String nickname, Color color) {
        if (model.getPlayers().size()<4){
            model.removeAvailableColor(color);
            PlayerHand ph = new PlayerHand();
            Player p = new Player(nickname, color, ph);
            model.addPlayer(p);
        }

        if (model.getPlayers().size() == 4) {
            model.initGame();
        }





    }


}
