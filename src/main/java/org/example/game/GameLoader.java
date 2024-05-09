package org.example.game;

import org.example.commands.Commands;

import java.util.Arrays;
import java.util.List;

public class GameLoader implements ChooseGame {
    List<String> gameNames = Arrays.asList("War", "BlackJack", "Old Maid");

    @Override
    public void printGames() {
        gameNames.forEach(gameName -> {
            System.out.println(gameName);
        });
    }

    @Override
    public Game chooseGame() {
        Commands commands = new Commands();
        int gameChoice = commands.displayChoices(gameNames);
        switch (gameChoice) {
            case 0:
                return new War();
            case 1:
                return new Blackjack();
            case 2:
                return new OldMaid();
            default:
                return new Blackjack();
        }
    }
}
