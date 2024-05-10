package org.example.deck;

import org.example.card.BlackJackCard;
import org.example.card.Suit;

public class BlackJackDeck extends Deck {

    @Override
    public void createFullDeck() {
        for (Suit suit : suits) {
            for (String symbol : symbols) {
                cards.add(new BlackJackCard(suit, symbol));
            }
        }
    }
}
