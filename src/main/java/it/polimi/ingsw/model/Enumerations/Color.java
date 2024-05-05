package it.polimi.ingsw.model.Enumerations;

/**
 * The Color enum of the possible color of the player
 */
public enum Color {
    /**
     * The green player's color
     */
    GREEN {
        @Override
        public String toString() {
            return "\u001B[32m" + "Verde" + "\u001B[0m";
        }
    },
    /**
     * The red player's color
     */
    RED{
        @Override
        public String toString() {
            return "\u001B[31m" + "Rosso" + "\u001B[0m";
        }
    },
    /**
     * The blue player's color
     */
    BLUE{
        @Override
        public String toString() {
            return "\u001B[34m" + "Blu" + "\u001B[0m";
        }
    },
    /**
     * The yellow player's color
     */
    YELLOW{
        @Override
        public String toString() {
            return "\u001B[33m" + "Giallo" + "\u001B[0m";
        }
    }
}
