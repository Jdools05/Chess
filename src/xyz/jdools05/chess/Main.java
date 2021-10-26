package xyz.jdools05.chess;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        game.showBoard();
        while (true) {
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
    }
}
