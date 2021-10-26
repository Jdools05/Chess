package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public class Bishop extends Piece {
    public Bishop(boolean white) {
        super(white, 3, 'B');
    }

    @Override
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        if (this.verifySpots(start, end)) {
            // set movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            if ((int)Math.sqrt(Math.abs(xOffset * yOffset)) != Math.abs(xOffset)) throw new Exception("Illegal Move!");


            if (xOffset > 0 && yOffset > 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(i + start.x, i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            if (xOffset < 0 && yOffset > 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(i + start.x, -i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            if (xOffset < 0 && yOffset < 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(i + start.x, i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            if (xOffset > 0 && yOffset < 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(i + start.x, -i + start.y).isEmpty)  throw new Exception("Illegal Move!");

            return true;
        }
        return false;
    }
}
