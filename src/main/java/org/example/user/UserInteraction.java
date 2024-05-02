package org.example.user;

import org.example.card.Card;
import org.example.card.SortBySuitThenValue;
import org.example.card.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class UserInteraction extends User {

    public Optional<Card> removeFromHand(Suit suit, int value) {
        // array of cards hand - select a card to dicard/play select by suit and value
        //return the card
        //remove it from the hand array
        Optional<Card> cardToBeRemoved = hand.stream()
                .filter(card -> card.getSuit().equals(suit) && card.getValue() == value)
                .findFirst();
        cardToBeRemoved.ifPresent(hand::remove);
        return cardToBeRemoved;
    };

    public ArrayList<Card> sortHand() {
        //overload - one that takes a parameter (suit or value) and one that dosent
        Collections.sort(hand, (left, right) -> left.getValue() - right.getValue());
        return hand;
    };

    public ArrayList<Card> sortHand(SortBySuitThenValue sortBySuitThenValue) {
        //overload - one that takes a parameter (suit or value) and one that dosent
        Collections.sort(hand, new SortBySuitThenValue());
        return hand;
    };

    public ArrayList<Card> removeALLCards(){
        //enables player to dis=card all cards. needs to be able to discard an array of cards. - the
        ArrayList<Card> discardedHand = (ArrayList<Card>) hand.clone();
        hand = new ArrayList<>();
        return discardedHand;
    };

    public void drawACard(Card card){
        if(card != null) {
            hand.add(card);
        }
//                return ??
        //take a new card from the deck
        //adding a card to their hand their array of cards.
    };

//    public Card shuffleHand(){
//        // reorder cards in the hand - eg old maid
//    };

    public void removeCardByIndex(int index){
        if(!hand.isEmpty()) {
            hand.remove(index);
        }
        //player is selecting a card from other players hand
        // index of the  players hand
    };

    public Card getHand() {
     //   read your hand - exisiting hand as it changes through the game
    };

}
