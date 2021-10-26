package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public abstract class Piece {
    public boolean white;
    public int value;
    public boolean hasMoved = false;
    public Character symbol;

    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        return false;
    }

    public Piece(boolean white, int value, Character symbol) {
        this.white = white;
        this.value = value;
        this.symbol = symbol;
    }

    public Character getSymbol() {
        if (this.white) return this.symbol;
        return Character.toLowerCase(this.symbol);
    }

    public boolean verifySpots(Tile start, Tile end) {
        if (!end.isEmpty) return start.piece.white != end.piece.white;
        return true;
    }
}
