package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        board.setSize(Integer.parseInt(line));

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        board.setNumPieces(Integer.parseInt(line));

        for (int i = 0; i < board.getNumPieces(); i++) {

            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = line.split(":");

            Piece piecesTemp = new Piece(Integer.parseInt(parts[0]), parts[3], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            idToPiece.put(Integer.parseInt(parts[0]), piecesTemp);

        }

        for (int lineBoard = 0; lineBoard < board.getSize(); lineBoard++) {

            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = line.split(":");

            for (int columnBoard = 0; columnBoard < parts.length; columnBoard++) {

                HashMap<Integer, Integer> coordsTemp = new HashMap<>();
                coordsTemp.put(columnBoard, lineBoard);

                if (idToPiece.containsKey(Integer.parseInt(parts[columnBoard]))) {

                    board.setCoordsToId((HashMap<HashMap<Integer, Integer>, Integer>) new HashMap<>().put(coordsTemp, Integer.parseInt(parts[columnBoard])));
                    idToPiece.get(Integer.parseInt(parts[columnBoard])).setCoords(columnBoard,lineBoard);

                } else {

                    board.setCoordsToId((HashMap<HashMap<Integer, Integer>, Integer>) new HashMap<>().put(coordsTemp, Integer.parseInt(parts[columnBoard])));
                }
            }
        }
        board.setIdToPiece(idToPiece);

        return true;
    }

    public int getBoardSize() {
        return board.getSize();
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        if (x0 < 0 || x1 < 0 || y0 < 0 || y1 < 0 || x0 > getBoardSize() || x1 > getBoardSize() || y0 > getBoardSize() || y1 > getBoardSize()) {

            return false;
        }

        HashMap<Integer, Integer> coordsStart = new HashMap<>();
        HashMap<Integer, Integer> coordsEnd = new HashMap<>();

        coordsStart.put(x0, y0);
        coordsEnd.put(x1, y1);

        String id = getSquareInfo(x0, y0)[0];

        String[] info = getPieceInfo(Integer.parseInt(id));


        return true;
    }

    public String[] getSquareInfo(int x, int y) {

        String[] squareInfo = new String[5];
        HashMap<Integer, Integer> coodrs = new HashMap<Integer, Integer>();
        coodrs.put(x, y);

        if (board.getCoordsToId() == null) {
            return new String[]{};
        }

        squareInfo[0] = String.valueOf(board.getCoordsToId().get(coodrs));

        Piece tempPiece = board.getIdToPiece().get(Integer.parseInt(squareInfo[0]));

        squareInfo[1] = String.valueOf(tempPiece.getPieceType());
        squareInfo[2] = String.valueOf(tempPiece.getTeam());
        squareInfo[3] = tempPiece.getName();

        if (tempPiece.getTeam() == 0) {
            squareInfo[4] = "crazy_emoji_white.png";
        } else {
            squareInfo[4] = "crazy_emoji_black.png";
        }

        return squareInfo;
    }

    public String[] getPieceInfo(int ID) {

        String[] pieceInfo = new String[7];

        pieceInfo[0] = String.valueOf(ID);
        pieceInfo[1] = String.valueOf(board.getIdToPiece().get(ID).getPieceType());
        pieceInfo[2] = String.valueOf(board.getIdToPiece().get(ID).getTeam());
        pieceInfo[3] = String.valueOf(board.getIdToPiece().get(ID).getName());
        pieceInfo[4] = String.valueOf(board.getIdToPiece().get(ID).getInGame());

        Iterator<Map.Entry<Integer, Integer>> iterator = board.getIdToPiece().get(ID).getCoords().entrySet().iterator();
        Map.Entry<Integer, Integer> entry = iterator.next();

        pieceInfo[5] = String.valueOf(entry.getKey());
        pieceInfo[6] = String.valueOf(entry.getValue());

        return pieceInfo;
    }

    public String getPieceInfoAsString(int ID) {

        String[] pieceInfo = getPieceInfo(ID);

        return pieceInfo[0] + "|" + pieceInfo[1] + "|" + pieceInfo[2] + "|" + pieceInfo[3] + "@(" + pieceInfo[5] + "," + pieceInfo[6] + ")";
    }

    public int getCurrentTeamID() {

        return board.getTeam();
    }

    public boolean gameOver() {
        return true;
    }

    public ArrayList<String> getGameResults() {
        return new ArrayList<>();
    }

    public JPanel getAuthorsPanel() {
        return new JPanel();
    }

}
