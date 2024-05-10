package org.example.user;

import org.example.card.Card;
import org.example.card.SortBySuitThenValue;
import org.example.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class UserInteraction extends User {

    private ArrayList<Card> hand = new ArrayList<>();


    public UserInteraction() {
        hand = new ArrayList<>();
    }

    public Optional<Card> removeFromHand(Suit suit, int value) {
        Optional<Card> cardToBeRemoved = hand.stream()
                .filter(card -> card.getSuit().equals(suit) && card.getValue() == value)
                .findFirst();
        cardToBeRemoved.ifPresent(hand::remove);
        return cardToBeRemoved;
    };

    public ArrayList<Card> sortHand() {
        Collections.sort(hand, (left, right) -> left.getValue() - right.getValue());
        return hand;
    };

    public ArrayList<Card> sortHand(SortBySuitThenValue sortBySuitThenValue) {
        Collections.sort(hand, new SortBySuitThenValue());
        return hand;
    };

    public ArrayList<Card> removeALLCards(){
        ArrayList<Card> discardedHand = (ArrayList<Card>) hand.clone();
        hand = new ArrayList<>();
        return discardedHand;
    };

    public void drawACard(Card card){
        if(card != null) {
            hand.add(card);
        }
    };

    public void shuffleHand(){
        Collections.shuffle(hand);
    };

    public void removeCardByIndex(int index){
        if(!hand.isEmpty()) {
            hand.remove(index);
        }
    };

    public ArrayList<Card> getHand() {
        return hand;
    }
}