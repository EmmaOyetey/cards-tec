package org.example.utilities;

import org.example.card.Card;

import java.util.Scanner;

public class Utilities {

    public static void printCard(Card card) {
        String[] lines = cardToAscii(card).split("\n");
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public static String cardToAscii(Card card) {
        String paddedSymbol = padSymbol(card.getSymbol());
        String paddedSuitSymbol = padSuitSymbol(String.valueOf(card.getSuit().getSuitSymbol()));

        return " __________ \n" +
                "|" + paddedSymbol + "        |\n" +
                "|" + paddedSuitSymbol + "        |\n" +
                "|          |\n" +
                "|        " + paddedSuitSymbol + "|\n" +
                "|        " + paddedSymbol + "|\n" +
                " ---------- \n";
    }

    private static String padSymbol(String symbol) {
        return String.format("%-2s", symbol); // Pad the symbol to have a width of 2 characters
    }

    private static String padSuitSymbol(String suitSymbol) {
        return String.format("%-2s", suitSymbol); // Pad the suit symbol to have a width of 2 characters
    }

    public static void displayCards(Runnable printCardsInPlay) {
        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine(); // Read the input line

        if (!inputLine.isEmpty()) {
            System.out.println("Invalid input. Press Enter to trigger the action.");
        } else {
            printCardsInPlay.run();
        }
    }

}
