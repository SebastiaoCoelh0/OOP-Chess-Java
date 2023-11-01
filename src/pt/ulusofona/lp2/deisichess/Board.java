package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;
import java.util.Objects;

public class Board {

    int size;
    int numPieces;
    HashMap<String, Integer> coordsToId = new HashMap<String, Integer>(); //"x,y" to id
    HashMap<Integer, Piece> idToPiece = new HashMap<Integer, Piece>(); //id,Pieces
    String team = "BLACK";
    int numPlays = 0;
    int playsWhitoutCaptures = 0;
    int capturesBlack = 0;
    int capturesWhite = 0;
    int notValidPlaysBlack = 0;
    int notValidPlaysWhite = 0;
    int missedAttemptsBlack = 0;
    int missedAttemptsWhite = 0;

    public Board() {
    }

    public int getPlaysWhitoutCaptures() {
        return playsWhitoutCaptures;
    }


    public int getNumPlays() {
        return numPlays;
    }

    public void addPlay() {
        numPlays++;
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

    public void addNotValidPlaysBlack() {
        notValidPlaysBlack++;
    }

    public void addNotValidPLaysWhite() {
        notValidPlaysWhite++;
    }

    public void addMissedAttemptsBlack() {
        missedAttemptsBlack++;
    }

    public void addMissedAttemptsWhite() {
        missedAttemptsWhite++;
    }

    public void addPlaysWithoutCaptures() {
        playsWhitoutCaptures++;
    }

    public int getCapturesBlack() {
        return capturesBlack;
    }

    public int getCapturesWhite() {
        return capturesWhite;
    }

    public int getNotValidPlaysBlack() {
        return notValidPlaysBlack;
    }

    public int getNotValidPlaysWhite() {
        return notValidPlaysWhite;
    }

    public int getMissedAttemptsBlack() {
        return missedAttemptsBlack;
    }

    public int getMissedAttemptsWhite() {
        return missedAttemptsWhite;
    }
}
