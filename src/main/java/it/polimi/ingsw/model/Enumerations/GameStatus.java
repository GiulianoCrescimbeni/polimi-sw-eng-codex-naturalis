package it.polimi.ingsw.model.Enumerations;

/**
 * The GameStatus enum represents the possible state of the game
 */
public enum GameStatus {

    /**
     * The game is waiting to start
     */
    WAITING_TO_START,
    /**
     * The game is running
     */
    RUNNING,
    /**
     * The game reached the last turn
     */
    LAST_TURN,
    /**
     * The game has ended
     */
    ENDED
}
