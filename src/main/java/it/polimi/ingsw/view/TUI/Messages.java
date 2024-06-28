package it.polimi.ingsw.view.TUI;

/**
 * Class for messages management
 */
public class Messages {

    private static Messages instance;

    /**
     * Constructor
     */
    private Messages() {}

    public static Messages getInstance() {
        if (instance == null) instance = new Messages();
        return instance;
    }

    /**
     * Print an info message
     * @param message the message
     */
    public void info(String message) {
        System.out.println(TextColor.BRIGHT_BLUE + "[INFO] " + TextColor.RESET + message);
    }

    /**
     * Print an error message
     * @param message the message
     */
    public void error(String message) {
        System.out.println(TextColor.RED + "[ERROR] " + TextColor.RESET + message);
    }

    /**
     * Print an input message
     * @param message the message
     */
    public void input(String message) {
        System.out.print(TextColor.BRIGHT_YELLOW + "[INPUT] " + TextColor.RESET + message);
    }

    /**
     * Print a chat message
     * @param message the message
     * @param isPrivate true if is private, false if is public
     */
    public void message(String message, boolean isPrivate) {
        if (isPrivate) {
            System.out.println(TextColor.BRIGHT_PURPLE + "[PRIVATE MESSAGE]: " + TextColor.RESET + message);
        } else {
            System.out.println(TextColor.BRIGHT_PURPLE + "[MESSAGE]: " + TextColor.RESET + message);
        }
    }

    /**
     * @param message the message
     * @return the string for an info message
     */
    public String getInfoMessage(String message) {
        return TextColor.BRIGHT_BLUE + "[INFO] " + TextColor.RESET + message;
    }

    /**
     * @param message the message
     * @return the string for an error message
     */
    public String getErrorMessage(String message) {
        return TextColor.RED + "[ERROR] " + TextColor.RESET + message;
    }

    /**
     * @param message the message
     * @return the string for an input message
     */
    public String getInputMessage(String message) {
        return TextColor.BRIGHT_YELLOW + "[INPUT] " + TextColor.RESET + message;
    }

    /**
     * @param message the message
     * @return the string for a chat message
     */
    public String getMessage(String message, boolean isPrivate) {
        if (isPrivate) {
            return TextColor.BRIGHT_PURPLE + "[PRIVATE MESSAGE] " + TextColor.RESET + message;
        } else {
            return TextColor.BRIGHT_PURPLE + "[MESSAGE] " + TextColor.RESET + message;
        }
    }

}
