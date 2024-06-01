package it.polimi.ingsw.model.Enumerations;

import it.polimi.ingsw.view.TUI.TextColor;

/**
 * The Color enum of the possible color of the player
 */
public enum Color {
    /**
     * The green player's color
     */
    Green {
        @Override
        public String toString() {
            return TextColor.GREEN + "Green" + TextColor.RESET;
        }
    },
    /**
     * The red player's color
     */
    Red {
        @Override
        public String toString() {
            return TextColor.RED + "Red" + TextColor.RESET;
        }
    },
    /**
     * The blue player's color
     */
    Blue {
        @Override
        public String toString() {
            return TextColor.BLUE + "Blue" + TextColor.RESET;
        }
    },
    /**
     * The yellow player's color
     */
    Yellow {
        @Override
        public String toString() {
            return TextColor.BRIGHT_YELLOW + "Yellow" + TextColor.RESET;
        }
    };
}
