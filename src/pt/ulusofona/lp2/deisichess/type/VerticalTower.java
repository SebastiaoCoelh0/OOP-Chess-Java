package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveVertical;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class VerticalTower extends Piece {

    public VerticalTower(int nrId, String name, int team) {
        super(nrId, name, team, 3, 5, new Movement[]{new MoveVertical()});
    }

    @Override
    public String toString() {

        if (getInGame()) {

            return getNrId() + " | TorreVert | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (" + getCoords().split(",")[0] + ", " + getCoords().split(",")[1] + ")";

        } else {

            return getNrId() + " | TorreVert | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (n/a)";
        }
    }

    public VerticalTower copy() {

        return new VerticalTower(getNrId(), getName(), getTeam());
    }

    @Override
    public String[] getPieceInfo() {
        return new String[0];
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        for (Movement movement : getMovementsPiece()) {

            if (movement.canMovePiece(x0, y0, x1, y1, board, board.getSize())) {
                return true;
            }

        }
        return false;
    }
}
