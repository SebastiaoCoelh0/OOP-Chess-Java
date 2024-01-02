package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.movement.Movement;

abstract public class Piece {

    int nrId;
    String name;
    int pieceType;
    int team;
    String coords; //x,y
    int points;
    int pointsCaptured = 0;
    int piecesCaptured = 0;
    int validMoves = 0;
    int invalidMoves = 0;
    Boolean inGame = false; //ou true caso em jogo
    Movement[] movementsPiece = null;

    public Piece(int nrId, String name, int team, int points, int pieceType, Movement[] movements) {

        this.nrId = nrId;
        this.name = name;
        this.team = team;
        this.points = points;
        this.pieceType = pieceType;
        this.movementsPiece = movements;
    }

    @Override
    public String toString() {


        if (getInGame()) {

            return getNrId() + " | " + getPieceType() + " | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (" + getCoords().split(",")[0] + ", " + getCoords().split(",")[1] + ")";

        } else {

            return getNrId() + " | " + getPieceType() + " | " + getPoints() + " | " + getTeam() + " | "
                    + getName() + " @ (n/a)";
        }

    }

    public void addInvalidMove() {
        invalidMoves++;
    }

    public int getInvalidMoves() {
        return invalidMoves;
    }

    public void setInvalidMoves(int invalidMoves) {
        this.invalidMoves = invalidMoves;
    }

    public void addvalidMove() {
        validMoves++;
    }

    public int getValidMoves() {
        return validMoves;
    }

    public void setValidMoves(int validMoves) {
        this.validMoves = validMoves;
    }

    public void addPiecesCaptured() {
        piecesCaptured++;
    }

    public int getPiecesCaptured() {
        return piecesCaptured;
    }

    public void setPiecesCaptured(int piecesCaptured) {
        this.piecesCaptured = piecesCaptured;
    }

    public Movement[] getMovementsPiece() {
        return movementsPiece;
    }

    public void addPointCapture(int points) {
        addPiecesCaptured();
        pointsCaptured += points;
    }

    public abstract boolean validPieceMovement(int x0, int y0, int x1, int y1, Board board);

    public boolean isKing() {
        return pieceType == 0;
    }

    public boolean isQueen() {
        return pieceType == 1;
    }

    public boolean isJoker() {
        return pieceType == 7;
    }

    public boolean isHommer() {
        return pieceType == 6;
    }

    public String[] getPieceInfo() {

        String[] infoArray = new String[7];

        infoArray[0] = String.valueOf(getNrId());
        infoArray[1] = String.valueOf(getPieceType());
        infoArray[2] = String.valueOf(getTeam());
        infoArray[3] = getName();

        if (getInGame()) {
            infoArray[4] = "em jogo";
            infoArray[5] = getCoords().split(",")[0];
            infoArray[6] = getCoords().split(",")[1];

        } else {
            infoArray[4] = "capturado";
            infoArray[5] = "";
            infoArray[6] = "";
        }
        return infoArray;
    }

    public void move(int x, int y) {

        setCoords(x, y);
    }

    public void setPointsCaptured(int pointsCaptured) {
        this.pointsCaptured = pointsCaptured;
    }

    public int getPointsCaptured() {
        return pointsCaptured;
    }

    public void capture() {
        setInGame(false);
    }

    public void setInGame(Boolean inGame) {
        this.inGame = inGame;
    }

    public int getNrId() {
        return nrId;
    }

    public Boolean getInGame() {
        return inGame;
    }

    public int getPieceType() {
        return pieceType;
    }

    public int getTeam() {
        return team;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public String getCoords() {
        return coords;
    }

    public void setCoords(int x, int y) {
        this.coords = x + "," + y;
    }

    public String getPieceText() {

        return getNrId() + ":" + getPieceType() + ":" + getTeam() + ":" + getName();
    }


    public void setStats(String line) {

        String[] parts = line.split(":");
        setPointsCaptured(Integer.parseInt(parts[1]));
        setValidMoves(Integer.parseInt(parts[2]));
        setInvalidMoves(Integer.parseInt(parts[3]));
        setPiecesCaptured(Integer.parseInt(parts[4]));
        setPointsCaptured(Integer.parseInt(parts[5]));
    }

    public String getPieceStatsText() {

        return getNrId() + ":" + getPointsCaptured() + ":" + getValidMoves() + ":" + getInvalidMoves() + ":" +
                getPiecesCaptured() + ":" + getPointsCaptured();
    }
}
