package pt.ulusofona.lp2.deisichess.movement;

import pt.ulusofona.lp2.deisichess.Board;

public abstract class Movement {

    public abstract boolean canMovePiece(int x0, int y0, int x1, int y1, Board board, int delta);

}
