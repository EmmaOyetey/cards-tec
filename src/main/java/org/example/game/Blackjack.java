package org.example.game;

import org.example.card.Card;
import org.example.commands.Commands;
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

    public Blackjack(String rules, int playerCount) {
        super("Blackjack", rules);
        this.playerCount = playerCount;
        for (int i = 0; i < playerCount; i++) {
            users.add(new UserInteraction());
            playerScores.add(0);
        }

        Deck freshDeck = new Deck();
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
            UserInteraction currentPlayer = users.get(playerTurn);
            System.out.println("Player " + (playerTurn + 1) + "'s turn:");

            ArrayList<Card> playerHand = currentPlayer.getHand();
            playerScores.set(playerTurn, playerHand.stream()
                    .reduce(0, (subtotal, card) -> {
                        return subtotal + card.getValue();
                    }, Integer::sum));

            System.out.println(playerHand);
            System.out.println("Score: " + playerScores.get(playerTurn));

            int choice = commands.displayChoices(choices);
            System.out.println("You chose: " + choice);

//            gameOver = true;
            playerTurn = (playerTurn + 1) % playerCount;
        }
    }

    public boolean playAgain() {
        return false;
    }
}
