package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Board {

    int size;
    int numPieces;
    HashMap<HashMap<Integer, Integer>, Integer> coordsToId;
    HashMap<Integer, Piece> idToPiece; //id,Pieces
    int team = 0;
    int numPlays = 0;

    public Board() {
    }

    public int getNumPlays() {
        return numPlays;
    }

    public void addPlay() {
        numPlays++;
    }

    public int getTeam() {
        return team;
    }

    public int getSize() {
        return size;
    }

    public int getNumPieces() {
        return numPieces;
    }

    public HashMap<Integer, Piece> getIdToPiece() {
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

    public void setIdToPiece(HashMap<Integer, Piece> idToPiece) {
        this.idToPiece = idToPiece;
    }
}
