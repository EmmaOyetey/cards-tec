package org.example;

import org.example.commands.Commands;
import org.example.game.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to CardsTEC - card games by Todd, Emma & Cheryl"
                + "\n Which card game would you like to play?");

        Game game;
        ChooseGame gameLoader = new GameLoader();
        do {
            game = gameLoader.chooseGame();
            game.printRules();
            game.play();
        } while (game.playAgain());

    }

}