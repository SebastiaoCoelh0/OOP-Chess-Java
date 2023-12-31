package pt.ulusofona.lp2.deisichess.movement;

import pt.ulusofona.lp2.deisichess.Board;

public class MoveVertical extends Movement {

    public MoveVertical() {

    }

    @Override
    public boolean canMovePiece(int x0, int y0, int x1, int y1, Board board, int delta) {

        int difX = x0 - x1;
        int difY = y0 - y1;

        if (Math.abs(difY) > delta) {
            return false;
        }

        if (difX == 0 && difY != 0) { //verical

            for (int i = 1; i < Math.abs(difY); i++) {

                if (difY < 0) { //moved down

                    if (board.getCoordsToPiece(x0, y0 + i) != null) {

                        return false;
                    }

                } else { //moved up

                    if (board.getCoordsToPiece(x0, y0 - i) != null) {

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
