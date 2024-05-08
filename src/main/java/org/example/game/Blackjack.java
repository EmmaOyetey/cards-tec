package org.example.game;

import org.example.card.Card;
import org.example.commands.Commands;
import org.example.deck.BlackJackDeck;
import org.example.deck.Deck;
import org.example.user.User;
import org.example.user.UserInteraction;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Blackjack extends Game {
    private Deck deck;
    private List<UserInteraction> users = new ArrayList<>();
    private int playerCount = 0;
    private int playerTurn = 0;
    private boolean gameOver = false;
    private Commands commands = new Commands();
    static final List<String> choices = Arrays.asList("Hit", "Stay");
    private List<Integer> playerScores = new ArrayList<Integer>();
    private List<Boolean> playerFinished = new ArrayList<Boolean>();

    public Blackjack(String rules, int playerCount) {
        super("Blackjack", rules);
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
        System.out.println("Welcome to ");
        printTitle();
        printRules();
        dealToAllPlayers();
        while (!gameOver) {
            if (playerFinished.get(playerTurn)) {
                System.out.println("Player " + (playerTurn + 1) + " is done for this round");
                playerTurn = (playerTurn + 1) % playerCount;
                continue;
            }
            UserInteraction currentPlayer = users.get(playerTurn);
            System.out.println("Player " + (playerTurn + 1) + "'s turn:");

            ArrayList<Card> playerHand = currentPlayer.getHand();
            playerScores.set(playerTurn, playerHand.stream()
                    .reduce(0, (subtotal, card) -> subtotal + card.getValue(), Integer::sum));

            System.out.println(playerHand);
            System.out.println("Score: " + playerScores.get(playerTurn));

            // TODO: Fix the display of choice confirmation in Commands
            int choice = commands.displayChoices(choices);
            System.out.println("You chose: " + choice);
            if (choice == 0) {
                currentPlayer.drawACard(deck.dealCard());
                // TODO: Create new blackjack deck for correct scoring
                int newScore = playerHand.stream().reduce(0, (subtotal, card) -> subtotal + card.getValue(), Integer::sum);
                System.out.println("Your score is now: " + newScore);
                if (newScore > 21) {
                    System.out.println("You went over 21 :(");
                    playerFinished.set(playerTurn, true);
                }
            } else {
                playerFinished.set(playerTurn, true);
            }

            gameOver = playerFinished.stream().reduce(true, (allPlayersFinished, playerFinish) -> allPlayersFinished && playerFinish, Boolean::logicalAnd);
            playerTurn = (playerTurn + 1) % playerCount;
        }
        System.out.println("Game finished");
    }

    public boolean playAgain() {
        return false;
    }
}
