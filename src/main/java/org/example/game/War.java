package org.example.game;

import org.example.card.Card;
import org.example.deck.Deck;
import org.example.user.UserInteraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class War {
    private final Deck warDeck;
    private final UserInteraction bill;
    private final UserInteraction playerOne;
    private final ArrayList<Card> cardsInPlay;
    private Card cardPlayedByBill;
    private Card cardPlayedByPlayerOne;


    public War() {
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

    public void play() {
        // Print players' hands before the game starts
        System.out.println("Welcome to War. You are playing against Bill");
        System.out.println("Hey i'm Bill. Lets War!...");

        dealInitialHands();

        // Main game loop
        while (!bill.getHand().isEmpty() && !playerOne.getHand().isEmpty()) {

            //System.out.println("I've played");
            Card cardPlayedByBill = bill.getHand().remove(bill.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByBill);
            System.out.println("Hit return when you are ready for me to play my card");
            //printCardsInPlay();
            playCard(this::printCardsInPlay);

            System.out.println("Your turn. Hit return when you are ready to play your card");
            Card cardPlayedByPlayerOne = playerOne.getHand().remove(playerOne.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByPlayerOne);
            playCard(this::printCardsInPlay);

            // Compare the cards played by Bill and Player One
            int comparison = cardPlayedByBill.getValue() - cardPlayedByPlayerOne.getValue();

            // Handle the game outcome
            if (comparison > 0) {
                Collections.shuffle(cardsInPlay);
                addCardsToHand(bill);
                System.out.println("I win! I'll take those cards!");
                } else if (comparison < 0) {
                    Collections.shuffle(cardsInPlay);
                    addCardsToHand(playerOne);
                    System.out.println("You win! i've added them to your pile");
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
    }

    // Remove the last card from Bill's hand and play it
    // Remove the last card from Player One's hand and play it

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



private void war(){
    System.out.println("It's WAR!");
    if (bill.getHand().size() < 4) {
        System.out.println("You Win! I don't have enough cards for a war.");
        return; // End the game
    }
    if (playerOne.getHand().size() < 4) {
        System.out.println("I win! You don't have enough cards for a war.");
        return; // End the game
    }
    // Add 4 cards from each player's hand to cardsInPlay
    for (int i = 0; i < 4; i++) {
        cardsInPlay.add(bill.getHand().remove(bill.getHand().size() - 1)); // Add card from Bill's hand
    }
    for (int i = 0; i < 4; i++) {
        cardsInPlay.add(playerOne.getHand().remove(playerOne.getHand().size() - 1)); // Add card from Player One's hand
    }


    System.out.println("Hit return when you are ready for us to play four cards each");
    playCard(this::printCardsInPlay);

    // Compare the last card with the fifth-to-last card

    int lastIndex = cardsInPlay.size() - 1;
    int fifthToLastIndex = lastIndex - 4;
    Card lastCard = cardsInPlay.get(lastIndex);
    Card fifthToLastCard = cardsInPlay.get(fifthToLastIndex);

    int warComparison = lastCard.getValue() - fifthToLastCard.getValue();
    System.out.println("lastCard" + lastCard + "fifthLastCard" + fifthToLastCard);
    // Handle the outcome of the war
    if (warComparison > 0) {
        addCardsToHand(playerOne);
        System.out.println("You won the WAR and collect all the cards" );
    } else if (warComparison < 0) {
        addCardsToHand(bill);
        System.out.println("I won the WAR! I collect all the cards");
    } else {
        war();
    }
}

private void printCard(Card card) {
    String[] lines = cardToAscii(card).split("\n");
    for (String line : lines) {
        System.out.println(line);
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

        // Print the last card and the fifth-to-last card
        printCard(fifthToLastCard);
        printCard(lastCard);
    }
}

private String cardToAscii(Card card) {
    // Example ASCII representation for a card
    return " _________ \n" +
            "|" + card.getSymbol() + "        |\n" +
            "|" + card.getSuit().getSuitSymbol() + "        |\n" +
            "|         |\n" +
            "|        " + card.getSuit().getSuitSymbol() + "|\n" +
            "|        " + card.getSymbol() + "|\n" +
            " --------- \n";
}


    public static void playCard(Runnable printCardsInPlay) {
        Scanner scanner = new Scanner(System.in);

        //System.out.println("Hit Enter to play...");
        String inputLine = scanner.nextLine(); // Read the input line

        if (!inputLine.isEmpty()) {
            System.out.println("Invalid input. Press Enter to trigger the action.");
        } else {
            printCardsInPlay.run();
        }
    }



    //to pass a method reference as a parameter, you can use a functional interface like Runnable or Consumer.
    //This allows you to pass a method reference or lambda expression that matches
    // the Runnable interface to the playCard method,
    // //and it will execute that code when Enter or Space is pressed.
    //the playCard method above now enables the printCardsInPlay method to be passed as a parameter
    // and be called  when needed. To call it use
    // playCard(this::printCardsInPlay) or with a lambda expression
    // playCard(() -> printCardsInPlay()) where you want to trigger it.


    public static void main(String[] args) {
        War warGame = new War();
        warGame.play();
    }



}