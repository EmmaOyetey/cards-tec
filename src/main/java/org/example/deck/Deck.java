package org.example.deck;

import org.example.card.Card;
import org.example.card.SortBySuitThenValue;
import org.example.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    protected List<Card> cards = new ArrayList<>();
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

    public void createDemoDeck() {
        clearDeck(); // Clear the deck before adding new cards
        for (Suit suit : suits) {
            // Add cards 7, 10, Jack, King for each suit
            cards.add(new Card(suit, "7", 7)); // Add 7
            cards.add(new Card(suit, "10", 10)); // Add 10
            cards.add(new Card(suit, "J", 11)); // Add Jack
            cards.add(new Card(suit, "K", 13)); // Add King
        }
    }
//convert to a ternary?
    //added User user to parameter - need to overload?
    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }


    public Deck sortDeck(SortBySuitThenValue sortBySuitThenValue) {
        Collections.sort(cards, new SortBySuitThenValue());
        return this;
    }

    public Deck sortDeck() {
        Collections.sort(cards, (left, right) -> left.getValue() - right.getValue() );
        return this;
    }

    public void printDeck() {
        cards.forEach(System.out::println);
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}

