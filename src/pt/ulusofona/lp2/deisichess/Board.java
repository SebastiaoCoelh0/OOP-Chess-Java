package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.type.HommerSimpson;
import pt.ulusofona.lp2.deisichess.type.Joker;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Board {

    int size;
    int numPieces;
    int turn = 0;
    HashMap<String, Integer> coordsToId = new HashMap<String, Integer>(); //"x,y" to id
    HashMap<Integer, Piece> idToPiece = new HashMap<Integer, Piece>(); //id,Pieces
    String team = "BLACK";
    int pointsBlack = 0;
    int pointsWhite = 0;
    int playsWithoutCaptures = 0;
    int capturesBlack = 0;
    int capturesWhite = 0;
    int validPlaysBlack = 0;
    int validPlaysWhite = 0;
    int invalidAttemptsBlack = 0;
    int invalidAttemptsWhite = 0;

    public Board() {
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        Board clonedBoard = (Board) super.clone();

        // Create new HashMap instances to avoid sharing references
        clonedBoard.coordsToId = new HashMap<>(this.coordsToId);
        clonedBoard.idToPiece = new HashMap<>(this.idToPiece.size());

        for (Map.Entry<Integer, Piece> entry : this.idToPiece.entrySet()) {

            clonedBoard.idToPiece.put(entry.getKey(), (Piece) entry.getValue().clone());
        }


        clonedBoard.size = this.size;
        clonedBoard.numPieces = this.numPieces;
        clonedBoard.turn = this.turn;
        clonedBoard.team = this.team;
        clonedBoard.pointsBlack = this.pointsBlack;
        clonedBoard.pointsWhite = this.pointsWhite;
        clonedBoard.playsWithoutCaptures = this.playsWithoutCaptures;
        clonedBoard.capturesBlack = this.capturesBlack;
        clonedBoard.capturesWhite = this.capturesWhite;
        clonedBoard.validPlaysBlack = this.validPlaysBlack;
        clonedBoard.validPlaysWhite = this.validPlaysWhite;
        clonedBoard.invalidAttemptsBlack = this.invalidAttemptsBlack;
        clonedBoard.invalidAttemptsWhite = this.invalidAttemptsWhite;

        return clonedBoard;
    }

    public String getPieceInfoAsString(int id) {

        if (getIdToPiece(id) == null) {
            return null;
        }

        return getIdToPiece(id).toString();
    }

    public String[] getSquareInfo(int x, int y) {

        if (getCoordsToPiece(x, y) != null) {

            String[] info = new String[5];
            Piece temp = getCoordsToPiece(x, y);

            info[0] = String.valueOf(temp.getNrId());
            info[1] = String.valueOf(temp.getPieceType());
            info[2] = String.valueOf(temp.getTeam());
            info[3] = temp.getName();
            info[4] = temp.getPieceType() + "_" + getTeamID() + ".png";

            return info;
        }

        return null;
    }


    public String[] getPieceInfo(int id) {

        if (getIdToPiece(id) == null) {
            return null;
        }

        return getIdToPiece(id).getPieceInfo();
    }

    public Boolean checkTeamPlaying(int x, int y) {

        int temp;

        if (Objects.equals(team, "BLACK")) {

            temp = 10;
        } else {

            temp = 20;
        }

        return getCoordsToPiece(x, y).getTeam() == temp;
    }

    public void addPointsWhite(int points) {
        pointsWhite += points;
    }

    public void addPointsBlack(int points) {
        pointsBlack += points;
    }

    public int getPlaysWithoutCaptures() {
        return playsWithoutCaptures;
    }

    public String getTeam() {
        return team;
    }

    public int getTeamID() {

        if (Objects.equals(getTeam(), "BLACK")) {
            return 10;
        }
        return 20;
    }

    public int getTurn() {
        return turn;
    }

    public void endTurn() {
        turn++;
        hommerCanMove();
        jokerUpdate();
        changeTeam();
    }

    public void changeTeam() {

        if (Objects.equals(team, "BLACK")) {

            team = "WHITE";
        } else {

            team = "BLACK";
        }
    }

    public void hommerCanMove() {

        for (int id = 0; id < getNumPieces(); id++) {

            if (getIdToPiece(id) != null) {

                if (getIdToPiece(id).isHommer()) {

                    HommerSimpson hommerSimpson = (HommerSimpson) getIdToPiece(id);

                    if (getTurn() % 3 == 0) {

                        hommerSimpson.isNotSleeping();
                    } else {

                        hommerSimpson.isSleeping();
                    }
                }
            }
        }
    }

    public void jokerUpdate() {

        for (int id = 0; id < getNumPieces(); id++) {

            if (getIdToPiece(id) != null) {

                if (getIdToPiece(id).isJoker()) {

                    Joker joker = (Joker) getIdToPiece(id);

                    joker.changeType(getTurn());

                }
            }
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

    public Piece getCoordsToPiece(int x, int y) {
        return getIdToPiece(getCoordsToId(x, y));
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
