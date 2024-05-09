package org.example.game;
import org.example.card.Card;
import org.example.deck.Deck;
import org.example.user.UserInteraction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class OldMaid extends Game {
    private Deck deck = new Deck();
    private Card oldMaid;
    private UserInteraction computer = new UserInteraction();
    private UserInteraction playerOne = new UserInteraction();
    private boolean computersTurn = false;

    public OldMaid() {
        super("Old Maid", "Take turns to select a card from the opponent's hand." + "\nIf you have a card with a matching value in your hand, that pair of cards will be removed from your hand." + "\nBe the first to pair off all your cards to win." + "\nIf you are left with the odd card out (old maid) at the end, you lose the game. Good luck." );

        printWelcomeMessage();
    }

    public void printWelcomeMessage() {
        System.out.println("\n" + "====================");
        System.out.print("Let's play ");
        printTitle();
        System.out.println("====================" + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        printRules();
        System.out.println();
        System.out.println("====================");
    }

    public void setupCards() {
        deck.createFullDeck();
        deck.shuffleDeck();
        oldMaid = deck.dealCard();
        dealCards();
        removeAllPairs(computer);
        removeAllPairs(playerOne);
        computer.shuffleHand();
        playerOne.shuffleHand();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n" + "Let's start! The cards have been dealt and the pairs removed." + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        printNumberOfCardsInEachHand();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("====================");
        System.out.println("Old maid is: [CARD]");
        System.out.println("====================");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        printPlayerHands();
    }

    public void dealCards() {
        int numberOfCardsInDeck = deck.getCards().size();
        for (int i = 0; i < numberOfCardsInDeck; i++) {
            if(i % 2 == 0) {
                computer.drawACard(deck.dealCard());
            } else {
                playerOne.drawACard(deck.dealCard());
            }
        }
    }

    public void removeAllPairs(UserInteraction player) {
        player.sortHand();
        int i = 0;
        while(i < player.getHand().size()-1) {
            if(player.getHand().get(i).getSymbol().equals(player.getHand().get(i+1).getSymbol())) {
                player.removeCardByIndex(i);
                player.removeCardByIndex(i);
                i = Math.max(0, (i - 1));
            } else {
                i++;
            }
        }
    }

    @Override
    public void play() {
        setupCards();
        while(!computer.getHand().isEmpty() && !playerOne.getHand().isEmpty()) {
            handleGameLogic();
        }
        checkForWin();
        boolean playAgain = playAgain();

        if (playAgain) {
            reset();
            play();
        } else {
            System.out.println("Thanks for playing! Goodbye.");
        }
    }

    public void handleGameLogic() {
        if(computersTurn) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Computer's turn:");
            System.out.println();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            getComputerChoice();
            boolean isThereAMatch = checkPlayersHandForPair(computer);

            if(!isThereAMatch) {
                System.out.println("No match found. Shuffling cards…");
                computer.shuffleHand();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (!computer.getHand().isEmpty() && !playerOne.getHand().isEmpty()) {
                printPlayerHands();
                System.out.println();
                computersTurn = false;
            }
        } else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int chosenIndex = getPlayerOnesChoice(computer.getHand().size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("You chose to take card " + chosenIndex + "\n");

            Card chosenCard = computer.getHand().get(chosenIndex-1);
            playerOne.drawACard(chosenCard);
            computer.removeCardByIndex(chosenIndex-1);
            boolean isThereAMatch = checkPlayersHandForPair(playerOne);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(!isThereAMatch) {
                System.out.println("No match found. Shuffling cards…");
                playerOne.shuffleHand();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!computer.getHand().isEmpty() && !playerOne.getHand().isEmpty()) {
                printPlayerHands();
                System.out.println();
                computersTurn = true;
            }
        }
    }

    public ArrayList<String> getComputersDisplayHand() {

        ArrayList<String> computersHand = new ArrayList<>();

        if(playerOne.getHand().isEmpty()) {
            for (int i = 0; i < computer.getHand().size(); i++) {
                computersHand.add("[" + computer.getHand().get(i).getSuit().getSuitSymbol() + " " +  computer.getHand().get(i).getSymbol() + "]");
            }
        } else {
            for (int i = 0; i < computer.getHand().size(); i++) {
                computersHand.add("[CARD]");
            }
        }
        return computersHand;
    }

    public ArrayList<String> getPlayerOnesDisplayHand() {
        ArrayList<String> playerOnesHand = new ArrayList<>();

        for (int i = 0; i < playerOne.getHand().size(); i++) {
            playerOnesHand.add("[" + playerOne.getHand().get(i).getSuit().getSuitSymbol() + " " +  playerOne.getHand().get(i).getSymbol() + "]");
        }
        return playerOnesHand;
    }

    public void printPlayerHands() {
        ArrayList<String> computersHand = getComputersDisplayHand();
        ArrayList<String> playerOnesHand = getPlayerOnesDisplayHand();
        System.out.println();
        System.out.println("Computer's hand ("  + computersHand.size() + "):" + "\n" + computersHand);
        System.out.println();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Player One's hand ("  + playerOne.getHand().size() + "):" + "\n" + playerOnesHand);
        System.out.println();
        System.out.println("====================");
    }

    public void printNumberOfCardsInEachHand() {
        System.out.println("Computer has " +  computer.getHand().size() + " cards remaining");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Player One has " +  playerOne.getHand().size() + " cards remaining");
        System.out.println();
    }

    public int getPlayerOnesChoice(int currentSizeOfHand) {
        List<String> acceptedInputs = new ArrayList<>();

        for (int i = 1; i < currentSizeOfHand+1; i++) {
            acceptedInputs.add(String.valueOf(i));
        }

        System.out.println("Player One, it's your turn.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\nComputer has " + currentSizeOfHand + " cards remaining - please choose a card number between 1 and " + currentSizeOfHand + " to add to your hand.");

        Scanner scanner = new Scanner(System.in);

        String userInput = scanner.nextLine().trim();

        while (!acceptedInputs.contains(userInput)) {
            System.out.println("Not a valid option. Please choose a card number between 1 and " + currentSizeOfHand + " to add to your hand.");
            userInput = scanner.nextLine().trim();
        }
        return Integer.parseInt(userInput);
    }

    public void getComputerChoice() {
        Random random = new Random();
        int randomIndex = random.nextInt(playerOne.getHand().size());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Computer chose to take card " + (randomIndex+1) + " from Player One's hand" + "\n");
        Card chosenCard = playerOne.getHand().get(randomIndex);
        computer.drawACard(chosenCard);
        playerOne.removeCardByIndex(randomIndex);
    }

    public boolean checkPlayersHandForPair(UserInteraction player) {
        Card cardToCheck = player.getHand().get(player.getHand().size()-1);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Card to check: [" + cardToCheck.getSuit().getSuitSymbol() + " " + cardToCheck.getSymbol() + "]" + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < player.getHand().size()-1; i++) {
            if(cardToCheck.getSymbol().equals(player.getHand().get(i).getSymbol())) {

                System.out.println("There's a MATCH. Pair removed from hand.");
                player.removeCardByIndex(i);
                player.removeCardByIndex(player.getHand().size()-1);
                return true;
            }
        }
        return false;
    }

    public void checkForWin() {
        if(computer.getHand().isEmpty()) {
            System.out.println( "\n" + "====================");
            System.out.println("\n" + "OH NO, THE COMPUTER MATCHED ALL THEIR CARDS. GAME OVER.");
            System.out.println("THE OLD MAID WAS: " + "["+oldMaid+"]");
            System.out.println("\n" + "====================");
        } else {
            System.out.println( "\n" + "====================");
            System.out.println("\n" + "WELL DONE, YOU'VE MATCHED UP ALL YOUR CARDS. YOU WIN!");
            System.out.println("THE OLD MAID WAS: " + "["+oldMaid+"]");
            System.out.println("\n" + "====================");
        }
        printPlayerHands();
    }

    @Override
    public boolean playAgain() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n" + "Play again? Y/N");
        String answer = scanner.nextLine().trim().toUpperCase();

        while (answer.isEmpty()) {
            answer = scanner.nextLine().trim().toUpperCase();
        }

        while (!answer.equals("Y") && !answer.equals("N")) {
            answer = scanner.nextLine().trim().toUpperCase();
        }
        return answer.equals("Y");
    }

    public void reset() {
        deck.clearDeck();
        computer = new UserInteraction();
        playerOne = new UserInteraction();
        computersTurn = false;
    }

}