package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public class Knight extends Piece{
    public Knight(boolean white) {
        super(white, 3, 'N');
    }

    @Override
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        if (this.verifySpots(start, end)) {
            // set validity and movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            // check if the movement is valid
            if (Math.abs(xOffset * yOffset) != 2) throw new Exception("Illegal Move!");

            return true;
        }
        return false;
    }
}
