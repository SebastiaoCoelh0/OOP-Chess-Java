package pt.ulusofona.lp2.deisichess;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class GameManager {

    boolean loadGame(File file) {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {

            return false;
        }

        String line = null;

        do {

            try {

                line = reader.readLine();
            } catch (IOException e) {

                throw new RuntimeException(e);
            }

            if (line != null) {


            }
        } while (line != null);

        return true;
    }

    int getBoardSize() {
        return 0;
    }

    boolean move(int x0, int y0, int x1, int y1) {
        return true;
    }

    String[] getSquareInfo(int x, int y) {
        return new String[]{};
    }

    String[] getPieceInfo(int ID) {
        return new String[]{};
    }

    String getPieceInfoAsString(int ID) {
        return "";
    }

    int getCurrentTeamID() {
        return 0;
    }

    boolean gameOver() {
        return true;
    }

    ArrayList<String> getGameResults() {
        return new ArrayList<>();
    }

    JPanel getAuthorsPanel() {
        return new JPanel();
    }

}
