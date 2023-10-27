package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Board {

    int size;
    int numPieces;
    HashMap<Integer, Pieces> idToPiece; //id,Pieces

    public Board() {
    }

    public int getSize() {
        return size;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public HashMap<Integer, Pieces> getIdToPiece() {
        return idToPiece;
    }

    public void setSize(int size) {

        this.size = size;
    }

    public void setNumPieces(int numPieces) {

        this.numPieces = numPieces;
    }

    public void setIdToPiece(HashMap<Integer, Pieces> idToPiece) {

        this.idToPiece = idToPiece;
    }
}
