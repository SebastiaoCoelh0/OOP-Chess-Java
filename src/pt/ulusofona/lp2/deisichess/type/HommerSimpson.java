package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveDiagonally;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class HommerSimpson extends Piece {

    public HommerSimpson(int nrId, String name, int team) {
        super(nrId, name, team, 2, 6, new Movement[]{new MoveDiagonally()});
    }

    @Override
    public String toString() {
        //TODO Doh! zzzzzzz

        return super.toString();
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        if (board.hommerCanMove()) {

            for (Movement movement : getMovementsPiece()) {

                if (movement.canMovePiece(x0, y0, x1, y1, board, 1)) {
                    return true;
                }

            }
        }


        return false;
    }
}
