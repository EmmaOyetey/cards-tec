package org.example.game;

import org.example.card.Card;
import org.example.deck.Deck;
import org.example.user.UserInteraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import static org.example.utilities.Utilities.*;

public class War extends Game {
    private final Deck warDeck;
    private final UserInteraction bill;
    private final UserInteraction playerOne;
    private final ArrayList<Card> cardsInPlay;
    private boolean isNoWinner= true;

    public War() {
        super("War", "In this 2 player game, a player competes against 'Bill' (the computer) to win all the cards. "
                + "\nEach player starts with half the deck. "
                + "\nA turn consists of each player placing one card up "
                + "\nThe 2 cards in play are compared. "
                + "\nThe person who played the highest value card, of the 2 in play, wins the cards, "
                + "\nThey add them to the bottom of their pile. "
                + "\nIf the cards played have equal value, a war ensues! "
                + "\nDuring a war, each player places an additional 4 cards on top of the initial card they played. "
                + "\nThe highest of these cards is again compared to determine the winner"
                + "\nNote - a war can lead to another war"
                + "\nPlay continues until one player has won all cards, or does not have enough cards to war, and is the winner");
        // Initialize a new deck
        this.warDeck = new Deck();
        warDeck.createDemoDeck();//  warDeck.createFullDeck();
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

        printTitle();

        System.out.println("--------------------------");
        System.out.println("Hey i'm Bill. Lets War!...");

        while (isNoWinner) {

            if (bill.getHand().isEmpty() || playerOne.getHand().isEmpty()) {
                isNoWinner = false;
                break;
            }

            Card cardPlayedByBill = bill.getHand().remove(bill.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByBill);
            System.out.println("Hit return when you are ready for me to play my card");
            displayCards(this::printCardsInPlay);

            if (playerOne.getHand().isEmpty()) {
                isNoWinner = false;
                break;
            }

            System.out.println("Your turn. Hit return when you are ready to play your card");
            Card cardPlayedByPlayerOne = playerOne.getHand().remove(playerOne.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByPlayerOne);
            displayCards(this::printCardsInPlay);

            int comparison = cardPlayedByBill.getValue() - cardPlayedByPlayerOne.getValue();

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

        if (!isNoWinner) {
            if (playerOne.getHand().size() < bill.getHand().size()) {
                System.out.println("Ahhh better luck next time! I win!");
            } else {
                System.out.println("Boom you win!");
            }
        }

    }
    private void dealInitialHands() {

        while (bill.getHand().size() < 26 && playerOne.getHand().size() < 26) {

            Card cardForBill = warDeck.dealCard();
            if (cardForBill == null) break; // Deck is empty
            bill.drawACard(cardForBill);

            Card cardForPlayerOne = warDeck.dealCard();
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

        if ((bill.getHand().size()<4) || (playerOne.getHand().size()< 4)) {
            isNoWinner = false;
            if (bill.getHand().size() < 4) {
                System.out.println("I don't have enough cards for a war.");
            } else {
                System.out.println("You don't have enough cards for a war");
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            cardsInPlay.add(bill.getHand().remove(bill.getHand().size() - 1)); // Add card from Bill's hand
        }
        for (int i = 0; i < 4; i++) {
            cardsInPlay.add(playerOne.getHand().remove(playerOne.getHand().size() - 1)); // Add card from Player One's hand
        }

        System.out.println("Hit return when you are ready for us to play four cards each");
        displayCards(this::printCardsInPlay);


        int lastIndex = cardsInPlay.size() - 1;
        int fifthToLastIndex = lastIndex - 4;
        Card lastCard = cardsInPlay.get(lastIndex);
        Card fifthToLastCard = cardsInPlay.get(fifthToLastIndex);

        int warComparison = lastCard.getValue() - fifthToLastCard.getValue();

        if (warComparison > 0) {
            addCardsToHand(playerOne);
            System.out.println("You won the WAR and collect all the cards");
            System.out.println("Cards in my hand: " + bill.getHand().size() + " Cards in your hand : " + playerOne.getHand().size());
        } else if (warComparison < 0) {
            addCardsToHand(bill);
            System.out.println("I won the WAR! I collect all the cards");
            System.out.println("Cards in my hand: " + bill.getHand().size() + " Cards in your hand : " + playerOne.getHand().size());
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
        return playAgain.equalsIgnoreCase("yes");
    }
}

