package org.example;

import org.example.commands.Commands;
import org.example.deck.Deck;
import org.example.game.Blackjack;
import org.example.game.War;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

            Commands commands = new Commands();
            List<String> gameChoices = new ArrayList<>();
            gameChoices.add("War");
            gameChoices.add("Blackjack");
            gameChoices.add("OldMaid");

        System.out.println("Welcome to CardsTEC - card games by Todd, Emma & Cheryl"
        +"\n Which card game would you like to play?");

            int choice = commands.displayChoices(gameChoices);

            switch (choice) {
                case 0:
                    // Play War game
                    War warGame = new War();
                    warGame.play();
                    break;
                case 1:
//                    System.out.println("blackjack coming soon");
                    Blackjack blackjack = new Blackjack("Play blackjack", 3);
                    blackjack.play();
                    break;
                case 2:
                    System.out.println("Old Maid coming soon");
                    // Add your oldmaid logic
                default:
                    System.out.println("Invalid choice!");
                    break;
            }


    }

}