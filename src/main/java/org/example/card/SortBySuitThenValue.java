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

//        if( left.getSuit() == right.getSuit() ) {
//        System.out.println(left.getValue() - right.getValue());
//        return left.getValue() - right.getValue();
//
//    } else {
//        System.out.println(left.getSuit().getSuitLetter() - right.getSuit().getSuitLetter());
//        return left.getSuit().getSuitLetter() - right.getSuit().getSuitLetter();
//    }
//
//}