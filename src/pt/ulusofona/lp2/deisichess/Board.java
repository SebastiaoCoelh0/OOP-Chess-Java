package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Board {

    int size;
    int numPieces;
    HashMap<HashMap<Integer, Integer>, Integer> coordsToId;
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

    public HashMap<HashMap<Integer, Integer>, Integer> getCoordsToId() {
        return coordsToId;
    }

    public void setSize(int size) {

        this.size = size;
    }

    public void setNumPieces(int numPieces) {

        this.numPieces = numPieces;
    }

    public void setCoordsToId(HashMap<HashMap<Integer, Integer>, Integer> coordsToId) {
        this.coordsToId = coordsToId;
    }

    public void setIdToPiece(HashMap<Integer, Pieces> idToPiece) {

        this.idToPiece = idToPiece;
    }
}
