package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    public GameManager() {
    } //construtor vazio pedido pelos profs.

    public boolean loadGame(File file) {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {

            return false;
        }

        String line = null;

        try {

            line = reader.readLine();
        } catch (IOException e) {

            throw new RuntimeException(e);
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
