package org.example.game;

import org.example.card.Card;
import org.example.deck.Deck;
import org.example.user.UserInteraction;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.example.utilities.Utilities.*;

public class War extends Game {
    private final Deck warDeck;
    private final UserInteraction bill;
    private final UserInteraction playerOne;
    private final ArrayList<Card> cardsInPlay;

    public War() {
        super("War", "In this 2 player game, a player competes against 'Bill' (the computer) to win all the cards. "
                + "\nEach player starts with half the deck. "
                + "\nA turn consists of each player laying a card up "
                + "\nThe 2 cards in play are compared. "
                + "\nThe person who played the highest value card, of the 2 in play, wins the cards, "
                + "\nand adds them to the bottom of their pile. "
                + "\nIf the cards played have equal value, a war ensues! "
                + "\nDuring a war, each player places an additional 4 cards on top of the initial card they played. "
                + "\nThe highest of these cards is again compared to determine the winner"
                + "\nNote - a war can lead to another war"
                + "\nPlay continues until one player has won all cards and is the winner");
        // Initialize a new deck
        warDeck = new Deck();
        warDeck.createFullDeck();
        warDeck.shuffleDeck();

        // Initialize hands for players
        bill = new UserInteraction();
        playerOne = new UserInteraction();
        cardsInPlay = new ArrayList<>();

        // Deal cards to players
        dealInitialHands();
    }

    @Override
    public void play() {

        // Print players' hands before the game starts
        printTitle();
        printRules();

        System.out.println("--------------------------");
        System.out.println("Hey i'm Bill. Lets War!...");

        // Main game loop
        while (!bill.getHand().isEmpty() && !playerOne.getHand().isEmpty()) {

            Card cardPlayedByBill = bill.getHand().remove(bill.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByBill);
            System.out.println("Hit return when you are ready for me to play my card");
            displayCards(this::printCardsInPlay);

            System.out.println("Your turn. Hit return when you are ready to play your card");
            Card cardPlayedByPlayerOne = playerOne.getHand().remove(playerOne.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByPlayerOne);
            displayCards(this::printCardsInPlay);

            // Compare the cards played by Bill and Player One
            int comparison = cardPlayedByBill.getValue() - cardPlayedByPlayerOne.getValue();

            // Handle the game outcome
            if (comparison > 0) {
                Collections.shuffle(cardsInPlay);
                addCardsToHand(bill);
                System.out.println("I win! I'll take those cards!");
                System.out.println("Cards in my hand: " + bill.getHand().size() + " Cards in your hand : " + playerOne.getHand().size());
            } else if (comparison < 0) {
                Collections.shuffle(cardsInPlay);
                addCardsToHand(playerOne);
                System.out.println("You win! i've added them to your pile");
                System.out.println("Cards in my hand: " + bill.getHand().size() + " Cards in your hand : " + playerOne.getHand().size());
            } else {
                war();
            }

        }
        // Determine the winner
        if (bill.getHand().isEmpty()) {
            System.out.println("Boom you win!");
        } else {
            System.out.println("I've Won");
        }

        if (playAgain()) {
            play(); // Restart the game if the user wants to play again
        } else {
            System.out.println("Thanks for playing!");
            //add other game logic - reshow games options...
        }
    }

    private void dealInitialHands() {

        // Deal cards to players alternately until their hands are full
        while (bill.getHand().size() < 26 && playerOne.getHand().size() < 26) {

            Card cardForBill = warDeck.dealCard(bill);
            if (cardForBill == null) break; // Deck is empty
            bill.drawACard(cardForBill);

            Card cardForPlayerOne = warDeck.dealCard(playerOne);
            if (cardForPlayerOne == null) break; // Deck is empty
            playerOne.drawACard(cardForPlayerOne);
        }
    }

    private void addCardsToHand(UserInteraction user) {
        for (Card card : cardsInPlay) {
            user.getHand().add(0, card); // Add cards to the start of the hand
        }
        cardsInPlay.clear();
    }

    private void war() {
        System.out.println("It's WAR!");
        if (bill.getHand().size() < 4) {
            System.out.println("You Win! I don't have enough cards for a war.");
            return;
        }
        if (playerOne.getHand().size() < 4) {
            System.out.println("I win! You don't have enough cards for a war.");
            return;
        }
        // Add 4 cards from each player's hand to cardsInPlay
        for (int i = 0; i < 4; i++) {
            cardsInPlay.add(bill.getHand().remove(bill.getHand().size() - 1)); // Add card from Bill's hand
        }
        for (int i = 0; i < 4; i++) {
            cardsInPlay.add(playerOne.getHand().remove(playerOne.getHand().size() - 1)); // Add card from Player One's hand
        }

        System.out.println("Hit return when you are ready for us to play four cards each");
        displayCards(this::printCardsInPlay);

        // Compare the last card with the fifth-to-last card

        int lastIndex = cardsInPlay.size() - 1;
        int fifthToLastIndex = lastIndex - 4;
        Card lastCard = cardsInPlay.get(lastIndex);
        Card fifthToLastCard = cardsInPlay.get(fifthToLastIndex);

        int warComparison = lastCard.getValue() - fifthToLastCard.getValue();
        //System.out.println("lastCard" + lastCard + "fifthLastCard" + fifthToLastCard);
        // Handle the outcome of the war
        if (warComparison > 0) {
            addCardsToHand(playerOne);
            System.out.println("You won the WAR and collect all the cards");
            System.out.println("Cards in my hand: " + bill.getHand().size() + " , Cards in your hand : " + playerOne.getHand().size());
        } else if (warComparison < 0) {
            addCardsToHand(bill);
            System.out.println("I won the WAR! I collect all the cards");
            System.out.println("Cards in my hand: " + bill.getHand().size() + " , Cards in your hand : " + playerOne.getHand().size());
        } else {
            war();
        }
    }


    private void printCardsInPlay() {
        int numCardsInPlay = cardsInPlay.size();

        if (cardsInPlay.size() == 1) {
            printCard(cardsInPlay.get(0));
        } else if (cardsInPlay.size() == 2) {
            Card firstCard = cardsInPlay.get(0);
            Card secondCard = cardsInPlay.get(1);
            String[] firstCardLines = cardToAscii(firstCard).split("\n");
            String[] secondCardLines = cardToAscii(secondCard).split("\n");
            for (int i = 0; i < firstCardLines.length; i++) {
                System.out.println(firstCardLines[i] + "    " + secondCardLines[i]);
            }
        } else {
            // More than two cards in play
            Card lastCard = cardsInPlay.get(numCardsInPlay - 1);
            Card fifthToLastCard = cardsInPlay.get(numCardsInPlay - 5);
            String[] firstCardLines = cardToAscii(fifthToLastCard).split("\n");
            String[] secondCardLines = cardToAscii(lastCard).split("\n");
            for (int i = 0; i < firstCardLines.length; i++) {
                System.out.println(firstCardLines[i] + "    " + secondCardLines[i]);
            }
        }
    }


    @Override
    public boolean playAgain() {
        System.out.println("Do you want to play again? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String playAgain = scanner.nextLine();
        if (playAgain.equalsIgnoreCase("yes")) {
            return true; // Return true if the user wants to play again
        } else {
            System.out.println("Thanks for playing!");
            return false; // Return false if the user doesn't want to play again
        }
    }

    //for testing
    // Getters and setters for player hands
    public UserInteraction getBill() {
        return bill;
    }

    public UserInteraction getPlayerOne() {
        return playerOne;
    }

    public List<Card> getCardsInPlay() {
        return cardsInPlay;
    }


//The equalsIgnoreCase() method is a Java method used to compare two strings while ignoring their case differences.
    //can we define method playagain in game and pass the game name as a parameter
    //will need handling in main...


}

//to pass a method reference as a parameter, you can use a functional interface like Runnable or Consumer.
//This allows you to pass a method reference or lambda expression that matches
// the Runnable interface to the playCard method,
// //and it will execute that code when Enter or Space is pressed.
//the playCard method above now enables the printCardsInPlay method to be passed as a parameter
// and be called  when needed. To call it use
// playCard(this::printCardsInPlay) or with a lambda expression
// playCard(() -> printCardsInPlay()) where you want to trigger it.