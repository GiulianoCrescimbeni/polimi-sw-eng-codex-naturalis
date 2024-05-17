package it.polimi.ingsw.view.TUI;

import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.GameComponents.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.client.commands.CreateMatchCommand;
import it.polimi.ingsw.network.client.commands.JoinMatchCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class View {

    private static View instance;

    private Scanner s = new Scanner(System.in);

    private View() {}

    public static View getInstance() {
        if (instance == null) {
            instance = new View();
        }
        return instance;
    }

    public void clear() {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public void showTitleScreen() {
        System.out.println(TextColor.BRIGHT_GREEN +
                        " ▄▄·       ·▄▄▄▄  ▄▄▄ .▐▄• ▄      ▐ ▄  ▄▄▄· ▄▄▄▄▄▄• ▄▌▄▄▄   ▄▄▄· ▄▄▌  ▪  .▄▄ · \n" +
                        "▐█ ▌▪▪     ██▪ ██ ▀▄.▀· █▌█▌▪    •█▌▐█▐█ ▀█ •██  █▪██▌▀▄ █·▐█ ▀█ ██•  ██ ▐█ ▀. \n" +
                        "██ ▄▄ ▄█▀▄ ▐█· ▐█▌▐▀▀▪▄ ·██·     ▐█▐▐▌▄█▀▀█  ▐█.▪█▌▐█▌▐▀▀▄ ▄█▀▀█ ██▪  ▐█·▄▀▀▀█▄\n" +
                        "▐███▌▐█▌.▐▌██. ██ ▐█▄▄▌▪▐█·█▌    ██▐█▌▐█ ▪▐▌ ▐█▌·▐█▄█▌▐█•█▌▐█ ▪▐▌▐█▌▐▌▐█▌▐█▄▪▐█\n" +
                        "·▀▀▀  ▀█▄▀▪▀▀▀▀▀•  ▀▀▀ •▀▀ ▀▀    ▀▀ █▪ ▀  ▀  ▀▀▀  ▀▀▀ .▀  ▀ ▀  ▀ .▀▀▀ ▀▀▀ ▀▀▀▀ \n"
        );
    }

    public void showColors() {
        Messages.getInstance().info("Here's a list of the available colors: ");
        for(int i = 0; i < ClientController.getInstance().getAvailableColors().size(); i++) {
            System.out.println(i + ": " + ClientController.getInstance().getAvailableColors().get(i).toString());
        }
        pickUsernameAndColor();
    }

    public void pickUsernameAndColor() {

        Messages.getInstance().input("Insert your username: ");

        String username = s.nextLine();

        if (ClientController.getInstance().getAvailableColors().size() > 1) {
            Messages.getInstance().input("Insert the number representing the color you would like to choose: ");
            int colorIndex = s.nextInt();

            if (ClientController.getInstance().getAvailableColors().get(colorIndex) != null) {
                Color c = ClientController.getInstance().getAvailableColors().get(colorIndex);
                ClientController.getInstance().sendUsernameAndColor(username, c);
            }
        } else {
            Color c = ClientController.getInstance().getAvailableColors().get(0);
            ClientController.getInstance().sendUsernameAndColor(username, c);
        }

        s.nextLine();
    }

    public void selectPlayersNumber() {

        int maxPlayers = 0;

        while (maxPlayers < 2 || maxPlayers > 4) {
            Messages.getInstance().input("Select the maximum number of players that can join the game (from " + TextColor.BRIGHT_YELLOW + "2" + TextColor.RESET + " to " + TextColor.BRIGHT_YELLOW + "4" + TextColor.RESET + "): ");
            maxPlayers = s.nextInt();

            if (maxPlayers < 2 || maxPlayers > 4) {
                Messages.getInstance().error("The inserted number must be between " + TextColor.BRIGHT_RED + "2" + TextColor.RESET + " and " + TextColor.BRIGHT_RED + "4" + TextColor.RESET);
            }

        }

        ClientController.getInstance().sendMaxPlayers(maxPlayers);
        s.nextLine();
    }

    public void selectPersonalGoal() {
        System.out.print("1)");
        ClientController.getInstance().getGoalsToPick().get(0).draw();
        System.out.println("   Goal Score: " + TextColor.BRIGHT_YELLOW + ClientController.getInstance().getGoalsToPick().get(0).getScore() + TextColor.RESET);
        System.out.println("\n");
        System.out.print("2)");
        ClientController.getInstance().getGoalsToPick().get(1).draw();
        System.out.println("   Goal Score: " + TextColor.BRIGHT_YELLOW + ClientController.getInstance().getGoalsToPick().get(1).getScore() + TextColor.RESET);
        System.out.println("\n");
        Messages.getInstance().info("Digit the number of the goal you want to choose");
        int optionChoosen = getOptionsInput(2);
        if(optionChoosen == 1) {
            ClientController.getInstance().selectPersonalGoal(ClientController.getInstance().getGoalsToPick().get(0));
        } else if(optionChoosen == 2) {
            ClientController.getInstance().selectPersonalGoal(ClientController.getInstance().getGoalsToPick().get(1));
        }

    }

    public void joinOrCreateMatch(ArrayList<SerializedGame> availableMatches) {

        if (availableMatches.isEmpty()) {
            createMatch();
            return;
        }

        Messages.getInstance().input("Do you want to join or create a match? \nJoin: 1\nCreate: 2\n");
        int input = getOptionsInput(2);

        if (input == 1) {
            selectAvailableMatch(availableMatches, null);
        } else {
            createMatch();
        }

    }

    public void createMatch() {
        int maxPlayers = 0;

        while (maxPlayers < 2 || maxPlayers > 4) {
            Messages.getInstance().input("Select the maximum number of players that can join the game (from " + TextColor.BRIGHT_YELLOW + "2" + TextColor.RESET + " to " + TextColor.BRIGHT_YELLOW + "4" + TextColor.RESET + "): ");
            maxPlayers = s.nextInt();

            if (maxPlayers < 2 || maxPlayers > 4) {
                Messages.getInstance().error("The inserted number must be between " + TextColor.BRIGHT_RED + "2" + TextColor.RESET + " and " + TextColor.BRIGHT_RED + "4" + TextColor.RESET);
            }

        }

        s.nextLine();

        CreateMatchCommand cmd = new CreateMatchCommand(maxPlayers);
        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void selectAvailableMatch(ArrayList<SerializedGame> availableMatches, String error) {
        if (error != null) System.out.println(error);
        Messages.getInstance().info("Here's a list of the available matches: ");
        for (int i = 0; i < availableMatches.size(); i++) {
            SerializedGame g = availableMatches.get(i);
            System.out.println((i+1) + ": " + TextColor.BRIGHT_BLUE + g.getGameID() + TextColor.RESET + " (" + TextColor.BRIGHT_YELLOW + g.getCurrentPlayers() + TextColor.RESET + "/" + TextColor.BRIGHT_YELLOW + g.getMaxPlayers() + TextColor.RESET + ")");
        }
        int match = getOptionsInput(availableMatches.size()) - 1;
        JoinMatchCommand cmd = new JoinMatchCommand(availableMatches.get(match).getGameID());

        try {
            ClientSR.getInstance().sendCommand(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void printCodex(Codex codex) {
        List<Integer> xCoordinates = codex.getCards().keySet().stream().map(Coordinate::getX).distinct().sorted().collect(Collectors.toList());
        List<Integer> yCoordinates = codex.getCards().keySet().stream().map(Coordinate::getY).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        System.out.printf("    ");
        for(Integer i : xCoordinates) {
            System.out.printf("     " + i);
        }
        System.out.printf("\n");
        for(Integer j : yCoordinates) {
            System.out.printf("    " + j + "   ");
            for(Integer i : xCoordinates) {
                Coordinate c = new Coordinate(i, j);
                if(codex.getCard(c) != null) {
                    System.out.printf(getCardColor(codex.getCard(c)) + "██     " + TextColor.RESET);
                } else {
                    System.out.printf("       ");
                }
            }
            System.out.printf("\n\n");
        }
    }

    public void inspectCard(Card card) {
        printCardCoverage(card);
        printCardStatistics(card);
    }

    private void printCardCoverage(Card card) {
        String cardColor = getCardColor(card);
        String black = TextColor.BRIGHT_BLACK.toString();
        boolean isULHidden = card.getAngle(AnglePos.UL).isHidden();
        boolean isURHidden = card.getAngle(AnglePos.UR).isHidden();
        boolean isDLHidden = card.getAngle(AnglePos.DL).isHidden();
        boolean isDRHidden = card.getAngle(AnglePos.DR).isHidden();

        if(isULHidden == false && isURHidden == false) {
            System.out.println(cardColor+
                    "    ┌─────────────────────────────────────────────┐\n" +
                    "    │                                             │\n" +
                    "    │                                             │\n" +
                    "    │                                             │\n" +
                    "    │                                             │"
            );
        } else if(isULHidden == true && isURHidden == false) {
            System.out.println(black+
                    "                │                                  \n" +
                    "                ├"+cardColor+"─────────────────────────────────┐\n" +black+
                    "                │"+cardColor+"                                 │\n" +black+
                    "                │"+cardColor+"                                 │\n" +black+
                    "                │"+cardColor+"                                 │\n" +black+
                    "────┬───────────┘"+cardColor+"                                 │" +TextColor.RESET
            );
        } else if(isULHidden == false && isURHidden == true) {
            System.out.println(black+
                    "                                      │                \n" +cardColor+
                    "    ┌─────────────────────────────────"+black+"┤                \n" +cardColor+
                    "    │                                 "+black+"│                \n" +cardColor+
                    "    │                                 "+black+"│                \n" +cardColor+
                    "    │                                 "+black+"│                \n" +cardColor+
                    "    │                                 "+black+"└───────────┬────" +TextColor.RESET
            );
        } else if(isULHidden == true && isURHidden == true) {
            System.out.println(black+
                    "                │                     │                \n" +
                    "                ├"+cardColor+"─────────────────────"+black+"┤                \n" +
                    "                │"+cardColor+"                     "+black+"│                \n" +
                    "                │"+cardColor+"                     "+black+"│                \n" +
                    "                │"+cardColor+"                     "+black+"│                \n" +
                    "────┬───────────┘"+cardColor+"                     "+black+"└───────────┬────" +TextColor.RESET
            );
        }

        System.out.println(cardColor+
                "    │                                             │\n" +
                "    │                                             │\n" +
                "    │                                             │" +TextColor.RESET
        );
        if(isDLHidden == false && isDRHidden ==false) {
            System.out.println(cardColor+
                    "    │                                             │\n" +
                    "    │                                             │\n" +
                    "    │                                             │\n" +
                    "    │                                             │\n" +
                    "    └─────────────────────────────────────────────┘\n" +TextColor.RESET
            );
        } else if(isDLHidden == true && isDRHidden == false) {
            System.out.println(black+
                    "────┴───────────┐"+cardColor+"                                 │    \n" +black+
                    "                │"+cardColor+"                                 │    \n" +black+
                    "                │"+cardColor+"                                 │    \n" +black+
                    "                │"+cardColor+"                                 │    \n" +black+
                    "                ├"+cardColor+"─────────────────────────────────┘    \n" +black+
                    "                │                                      \n"+TextColor.RESET
            );
        } else if(isDLHidden == false && isDRHidden == true) {
            System.out.println(cardColor+
                    "    │                                 "+black+"┌───────────┴────\n" +cardColor+
                    "    │                                 "+black+"│                \n" +cardColor+
                    "    │                                 "+black+"│                \n" +cardColor+
                    "    │                                 "+black+"│                \n" +cardColor+
                    "    └─────────────────────────────────"+black+"┤                \n" +
                    "                                      │                \n"+TextColor.RESET
            );
        } else if(isDLHidden == true && isDRHidden ==true) {
            System.out.println(black +
                    "────┴───────────┐                     ┌───────────┴────\n" +
                    "                │                     │                \n" +
                    "                │                     │                \n" +
                    "                │                     │                \n" +
                    "                ├" + cardColor + "─────────────────────" + black + "┤                \n" +
                    "                │                     │                \n" + TextColor.RESET
            );
        }
    }

    private void printCardStatistics(Card card) {
        Angle UL = card.getAngle(AnglePos.UL);
        Angle UR = card.getAngle(AnglePos.UR);
        Angle DL = card.getAngle(AnglePos.DL);
        Angle DR = card.getAngle(AnglePos.DR);
        boolean isULHidden = card.getAngle(AnglePos.UL).isHidden();
        boolean isURHidden = card.getAngle(AnglePos.UR).isHidden();
        boolean isDLHidden = card.getAngle(AnglePos.DL).isHidden();
        boolean isDRHidden = card.getAngle(AnglePos.DR).isHidden();

        if (card.getClass() == Card.class) {
            System.out.println(TextColor.WHITE + "Resource Card" + TextColor.RESET);
        } else if(card.getClass() == GoldCard.class) {
            System.out.println(TextColor.BRIGHT_YELLOW + "Gold " + TextColor.WHITE + "Card" + TextColor.RESET);
        } else if(card.getClass() == AngleGoldCard.class) {
            System.out.println(TextColor.BRIGHT_YELLOW + "Angle Gold " + TextColor.WHITE + "Card" + TextColor.RESET);
        } else if(card.getClass() == ResourceGoldCard.class) {
            System.out.println(TextColor.BRIGHT_YELLOW + "Resource Gold " + TextColor.WHITE + "Card" + TextColor.RESET);
            System.out.println("Resource Type: " + printResource(((ResourceGoldCard) card).getResourceType()) + TextColor.RESET);
        }

        if(isULHidden || UL.getResource() == null) {
            System.out.println("Top Left Corner : "+TextColor.BRIGHT_BLACK+"Blocked"+TextColor.RESET);
        } else {
            System.out.println("Top Left Corner : " + printResource(UL.getResource()) + TextColor.RESET);
        }
        if(isURHidden || UR.getResource() == null) {
            System.out.println("Top Right Corner : "+TextColor.BRIGHT_BLACK+"Blocked"+TextColor.RESET);
        } else {
            System.out.println("Top Right Corner : " + printResource(UR.getResource()) + TextColor.RESET);
        }
        if(isDLHidden || DL.getResource() == null) {
            System.out.println("Bottom Left Corner : "+TextColor.BRIGHT_BLACK+"Blocked"+TextColor.RESET);
        } else {
            System.out.println("Bottom Left Corner : " + printResource(DL.getResource()) + TextColor.RESET);
        }
        if(isDRHidden || DR.getResource() == null) {
            System.out.println("Bottom Right Corner : "+TextColor.BRIGHT_BLACK+"Blocked"+TextColor.RESET);
        } else {
            System.out.println("Bottom Right Corner : " + printResource(DR.getResource()) + TextColor.RESET);
        }
        if(card.isTurned()) {
            System.out.println(TextColor.WHITE + "Card Turned");
        } else {
            if(card.getCardScore() != 0) {
                System.out.println("Card Score : " + TextColor.BRIGHT_YELLOW+card.getCardScore()+TextColor.RESET);
            }
            if(card.getClass() == GoldCard.class || card.getClass() == AngleGoldCard.class || card.getClass() == ResourceGoldCard.class) {
                System.out.printf("Play Condition: ");
                for(Resource r : ((GoldCard) card).getPlayCondition()) {
                    System.out.printf(printResource(r) + " ");
                }
            }
        }
    }

    public String printResource(Resource resource) {
        if (resource == null) {
            return " ";
        } else {
            switch (resource) {
                case FUNGI:
                    return TextColor.RED + "Fungi" + TextColor.RESET;
                case PLANT:
                    return TextColor.GREEN + "Plant" + TextColor.RESET;
                case INSECT:
                    return TextColor.PURPLE + "Insect" + TextColor.RESET;
                case ANIMAL:
                    return TextColor.BRIGHT_BLUE + "Animal" + TextColor.RESET;
                case FEATHER:
                    return TextColor.BRIGHT_YELLOW + "Feather" + TextColor.RESET;
                case JAR:
                    return TextColor.BRIGHT_YELLOW + "Jar" + TextColor.RESET;
                case SCROLL:
                    return TextColor.BRIGHT_YELLOW + "Scroll" + TextColor.RESET;
                case BLANK:
                    return TextColor.BRIGHT_WHITE + "Empty" + TextColor.RESET;
                default:
                    return "";
            }
        }
    }

    public String getCardColor(Card card) {
        switch (card.getCardType()) {
            case FUNGI:
                return TextColor.RED.toString();
            case PLANT:
                return TextColor.GREEN.toString();
            case INSECT:
                return TextColor.PURPLE.toString();
            case ANIMAL:
                return TextColor.BRIGHT_BLUE.toString();
            default:
                return "";
        }
    }

    public String getCardTypeColor(CardType cardType) {
        switch (cardType) {
            case FUNGI:
                return TextColor.RED.toString();
            case PLANT:
                return TextColor.GREEN.toString();
            case INSECT:
                return TextColor.PURPLE.toString();
            case ANIMAL:
                return TextColor.BRIGHT_BLUE.toString();
            default:
                return "";
        }
    }

    public String getReverseCardTypeColor(CardType cardType) {
        switch (cardType) {
            case FUNGI:
                return TextColor.GREEN.toString();
            case PLANT:
                return TextColor.PURPLE.toString();
            case INSECT:
                return TextColor.BRIGHT_BLUE.toString();
            case ANIMAL:
                return TextColor.RED.toString();
            default:
                return "";
        }
    }

    public int getOptionsInput(int numOfOptions) {
        Messages.getInstance().input("Choose an option (1 to "+numOfOptions+"): ");
        int option = s.nextInt();
        while (option < 1 || option > numOfOptions) {
            Messages.getInstance().error("Option not valid, try again");
            Messages.getInstance().input("Choose an option (1 to "+numOfOptions+"): ");
            option = s.nextInt();
        }
        s.nextLine();
        return option;
    }
}
