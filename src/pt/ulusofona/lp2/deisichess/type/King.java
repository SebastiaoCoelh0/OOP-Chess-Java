package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveDiagonally;
import pt.ulusofona.lp2.deisichess.movement.MoveHorizontal;
import pt.ulusofona.lp2.deisichess.movement.MoveVertical;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class King extends Piece {

    public King(int nrId, String name, int team) {
        super(nrId, name, team, 1000, 0, new Movement[]{new MoveHorizontal(), new MoveVertical(), new MoveDiagonally()});
    }

    @Override
    public String toString() {

        if (getInGame()) {

            return getNrId() + " | Rei | (infinito) | " + getTeam() + " | "
                    + getName() + " @(" + getCoords().split(",")[0] + ", " + getCoords().split(",")[1] + ")";

        } else {

            return getNrId() + " | " + getPieceType() + " | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @(n/a)";
        }
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        for (Movement movement : getMovementsPiece()) {

            if (movement.canMovePiece(x0, y0, x1, y1, board, 1)) {

                return true;
            }
        }

        return false;
    }

}
