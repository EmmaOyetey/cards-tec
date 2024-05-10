package org.example.card;

import java.util.Comparator;

public class SortBySuitThenValue implements Comparator<Card> {

    @Override
    public int compare(Card left, Card right) {
        if(left.getSuit().equals(right.getSuit())) {
            return Integer.compare(left.getValue(), right.getValue());
        } else {
            return Character.compare(left.getSuit().getSuitSymbol(), right.getSuit().getSuitSymbol());
        }
    }
}