package pt.ulusofona.lp2.deisichess.movement;

import pt.ulusofona.lp2.deisichess.Board;

public class MoveDiagonally extends Movement {

    public MoveDiagonally() {

    }

    @Override
    public boolean canMovePiece(int x0, int y0, int x1, int y1, Board board, int delta) {

        int difX = x0 - x1;
        int difY = y0 - y1;

        if (Math.abs(difX) != Math.abs(difY)) {
            return false;
        }

        if (Math.abs(difX) > delta) {
            return false;
        }

        for (int i = 1; i < Math.abs(difX); i++) {

            if (difX < 0) { // down

                if (difY < 0) { // down right

                    if (board.getCoordsToPiece(x0 + i, y0 + i) != null) {
                        return false;
                    }
                } else { // down left

                    if (board.getCoordsToPiece(x0 + i, y0 - i) != null) {
                        return false;
                    }
                }
            } else { // up

                if (difY < 0) { // up right

                    if (board.getCoordsToPiece(x0 - i, y0 + i) != null) {
                        return false;
                    }
                } else { // up left

                    if (board.getCoordsToPiece(x0 - i, y0 - i) != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
