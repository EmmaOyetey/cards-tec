package org.example.user;

import org.example.card.Card;
import org.example.card.Suit;
import org.example.deck.Deck;

import java.util.ArrayList;

public class User {
    int inGameScore = 0;
    ArrayList<Card> hand = new ArrayList<>();
    String name;
    int playerId;
    Deck myDeck = new Deck();


    public User() {
        myDeck.createFullDeck();
        System.out.println("hand");
        System.out.println(hand);
        hand.add(myDeck.dealCard());
        hand.add(myDeck.dealCard());
        hand.add(myDeck.dealCard());
        hand.add(myDeck.dealCard());
        hand.add(myDeck.dealCard());
        System.out.println("hand");
        System.out.println(hand);
    }
}