package pt.ulusofona.lp2.deisichess;

import java.util.HashMap;

public class Pieces {

    int nrId;
    int pieceType;
    int team;
    HashMap<Integer, Integer> coords = new HashMap<>();

    public Pieces(int nrId, int pieceType, int team) {
        this.nrId = nrId;
        this.pieceType = pieceType;
        this.team = team;
    }

    public void setCoords(int x, int y) {

        this.coords.put(x, y);
    }
}
