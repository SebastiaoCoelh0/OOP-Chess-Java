package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;
import java.util.Objects;

public class Board {

    int size;
    int numPieces;
    HashMap<String, Integer> coordsToId = new HashMap<String, Integer>(); //"x,y" to id
    HashMap<Integer, Piece> idToPiece = new HashMap<Integer, Piece>(); //id,Pieces
    String team = "BLACK";
    int playsWithoutCaptures = 0;
    int capturesBlack = 0;
    int capturesWhite = 0;
    int validPlaysBlack = 0;
    int validPlaysWhite = 0;
    int invalidAttemptsBlack = 0;
    int invalidAttemptsWhite = 0;

    public Board() {
    }

    public int getPlaysWithoutCaptures() {
        return playsWithoutCaptures;
    }

    public String getTeam() {
        return team;
    }

    public void changeTeam() {

        if (Objects.equals(team, "BLACK")) {

            team = "WHITE";
        } else {

            team = "BLACK";
        }
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

    public Integer getCoordsToId(int x, int y) {
        return coordsToId.get(x + "," + y);
    }

    public void setSize(int size) {

        this.size = size;
    }

    public void setNumPieces(int numPieces) {
        this.numPieces = numPieces;
    }

    public void setCoordsToId(int x, int y, int id) {
        this.coordsToId.put(x + "," + y, id);
    }

    public void setIdToPiece(HashMap<Integer, Piece> idToPiece) {
        this.idToPiece = idToPiece;
    }

    public void addCapturesBlack() {
        capturesBlack++;
    }

    public void addCapturesWhite() {
        capturesWhite++;
    }

    public void addValidPlaysBlack() {
        validPlaysBlack++;
    }

    public void addValidPLaysWhite() {
        validPlaysWhite++;
    }

    public void addInvalidAttemptsBlack() {
        invalidAttemptsBlack++;
    }

    public void addInvalidAttemptsWhite() {
        invalidAttemptsWhite++;
    }

    public void addPlaysWithoutCaptures() {
        playsWithoutCaptures++;
    }

    public void resetPlaysWithoutCaptures() {
        playsWithoutCaptures = 0;
    }

    public int getCapturesBlack() {
        return capturesBlack;
    }

    public int getCapturesWhite() {
        return capturesWhite;
    }

    public int getValidPlaysBlack() {
        return validPlaysBlack;
    }

    public int getValidPlaysWhite() {
        return validPlaysWhite;
    }

    public int getInvalidAttemptsBlack() {
        return invalidAttemptsBlack;
    }

    public int getInvalidAttemptsWhite() {
        return invalidAttemptsWhite;
    }
}
