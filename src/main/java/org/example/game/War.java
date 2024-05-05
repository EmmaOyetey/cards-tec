package org.example.game;

import org.example.card.Card;
import org.example.deck.Deck;
import org.example.user.UserInteraction;

import java.util.ArrayList;

public class War {
    private final Deck warDeck;
    private final UserInteraction bill;
    private final UserInteraction playerOne;
    private final ArrayList<Card> cardsInPlay;

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

    private void dealInitialHands() {
        // Deal cards to players alternately until the deck is empty
        while (true) {
            Card cardForBill = warDeck.dealCard(bill);
            if (cardForBill == null) break; // Deck is empty
            bill.drawACard(cardForBill);

            Card cardForPlayerOne = warDeck.dealCard(playerOne);
            if (cardForPlayerOne == null) break; // Deck is empty
            playerOne.drawACard(cardForPlayerOne);
        }
    }

    public void play() {
        // Print players' hands before the game starts
        System.out.println("Bill's hand: " + bill.getHand());
        System.out.println("Player One's hand: " + playerOne.getHand());

        // Main game loop
        while (!bill.getHand().isEmpty() && !playerOne.getHand().isEmpty()) {
            // Remove the last card from Bill's hand and play it
            Card cardPlayedByBill = bill.getHand().remove(bill.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByBill);
            System.out.println("cards in play" + cardsInPlay);

            // Remove the last card from Player One's hand and play it
            Card cardPlayedByPlayerOne = playerOne.getHand().remove(playerOne.getHand().size() - 1);
            cardsInPlay.add(cardPlayedByPlayerOne);
            System.out.println("cards in play" + cardsInPlay);

            // Compare the cards played by Bill and Player One
            int comparison = cardPlayedByBill.getValue() - cardPlayedByPlayerOne.getValue();

            // Handle the game outcome
            if (comparison > 0) {
                addCardsToHand(bill, cardPlayedByBill, cardPlayedByPlayerOne);
                System.out.println("bill wins add to his deck" + bill.getHand());
            } else if (comparison < 0) {
                addCardsToHand(playerOne, cardPlayedByBill, cardPlayedByPlayerOne);
                System.out.println("player1 wins add to his deck" + playerOne.getHand());
            } else {
                // War scenario
                System.out.println("War");

                // Add 4 cards from each player's hand to cardsInPlay
                for (int i = 0; i < 4; i++) {
                    cardsInPlay.add(bill.getHand().remove(bill.getHand().size() - 1)); // Add card from Bill's hand
                }
                for (int i = 0; i < 4; i++) {
                    cardsInPlay.add(playerOne.getHand().remove(playerOne.getHand().size() - 1)); // Add card from Player One's hand
                }
                System.out.println(cardsInPlay);
                // Compare the last card with the fifth-to-last card
                int lastIndex = cardsInPlay.size() - 1;
                int fifthToLastIndex = lastIndex - 4;
                Card lastCard = cardsInPlay.get(lastIndex);
                Card fifthToLastCard = cardsInPlay.get(fifthToLastIndex);
                int warComparison = lastCard.getValue() - fifthToLastCard.getValue();
                System.out.println("lastCard" + lastCard + "fifthLastCard" + fifthToLastCard);
                // Handle the outcome of the war
                if (warComparison > 0) {
                    addCardsToHand(bill);
                    System.out.println("bill wins cards" + bill.getHand());
                } else {
                    addCardsToHand(playerOne);
                    System.out.println("player! wins cards" + playerOne.getHand());
                }
            }

            // Print players' hands after each round
            System.out.println("Bill's hand: " + bill.getHand());
            System.out.println("Player One's hand: " + playerOne.getHand());
        }

        // Determine the winner
        if (bill.getHand().isEmpty()) {
            System.out.println("Player One wins!");
        } else {
            System.out.println("Bill wins!");
        }
    }

    private void addCardsToHand(UserInteraction user, Card... cards) {
        for (Card card : cards) {
            user.getHand().add(0, card); // Add cards to the start of the hand
        }
        cardsInPlay.clear();
    }

    public static void main(String[] args) {
        War warGame = new War();
        warGame.play();
    }
}
//public class WarCardGame {
//    // Declare instances for bill and player
//    private UserInteraction billInteraction;
//    private UserInteraction playerInteraction;
//
//    // Constructor to initialize the game
//    public WarCardGame() {
//        // Create instances of UserInteraction for bill and player
//        billInteraction = new UserInteraction();
//        playerInteraction = new UserInteraction();
//
//        // Create a new deck and shuffle it
//        Deck warDeck = new Deck();
//        warDeck.createFullDeck();
//        warDeck.shuffleDeck();
//
//        // Deal initial cards to bill and player
//        for (int i = 0; i < 5; i++) {
//            billInteraction.drawACard(warDeck.dealCard(billInteraction));
//            playerInteraction.drawACard(warDeck.dealCard(playerInteraction));
//        }
//    }
//
//    // Method to display the hand for each player
//    public void displayHands() {
//        System.out.println("Bill's hand: " + billInteraction.getHand());
//        System.out.println("Player's hand: " + playerInteraction.getHand());
//    }
//
//    // Main method to run the game
//    public static void main(String[] args) {
//        WarCardGame warGame = new WarCardGame();
//        warGame.displayHands(); // Display hands after dealing initial cards
//        // Add game logic or interactions here...
//    }
//}
//
//




    // Add other game logic methods here...

//    // Main method to run the game
//    public static void main(String[] args) {
//        WarCardGame warGame = new WarCardGame();
//        // Add game logic or interactions here...
//    }


// public void play() {
//
//     // Draw a card for Bill
//
//     userInteraction.drawACard(warDeck.drawCard(User bill));
//
//     // Draw a card for Player
//
//     userInteraction.drawACard(player, warDeck.drawCard(User player));
//
//     // Display hands after drawing
//     System.out.println("Bill's hand: " + bill.getHand());
//     System.out.println("Player's hand: " + player.getHand());
// }

//     Card card;
//     while ((card = warDeck.dealCard()) != null) {
//         bill.drawCard(card);
//         player.draw
//         //                }
//         //            }
//
// }
//}
//                        //deal cards in deck to deck. full deck to be dealt between the 2 users

            //            dealCardsFromDeckToHand(deck, userInteraction);
            //
            //            private static void dealCardsFromDeckToHand(Deck warDeck, Card card,  UserInterction  user) {
            //                Card card;
            //                while ((card = warDeck.dealCard()) != null) {
            //                    bill.drawACard(card);
            //                }
            //            }
            //
            //            commandLine.displayCorrectLetters(handleGuess.getCorrectLetters());
            //

            //            // Use methods from UserInteraction
            //            userInteraction.shuffleHand();
            //            userInteraction.sortHand();
            //            userInteraction.drawACard(deck.dealCard(bill));
            //
            //            // Example usage of other methods
            //            Card cardToRemove = userInteraction.removeFromHand(Suit.HEART, 10).orElse(null);
            //            if (cardToRemove != null) {
            //                // Do something with the removed card
            //            }



