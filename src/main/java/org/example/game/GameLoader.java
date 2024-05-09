package org.example.game;

import org.example.commands.Commands;

import java.util.Arrays;

public class GameLoader implements ChooseGame {
    @Override
    public void printGames() {

    }

    @Override
    public Game chooseGame() {
        Commands commands = new Commands();
        int gameChoice = commands.displayChoices(Arrays.asList("War", "BlackJack", "Old Maid"));
        switch (gameChoice) {
            case 0:
                return new War();
            case 1:
                return new Blackjack("Play blackjack", 3);
            case 2:
                return new OldMaid();
            default:
                return new Blackjack("Play blackjack", 3);
        }
    }
}
