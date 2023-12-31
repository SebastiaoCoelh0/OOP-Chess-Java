package pt.ulusofona.lp2.deisichess.type;

import pt.ulusofona.lp2.deisichess.Board;
import pt.ulusofona.lp2.deisichess.Piece;

public class PeterGriffin extends Piece {

    public PeterGriffin(int nrId, String name, int team) {
        super(nrId, name, team, 2, 8, null);
    }

    @Override
    public String[] getPieceInfo() {
        return new String[0];
        //            squareInfo[4] = "peterGriffin.png";
    }

    public boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board) {

        //TODO
        return true;

    }
}
