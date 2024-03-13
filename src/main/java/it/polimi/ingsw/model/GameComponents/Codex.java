package it.polimi.ingsw.model.GameComponents;

import java.util.ArrayList;

public class Codex {

    private InitialCard initialCard;
    private int score;
    //TODO Aggiungere i goalsToPick di tipo Goal
    //TODO Aggiungere il personalGoal di tipo Goal
    private ArrayList<Integer> numOfResources;

    public Codex(InitialCard initialCard, /*ArrayList<Goal> goalsToPick, Goal personalGoal,*/ ArrayList<Integer> numOfResources) {
        this.initialCard = initialCard;
        /* TODO Abilitare il seguente codice dopo l'aggiunta dei Goal
        this.goalsToPick = goalsToPick;
        this.personalGoal = personalGoal;
         */
        this.numOfResources = numOfResources;
    }

    public InitialCard getInitialCard() {
        return this.initialCard;
    }

    public int getScore() {
        //TODO Creare un modo di calcolare il punteggio
        return this.score;
    }

    /* TODO Abilitare i seguenti metodi dopo l'aggiunta dei Goal
    public ArrayList<Goal> getGoalsToPick() {
        return this.goalsToPick;
    }

    public Goal getPersonalGoal() {
        return this.personalGoal;
    }
    */

    public ArrayList<Integer> getNumOfResources() {
        return this.numOfResources;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setNumOfResources(ArrayList<Integer> numOfResources) {
        this.numOfResources = numOfResources;
    }

    /*
    public void setPersonalGoal(Goal personalGoal) {
        this.personalGoal = personalGoal;
    }
    */
}
