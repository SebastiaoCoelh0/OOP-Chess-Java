package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
        } //tenta abrir ficheiro


        HashMap<Integer, Pieces> idToPiece = new HashMap<>();

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

            Pieces piecesTemp = new Pieces(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
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
                coordsTemp.put(board.getSize() - lineBoard, columnBoard);

                if (idToPiece.containsKey(Integer.parseInt(parts[columnBoard]))) {

                    board.setCoordsToId((HashMap<HashMap<Integer, Integer>, Integer>) new HashMap<>().put(coordsTemp, Integer.parseInt(parts[columnBoard])));
                    idToPiece.get(Integer.parseInt(parts[columnBoard])).setCoords(board.getSize() - lineBoard, columnBoard);

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
        //TODO

        return true;
    }

    public String[] getSquareInfo(int x, int y) {

        HashMap<Integer, Integer> coodrs = new HashMap<>();
        return new String[]{};
    }

    public String[] getPieceInfo(int ID) {
        return new String[]{};
    }

    public String getPieceInfoAsString(int ID) {
        return "";
    }

    public int getCurrentTeamID() {
        return 0;
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
