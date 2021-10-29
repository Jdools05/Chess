package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

public class King extends Piece{
    public King(boolean white) {
        super(white, 50, 'K');
    }

    @Override
    public boolean checkMoves(Tile start, Tile end, Game game) throws Exception {
        if (this.verifySpots(start, end)) {
            // set movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            // attempting to castle
            if (Math.abs(xOffset) == 2 && yOffset == 0 && !this.hasMoved && !(xOffset < 0 ? game.getTile(0, start.y).isEmpty || game.getTile(0, start.y).piece.hasMoved : game.getTile(7, start.y).isEmpty || game.getTile(7, start.y).piece.hasMoved)) {
                // check if there are pieces between the king and the rook
                if (xOffset < 0) {
                    if (!game.getTile(3, start.y).isEmpty) throw new Exception("Illegal Move!");
                    if (!game.getTile(2, start.y).isEmpty) throw new Exception("Illegal Move!");
                    if (!game.getTile(1, start.y).isEmpty) throw new Exception("Illegal Move!");
                } else {
                    if (!game.getTile(5, start.y).isEmpty) throw new Exception("Illegal Move!");
                    if (!game.getTile(6, start.y).isEmpty) throw new Exception("Illegal Move!");
                }
                game.castle(this.white, xOffset > 0);
            }
            else if (xOffset > 1 || yOffset > 1) throw new Exception("Illegal Move!");

            return true;
        }
        return false;
    }
}
