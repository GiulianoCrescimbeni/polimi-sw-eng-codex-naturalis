package it.polimi.ingsw.model.GameComponents.Exceptions;

/**
 * Exception class for illegal card placement
 */
public class IllegalCardPlacementException extends Exception{
    public IllegalCardPlacementException(String message) {
        super(message);
    }
}
