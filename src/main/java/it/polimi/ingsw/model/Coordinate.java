package it.polimi.ingsw.model;

/**
 * Class that represents the codex's coordinate of each card
 */
public class Coordinate {
    private int x;
    private int y;

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
