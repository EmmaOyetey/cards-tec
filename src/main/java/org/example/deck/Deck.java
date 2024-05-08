package org.example.deck;

import org.example.card.BlackJackCard;
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

//    public Deck() {
//        createFullDeck();
//    }


// return a collection ?? return Collections.unmodifiableList(cards)
// (unmodifianble is a method provided by the collections class - returns unmodifiable/immutable view of the specified list.
// once created you canonot modify its content but can access its elements...

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
//convert to a ternary?
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

    //shuffle is a collections method - shuffle in random order
    public void shuffleDeck() {
        Collections.shuffle(cards);
    }
}