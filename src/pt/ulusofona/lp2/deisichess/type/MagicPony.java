package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveMagicPoney;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class MagicPony extends Piece {

    public MagicPony(int nrId, String name, int team) {
        super(nrId, name, team, 5, 2, new Movement[]{new MoveMagicPoney()});
    }

    @Override
    public String[] getPieceInfo() {
        return new String[0];
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        for (Movement movement : getMovementsPiece()) {

            if (movement.canMovePiece(x0, y0, x1, y1, board, -1)) { // delta -1 because the movement is fixed
                return true;
            }

        }
        return false;
    }
}
