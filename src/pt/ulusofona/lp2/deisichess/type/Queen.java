package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveDiagonally;
import pt.ulusofona.lp2.deisichess.movement.MoveHorizontal;
import pt.ulusofona.lp2.deisichess.movement.MoveVertical;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class Queen extends Piece {

    public Queen(int nrId, String name, int team) {
        super(nrId, name, team, 8, 1, new Movement[]{new MoveHorizontal(), new MoveVertical(), new MoveDiagonally()});
    }

    @Override
    public String toString() {

        if (getInGame()) {

            return getNrId() + " | Rainha | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (" + getCoords().split(",")[0] + ", " + getCoords().split(",")[1] + ")";

        } else {

            return getNrId() + " | Rainha | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (n/a)";
        }

    }

    public Queen copy() {

        return new Queen(getNrId(), getName(), getTeam());
    }

    @Override
    public String[] getPieceInfo() {
        return new String[0];
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        if (board.getCoordsToPiece(x1, y1) != null && board.getCoordsToPiece(x1, y1).isQueen()) { //cannot eat queen piece
            return false;
        }

        if (board.getCoordsToPiece(x1, y1) != null && board.getCoordsToPiece(x1, y1).isJoker()) {

            Joker temp = (Joker) board.getCoordsToPiece(x1, y1);

            if (temp.isJokerAsQueen()) {

                return false;
            }
        }

        for (Movement movement : getMovementsPiece()) {

            if (movement.canMovePiece(x0, y0, x1, y1, board, 5)) {

                return true;
            }
        }
        return false;
    }

}