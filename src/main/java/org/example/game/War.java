package org.example.game;

import org.example.card.Card;
import org.example.deck.Deck;
import org.example.user.UserInteraction;

import java.util.ArrayList;

public class War {
    private Deck warDeck;
    private UserInteraction bill;
    private UserInteraction playerOne;
    private ArrayList<Card> cardsInPlay;

    public War() {
        super();
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

  //  @Override
    public void play() {
        // print players hands to check
        System.out.println("Bill's hand: " + bill.getHand());
        System.out.println("Player One's hand: " + playerOne.getHand());


// Remove the last card from Bill's hand and play it
        Card cardPlayedByBill = bill.getHand().remove(bill.getHand().size() - 1);
        cardsInPlay.add(cardPlayedByBill);
        System.out.println("card played by bill" + cardPlayedByBill);
        System.out.println("cards in play" + cardsInPlay);

// Remove the last card from Player One's hand and play it
        Card cardPlayedByPlayerOne = playerOne.getHand().remove(playerOne.getHand().size() - 1);
        cardsInPlay.add(cardPlayedByPlayerOne);
        System.out.println("card played by playerone " + cardPlayedByPlayerOne);
        System.out.println("cards in play " + cardsInPlay);

// Compare the cards played by Bill and Player One
        int comparison = cardPlayedByBill.getValue() - cardPlayedByPlayerOne.getValue();

// If Bill's card has a higher value, add both cards to start of Bill's hand
        if (comparison > 0) {
            bill.getHand().add(0, cardPlayedByBill);
            bill.getHand().add(0, cardPlayedByPlayerOne);
            System.out.println( "bills hand" + bill.getHand());
            System.out.println("playerOne hand " + playerOne.getHand());
        } else if (comparison < 0) {
            // If Player One's card has a higher value, add both cards to Player One's hand
            playerOne.getHand().add(0, cardPlayedByBill);
            playerOne.getHand().add(0, cardPlayedByPlayerOne);
            System.out.println("bills hand " + bill.getHand());
            System.out.println("playerone hand" + playerOne.getHand());
        } else {
            // Handle the case where the cards have equal value (a "war" scenario)
            // This logic depends on how you want to handle ties in the game
            System.out.println("War");
        }
    }

//    @Override
//    public boolean playAgain() {
//        // Implement whether the game should be played again
//        // For simplicity, let's just return false here
//        return false;
//    }

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



