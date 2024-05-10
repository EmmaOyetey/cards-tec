package org.example.game;

import org.example.card.Card;
import org.example.commands.Commands;
import org.example.deck.BlackJackDeck;
import org.example.deck.Deck;
import org.example.user.UserInteraction;

import java.util.*;
import java.util.stream.Collectors;

import static org.example.utilities.Utilities.cardToAscii;
import static org.example.utilities.Utilities.printCard;

public class Blackjack extends Game {
    private Deck deck;
    private List<UserInteraction> users = new ArrayList<>();
    private int playerCount;
    private int playerTurn = 0;
    private boolean gameOver = false;
    private Commands commands = new Commands();
    private static final List<String> playerChoices = Arrays.asList("Hit", "Stay");
    private List<Integer> playerScores = new ArrayList<>();
    private List<Boolean> playerFinished = new ArrayList<>();

    public Blackjack() {
        // TODO: Iron out ruleset, add to gameLoader
        super("Blackjack",
                "\n\nYou and fellow players are initially dealt two cards each." +
                        "\nYour score is the total value of cards in your hand." +
                        "\nThe highest score you can achieve is 21 (an Ace and a card valued 10),that’s Blackjack !" +
                        "\nIf you have a total at or below 21, decide whether you wish to ‘draw’or ‘stay’." +
                        "\nYou can continue to draw cards until you are happy with your hand." +
                        "\nThe winner is the player with the highest score !"
        );
        int playerCount = 0;
        while (!(playerCount > 1)) {
            playerCount = commands.getUserInteger("Please choose the number of players (must be more than 1)");
        }
        this.playerCount = playerCount;
        for (int i = 0; i < playerCount; i++) {
            users.add(new UserInteraction());
            playerScores.add(0);
            playerFinished.add(false);
        }

        BlackJackDeck freshDeck = new BlackJackDeck();
        freshDeck.createFullDeck();
        freshDeck.shuffleDeck();
        this.deck = freshDeck;
    }

    private void dealToAllPlayers() {
        users.stream().forEach(user -> {
            user.removeALLCards();
            user.drawACard(deck.dealCard());
            user.drawACard(deck.dealCard());
        });
    }

    public void play() {
        System.out.println("\n\nWelcome to ");
        printTitle();
        dealToAllPlayers();

        while (!gameOver) {
            if (playerFinished.get(playerTurn)) {
                System.out.println("\n\nPlayer " + (playerTurn + 1) + " is done for this round");
                playerTurn = (playerTurn + 1) % playerCount;
                continue;
            }

            UserInteraction currentPlayer = users.get(playerTurn);
            System.out.println("\n\nPlayer " + (playerTurn + 1) + "'s turn:");

            ArrayList<Card> playerHand = currentPlayer.getHand();
            playerScores.set(playerTurn, playerHand.stream().reduce(0, (subtotal, card) -> subtotal + card.getValue(), Integer::sum));

            displayCards(playerHand);
            System.out.println("Current score: " + playerScores.get(playerTurn));

            int choice = commands.displayChoices(playerChoices);
            System.out.println("You chose: " + playerChoices.get(choice));

            if (choice == 1) {
                playerFinished.set(playerTurn, true);
            } else {
                currentPlayer.drawACard(deck.dealCard());
                playerHand = currentPlayer.getHand();
                displayCards(playerHand);
                int newScore = playerHand.stream().reduce(0, (subtotal, card) -> subtotal + card.getValue(), Integer::sum);
                playerScores.set(playerTurn, newScore);
                System.out.println("Your score is now: " + newScore);
                if (newScore > 21) {
                    System.out.println("You went over 21 :(");
                    playerFinished.set(playerTurn, true);
                }
            }

            gameOver = playerFinished.stream().reduce(true, (allPlayersFinished, playerFinish) -> allPlayersFinished && playerFinish, Boolean::logicalAnd);
            playerTurn = (playerTurn + 1) % playerCount;
        }

        resolveGameOutcome();
    }

    private void displayCards(List<Card> cardHand) {
        if (cardHand.size() == 1) {
            printCard(cardHand.get(0));
        } else if (cardHand.size() > 1) {
            List<String[]> deconstructedDisplayLines = new ArrayList<>();
            for (Card card : cardHand) {
                String[] cardLines = cardToAscii(card).split("\n");
                deconstructedDisplayLines.add(cardLines);
            }

            for (int i = 0; i < deconstructedDisplayLines.get(0).length; i++) {
                StringBuilder displayLine = new StringBuilder();
                for (String[] deconstructedDisplayLine : deconstructedDisplayLines) {
                    displayLine.append(deconstructedDisplayLine[i]);
                }
                System.out.println(displayLine);
            }
        }
    }

    private List<Integer> getDrawnPlayersIndexes(List<Integer> playersWhoScoredUnder21) {
        Integer maxScore = playersWhoScoredUnder21.stream().max(Integer::compare).orElse(null);
        List<Integer> indexesOfDrawnPlayers = new ArrayList<>();
        for (int i = 0; i < playersWhoScoredUnder21.size(); i++) {
            if (Objects.equals(playersWhoScoredUnder21.get(i), maxScore)) indexesOfDrawnPlayers.add(i);
        }
        return indexesOfDrawnPlayers;
    }

    private void resolveGameOutcome() {
        List<Integer> playersWhoScoredUnder21 = playerScores.stream().map(score -> {
            if (score > 21) {
                return 0;
            }
            return score;
        }).collect(Collectors.toList());

        Integer maxScore = playersWhoScoredUnder21.stream().max(Integer::compare).orElse(null);

        if (maxScore == null || maxScore == 0) {
            System.out.println("\nAll players lose");
        } else if (playersWhoScoredUnder21.stream().filter(score -> score.equals(maxScore)).count() == 1) {
            System.out.println("\nPlayer " + (playersWhoScoredUnder21.indexOf(maxScore) + 1) + " wins!!!!");
        } else {
            List<Integer> indexesOfDrawnPlayers = getDrawnPlayersIndexes(playerScores);
            StringBuilder drawnPlayersString = new StringBuilder(String.valueOf(indexesOfDrawnPlayers.get(0) + 1));
            for (int i = 1; i < indexesOfDrawnPlayers.size(); i++) {
                drawnPlayersString.append(" and ").append(indexesOfDrawnPlayers.get(i) + 1);
            }
            System.out.println("\nPlayer " + drawnPlayersString + " have drawn");
        }
    }

    public boolean playAgain() {
        System.out.println("\nWould you like to play another game?");
        int playAgainChoice = commands.displayChoices(Arrays.asList("Yes", "No"));
        return playAgainChoice == 0;
    }
}
