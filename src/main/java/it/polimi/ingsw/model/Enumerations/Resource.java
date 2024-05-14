package it.polimi.ingsw.model.Enumerations;

/**
 * The Resource Enum represents the possible type of resources in the game
 */
public enum Resource {
    /**
     * Plant Resource (The green one)
     */
    PLANT,
    /**
     * Animal Resource (The blue one)
     */
    ANIMAL,
    /**
     * Fungi resource (The red one)
     */
    FUNGI,
    /**
     * Insect resource (The purple one)
     */
    INSECT,
    /**
     * Scroll resource
     */
    SCROLL,
    /**
     * Feather resource
     */
    FEATHER,
    /**
     * Jar resource
     */
    JAR,
    /**
     * Blank resource
     */
    BLANK;

    /**
     *
     * @param name The name of the resource
     * @return The resource from the name
     */
    public static Resource getFromName(String name) {
        switch (name) {
            case "PLANT":
                return PLANT;

            case "ANIMAL":
                return ANIMAL;

            case "FUNGI":
                return FUNGI;

            case "INSECT":
                return INSECT;

            case "SCROLL":
                return SCROLL;

            case "FEATHER":
                return FEATHER;

            case "JAR":
                return JAR;

            case "BLANK":
                return BLANK;

            default:
                return null;
        }
    }

}
