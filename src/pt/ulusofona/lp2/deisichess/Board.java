package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;
import java.util.Objects;

public class Board {

    int size;
    int numPieces;
    HashMap<HashMap<Integer, Integer>, Integer> coordsToId = new HashMap<HashMap<Integer, Integer>, Integer>();
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
