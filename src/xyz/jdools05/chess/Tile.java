package xyz.jdools05.chess;

import xyz.jdools05.chess.pieces.Piece;

public class Tile {
    public int x, y;
    public boolean isEmpty;
    public Piece piece;
    public Tile(int x, int y, Piece p){
        this.x = x;
        this.y = y;
        this.piece = p;
        isEmpty = this.piece == null;
    }
}
