package xyz.jdools05.chess;

import xyz.jdools05.chess.Tile;
import xyz.jdools05.chess.pieces.*;

import java.util.Arrays;
import java.util.Random;

public class Game {
    Tile[][] board = new Tile[8][8];
    Tile whiteKing, blackKing;
    int whiteScore, blackScore = 0;
    boolean isWhiteTurn = true;

    public Game() {
        this.resetBoard();
    }

    public void resetBoard() {
        // set up pawns and clear spaces
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (y == 6) board[y][x] = new Tile(x, y, new Pawn(true));
                else if (y == 1) board[y][x] = new Tile(x, y, new Pawn(false));
                else board[y][x] = new Tile(x, y, null);
            }
        }

        // set up other pieces

        //Rooks
        board[0][0] = new Tile(0, 0, new Rook(false));
        board[0][7] = new Tile(7, 0, new Rook(false));
        board[7][0] = new Tile(0, 7, new Rook(true));
        board[7][7] = new Tile(7, 7, new Rook(true));

        // Knights
        board[0][1] = new Tile(1, 0, new Knight(false));
        board[0][6] = new Tile(6, 0, new Knight(false));
        board[7][1] = new Tile(1, 7, new Knight(true));
        board[7][6] = new Tile(6, 7, new Knight(true));

        // Bishops
        board[0][2] = new Tile(2, 0, new Bishop(false));
        board[0][5] = new Tile(5, 0, new Bishop(false));
        board[7][2] = new Tile(2, 7, new Bishop(true));
        board[7][5] = new Tile(5, 7, new Bishop(true));

        // Queens
        board[0][3] = new Tile(3, 0, new Queen(false));
        board[7][3] = new Tile(3, 7, new Queen(true));

        // Kings
        board[0][4] = new Tile(4, 0, new King(false));
        board[7][4] = new Tile(4, 7, new King(true));

        // set the king tiles as well
        whiteKing = board[7][4];
        blackKing = board[0][4];
    }

    public Tile getTile(int x, int y) {
        return board[y][x];
    }

    public void showBoard() {
        StringBuilder output = new StringBuilder();
//        output.append("  1|2|3|4|5|6|7|8\n");
        for (Tile[] tiles : board) {
            for (Tile tile : tiles) {
                if (tile.isEmpty) {
                    output.append(" |");
                    continue;
                }
                Piece piece = tile.piece;
                output.append(piece.getSymbol()).append("|");
            }
            output.append("\n");
        }
        System.out.print(output);
    }

    public boolean isInCheck(Tile tile, boolean white) {
        Tile step;

        // check from piece to left side of board
        for (int x = tile.x - 1; x >= 0; x--) {
            // save current step (current Tile on check)
            step = board[tile.y][x];
            // if there is no piece, skip check
            if (step.isEmpty) continue;
            // if the piece is same color, this provides protection, skip
            if (step.piece.white == white) break;
            // if there is a queen or rook, these are the only pieces that can take this direction.
            if (step.piece instanceof Queen || step.piece instanceof Rook) return true;
        }

        // check from piece to right side of the board
        for (int x = tile.x + 1; x < 8; x++) {
            // save current step (current Tile on check)
            step = board[tile.y][x];
            // if there is no piece, skip check
            if (step.isEmpty) continue;
            // if the piece is same color, this provides protection, skip
            if (step.piece.white == white) break;
            // if there is a queen or rook, these are the only pieces that can take this direction.
            if (step.piece instanceof Queen || step.piece instanceof Rook) return true;
        }

        // check from piece to top of board
        for (int y = tile.y - 1; y >= 0; y--) {
            step = board[y][tile.x];
            if (step.isEmpty) continue;
            if (step.piece.white == white) break;
            if (step.piece instanceof Queen || step.piece instanceof Rook) return true;
        }

        // check from piece to bottom of board
        for (int y = tile.y + 1; y < 8; y++) {
            step = board[y][tile.x];
            if (step.isEmpty) continue;
            if (step.piece.white == white) break;
            if (step.piece instanceof Queen || step.piece instanceof Rook) return true;
        }

        // check diagonally towards top left
        for (int i = 1; i < Math.min(tile.x, tile.y); i++) {
            step = board[tile.y - i][tile.x - i];
            if (step.isEmpty) continue;
            if (step.piece.white == white) break;
            if (step.piece instanceof Queen || step.piece instanceof Bishop) return true;
        }

        // top right
        for (int i = 1; i < Math.min(7 - tile.x, tile.y); i++) {
            step = board[tile.y - i][tile.x + i];
            if (step.isEmpty) continue;
            if (step.piece.white == white) break;
            if (step.piece instanceof Queen || step.piece instanceof Bishop) return true;
        }

        // bottom right
        for (int i = 1; i < Math.min(7 - tile.x, 7 - tile.y); i++) {
            step = board[tile.y + i][tile.x + i];
            if (step.isEmpty) continue;
            if (step.piece.white == white) break;
            if (step.piece instanceof Queen || step.piece instanceof Bishop) return true;
        }

        // bottom left
        for (int i = 1; i < Math.min(tile.x, 7 - tile.y); i++) {
            step = board[tile.y + i][tile.x - i];
            if (step.isEmpty) continue;
            if (step.piece.white == white) break;
            if (step.piece instanceof Queen || step.piece instanceof Bishop) return true;
        }

        if (!(Math.min(tile.y + (white ? -1 : 1), tile.x - 1) < 0) && !(Math.max(tile.y + (white ? -1 : 1), tile.x - 1) > 7)) {
            step = board[tile.y + (white ? -1 : 1)][tile.x - 1];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Pawn) return true;
        }

        if (!(Math.min(tile.y + (white ? -1 : 1), tile.x + 1) < 0) && !(Math.max(tile.y + (white ? -1 : 1), tile.x + 1) > 7)) {
            step = board[tile.y + (white ? -1 : 1)][tile.x + 1];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Pawn) return true;
        }

        if (!(Math.max(tile.y + 2, tile.x + 1) > 7)) {
            step = board[tile.y + 2][tile.x + 1];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(Math.max(tile.y + 1, tile.x + 2) > 7)) {
            step = board[tile.y + 1][tile.x + 2];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(!(Math.max(tile.y - 1, tile.x + 2) > 7) || !(Math.min(tile.y - 1, tile.x + 2) < 0))) {
            step = board[tile.y - 1][tile.x + 2];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(!(Math.max(tile.y - 2, tile.x + 1) > 7) || !(Math.min(tile.y - 2, tile.x + 1) < 0))) {
            step = board[tile.y - 2][tile.x + 1];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(!(Math.max(tile.y + 2, tile.x - 1) > 7) || !(Math.min(tile.y + 2, tile.x - 1) < 0))) {
            step = board[tile.y + 2][tile.x - 1];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(!(Math.max(tile.y + 1, tile.x - 2) > 7) || !(Math.min(tile.y + 1, tile.x - 2) < 0))) {
            step = board[tile.y + 1][tile.x - 2];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(Math.min(tile.y - 1, tile.x - 2) < 0)) {
            step = board[tile.y - 1][tile.x - 2];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        if (!(Math.min(tile.y - 2, tile.x - 1) < 0)) {
            step = board[tile.y - 2][tile.x - 1];
            if (!step.isEmpty && step.piece.white != white && step.piece instanceof Knight) return true;
        }

        return false;
    }


    // conducts the rook movement of the castling
    public void castle(boolean white, boolean shortCastle) {
        board[white ? 7 : 0][shortCastle ? 7 : 0] = new Tile(shortCastle ? 7 : 0, white ? 7 : 0, null);
        board[white ? 7 : 0][shortCastle ? 5 : 3] = new Tile(shortCastle ? 5 : 3, white ? 7 : 0, new Rook(white));
        board[white ? 7 : 0][shortCastle ? 5 : 3].piece.hasMoved = true;
    }

    // Takes input as start position - end position
    // accepts both a-h and 1-8
    // example: "e2-e4" or "52-54"
    // This would move the King's pawn forward 2 spaces
    // You do not have to specify piece
    public void makeMove(String move) throws Exception {
        if (move.length() != 5) throw new Exception("Invalid format");
        // format data
        move = move.toLowerCase();
        move = move.replaceAll("a", "1");
        move = move.replaceAll("b", "2");
        move = move.replaceAll("c", "3");
        move = move.replaceAll("d", "4");
        move = move.replaceAll("e", "5");
        move = move.replaceAll("f", "6");
        move = move.replaceAll("g", "7");
        move = move.replaceAll("h", "8");
        move = move.replaceAll(" ", "-");
        String[] components = move.split("-");

        System.out.println(Arrays.toString(components));

        // get respective tiles
        Tile start = board[8 - Integer.parseInt(components[0].substring(1))][Integer.parseInt(components[0].substring(0, 1)) - 1];
        Tile end = board[8 - Integer.parseInt(components[1].substring(1))][Integer.parseInt(components[1].substring(0, 1)) - 1];

        // if not valid piece to move
        if (start.isEmpty) throw new Exception("Invalid piece");

        // get piece
        Piece piece = start.piece;

        if (piece.white != isWhiteTurn) throw new Exception("Invalid turn");
        // check movement
        if (piece.checkMoves(start, end, this)) {

            // if moving the king, update the kings position
            if (isWhiteTurn) {
                if (start == whiteKing) whiteKing = end;
            } else if (start == blackKing) blackKing = end;

            // if results in check, return to previous state
            if (isInCheck(isWhiteTurn ? whiteKing : blackKing, isWhiteTurn)) {
                if (isWhiteTurn) whiteKing = start;
                else blackKing = start;
                throw new Exception("Illegal Move!");
            }

            System.out.println("Is black in check: " + isInCheck(blackKing, false));
            System.out.println("Is white in check: " + isInCheck(whiteKing, true));

            // clear current tile
            start.piece = null;
            start.isEmpty = true;

            // if capturing add points
            if (!board[end.y][end.x].isEmpty) {
                if (isWhiteTurn) whiteScore += board[end.y][end.x].piece.value;
                else blackScore += board[end.y][end.x].piece.value;
            }

            // set piece to new spot
            end.piece = piece;
//            end.piece.hasMoved = true;
            end.isEmpty = false;


        } else throw new Exception("Illegal Move!");

        // change turns
        isWhiteTurn = !isWhiteTurn;
    }
}
