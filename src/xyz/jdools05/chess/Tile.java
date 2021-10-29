package xyz.jdools05.chess;

import xyz.jdools05.chess.pieces.Piece;

public class Tile {
    // position on the board
    public int x, y;
    // if there is a piece on this tile
    public boolean isEmpty;
    // piece on this tile
    public Piece piece;

    // constructor
    public Tile(int x, int y, Piece p){
        this.x = x;
        this.y = y;
        this.piece = p;
        isEmpty = this.piece == null;
    }
}
