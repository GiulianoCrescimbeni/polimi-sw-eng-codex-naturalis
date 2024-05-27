package it.polimi.ingsw.view.TUI;

import it.polimi.ingsw.model.Data.SerializedGame;
import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.CardType;
import it.polimi.ingsw.model.Enumerations.Color;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.*;
import it.polimi.ingsw.model.Player.Player;
import it.polimi.ingsw.model.Player.PlayerHand;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.ClientSR;
import it.polimi.ingsw.network.client.commands.ChatMessageCommand;
import it.polimi.ingsw.network.client.commands.CreateMatchCommand;
import it.polimi.ingsw.network.client.commands.JoinMatchCommand;

import java.util.*;
import java.util.stream.Collectors;

public class View extends Thread {

    private static View instance;

    private Scanner s = new Scanner(System.in);

    private boolean chatMode = false;

    private View() {}

    public static View getInstance() {
        synchronized (View.class) {
            if (instance == null) {
                instance = new View();
            }
        }
        return instance;
    }

    @Override
    public void run() {
        menu();
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

    public void pickUsernameAndColor() {

        Messages.getInstance().input("Insert your username: ");

        String username = s.nextLine();

        showColors();

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

        //s.nextLine();
    }

    public void showColors() {
        Messages.getInstance().info("Here's a list of the available colors: ");
        for(int i = 0; i < ClientController.getInstance().getAvailableColors().size(); i++) {
            System.out.println(i + ": " + ClientController.getInstance().getAvailableColors().get(i).toString());
        }
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

    public void menu() {
        showCommands();
        while(true) {
            updateInfo(null, false);
            String input = s.nextLine().trim();
            String[] args = input.split(" ");
            String command = args[0].toLowerCase();
            switch (command) {
                case "playcard":
                    if (args.length == 5) {
                        int x = Integer.parseInt(args[1]);
                        int y = Integer.parseInt(args[2]);
                        int cardToPlay = Integer.parseInt(args[3]);
                        String cardToPick = args[4];
                        playCard(x, y, cardToPlay, cardToPick);
                    } else {
                        Messages.getInstance().error("Format error. Use: playcard [#x] [#y] [#CardToPlay] [#CardToPick]");
                    }
                    continue;

                case "inspectcodex":
                    String nameOfPlayer = args.length > 1 ? args[1] : ClientController.getInstance().getUsername();
                    inspectCodex(nameOfPlayer);
                    continue;

                case "inspectcard":
                    if (args.length == 4) {
                        nameOfPlayer = args[1];
                        int x = Integer.parseInt(args[2]);
                        int y = Integer.parseInt(args[3]);
                        inspectCard(nameOfPlayer, x, y);
                    } else if (args.length == 3) {
                        int x = Integer.parseInt(args[1]);
                        int y = Integer.parseInt(args[2]);
                        inspectCard(ClientController.getInstance().getUsername(), x, y);
                    } else {
                        Messages.getInstance().error("Format error. Use: inspectCard [optional: {NameOfPlayer}, default:{You}] [#x] [#y]");
                    }
                    continue;

                case "inspecthand":
                    if (args.length == 2) {
                        int cardToInspectFromHand = Integer.parseInt(args[1]);
                        inspectHand(cardToInspectFromHand);
                    } else {
                        inspectHand(-1);
                    }
                    continue;

                case "inspectground":
                    if (args.length == 2) {
                        int cardToInspectFromGround = Integer.parseInt(args[1]);
                        inspectGround(cardToInspectFromGround);
                    } else {
                        inspectGround(-1);
                    }
                    continue;

                case "viewgoals":
                    viewGoals();
                    continue;

                case "viewscores":
                    viewScores();
                    continue;

                case "chat":
                    chat();
                    continue;

                case "commands":
                    showCommands();
                    continue;

                default:
                    Messages.getInstance().error("Command not valid");
                    continue;
            }
        }
    }

    public void showCommands() {
        clear();
        System.out.println(
                        "╔══════════════════════════════════════════════════════════════════════════════════════════════╗\n" +
                        "║                                           Commands                                           ║\n" +
                        "╠══════════════════════════════════════════════════════════════════════════════════════════════╣\n" +
                        "║ - playcard [#x] [#y] [#CardToPlayFromHand] [#CardToPickFromGround / ResourceDeck / GoldDeck] ║\n" +
                        "║ - inspectcodex [optional: {NameOfPlayer}, default:{You}]                                     ║\n" +
                        "║ - inspectcard [optional: {NameOfPlayer}, default:{You}] [#x] [#y]                            ║\n" +
                        "║ - inspecthand [optional: #CardToInspectFromHand]                                             ║\n" +
                        "║ - inspectground [optional: #CardToInspectFromGround]                                         ║\n" +
                        "║ - viewgoals                                                                                  ║\n" +
                        "║ - viewscores                                                                                 ║\n" +
                        "║ - chat                                                                                       ║\n" +
                        "║ - commands                                                                                   ║\n" +
                        "╚══════════════════════════════════════════════════════════════════════════════════════════════╝"
        );

    }

    public void updateInfo(String message, boolean clear) {
        if(clear)
            clear();
        Messages.getInstance().info("Current Player: " + printPlayer(ClientController.getInstance().getCurrentPlayer()));
        if(message != null) {
            Messages.getInstance().error(message);
        }
        Messages.getInstance().input("Command: ");
    }

    public void playCard(int x, int y, int cardToPlay, String cardToPick) {
        if(isMyTurn()) {
            Coordinate coordinate = new Coordinate(x, y);
            Card cardPlaced = ClientController.getInstance().getPlayerHand().getCards().get(cardToPlay - 1);
            if(cardToPick.equals("ResourceDeck")) {
                ClientController.getInstance().playWithPickFromDeck(coordinate, cardPlaced, 0);
            } else if(cardToPick.equals("GoldDeck")) {
                ClientController.getInstance().playWithPickFromDeck(coordinate, cardPlaced, 1);
            } else {
                ArrayList<Card> cardsToPick = new ArrayList<Card>();
                cardsToPick.addAll(ClientController.getInstance().getCardToPick());
                cardsToPick.addAll(ClientController.getInstance().getGoldCardToPick());
                Card cardPicked = cardsToPick.get(Integer.parseInt(cardToPick) - 1);
                ClientController.getInstance().playWithPickFromGround(coordinate, cardPlaced, cardPicked);
            }
        } else {
            Messages.getInstance().error("Wait your turn before playing!");
        }
    }

    public void inspectCodex(String username) {
        clear();
        Player player = ClientController.getInstance().getPlayerByUsername(username);
        Codex codex = ClientController.getInstance().getCodexMap().get(player);
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

        for(Resource r : codex.getNumOfResources().keySet()) {
            switch (r) {
                case FUNGI:
                    System.out.println(TextColor.RED + "Fungi: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
                case PLANT:
                    System.out.println(TextColor.GREEN + "Plant: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
                case INSECT:
                    System.out.println(TextColor.PURPLE + "Insect: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
                case ANIMAL:
                    System.out.println(TextColor.BRIGHT_BLUE + "Animal: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
                case FEATHER:
                    System.out.println(TextColor.BRIGHT_YELLOW + "Feather: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
                case JAR:
                    System.out.println(TextColor.BRIGHT_YELLOW + "Jar: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
                case SCROLL:
                    System.out.println(TextColor.BRIGHT_YELLOW + "Scroll: " + TextColor.RESET + codex.getNumOfResources(r));
                    break;
            }
        }
    }

    public void inspectCard(String username, int x, int y) {
        clear();
        Player player = ClientController.getInstance().getPlayerByUsername(username);
        Codex codex = ClientController.getInstance().getCodexMap().get(player);
        Coordinate coordinate = new Coordinate(x, y);
        Card card = codex.getCard(coordinate);
        printCardCoverage(card);
        printCardStatistics(card);
    }

    public void inspectHand(int cardToInspect) {
        clear();
        PlayerHand playerHand = ClientController.getInstance().getPlayerHand();
        if(cardToInspect == -1) {
            int cardNumber = 1;
            for(Card c : playerHand.getCards()) {
                String cardString = "";
                if(c.getClass() == GoldCard.class || c.getClass() == AngleGoldCard.class || c.getClass() == ResourceGoldCard.class) {
                    cardString = TextColor.BRIGHT_YELLOW + "Gold Card" + TextColor.RESET;
                } else {
                    cardString = getCardColor(c) + "Card" + TextColor.RESET;
                }
                System.out.println(cardNumber + ") " + cardString);
                cardNumber++;
            }
        } else {
            Card card = playerHand.getCards().get(cardToInspect - 1);
            printCardCoverage(card);
            printCardStatistics(card);
        }
    }

    public void inspectGround(int cardToInspectFromGround) {
        clear();
        ArrayList<Card> cardsToPick = new ArrayList<Card>();
        cardsToPick.addAll(ClientController.getInstance().getCardToPick());
        cardsToPick.addAll(ClientController.getInstance().getGoldCardToPick());
        if(cardToInspectFromGround == -1) {
            int cardNumber = 1;
            for(Card c : cardsToPick) {
                String cardString = "";
                if(c.getClass() == GoldCard.class || c.getClass() == AngleGoldCard.class || c.getClass() == ResourceGoldCard.class) {
                    cardString = TextColor.BRIGHT_YELLOW + "Gold Card" + TextColor.RESET;
                } else {
                    cardString = getCardColor(c) + "Card" + TextColor.RESET;
                }
                System.out.println(cardNumber + ") " + cardString);
                cardNumber++;
            }
        } else {
            Card cardToInspect = cardsToPick.get(cardToInspectFromGround - 1);
            printCardCoverage(cardToInspect);
            printCardStatistics(cardToInspect);
        }
    }

    public void viewGoals() {
        clear();
        System.out.println("Common Goals: ");
        System.out.print("1)");
        ClientController.getInstance().getCommonGoals().get(0).draw();
        System.out.print("2)");
        ClientController.getInstance().getCommonGoals().get(1).draw();
        System.out.println("Personal Goal: ");
        System.out.print("  ");
        ClientController.getInstance().getPersonalGoal().draw();
    }

    public void viewScores() {
        clear();
        for(Player player : ClientController.getInstance().getPlayers()) {
            System.out.println(printPlayer(player) + " Score: " + TextColor.BRIGHT_YELLOW + ClientController.getInstance().getCodexMap().get(player).getScore() + TextColor.RESET);
        }
    }

    public void chat() {
        chatMode = true;
        updateChatView("");

        while (chatMode) {
            String text = s.nextLine();
            String[] args = text.split(" ");

            if (args.length == 1) {

                if (args[0].equals("0")) {
                    chatMode = false;
                    menu();
                    return;
                }

                updateChatView("The message is too short");
                continue;
            }

            if (args[0].equals("public")) {

                String[] message = new String[args.length - 1];
                for (int i = 1; i < args.length; i++) {
                    message[i - 1] = args[i];
                }

                String toSend = String.join(" ", message);

                ChatMessageCommand cmd = new ChatMessageCommand(toSend, "public", ClientController.getInstance().getUsername());

                try {
                    ClientSR.getInstance().sendCommand(cmd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                updateChatView("");

            } else {
                boolean found = false;

                for (Player p : ClientController.getInstance().getPlayers()) {
                    if (p.getNickname().equals(args[0])) found = true;
                }

                if (!found) {
                    updateChatView("The specified player does not exists");
                    continue;
                }

                String[] message = new String[args.length - 1];
                for (int i = 1; i < args.length; i++) {
                    message[i - 1] = args[i];
                }

                String toSend = String.join(" ", message);

                ChatMessageCommand cmd = new ChatMessageCommand(toSend, args[0], ClientController.getInstance().getUsername());

                try {
                    ClientSR.getInstance().sendCommand(cmd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                updateChatView("");

            }

        }

    }

    public synchronized void updateChatView(String error) {
        if (chatMode) {
            clear();
            Messages.getInstance().info("You're in the chat section");

            if (ClientController.getInstance().getMessages() != null) {
                for (String s : ClientController.getInstance().getMessages()) {
                    System.out.println(s);
                }
            }

            if (!error.equals("")) {
                Messages.getInstance().error(error);
            }

            Messages.getInstance().input("Message: ");
        }
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
                System.out.printf("\n");
            }
        }
    }

    public String printPlayer(Player player) {
        switch(player.getColor()) {
            case RED: return TextColor.RED + player.getNickname() + TextColor.RESET;
            case BLUE: return TextColor.BLUE + player.getNickname() + TextColor.RESET;
            case GREEN: return TextColor.GREEN + player.getNickname() + TextColor.RESET;
            case YELLOW: return TextColor.BRIGHT_YELLOW + player.getNickname() + TextColor.RESET;
        }
        return null;
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
        if(card.getCardType() == null) {
            return TextColor.WHITE.toString();
        }
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

    public boolean isMyTurn() {
        if(ClientController.getInstance().getUsername().equals(ClientController.getInstance().getCurrentPlayer().getNickname())) {
            return true;
        } else {
            return false;
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