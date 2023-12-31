package pt.ulusofona.lp2.deisichess.movement;

import pt.ulusofona.lp2.deisichess.Board;

public class MoveMagicPoney extends Movement {
    @Override
    public boolean canMovePiece(int x0, int y0, int x1, int y1, Board board, int delta) {

        int difX = x0 - x1;
        int difY = y0 - y1;

        if (difX == 0 || difY == 0) {
            return false;
        }

        if (difX < 0) { //right side

            if (difY < 0) {// right down

                if (board.getCoordsToPiece(x0 + 1, y0) == null && board.getCoordsToPiece(x0 + 2, y0) == null &&
                        board.getCoordsToPiece(x0 + 2, y0 + 1) == null) { //moved right first and after down

                    return true;

                } else if (board.getCoordsToPiece(x0, y0 + 1) == null && board.getCoordsToPiece(x0, y0 + 2) == null &&
                        board.getCoordsToPiece(x0 + 1, y0 + 2) == null) {// moved down first and then right

                    return true;
                }
            } else { //right up

                if (board.getCoordsToPiece(x0 + 1, y0) == null && board.getCoordsToPiece(x0 + 2, y0) == null &&
                        board.getCoordsToPiece(x0 + 2, y0 - 1) == null) { //moved right first and then up

                    return true;

                } else if (board.getCoordsToPiece(x0, y0 - 1) == null && board.getCoordsToPiece(x0, y0 - 2) == null &&
                        board.getCoordsToPiece(x0 + 1, y0 - 2) == null) {// moved up first and then right

                    return true;
                }
            }
        } else { // left side

            if (difY < 0) {// left down

                if (board.getCoordsToPiece(x0 - 1, y0) == null && board.getCoordsToPiece(x0 - 2, y0) == null &&
                        board.getCoordsToPiece(x0 - 2, y0 + 1) == null) { //moved left first and after down

                    return true;

                } else if (board.getCoordsToPiece(x0, y0 + 1) == null && board.getCoordsToPiece(x0, y0 + 2) == null &&
                        board.getCoordsToPiece(x0 - 1, y0 + 2) == null) {// moved down first and then left

                    return true;
                }
            } else { //left up

                if (board.getCoordsToPiece(x0 - 1, y0) == null && board.getCoordsToPiece(x0 - 2, y0) == null &&
                        board.getCoordsToPiece(x0 - 2, y0 - 1) == null) { //moved left first and then up

                    return true;

                } else if (board.getCoordsToPiece(x0, y0 - 1) == null && board.getCoordsToPiece(x0, y0 - 2) == null &&
                        board.getCoordsToPiece(x0 - 1, y0 - 2) == null) {// moved up first and then left

                    return true;
                }
            }
        }
        return false;
    }
}
