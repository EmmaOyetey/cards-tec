package org.example.deck;

import org.example.card.Card;
import org.example.card.Suit;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards = new ArrayList<>();
    final String[] symbols = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
    final Suit[] suits = new Suit[]{Suit.HEART, Suit.CLUB, Suit.SPADE, Suit.DIAMOND};

    public Deck() {
        createFullDeck();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void clearDeck() {
        cards = new ArrayList<>();
    }

    public void createFullDeck() {
        for (Suit suit : suits) {
            int cardValue = 2;
            for (String symbol : symbols) {
                cards.add(new Card(suit, symbol, cardValue));
                cardValue++;
            }
        }
    }
}