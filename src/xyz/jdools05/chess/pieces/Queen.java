package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public class Queen extends Piece {
    public Queen(boolean white) {
        super(white, 9, 'Q');
    }

    @Override
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        // if we have pieces to move
        if (this.verifySpots(start, end)) {
            // set validity and movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            // make sure it is only in one direction
            if (!(xOffset * yOffset == 0 && !(xOffset == 0 && yOffset == 0)) && (int)Math.sqrt(Math.abs(xOffset * yOffset)) != Math.abs(xOffset)) throw new Exception("Illegal Move!");

            if (xOffset * yOffset == 0) {
                if (xOffset < 0) for (int i = xOffset + 1; i != 0; i++)
                    if (!game.getTile(start.x + i, start.y).isEmpty) throw new Exception("Illegal Move!");
                if (xOffset > 0) for (int i = xOffset - 1; i != 0; i--)
                    if (!game.getTile(start.x + i, start.y).isEmpty) throw new Exception("Illegal Move!");
                if (yOffset < 0) for (int i = yOffset + 1; i != 0; i++)
                    if (!game.getTile(start.x, start.y + i).isEmpty) throw new Exception("Illegal Move!");
                if (yOffset > 0) for (int i = yOffset - 1; i != 0; i--)
                    if (!game.getTile(start.x, start.y + i).isEmpty) throw new Exception("Illegal Move!");
            } else {
                if (xOffset > 0 && yOffset > 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(i + start.x, i + start.y).isEmpty)  throw new Exception("Illegal Move!");
                if (xOffset < 0 && yOffset > 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(i + start.x, -i + start.y).isEmpty)  throw new Exception("Illegal Move!");
                if (xOffset < 0 && yOffset < 0) for (int i = xOffset + 1; i != 0; i++) if (!game.getTile(i + start.x, i + start.y).isEmpty)  throw new Exception("Illegal Move!");
                if (xOffset > 0 && yOffset < 0) for (int i = xOffset - 1; i != 0; i--) if (!game.getTile(i + start.x, -i + start.y).isEmpty)  throw new Exception("Illegal Move!");
            }


            return true;
        }
        return false;
    }
}