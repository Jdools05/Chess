package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public class Rook extends Piece{
    public Rook(boolean white) {
        super(white, 5, 'R');
    }

    @Override
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        // if we have pieces to move
        if (this.verifySpots(start, end)) {
            // set validity and movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            // make sure it is only in one direction
            if (!(xOffset * yOffset == 0 && !(xOffset == 0 && yOffset == 0))) throw new Exception("Illegal Move!");

            // make sure there are no pieces in the way
            if (xOffset < 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(start.x + i, start.y).isEmpty) throw new Exception("Illegal Move!");
            if (xOffset > 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(start.x + i, start.y).isEmpty) throw new Exception("Illegal Move!");
            if (yOffset < 0) for (int i = yOffset + 1; i != 0; i++) if (!game.getTile(start.x, start.y + i).isEmpty) throw new Exception("Illegal Move!");
            if (yOffset > 0) for (int i = yOffset - 1; i != 0; i--) if (!game.getTile(start.x, start.y + i).isEmpty) throw new Exception("Illegal Move!");


            return true;
        }
        return false;
    }
}
