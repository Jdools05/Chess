package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public abstract class Piece {
    // The piece's color
    public boolean white;
    // The piece's value
    public int value;
    // if the piece has moved
    public boolean hasMoved = false;
    // the piece's symbol
    public Character symbol;

    // Returns true if the move is valid
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        return false;
    }

    // constructor
    public Piece(boolean white, int value, Character symbol) {
        this.white = white;
        this.value = value;
        this.symbol = symbol;
    }

    // returns the piece's symbol
    public Character getSymbol() {
        if (this.white) return this.symbol;
        return Character.toLowerCase(this.symbol);
    }

    // verifies the move doesn't capture same color piece
    public boolean verifySpots(Tile start, Tile end) {
        if (!end.isEmpty) return start.piece.white != end.piece.white;
        return true;
    }
}
