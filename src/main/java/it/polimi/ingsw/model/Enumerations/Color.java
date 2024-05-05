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
            return "Verde";
        }
    },
    /**
     * The red player's color
     */
    RED{
        @Override
        public String toString() {
            return "Rosso";
        }
    },
    /**
     * The blue player's color
     */
    BLUE{
        @Override
        public String toString() {
            return "Blu";
        }
    },
    /**
     * The yellow player's color
     */
    YELLOW{
        @Override
        public String toString() {
            return "Giallo";
        }
    }
}
