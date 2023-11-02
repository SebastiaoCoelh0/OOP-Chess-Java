package pt.ulusofona.lp2.deisichess;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class GameManager {

    public GameManager() {
    } //construtor vazio pedido pelos profs.

    Board board = new Board();

    public boolean loadGame(File file) {

        board = new Board(); //reset
        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {

            return false;
        } //trys to open file

        HashMap<Integer, Piece> idToPiece = new HashMap<>();

        String lineReader = null;

        try {
            lineReader = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        board.setSize(Integer.parseInt(lineReader));

        try {
            lineReader = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        board.setNumPieces(Integer.parseInt(lineReader));

        for (int i = 0; i < board.getNumPieces(); i++) {

            try {
                lineReader = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = lineReader.split(":");

            Piece piecesTemp = new Piece(Integer.parseInt(parts[0]), parts[3], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            idToPiece.put(Integer.parseInt(parts[0]), piecesTemp);

        }

        for (int line = 0; line < board.getSize(); line++) {

            try {
                lineReader = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = lineReader.split(":");

            for (int column = 0; column < parts.length; column++) {

                if (idToPiece.containsKey(Integer.parseInt(parts[column]))) {

                    board.setCoordsToId(column, line, Integer.parseInt(parts[column]));
                    idToPiece.get(Integer.parseInt(parts[column])).move(column, line);
                    idToPiece.get(Integer.parseInt(parts[column])).setInGame("em jogo");

                } else {

                    board.setCoordsToId(column, line, 0);
                }
            }
        }

        board.setIdToPiece(idToPiece);
        return true;
    }

    public String[] getSquareInfo(int x, int y) {

        String[] squareInfo = new String[5];
        squareInfo[0] = String.valueOf(board.getCoordsToId(x, y));

        if (board.getCoordsToId(x, y) == null || board.getIdToPiece(Integer.parseInt(squareInfo[0])) == null) {

            return new String[]{};
        }

        Piece tempPiece = board.getIdToPiece(Integer.parseInt(squareInfo[0]));

        squareInfo[1] = String.valueOf(tempPiece.getPieceType());
        squareInfo[2] = String.valueOf(tempPiece.getTeam());
        squareInfo[3] = tempPiece.getName();

        if (tempPiece.getTeam() == 0) {
            squareInfo[4] = "crazy_emoji_black.png"; //TODO alterar para um rei de C e rei java
        } else {
            squareInfo[4] = "crazy_emoji_white.png";
        }

        return squareInfo;
    }

    public int getBoardSize() {
        return board.getSize();
    }

    boolean checkCoordsLimits(int val) {

        return val >= 0 && val < getBoardSize();
    }

    boolean checkPieceExists(int x, int y) {

        return getSquareInfo(x, y).length != 0;
    }

    boolean checkTeamPlaying(int x0, int y0) {

        if (Integer.parseInt(getSquareInfo(x0, y0)[2]) != getCurrentTeamID()) {

            if (getCurrentTeamID() == 0) {

                board.addNotValidPlaysBlack();
            } else {

                board.addNotValidPLaysWhite();
            }
            return false;
        }
        return true;
    }

    boolean checkKingMove(int x0, int y0, int x1, int y1) {

        return (x1 == x0 + 1 || x1 == x0 || x1 == x0 - 1) && (y1 == y0 + 1 || y1 == y0 || y1 == y0 - 1);
    }

    boolean checkSameTeamMove(int x0, int y0, int x1, int y1) {

        if (getSquareInfo(x1, y1).length != 0) {

            if (Integer.parseInt(getSquareInfo(x0, y0)[2]) == Integer.parseInt(getSquareInfo(x1, y1)[2])) {

                if (getCurrentTeamID() == 0) {

                    board.addNotValidPlaysBlack();
                } else {

                    board.addNotValidPLaysWhite();
                }
                return false;
            }
        }

        return true;
    }

    boolean checkValidMove(int x0, int y0, int x1, int y1) {


        if (!checkCoordsLimits(x0) || !checkCoordsLimits(y0) || !checkCoordsLimits(x1) || !checkCoordsLimits(y1)) {

            return false;
        }

        if (!checkPieceExists(x0, y0) || !checkTeamPlaying(x0, y0)) {

            return false;
        }

        if (!checkKingMove(x0, y0, x1, y1) || !checkSameTeamMove(x0, y0, x1, y1)) {

            return false;
        }

        return true; //se for valido
    }

    public boolean move(int x0, int y0, int x1, int y1) {


        if (!checkValidMove(x0, y0, x1, y1)) {

            return false;
        }

        if (board.getCoordsToId(x1, y1) != 0) {

            board.resetPlaysWithoutCaptures();
            board.getIdToPiece(Integer.parseInt(getSquareInfo(x1, y1)[0])).capture();

            if (getCurrentTeamID() == 0) {

                board.addCapturesBlack();
            } else {

                board.addCapturesWhite();
            }

        } else {
            board.addPlaysWithoutCaptures();
        }

        board.getIdToPiece(Integer.parseInt(getSquareInfo(x0, y0)[0])).move(x1, y1); //atualiza as coords na peça
        board.setCoordsToId(x1, y1, Integer.parseInt(getSquareInfo(x0, y0)[0])); //atualiza as coords da peça na nova posição no tabuleiro
        board.setCoordsToId(x0, y0, 0); //atualiza as coords antigas da peça no tabuleiro

        board.changeTeam();
        board.addPlay();
        return true;
    }


    public String[] getPieceInfo(int ID) {

        String[] pieceInfo = new String[7];

        pieceInfo[0] = String.valueOf(ID);
        pieceInfo[1] = String.valueOf(board.getIdToPiece(ID).getPieceType());
        pieceInfo[2] = String.valueOf(board.getIdToPiece(ID).getTeam());
        pieceInfo[3] = String.valueOf(board.getIdToPiece(ID).getName());
        pieceInfo[4] = String.valueOf(board.getIdToPiece(ID).getInGame());

        if (pieceInfo[4] == "em jogo") {

            pieceInfo[5] = board.getIdToPiece(ID).getCoords().split(",")[0];
            pieceInfo[6] = board.getIdToPiece(ID).getCoords().split(",")[1];
        } else {
            pieceInfo[5] = "";
            pieceInfo[6] = "";
        }


        return pieceInfo;
    }

    public String getPieceInfoAsString(int ID) {

        String[] pieceInfo = getPieceInfo(ID);

        if (pieceInfo[4] == "em jogo") {

            return pieceInfo[0] + " | " + pieceInfo[1] + " | " + pieceInfo[2] + " | " + pieceInfo[3] + " @ (" + pieceInfo[5] + ", " + pieceInfo[6] + ")";

        } else {

            return pieceInfo[0] + " | " + pieceInfo[1] + " | " + pieceInfo[2] + " | " + pieceInfo[3] + " @ (n/a)";
        }

    }

    public int getCurrentTeamID() {

        if (board.getTeam().equals("BLACK")) {
            return 0;
        }
        return 1;
    }

    public boolean gameOver() {

        int piecesTeamBlack = 0;
        int piecesTeamWhite = 0;

        for (int pieceId = 1; pieceId <= board.getNumPieces(); pieceId++) {

            if (Objects.equals(board.getIdToPiece(pieceId).inGame, "em jogo")) {

                if (Objects.equals(board.getIdToPiece(pieceId).getTeam(), 0)) {

                    piecesTeamBlack++;
                } else {
                    piecesTeamWhite++;
                }
            }
        }

        if ((piecesTeamBlack == 0 || piecesTeamWhite == 0) || (piecesTeamWhite == 1 && piecesTeamBlack == 1) ||
                ((board.getCapturesWhite() != 0 || board.capturesBlack != 0) && board.getPlaysWhitoutCaptures() == 10)) {

            return true;
        }
        return false;
    }

    public ArrayList<String> getGameResults() {

        ArrayList<String> results = new ArrayList<>();
        results.add("JOGO DE CRAZY CHESS");

        int piecesTeamBlack = 0;
        int piecesTeamWhite = 0;

        for (int pieceId = 1; pieceId <= board.getNumPieces(); pieceId++) {

            if (Objects.equals(board.getIdToPiece(pieceId).inGame, "em jogo")) {

                if (Objects.equals(board.getIdToPiece(pieceId).getTeam(), 0)) {

                    piecesTeamBlack++;
                } else {

                    piecesTeamWhite++;
                }
            }
        }

        if (piecesTeamBlack == 0 && piecesTeamWhite != 0) {

            results.add("RESULTADO: VENCERAM AS BRANCAS");

        } else if (piecesTeamWhite == 0 && piecesTeamBlack != 0) {

            results.add("RESULTADO: VENCERAM AS PRETAS");

        } else {

            results.add("RESULTADO: EMPATE");
        }

        results.add("---");

        results.add("Equipa das Pretas");
        results.add(String.valueOf(board.getCapturesBlack()));
        results.add(String.valueOf(board.getNotValidPlaysBlack()));
        results.add(String.valueOf(board.getMissedAttemptsBlack()));

        results.add("Equipa das Brancas");
        results.add(String.valueOf(board.getCapturesWhite()));
        results.add(String.valueOf(board.getNotValidPlaysWhite()));
        results.add(String.valueOf(board.getMissedAttemptsWhite()));

        return results;
    }

    public JPanel getAuthorsPanel() {

        JPanel jpanel = new JPanel();
        BufferedImage image;

        try {
            image = ImageIO.read(new File("src/images/memeSebastiao.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel icon = new JLabel(new ImageIcon(image));
        jpanel.add(icon);
        return jpanel;
    }

}
