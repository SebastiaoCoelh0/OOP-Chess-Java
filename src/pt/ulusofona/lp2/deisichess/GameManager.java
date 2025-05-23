package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.type.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

public class GameManager {

    public GameManager() {
    } //construtor vazio pedido pelos profs.

    Board board = new Board();
    Stack<Board> boardHistory = new Stack<>();

    public boolean loadGameTest(File file) { //chamar esta funcao nos testes
        try {
            loadGame(file);
            return true;

        } catch (IOException io) {
            return false;
        } catch (InvalidGameInputException e) {
            throw new RuntimeException(e);
        }
    }

    public Piece createNewPiece(int type, int nrID, String name, int team) {

        switch (type) {

            case 0:
                return new King(nrID, name, team);
            case 1:
                return new Queen(nrID, name, team);
            case 2:
                return new MagicPony(nrID, name, team);
            case 3:
                return new VillagePriest(nrID, name, team);
            case 4:
                return new HorizontalTower(nrID, name, team);
            case 5:
                return new VerticalTower(nrID, name, team);
            case 6:
                return new HommerSimpson(nrID, name, team);
            case 7:
                return new Joker(nrID, name, team);
            case 8:
                return new PeterGriffin(nrID, name, team);
            default:
                return null;
        }
    }

    public void loadGame(File file) throws InvalidGameInputException, IOException {

        board = new Board(); //reset
        boardHistory = new Stack<>(); //reset

        BufferedReader reader;
        reader = new BufferedReader(new FileReader(file));
        HashMap<Integer, Piece> idToPiece = new HashMap<>();
        String lineReader;
        lineReader = reader.readLine();

        board.setSize(Integer.parseInt(lineReader));
        lineReader = reader.readLine();
        board.setNumPieces(Integer.parseInt(lineReader));

        for (int i = 0; i < board.getNumPieces(); i++) {

            lineReader = reader.readLine();
            String[] parts = lineReader.split(":");

            if (parts.length > 4) {

                throw new InvalidGameInputException(3 + i, "DADOS A MAIS (Esperava: 4 ; Obtive: " + parts.length + ")");

            } else if (parts.length < 4) {

                throw new InvalidGameInputException(3 + i, "DADOS A MENOS (Esperava: 4 ; Obtive: " + parts.length + ")");
            }

            Piece piecesTemp = createNewPiece(Integer.parseInt(parts[1]), Integer.parseInt(parts[0]), parts[3], Integer.parseInt(parts[2]));
            idToPiece.put(Integer.parseInt(parts[0]), piecesTemp);
        }

        for (int line = 0; line < board.getSize(); line++) {

            lineReader = reader.readLine();
            String[] parts = lineReader.split(":");

            for (int column = 0; column < parts.length; column++) {

                if (idToPiece.containsKey(Integer.parseInt(parts[column]))) {

                    board.setCoordsToId(column, line, Integer.parseInt(parts[column]));
                    idToPiece.get(Integer.parseInt(parts[column])).setCoords(column, line);
                    idToPiece.get(Integer.parseInt(parts[column])).setInGame(true);

                } else {
                    board.setCoordsToId(column, line, 0); //empty square
                }
            }
        }

        //check for more info
        lineReader = reader.readLine();

        if (lineReader != null) {

            board.setTeamPlaying(Integer.parseInt(lineReader)); //erro jogar ate ao fim gravar e ler ficheiro
            lineReader = reader.readLine();
            board.setStats(lineReader);
            lineReader = reader.readLine();

            while (lineReader != null) {

                idToPiece.get(Integer.parseInt(lineReader.split(":")[0])).setStats(lineReader);
                lineReader = reader.readLine();
            }
        }

        board.setIdToPiece(idToPiece);
    }

    public String[] getSquareInfo(int x, int y) {

        return board.getSquareInfo(x, y);
    }

    public int getBoardSize() {
        return board.getSize();
    }

    public boolean checkCoordsLimits(int val) {

        return val >= 0 && val < board.getSize();
    }

    public boolean checkPieceExists(int x, int y) {

        return getSquareInfo(x, y) != null && getSquareInfo(x, y).length != 0;
    }

    public boolean checkTeamPlaying(int x, int y) {
        return board.checkTeamPlaying(x, y);
    }

    public boolean checkSameTeamMove(int x0, int y0, int x1, int y1) {

        if (board.getCoordsToPiece(x1, y1) != null) {

            if (board.checkTeamPlaying(x1, y1)) {

                return false;
            }
        }
        return true;
    }

    public boolean checkValidMove(int x0, int y0, int x1, int y1) {


        if (!checkCoordsLimits(x0) || !checkCoordsLimits(y0) || !checkCoordsLimits(x1) || !checkCoordsLimits(y1)) {

            if (checkCoordsLimits(x0) && checkCoordsLimits(y0)) {

                board.getCoordsToPiece(x0, y0).addInvalidMove();
            }
            return false;
        }
        if (!checkPieceExists(x0, y0) || !checkTeamPlaying(x0, y0)) {

            if (board.getCoordsToPiece(x0, y0) != null && !checkTeamPlaying(x0, y0)) {

                board.getCoordsToPiece(x0, y0).addInvalidMove();
            }
            return false;
        }

        return true; //se for valido
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        boardHistory.push(board.copyClone()); //copys old board before making the move

        //System.out.println("Assertions.assertTrue(gameManager.move(" + x0 + ", " + y0 + ", " + x1 + ", " + y1 + "));");

        if (!checkValidMove(x0, y0, x1, y1)) {

            board.addInvalidAttempt();
            return false;
        }

        if (!checkSameTeamMove(x0, y0, x1, y1)) {

            board.addInvalidAttempt();
            board.getCoordsToPiece(x0, y0).addInvalidMove();

            return false;
        }

        if (!board.getCoordsToPiece(x0, y0).validPieceMovement(x0, y0, x1, y1, board)) {

            board.addInvalidAttempt();
            board.getCoordsToPiece(x0, y0).addInvalidMove();

            return false;
        }

        board.addValidPlays(x0, y0);

        if (board.getCoordsToId(x1, y1) != 0) { //captures piece

            board.captures(x0, y0, x1, y1);

        } else {

            board.addPlaysWithoutCaptures();
        }

        board.getCoordsToPiece(x0, y0).move(x1, y1); //atualiza as coords na peça
        board.setCoordsToId(x1, y1, board.getCoordsToId(x0, y0)); //atualiza as coords da peça na nova posição no tabuleiro
        board.setCoordsToId(x0, y0, 0); //atualiza as coords antigas da peça no tabuleiro
        board.endTurn();
        return true;
    }


    public String[] getPieceInfo(int ID) {

        return board.getPieceInfo(ID);
    }

    public String getPieceInfoAsString(int ID) {

        return board.getPieceInfoAsString(ID);
    }

    public int getCurrentTeamID() {

        return board.getTeamID();
    }

    public boolean gameOver() {

        int kingsTeamBlack = board.countKingsTeam(10);
        int kingsTeamWhite = board.countKingsTeam(20);
        int otherPiece = 0;
        int count = 0;

        for (int pieceId = 0; count < board.getNumPieces(); pieceId++) {

            if (board.getIdToPiece(pieceId) != null) {

                count++;

                if (board.getIdToPiece(pieceId).getInGame()) {

                    if (!board.getIdToPiece(pieceId).isKing()) {

                        otherPiece++;
                    }
                }
            }
        }

        if ((kingsTeamBlack == 0 || kingsTeamWhite == 0) || (kingsTeamWhite == 1 && kingsTeamBlack == 1 && otherPiece == 0) ||
                ((board.getCapturesWhite() != 0 || board.getCapturesBlack() != 0) && board.getPlaysWithoutCaptures() == 10)) {

            return true;
        }

        return false;
    }

    public ArrayList<String> getGameResults() {

        ArrayList<String> results = new ArrayList<>();
        results.add("JOGO DE CRAZY CHESS");

        int kingsTeamBlack = board.countKingsTeam(10);
        int kingsTeamWhite = board.countKingsTeam(20);


        if (kingsTeamBlack == 0 && kingsTeamWhite != 0) {

            results.add("Resultado: VENCERAM AS BRANCAS");

        } else if (kingsTeamWhite == 0 && kingsTeamBlack != 0) {

            results.add("Resultado: VENCERAM AS PRETAS");

        } else { // if (kingsTeamWhite == 1 && kingsTeamBlack == 1)  ou 10 jogadas sem captura

            results.add("Resultado: EMPATE");
        }

        results.add("---");

        results.add("Equipa das Pretas");
        results.add(String.valueOf(board.getCapturesBlack()));
        results.add(String.valueOf(board.getValidPlaysBlack()));
        results.add(String.valueOf(board.getInvalidAttemptsBlack()));

        results.add("Equipa das Brancas");
        results.add(String.valueOf(board.getCapturesWhite()));
        results.add(String.valueOf(board.getValidPlaysWhite()));
        results.add(String.valueOf(board.getInvalidAttemptsWhite()));

        return results;
    }

    public void saveGame(File file) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            writer.write(String.valueOf(board.getSize()));
            writer.newLine();

            writer.write(String.valueOf(board.getNumPieces()));
            writer.newLine();

            int countPieces1 = 0;

            for (int id = 0; countPieces1 < board.getNumPieces(); id++) {

                if (board.getIdToPiece(id) != null) {
                    countPieces1++;
                    writer.write(board.getIdToPiece(id).getPieceText());
                    writer.newLine();
                }
            }

            for (int line = 0; line < board.getSize(); line++) {

                for (int column = 0; column < board.getSize(); column++) {

                    if (board.getCoordsToPiece(column, line) != null) {

                        writer.write(String.valueOf(board.getCoordsToId(column, line)));
                    } else {

                        writer.write("0");
                    }
                    if (column != board.getSize() - 1) {
                        writer.write(":");
                    }
                }

                writer.newLine();
            }

            //team plying, captures, valid plays, invalid attempts
            writer.write(String.valueOf(getCurrentTeamID()));
            writer.newLine();
            writer.write(board.getStatsText());
            writer.newLine();

            int countPieces2 = 0;
            for (int id = 0; countPieces2 < board.getNumPieces(); id++) {

                if (board.getIdToPiece(id) != null) {

                    countPieces2++;
                    writer.write(board.getIdToPiece(id).getPieceStatsText());
                    writer.newLine();
                }
            }


        } catch (IOException e) {
            throw new IOException("Error while saving game", e);
        }
    }

    public void undo() {
        if (!boardHistory.isEmpty()) {
            board = boardHistory.pop();
            board.updateTurnBasedPieces();
        }

    }

    public List<Comparable> getHints(int x, int y) {

        return board.getHints(x, y);
    }

    public Map<String, String> customizeBoard() {
        return Collections.emptyMap();
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

    public Board getBoard() {
        return board;
    }
}
