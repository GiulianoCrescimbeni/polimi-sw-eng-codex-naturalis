package it.polimi.ingsw.model.GameComponents;

/**
 * Class that represents the codex's coordinate of each card
 */
public class Coordinate {
    private int x;
    private int y;

    /**
     * Constructor
     * @param x
     * @param y
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor
     */
    public Coordinate() {

    }

    /**
     * @return the x coordinate of the card
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate of the card
     */
    public int getY() {
        return y;
    }

    /**
     * this compares the coordinates between two cards
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return this.x == that.x && this.y == that.y;
    }
}
