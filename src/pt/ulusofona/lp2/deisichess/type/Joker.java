package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;
import pt.ulusofona.lp2.deisichess.movement.Movement;

public class Joker extends Piece {

    Piece currentPiece = new Queen(getNrId(), getName(), getTeam());

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

    @Override
    public String toString() {

        StringBuilder str = new StringBuilder();
        str.append(getNrId()).append(" | Joker/");

        switch (currentPiece.getPieceType()) {
            case 1:
                str.append("Rainha | ");
                break;
            case 2:
                str.append("Ponei Mágico | ");
                break;
            case 3:
                str.append("Padre da Vila | ");
                break;
            case 4:
                str.append("TorreHor | ");
                break;
            case 5:
                str.append("TorreVert | ");
                break;
            case 6:
                str.append("Hommer Simpson | ");
                break;
            default:
                str.append("Joker | ");
        }

        if (getInGame()) {

            str.append(getPoints()).append(" | ").append(getTeam()).append(" | ").append(getName()).append(" @(");
            str.append(getCoords().split(",")[0]).append(", ").append(getCoords().split(",")[1]).append(")");

        } else {

            str.append(getPoints()).append(" | ").append(getTeam()).append(" | ").append(getName()).append(" @(n/a)");
        }
        return str.toString();
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
