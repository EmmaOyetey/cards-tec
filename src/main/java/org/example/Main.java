package org.example;

import org.example.deck.Deck;
import org.example.user.User;
import org.example.user.UserInteraction;

public class Main {
    public static void main(String[] args) {


        // Create a deck
        Deck deck = new Deck();


    //print the initial deck
        System.out.println("inital deck");
        deck.createFullDeck();
        deck.printDeck();

        System.out.println("shuffled deck");
        deck.shuffleDeck();
        deck.printDeck();

        System.out.println("sorted deck");
        deck.sortDeck();
        deck.printDeck();

        UserInteraction myUser = new UserInteraction();

        myUser.removeALLCards();

    }
}