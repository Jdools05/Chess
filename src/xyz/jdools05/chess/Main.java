package xyz.jdools05.chess;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // create a new game of chess
        Game game = new Game();
        // create a new scanner
        Scanner scanner = new Scanner(System.in);

        // print the board
        game.showBoard();

        // loop until the game is over
        while (true) {
            // get the user's move
            try {
                String input = scanner.nextLine();
                if (Objects.equals(input, "exit")) break;
                game.makeMove(input);
                game.showBoard();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Black Score: " + game.blackScore + "\nWhite Score: " + game.whiteScore);

        //close the scanner
        scanner.close();
    }
}
