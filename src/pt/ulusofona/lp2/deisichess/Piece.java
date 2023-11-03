package pt.ulusofona.lp2.deisichess;

public class Piece {

    int nrId;
    String name;
    int pieceType;
    int team;
    String coords; //x,y
    String inGame = "capturado"; //ou em jogo

    public Piece(int nrId, String name, int pieceType, int team) {

        this.nrId = nrId;
        this.name = name;
        this.pieceType = pieceType;
        this.team = team;
    }

    public void capture() {

        inGame = "capturado";
    }

    public void setInGame(String inGame) {
        this.inGame = inGame;
    }

    public void move(int x, int y) {

        coords = x + "," + y;
    }

    public String getInGame() {
        return inGame;
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

    public String getCoords() {
        return coords;
    }
}
