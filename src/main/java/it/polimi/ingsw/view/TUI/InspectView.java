package it.polimi.ingsw.view.TUI;

import it.polimi.ingsw.model.Enumerations.AnglePos;
import it.polimi.ingsw.model.Enumerations.Resource;
import it.polimi.ingsw.model.GameComponents.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InspectView {
    private static InspectView instance;

    private InspectView() {
    }

    public static InspectView getInstance() {
        if (instance == null) {
            instance = new InspectView();
        }
        return instance;
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
        String cardColor = getCardColor(card);
        String black = TextColor.BRIGHT_BLACK.toString();
        Angle UL = card.getAngle(AnglePos.UL);
        Angle UR = card.getAngle(AnglePos.UR);
        Angle DL = card.getAngle(AnglePos.DL);
        Angle DR = card.getAngle(AnglePos.DR);
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
                    return TextColor.RED + "Fungi";
                case PLANT:
                    return TextColor.GREEN + "Plant";
                case INSECT:
                    return TextColor.PURPLE + "Insect";
                case ANIMAL:
                    return TextColor.BRIGHT_BLUE + "Animal";
                case FEATHER:
                    return TextColor.BRIGHT_YELLOW + "Feather";
                case JAR:
                    return TextColor.BRIGHT_YELLOW + "Jar";
                case SCROLL:
                    return TextColor.BRIGHT_YELLOW + "Scroll";
                case BLANK:
                    return TextColor.BRIGHT_WHITE + "Empty";
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
}