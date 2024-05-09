package org.example.card;

import java.util.HashMap;
import java.util.Map;

public class BlackJackCard extends Card {
    static Map<String, Integer> symbolValueLookup = new HashMap<>();

    static {
        for (int i = 1; i < 11; i++) {
            symbolValueLookup.put(Integer.toString(i), i);
        }
        symbolValueLookup.put("A", 11);
        symbolValueLookup.put("K", 10);
        symbolValueLookup.put("Q", 10);
        symbolValueLookup.put("J", 10);
    }

    public BlackJackCard(Suit suit, String symbol) {
        super(suit, symbol, symbolValueLookup.get(symbol));
    }
}
