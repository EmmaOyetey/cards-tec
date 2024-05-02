package org.example.user;

import org.example.card.Card;
import org.example.card.Suit;

public class UserInteraction extends User {


    public Card RemoveFromHand(Suit suit, int value) {
        // array of cards hand - select a card to dicard/play select by suit and value
        //return the card
        //remove it from the hand array

    };

    public Card sortHand( ) {
        //overload - one that takes a parameter (suit or value) and one that dosent
    };

    public Card removeALLCards(){
        //enables player to dis=card all cards. needs to be able to discard an array of cards. - the
    };

    public Card drawACard(){
        //take a new card from the deck
        //adding a card to thier hand thier array of cards.
    };

    public Card shuffleHand(){
        // reorder cards in the hand - eg old maid
    };

    public Card RemoveCardByIndex(){
        //player is selecting a card from other players hand
        // index of the  players hand
    };

    public Card getHand() {
     //   read your hand - exisiting hand as it changes through the game
    };

}
