package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveDiagonally;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class HommerSimpson extends Piece {

    boolean sleeping = true;

    public HommerSimpson(int nrId, String name, int team) {
        super(nrId, name, team, 2, 6, new Movement[]{new MoveDiagonally()});
    }

    @Override
    public String toString() {

        if (!sleeping) {
            return super.toString();
        }
        return "TODO Doh! zzzzzzz";
    }

    public void isSleeping() {

        sleeping = true;
    }

    public void isNotSleeping() {

        sleeping = false;
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        if (!sleeping) {

            for (Movement movement : getMovementsPiece()) {

                if (movement.canMovePiece(x0, y0, x1, y1, board, 1)) {
                    return true;
                }

            }
        }


        return false;
    }
}
