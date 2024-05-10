package org.example.commands;

import java.util.List;
import java.util.Scanner;

public class Commands {
    final private Scanner scanner = new Scanner(System.in);

    public int displayChoices(List<String> choices) {
        for (int i = 0; i < choices.size(); i++) {
            System.out.println(i + " " + choices.get(i));
        }

        return getUserChoice(choices);
    }
    public int getUserInteger(String validationMessage) {
        Scanner scanner = new Scanner(System.in);
        int playerChoice = -1;
        while (!(playerChoice > -1)) {
            System.out.println(validationMessage);
            try {
                playerChoice = scanner.nextInt();
            } catch (Exception error) {
                scanner.nextLine();
            }
        }
        return playerChoice;
    }

    public int getUserChoice(List<String> choices) {
        int input = -1;
        while (input >= choices.size() || input < 0) {
            System.out.printf("Please enter a number between 0 and %d\n",
                    choices.size() - 1);
            try {
                input = scanner.nextInt();
            } catch (Exception error) {
                scanner.nextLine();
            }
        }
        return input;
    }

    public boolean isQuitRequested() {
        System.out.println("Press 'Q' to return to main menu...");
        String input = scanner.next().trim();
        return input.equalsIgnoreCase("Q");
    }
}