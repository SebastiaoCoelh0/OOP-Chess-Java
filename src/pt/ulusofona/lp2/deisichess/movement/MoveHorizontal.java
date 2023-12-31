package pt.ulusofona.lp2.deisichess.movement;

import pt.ulusofona.lp2.deisichess.Board;

public class MoveHorizontal extends Movement {

    public MoveHorizontal() {

    }

    @Override
    public boolean canMovePiece(int x0, int y0, int x1, int y1, Board board, int delta) {

        int difX = x0 - x1;
        int difY = y0 - y1;
        
        if (Math.abs(difX) > delta) {
            return false;
        }

        if (difX != 0 && difY == 0) { //horizontal

            for (int i = 1; i < Math.abs(difX); i++) {

                if (difX < 0) { //moved right

                    if (board.getCoordsToPiece(x0 + i, y0) != null) {

                        return false;
                    }

                } else { //moved left

                    if (board.getCoordsToPiece(x0 - i, y0) != null) {

                        return false;
                    }
                }
            }
        } else {
            return false;
        }

        return true;
    }
}
