package it.polimi.ingsw.model.Enumerations;

import it.polimi.ingsw.view.TextColor;

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
            return TextColor.GREEN + "Green" + TextColor.RESET;
        }
    },
    /**
     * The red player's color
     */
    RED{
        @Override
        public String toString() {
            return TextColor.RED + "Red" + TextColor.RESET;
        }
    },
    /**
     * The blue player's color
     */
    BLUE{
        @Override
        public String toString() {
            return TextColor.BLUE + "Blue" + TextColor.RESET;
        }
    },
    /**
     * The yellow player's color
     */
    YELLOW{
        @Override
        public String toString() {
            return TextColor.BRIGHT_YELLOW + "Yellow" + TextColor.RESET;
        }
    }
}
