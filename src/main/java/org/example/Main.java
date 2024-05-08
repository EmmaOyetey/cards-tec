package org.example;

import org.example.deck.Deck;
import org.example.game.Blackjack;

public class Main {
    public static void main(String[] args) {
        Blackjack blackjack = new Blackjack("Play blackjack", 3);
        blackjack.play();
    }
}