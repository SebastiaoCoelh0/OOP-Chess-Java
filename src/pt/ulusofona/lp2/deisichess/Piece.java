package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Piece {

    int nrId;
    String name;
    int pieceType;
    int team;
    HashMap<Integer, Integer> coords = new HashMap<>();
    boolean inGame = true;

    public Piece() {
    }

    public Piece(int nrId, String name, int pieceType, int team) {

        this.nrId = nrId;
        this.name = name;
        this.pieceType = pieceType;
        this.team = team;
    }

    public boolean getInGame() {
        return inGame;
    }

    public void setCoords(int x, int y) {

        this.coords.put(x, y);
    }

    public int getNrId() {
        return nrId;
    }

    public int getPieceType() {
        return pieceType;
    }

    public int getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Integer> getCoords() {
        return coords;
    }
}
