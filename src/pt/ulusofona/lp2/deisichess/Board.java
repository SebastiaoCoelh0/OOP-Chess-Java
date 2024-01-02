package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.type.*;

import java.util.*;

public class Board {

    int size;
    int numPieces;
    int turn = 0;
    HashMap<String, Integer> coordsToId = new HashMap<String, Integer>(); //"x,y" to id
    HashMap<Integer, Piece> idToPiece = new HashMap<Integer, Piece>(); //id,Pieces
    String teamPlaying = "BLACK";
    int playsWithoutCaptures = 0;
    int capturesBlack = 0;
    int capturesWhite = 0;
    int validPlaysBlack = 0;
    int validPlaysWhite = 0;
    int invalidAttemptsBlack = 0;
    int invalidAttemptsWhite = 0;
    boolean[] capturedPiecesTypes = new boolean[9]; //each position is a piece type

    public Board() {
        Arrays.fill(capturedPiecesTypes, false);
    }

    public Board copyClone() {

        Board clonedBoard = new Board();

        // Create new HashMap instances to avoid sharing references
        clonedBoard.coordsToId = new HashMap<>(this.coordsToId);
        clonedBoard.idToPiece = new HashMap<>(this.idToPiece.size());

        for (Map.Entry<Integer, Piece> entry : this.idToPiece.entrySet()) {

            switch (entry.getValue().getPieceType()) {

                case 0:
                    King temp0 = (King) entry.getValue();
                    temp0 = temp0.copy();
                    temp0.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp0.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp0.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp0.setValidMoves(entry.getValue().getValidMoves());
                    temp0.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp0.setPiecesCaptured(entry.getValue().getPiecesCaptured());

                    clonedBoard.idToPiece.put(entry.getKey(), temp0);
                    break;
                case 1:
                    Queen temp1 = (Queen) entry.getValue();
                    temp1 = temp1.copy();
                    temp1.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp1.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp1.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp1.setValidMoves(entry.getValue().getValidMoves());
                    temp1.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp1.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp1);
                    break;
                case 2:
                    MagicPony temp2 = (MagicPony) entry.getValue();
                    temp2 = temp2.copy();
                    temp2.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp2.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp2.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp2.setValidMoves(entry.getValue().getValidMoves());
                    temp2.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp2.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp2);
                    break;
                case 3:
                    VillagePriest temp3 = (VillagePriest) entry.getValue();
                    temp3 = temp3.copy();
                    temp3.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp3.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp3.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp3.setValidMoves(entry.getValue().getValidMoves());
                    temp3.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp3.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp3);
                    break;
                case 4:
                    HorizontalTower temp4 = (HorizontalTower) entry.getValue();
                    temp4 = temp4.copy();
                    temp4.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp4.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp4.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp4.setValidMoves(entry.getValue().getValidMoves());
                    temp4.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp4.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp4);
                    break;
                case 5:
                    VerticalTower temp5 = (VerticalTower) entry.getValue();
                    temp5 = temp5.copy();
                    temp5.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp5.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp5.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp5.setValidMoves(entry.getValue().getValidMoves());
                    temp5.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp5.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp5);
                    break;
                case 6:
                    HommerSimpson temp6 = (HommerSimpson) entry.getValue();
                    temp6 = temp6.copy();
                    temp6.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp6.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp6.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp6.setValidMoves(entry.getValue().getValidMoves());
                    temp6.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp6.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp6);
                    break;
                case 7:
                    Joker temp7 = (Joker) entry.getValue();
                    temp7 = temp7.copy();
                    temp7.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp7.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp7.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp7.setValidMoves(entry.getValue().getValidMoves());
                    temp7.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp7.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp7);
                    break;
                case 8:
                    PeterGriffin temp8 = (PeterGriffin) entry.getValue();
                    temp8 = temp8.copy();
                    temp8.setInGame(entry.getValue().getInGame());
                    if (entry.getValue().getCoords() != null) {

                        temp8.setCoords(Integer.parseInt(entry.getValue().getCoords().split(",")[0]), Integer.parseInt(entry.getValue().getCoords().split(",")[1]));
                    }
                    temp8.setPointsCaptured(entry.getValue().getPointsCaptured());
                    temp8.setValidMoves(entry.getValue().getValidMoves());
                    temp8.setInvalidMoves(entry.getValue().getInvalidMoves());
                    temp8.setPiecesCaptured(entry.getValue().getPiecesCaptured());
                    clonedBoard.idToPiece.put(entry.getKey(), temp8);
                    break;
                default:
                    return null;
            }
        }

        clonedBoard.size = this.size;
        clonedBoard.numPieces = this.numPieces;
        clonedBoard.turn = this.turn;
        clonedBoard.teamPlaying = this.teamPlaying;
        clonedBoard.playsWithoutCaptures = this.playsWithoutCaptures;
        clonedBoard.capturesBlack = this.capturesBlack;
        clonedBoard.capturesWhite = this.capturesWhite;
        clonedBoard.validPlaysBlack = this.validPlaysBlack;
        clonedBoard.validPlaysWhite = this.validPlaysWhite;
        clonedBoard.invalidAttemptsBlack = this.invalidAttemptsBlack;
        clonedBoard.invalidAttemptsWhite = this.invalidAttemptsWhite;
        clonedBoard.capturedPiecesTypes = this.capturedPiecesTypes;

        return clonedBoard;
    }

    public List<Comparable> getHints(int x, int y) {

        if (getCoordsToPiece(x, y) == null || getCoordsToPiece(x, y).getTeam() != getTeamID()) {
            return null;
        }

        List<Comparable> hints = new ArrayList<Comparable>();

        for (int column = 0; column < getSize(); column++) {

            for (int line = 0; line < getSize(); line++) {

                if (getCoordsToPiece(x, y).validPieceMovement(x, y, column, line, this)) {

                    if (getCoordsToPiece(column, line) != null) {

                        if (getCoordsToPiece(column, line).getTeam() != getCoordsToPiece(x, y).getTeam()) {

                            hints.add(new Hint(column, line, getCoordsToPiece(column, line).getPoints()));
                        }
                    } else {

                        hints.add(new Hint(column, line, 0));
                    }
                }
            }
        }

        return hints;
    }

    public boolean[] getCapturedPiecesTypes() {
        return capturedPiecesTypes;
    }

    public boolean getCapturedPiecesTypesPos(int pos) {
        return capturedPiecesTypes[pos];
    }

    public String getPieceNameText(int type) {

        switch (type) {

            case 0:
                return "Rei";

            case 1:
                return "Rainha";

            case 2:
                return "Ponei Mágico";

            case 3:
                return "Padre da Vila";

            case 4:
                return "TorreHor";

            case 5:
                return "TorreVert";

            case 6:
                return "Homer Simpson";

            case 7:

                int remainder = getTurn() % 6;
                switch (remainder) {
                    case 0:
                        return "Joker/Rainha";
                    case 1:
                        return "Joker/Ponei Mágico";
                    case 2:
                        return "Joker/Padre da Vila";
                    case 3:
                        return "Joker/TorreHor";
                    case 4:
                        return "Joker/TorreVert";
                    case 5:
                        return "Joker/Homer Simpson";
                    default: //error
                }

            case 8:
                return "Petter Griffin";

            default:
                return "";
        }
    }

    public String[] getCapturedPiecesTypesArrayString() {

        String[] arrayStr = new String[getCapturedPiecesTypes().length];

        for (int pos = 0; pos < arrayStr.length; pos++) {

            if (getCapturedPiecesTypesPos(pos)) {

                arrayStr[pos] = getPieceNameText(pos);

            } else {
                arrayStr[pos] = "";
            }

        }
        return arrayStr;
    }

    public String getPieceInfoAsString(int id) {

        if (getIdToPiece(id) == null) {
            return null;
        }

        return getIdToPiece(id).toString();
    }

    public List<Piece> getListPieces() {

        List<Piece> list = new ArrayList<>();

        for (Map.Entry<Integer, Piece> entry : idToPiece.entrySet()) {

            list.add(entry.getValue());
        }
        return list;
    }

    public String[] getSquareInfo(int x, int y) {

        if (getCoordsToPiece(x, y) != null) {

            String[] info = new String[5];
            Piece temp = getCoordsToPiece(x, y);

            info[0] = String.valueOf(temp.getNrId());
            info[1] = String.valueOf(temp.getPieceType());
            info[2] = String.valueOf(temp.getTeam());
            info[3] = temp.getName();
            info[4] = temp.getPieceType() + "_" + temp.getTeam() + ".png";

            return info;
        }
        return new String[]{};
    }


    public String[] getPieceInfo(int id) {

        if (getIdToPiece(id) == null) {
            return null;
        }

        return getIdToPiece(id).getPieceInfo();
    }

    public void addPointCapture(int x0, int y0, int x1, int y1) {


        getCoordsToPiece(x0, y0).addPointCapture(getCoordsToPiece(x1, y1).getPoints());
    }

    public void addCapturedPieceType(int x, int y) {

        capturedPiecesTypes[getCoordsToPiece(x, y).getPieceType()] = true;
    }

    public Boolean checkTeamPlaying(int x, int y) {

        return getCoordsToPiece(x, y).getTeam() == getTeamID();
    }

    public int getPlaysWithoutCaptures() {
        return playsWithoutCaptures;
    }

    public String getTeamPlaying() {
        return teamPlaying;
    }

    public int getTeamID() {

        if (Objects.equals(getTeamPlaying(), "BLACK")) {
            return 10;
        }
        return 20;
    }

    public int getTurn() {
        return turn;
    }

    public void endTurn() {
        turn++;
        updateTurnBasedPieces();
        changeTeam();
    }

    public void updateTurnBasedPieces() {
        hommerCanMove();
        jokerUpdate();
    }

    public void changeTeam() {

        if (Objects.equals(teamPlaying, "BLACK")) {

            teamPlaying = "WHITE";
        } else {

            teamPlaying = "BLACK";
        }
    }

    public void hommerCanMove() {

        int countPiece = 0;

        for (int id = 0; countPiece < getNumPieces(); id++) {

            if (getIdToPiece(id) != null) {

                countPiece++;
                if (getIdToPiece(id).isHommer()) {

                    HommerSimpson hommerSimpson = (HommerSimpson) getIdToPiece(id);

                    if (getTurn() % 3 == 0) {

                        hommerSimpson.isSleeping();
                    } else {

                        hommerSimpson.isNotSleeping();
                    }
                }
            }
        }
    }

    public void jokerUpdate() {

        int countPiece = 0;

        for (int id = 0; countPiece < getNumPieces(); id++) {

            countPiece++;
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

    public int getNumPieces() { //confirmar se ja foi a todas as peças ou nao!!
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

    public void addCaptures() {

        resetPlaysWithoutCaptures();

        if (getTeamID() == 10) {

            addCapturesBlack();

        } else {

            addCapturesWhite();
        }
    }

    public void addValidPlays(int x, int y) {

        getCoordsToPiece(x, y).addvalidMove();

        if (getTeamID() == 10) {

            addValidPlaysBlack();

        } else {

            addValidPLaysWhite();
        }

    }

    public void addValidPlaysBlack() {
        validPlaysBlack++;
    }

    public void addValidPLaysWhite() {
        validPlaysWhite++;
    }

    public void addInvalidAttempt() {

        if (getTeamID() == 10) {
            addInvalidAttemptsBlack();
        } else {
            addInvalidAttemptsWhite();
        }
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

    public int countKingsTeam(int team) {

        int count = 0;
        int kings = 0;

        for (int pieceId = 0; count < getNumPieces(); pieceId++) {

            if (getIdToPiece(pieceId) != null) {

                count++;

                if (getIdToPiece(pieceId).getInGame()) {

                    if (getIdToPiece(pieceId).getTeam() == team && getIdToPiece(pieceId).isKing()) {

                        kings++;

                    }
                }
            }
        }

        return kings;
    }

    public void setTeamPlaying(int teamID) {

        if (teamID == 10) {

            this.teamPlaying = "BLACK";
        } else {

            this.teamPlaying = "WHITE";
        }
    }

    public void captures(int x0, int y0, int x1, int y1) {

        addCapturedPieceType(x1, y1);
        addPointCapture(x0, y0, x1, y1); //adds points and number of pieces captured
        if (!getCoordsToPiece(x0, y0).isPetter() && !getCoordsToPiece(x1, y1).isHommer()) {

            getCoordsToPiece(x1, y1).capture();
        }
        addCaptures();
    }

    public void setCapturesBlack(int capturesBlack) {
        this.capturesBlack = capturesBlack;
    }

    public void setCapturesWhite(int capturesWhite) {
        this.capturesWhite = capturesWhite;
    }

    public void setValidPlaysBlack(int validPlaysBlack) {
        this.validPlaysBlack = validPlaysBlack;
    }

    public void setValidPlaysWhite(int validPlaysWhite) {
        this.validPlaysWhite = validPlaysWhite;
    }

    public void setInvalidAttemptsBlack(int invalidAttemptsBlack) {
        this.invalidAttemptsBlack = invalidAttemptsBlack;
    }

    public void setInvalidAttemptsWhite(int invalidAttemptsWhite) {
        this.invalidAttemptsWhite = invalidAttemptsWhite;
    }

    public void setStats(String line) {

        String[] parts = line.split(":");

        setCapturesBlack(Integer.parseInt(parts[0]));
        setCapturesWhite(Integer.parseInt(parts[1]));

        setValidPlaysBlack(Integer.parseInt(parts[2]));
        setValidPlaysWhite(Integer.parseInt(parts[3]));

        setInvalidAttemptsBlack(Integer.parseInt(parts[4]));
        setInvalidAttemptsWhite(Integer.parseInt(parts[5]));

    }

    public String getStatsText() {

        return getCapturesBlack() + ":" + getCapturesWhite() + ":" + getValidPlaysBlack() + ":" +
                getValidPlaysWhite() + ":" + getInvalidAttemptsBlack() + ":" + getInvalidAttemptsWhite();
    }
}
