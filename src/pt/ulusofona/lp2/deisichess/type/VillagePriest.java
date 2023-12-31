package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveDiagonally;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class VillagePriest extends Piece {

    public VillagePriest(int nrId, String name, int team) {
        super(nrId, name, team, 3, 3, new Movement[]{new MoveDiagonally()});
    }

    @Override
    public String toString() {

        if (getInGame()) {

            return getNrId() + " | Padre da Vila | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (" + getCoords().split(",")[0] + ", " + getCoords().split(",")[1] + ")";

        } else {

            return getNrId() + " | Padre da Vila | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (n/a)";
        }
    }

    public VillagePriest copy() {

        return new VillagePriest(getNrId(), getName(), getTeam());
    }

    @Override
    public String[] getPieceInfo() {
        return new String[0];
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        for (Movement movement : getMovementsPiece()) {

            if (movement.canMovePiece(x0, y0, x1, y1, board, 3)) {
                return true;
            }

        }
        return false;
    }
}