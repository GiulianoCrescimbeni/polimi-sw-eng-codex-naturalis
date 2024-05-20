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

        s.nextLine();
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
        clear();

        System.out.println("Current Player: " + printPlayer(ClientController.getInstance().getCurrentPlayer()));
        if(isMyTurn()) {
            System.out.println("1) Play a card");
            System.out.println("2) Inspect Codex");
            System.out.println("3) Inspect Hand");
            System.out.println("4) Inspect Ground");
            System.out.println("5) View Goals");
            System.out.println("6) View Scores");
            System.out.println("7) Chat");
            int option = getOptionsInput(7);
            switch (option) {
                case 1: playCard();
                case 2: inspectCodex();
                case 3: inspectHand();
                case 4: inspectGround();
                case 5: viewGoals();
                case 6: viewScores();
                case 7: chat();
            }
        } else {
            System.out.println("1) Inspect Codex");
            System.out.println("2) Inspect Hand");
            System.out.println("3) Inspect Ground");
            System.out.println("4) View Goals");
            System.out.println("5) View Scores");
            System.out.println("6) Chat");
            int option = getOptionsInput(6);
            switch (option) {
                case 1: inspectCodex();
                case 2: inspectHand();
                case 3: inspectGround();
                case 4: viewGoals();
                case 5: viewScores();
                case 6: chat();
            }
        }

    }

    public void playCard() {
        clear();
        System.out.println("Which card you want to play?");
        PlayerHand playerHand = ClientController.getInstance().getPlayerHand();
        int possibleOption = 1;
        for(Card c : playerHand.getCards()) {
            String cardString = "";
            if(c.getClass() == GoldCard.class || c.getClass() == AngleGoldCard.class || c.getClass() == ResourceGoldCard.class) {
                cardString = TextColor.BRIGHT_YELLOW + "Gold Card" + TextColor.RESET;
            } else {
                cardString = getCardColor(c) + "Card" + TextColor.RESET;
            }
            System.out.println(possibleOption + ") " + cardString);
            possibleOption++;
        }
        System.out.println(possibleOption + ") Back to the menu");
        int option = getOptionsInput(possibleOption);
        if(option == possibleOption) {
            menu();
            return;
        }

        Card card = playerHand.getCards().get(option - 1);
        System.out.print("Currently playing the card on the ");
        if(!card.isTurned()) {
            System.out.print(TextColor.BRIGHT_BLACK + "Front\n" + TextColor.RESET);
        } else {
            System.out.print(TextColor.BRIGHT_BLACK + "Back\n" + TextColor.RESET);
        }
        System.out.println("Do you want to turn it?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        option = getOptionsInput(2);
        if(option == 1) {
            card.turn();
        }
        Coordinate coordinates = getCoordinates();
        System.out.println("Where do you want to pick a new card?");
        System.out.println("1) Ground");
        System.out.println("2) Resource Deck");
        System.out.println("3) Gold Deck");
    }

    public void inspectCodex() {
        clear();
        System.out.println("Which codex you want to inspect?");
        int possibleOption = 1;
        for(Player player : ClientController.getInstance().getPlayers()) {
            System.out.println(possibleOption + ") " + printPlayer(player));
            possibleOption++;
        }
        System.out.println(possibleOption + ") Back to the menu");
        int option = getOptionsInput(possibleOption);
        if(option == possibleOption) {
            menu();
        } else {
            printCodex(ClientController.getInstance().getCodexMap().get(ClientController.getInstance().getPlayers().get(option - 1)));
        }
    }

    public void inspectHand() {
        clear();
        PlayerHand playerHand = ClientController.getInstance().getPlayerHand();
        System.out.println("Which card you want to inspect?");
        int possibleOption = 1;
        for(Card c : playerHand.getCards()) {
            String cardString = "";
            if(c.getClass() == GoldCard.class || c.getClass() == AngleGoldCard.class || c.getClass() == ResourceGoldCard.class) {
                cardString = TextColor.BRIGHT_YELLOW + "Gold Card" + TextColor.RESET;
            } else {
                cardString = getCardColor(c) + "Card" + TextColor.RESET;
            }
            System.out.println(possibleOption + ") " + cardString);
            possibleOption++;
        }
        System.out.println(possibleOption + ") Back to the menu");
        int option = getOptionsInput(possibleOption);
        if(option == possibleOption) {
            menu();
        } else {
            clear();
            printCardCoverage(playerHand.getCards().get(option - 1));
            printCardStatistics(playerHand.getCards().get(option - 1));
            System.out.println("1) Back to hand");
            System.out.println("2) Back to menu");
            option = getOptionsInput(2);
            if(option == 1) {
                inspectHand();
            } else if(option == 2) {
                 menu();
            }
        }

    }

    void inspectGround() {
        clear();
        ArrayList<Card> cardsToPick = new ArrayList<Card>();
        cardsToPick.addAll(ClientController.getInstance().getCardToPick());
        cardsToPick.addAll(ClientController.getInstance().getGoldCardToPick());
        System.out.println("Which card you want to inspect?");
        int possibleOption = 1;
        for(Card c : cardsToPick) {
            String cardString = "";
            if(c.getClass() == GoldCard.class || c.getClass() == AngleGoldCard.class || c.getClass() == ResourceGoldCard.class) {
                cardString = TextColor.BRIGHT_YELLOW + "Gold Card" + TextColor.RESET;
            } else {
                cardString = getCardColor(c) + "Card" + TextColor.RESET;
            }
            System.out.println(possibleOption + ") " + cardString);
            possibleOption++;
        }
        System.out.println(possibleOption + ") Back to the menu");
        int option = getOptionsInput(possibleOption);
        if(option == possibleOption) {
            menu();
        } else {
            clear();
            printCardCoverage(cardsToPick.get(option - 1));
            printCardStatistics(cardsToPick.get(option - 1));
            System.out.println("1) Back to Ground");
            System.out.println("2) Back to menu");
            option = getOptionsInput(2);
            if(option == 1) {
                inspectGround();
            } else if(option == 2) {
                menu();
            }
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
        System.out.println("");
        System.out.println("1) Go back to the menu");
        getOptionsInput(1);
        menu();
    }

    public void viewScores() {
        clear();
        for(Player player : ClientController.getInstance().getPlayers()) {
            System.out.println(printPlayer(player) + " Score: " + TextColor.BRIGHT_YELLOW + ClientController.getInstance().getCodexMap().get(player).getScore() + TextColor.RESET);
        }
        System.out.println("1) Back to menu");
        getOptionsInput(1);
        menu();
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

            if (!error.equals("")) {
                Messages.getInstance().error(error);
            }

            if (ClientController.getInstance().getMessages() != null) {
                for (String s : ClientController.getInstance().getMessages()) {
                    System.out.println(s);
                }
            }


            Messages.getInstance().input("Message: ");
        }
    }

    public void printCodex(Codex codex) {
        clear();
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
        System.out.println("1) Inspect Card");
        System.out.println("2) Back to menu");
        int option = getOptionsInput(2);
        if(option == 1) {
            inspectCard(codex);
        } else {
            menu();
        }
    }

    public void inspectCard(Codex codex) {
        Card card = getCard(codex);
        printCardCoverage(card);
        printCardStatistics(card);
        System.out.println("1) Back to codex");
        getOptionsInput(1);
        printCodex(codex);
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

    public Card getCard(Codex codex) {
        Messages.getInstance().input("Write the coordinate of the card you want to inspect (x y): ");
        int x = s.nextInt();
        int y = s.nextInt();
        Coordinate coordinate = new Coordinate(x, y);
        Card card = codex.getCard(coordinate);
        if(card != null) {
            return card;
        } else {
            Messages.getInstance().error("Coordinates not valid, try again!");
            getCard(codex);
        }
        return null;
    }

    public Coordinate getCoordinates() {
        Messages.getInstance().input("Write the coordinate where you want to place the card (x y): ");
        int x = s.nextInt();
        int y = s.nextInt();
        return new Coordinate(x, y);
    }
}
