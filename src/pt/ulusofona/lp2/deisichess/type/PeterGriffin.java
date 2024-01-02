package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.MoveHorizontal;
import pt.ulusofona.lp2.deisichess.movement.MoveVertical;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class PeterGriffin extends Piece {

    public PeterGriffin(int nrId, String name, int team) {
        super(nrId, name, team, 2, 8, new Movement[]{new MoveHorizontal(), new MoveVertical()});
    }

    public PeterGriffin copy() {

        return new PeterGriffin(getNrId(), getName(), getTeam());
    }

    public void capturesHomer(int x0, int y0, int x1, int y1, Board board) {

        int newX = -1;
        int newy = -1;
        boolean startsSearchFromTop = false;

        if (board.getTeamID() == 10) {

            startsSearchFromTop = true;
        }

        for (int line = 0; line < board.getSize(); line++) {

            for (int column = 0; column < board.getSize(); column++) {

                if (startsSearchFromTop) {

                    if (board.getCoordsToPiece(column, line) == null) {

                        newX = column;
                        newy = line;
                        break;
                    }

                } else {

                    if (board.getCoordsToPiece(board.getSize() - 1 - column, board.getSize() - 1 - line) == null) {

                        newX = board.getSize() - 1 - column;
                        newy = board.getSize() - 1 - line;
                        break;
                    }
                }
            }
            if (newX != -1) {
                break;
            }
        }

        board.getCoordsToPiece(x1, y1).setCoords(newX, newy);
        board.setCoordsToId(newX, newy, board.getCoordsToPiece(x1, y1).getNrId());
        board.getCoordsToPiece(newX, newy).changeTeam();
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        for (Movement movement : getMovementsPiece()) {

            if (movement.canMovePiece(x0, y0, x1, y1, board, 2)) {

                if (board.getCoordsToPiece(x1, y1) != null && board.getCoordsToPiece(x1, y1).isHommer()) {

                    capturesHomer(x0, y0, x1, y1, board);
                }
                return true;
            }
        }

        return false;

    }
}
