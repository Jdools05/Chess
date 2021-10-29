package xyz.jdools05.chess.pieces;

import xyz.jdools05.chess.Game;
import xyz.jdools05.chess.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(boolean white) {
        super(white, 1, 'P');
    }

    @Override
    public boolean checkMoves(Tile start, Tile end, Game board) throws Exception {
        // if we have pieces to move
        if (this.verifySpots(start, end)) {
            // set movement offsets
            int xOffset = end.x - start.x;
            int yOffset = end.y - start.y;

            // save valid moves
            List<String> validOffsetPairs = new ArrayList<>();

            // set valid moves
            if (!this.white) {
                validOffsetPairs.add(Arrays.toString(new int[]{-1, 1}));
                validOffsetPairs.add(Arrays.toString(new int[]{0, 1}));
                validOffsetPairs.add(Arrays.toString(new int[]{1, 1}));
                if (!this.hasMoved) validOffsetPairs.add(Arrays.toString(new int[]{0, 2}));
            } else {
                validOffsetPairs.add(Arrays.toString(new int[]{-1, -1}));
                validOffsetPairs.add(Arrays.toString(new int[]{0, -1}));
                validOffsetPairs.add(Arrays.toString(new int[]{1, -1}));
                if (!this.hasMoved) validOffsetPairs.add(Arrays.toString(new int[]{0, -2}));
            }

            // check if the move is valid
            if (!validOffsetPairs.contains(Arrays.toString(new int[]{xOffset, yOffset}))) throw new Exception("Illegal Move!");
            if (xOffset == 0 && yOffset == (this.white ? -1 : 1) && !end.isEmpty) throw new Exception("Illegal Move!");
            if (xOffset == 0 && yOffset == (this.white ? -2 : 2) && (!end.isEmpty || !board.getTile(start.x, start.y + (this.white ? -1 : 1)).isEmpty)) throw new Exception("Illegal Move!");
            if (xOffset != 0 && end.isEmpty) throw new Exception("Illegal Move!");

            return true;
        }
        return false;
    }
}
