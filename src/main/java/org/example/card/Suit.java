package org.example.card;

public enum Suit {
    DIAMOND('♦'), SPADE('♠'), CLUB('♣'), HEART('♥');


private char suitLetter;

    Suit(char suitLetter) {
        this.suitLetter = suitLetter;
    };

    public Character getSuitSymbol() {
        return suitLetter;
    }

}