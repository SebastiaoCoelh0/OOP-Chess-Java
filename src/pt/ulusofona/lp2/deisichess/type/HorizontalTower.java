package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveHorizontal;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class HorizontalTower extends Piece {

    public HorizontalTower(int nrId, String name, int team) {
        super(nrId, name, team, 3, 4, new Movement[]{new MoveHorizontal()});
    }

    @Override
    public String toString() {

        if (getInGame()) {

            return getNrId() + " | TorreHor | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (" + getCoords().split(",")[0] + ", " + getCoords().split(",")[1] + ")";

        } else {

            return getNrId() + " | TorreHor | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (n/a)";
        }
    }

    public HorizontalTower copy() {

        return new HorizontalTower(getNrId(), getName(), getTeam());
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
