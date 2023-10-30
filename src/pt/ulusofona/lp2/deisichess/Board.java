package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Board {

    int size;
    int numPieces;
    HashMap<HashMap<Integer, Integer>, Integer> coordsToId = new HashMap<HashMap<Integer, Integer>, Integer>();
    HashMap<Integer, Piece> idToPiece = new HashMap<Integer, Piece>(); //id,Pieces
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

    public Piece getIdToPiece(int id) {
        return idToPiece.get(id);
    }

    public Integer getCoordsToId(HashMap<Integer, Integer> coords) {
        return coordsToId.get(coords);
    }

    public void setSize(int size) {

        this.size = size;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public void setCoordsToId(HashMap<Integer, Integer> coords, int id) {
        this.coordsToId.put(coords, id);
    }

    public void setIdToPiece(HashMap<Integer, Piece> idToPiece) {
        this.idToPiece = idToPiece;
    }
}
