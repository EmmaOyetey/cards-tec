package org.example.game;

import org.example.card.BlackJackCard;
import org.example.card.Card;
import org.example.card.Suit;
import org.example.commands.Commands;
import org.example.deck.BlackJackDeck;
import org.example.deck.Deck;
import org.example.user.User;
import org.example.user.UserInteraction;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;

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
        // TODO: Iron out ruleset, add to gameLoader
        // Quick and dirty rules
//        Place a bet in the betting areas marked on the table. You and fellow players are dealt two cards each whilst the dealer is dealt one face up. If your first 2 cards add up to 21 (an Ace and a card valued 10), that’s Blackjack! If they have any other total, decide whether you wish to ‘draw’ or ‘stay’. You can continue to draw cards until you are happy with your hand.
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
                int newScore = currentPlayer.getHand().stream().reduce(0, (subtotal, card) -> subtotal + card.getValue(), Integer::sum);
                playerScores.set(playerTurn, newScore);
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

        List<Integer> playersWhoScoredUnder21 = playerScores.stream().map(score -> {
            if (score > 21) {
                return 0;
            }
            return score;
        }).collect(Collectors.toList());

        Integer maxScore = playersWhoScoredUnder21.stream().max(Integer::compare).orElse(null);
        if (maxScore == null || maxScore == 0) {
            System.out.println("All players lose");
        } else if (playersWhoScoredUnder21.stream().filter(score -> score.equals(maxScore)).count() == 1) {
            System.out.println("Player " + (playersWhoScoredUnder21.indexOf(maxScore) + 1) + " wins!!!!");
        } else {
            List<Integer> indexesOfDrawnPlayers = new ArrayList<>();
            for (int i = 0; i < playersWhoScoredUnder21.size(); i++) {
                if (playersWhoScoredUnder21.get(i) == maxScore) indexesOfDrawnPlayers.add(i);
            }
            String drawnPlayers = String.valueOf(indexesOfDrawnPlayers.get(0) + 1);
            for (int i = 1; i < indexesOfDrawnPlayers.size(); i++) {
                drawnPlayers += " and " + (indexesOfDrawnPlayers.get(i) + 1);
            }
            System.out.println("Player " + drawnPlayers + " have drawn");
        }

        System.out.println("Game finished");
    }

    public boolean playAgain() {
        System.out.println("\nWould you like to play again?");
        int playAgainChoice = commands.displayChoices(Arrays.asList("Yes", "No"));
        if (playAgainChoice == 0) {
            this.gameOver = false;
            this.users = new ArrayList<>();
            this.playerScores = new ArrayList<>();
            this.playerFinished = new ArrayList<>();
            System.out.println(this.playerCount);
            for (int i = 0; i < this.playerCount; i++) {
                users.add(new UserInteraction());
                playerScores.add(0);
                playerFinished.add(false);
            }
            System.out.println(this.users);

            BlackJackDeck freshDeck = new BlackJackDeck();
            freshDeck.createFullDeck();
            freshDeck.shuffleDeck();
            this.deck = freshDeck;
            dealToAllPlayers();
            play();
            return true;
        }
        return false;
    }
}
