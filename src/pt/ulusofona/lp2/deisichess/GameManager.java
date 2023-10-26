package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {

    public GameManager() {
    } //construtor vazio pedido pelos profs.

    public boolean loadGame(File file) {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {

            return false;
        } //tenta abrir ficheiro

        int boardSize = 0;
        int numPieces = 0;
        HashMap<Integer, Pieces> idToPiece = new HashMap<>();

        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boardSize = Integer.parseInt(line);

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        numPieces = Integer.parseInt(line);

        for (int i = 0; i < numPieces; i++) {

            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = line.split(":");

            Pieces piecesTemp = new Pieces(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            idToPiece.put(Integer.parseInt(parts[0]), piecesTemp);

        }

        for (int i = 0; i < boardSize; i++) {

            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] parts = line.split(":");

            for (int j = 0; j < parts.length; j++) {

                if (idToPiece.containsKey(Integer.parseInt(parts[j]))) {

                    idToPiece.get(Integer.parseInt(parts[j])).setCoords(boardSize - i, j);
                }
            }
        }

        return true;
    }

    public int getBoardSize() {
        return 0;
    }

    public boolean move(int x0, int y0, int x1, int y1) {
        return true;
    }

    public String[] getSquareInfo(int x, int y) {
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
