package org.example;

import org.example.game.ChooseGame;
import org.example.game.Game;
import org.example.game.GameLoader;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to CardsTEC - card games by Todd, Emma & Cheryl"
                + "\nWhich card game would you like to play?");

        Game game;
        ChooseGame gameLoader = new GameLoader();
        do {
            game = gameLoader.chooseGame();
            game.printRules();
            game.play();
        } while (game.playAgain());

    }

}