package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class Joker extends Piece {

    Piece currentPiece;

    public Joker(int nrId, String name, int team) {
        super(nrId, name, team, 4, 7, new Movement[]{});
    }

    @Override
    public String[] getPieceInfo() {
        return new String[0];
    }

    public Piece jokerMovement(int turn) {
        //queen - pony - priest - horizontal tower - vertical Tower - Hommer

        int remainder = turn % 6;

        switch (remainder) {
            case 0:
                return new Queen(getNrId(), getName(), getTeam());
            case 1:
                return new MagicPony(getNrId(), getName(), getTeam());
            case 2:
                return new VillagePriest(getNrId(), getName(), getTeam());
            case 3:
                return new HorizontalTower(getNrId(), getName(), getTeam());
            case 4:
                return new VerticalTower(getNrId(), getName(), getTeam());
            case 5:
                return new HommerSimpson(getNrId(), getName(), getTeam());
            default:
                return null; //error
        }
    }

    public void changeType(int turn) {
        currentPiece = jokerMovement(turn);
    }

    public Board cloneBoard(Board board) throws CloneNotSupportedException {
        return (Board) board.clone();
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        Board boardCopy = null;
        try {
            boardCopy = cloneBoard(board);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

        try {
            return currentPiece.validPieceMovement(x0, y0, x1, y1, boardCopy);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }
}
