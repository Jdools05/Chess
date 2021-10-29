package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public class Bishop extends Piece {
    // constructor
    public Bishop(boolean white) {
        super(white, 3, 'B');
    }

    // method to determine if a move is valid
    @Override
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        if (this.verifySpots(start, end)) {
            // set movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            // check if movement is diagonal
            if (xOffset == 0 || Math.abs(yOffset) / Math.abs(xOffset) != 1) throw new Exception("Illegal Move!");

            // check if movement is blocked
            if (xOffset > 0 && yOffset > 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(i + start.x, i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            if (xOffset < 0 && yOffset > 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(i + start.x, -i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            if (xOffset < 0 && yOffset < 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(i + start.x, i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            if (xOffset > 0 && yOffset < 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(i + start.x, -i + start.y).isEmpty)  throw new Exception("Illegal Move!");

            return true;
        }
        return false;
    }
}
