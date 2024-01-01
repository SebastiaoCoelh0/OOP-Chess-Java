package pt.ulusofona.lp2.deisichess;

import pt.ulusofona.lp2.deisichess.type.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//TODO tirar getter e setters com funcoes que façam as tarefas na classe
/*TODO LIST

    8 tipos de peças
        0- Rei 1 quadrado cada diracao -1000
        1- Rainha 5 quadrados cada direcao RAINHA NAO PODE CAPTURAR RAINHA -8
        2- Ponei magico 2 casas em qualquer direcao, roda 90 graus e depois mais 2 casas -5
        3- Padre da vila move se nas diagonais no maximo 3 posiçoes -3
        4- Torre Horizontal -3
        5- Torre Vertical -3
        6- Hommer Simpson esta a dormir de 3 em 3 turnos começando no primeiro (no turno 0, 3, 6, 9, 12, ...)
           move se na diagonal apenas uma posição -2
        7- Joker vai mudando de turno a turno 1º turno rainha - ponei - padre - th - tv - hommer -4
        8- peça extra: ideia petter griffin

    Peças nao podem saltar por cima de outras
    Tool tip alterada agora aparece
        nome da peca
        valor

    Equipa preta passou a ser a 10 e a branca a equipa 20

    Termina o jogo quando:
        a equipa adversaria deixa de ter reis
        apenas ha um rei de cada equipa e o jogo empata
        10 jogadas sem capturas

     Ficheiro Gravar:
        grava o jogo numa alura especifica
        em que pode ser qualquer equipa a jogar e ja com stats

     Alteração do ficheiro txt que guarda a info pertinente na ultima linha, NAO ALTERAR O RESTO DA ESTRUTURA
     caso nao tenha nada nessa linha e um jogo normal
     tem que ler e guardar com o mesmo formato

     UNDO vai desfazendo ate voltar ao estado inicial

     Sugerir jogada mostra todas as coordenadas de movimento possivel de uma peca selecionada, oordenada por pontos

     //TODO ALTERACOES API 16:30

 */
public class GameManager {

    public GameManager() {
    } //construtor vazio pedido pelos profs.

    Board board = new Board();
    Stack<Board> boardHistory = new Stack<>();

    //TODO
    public boolean loadGameTest(File file) { //chamar esta funcao nos testes
        try {
            loadGame(file);
            return true;

        } catch (IOException io) {
            return false;
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
        //TODO FORMATO VALIDO
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

            Piece piecesTemp = createNewPiece(Integer.parseInt(parts[1]), Integer.parseInt(parts[0]), parts[3], Integer.parseInt(parts[2]));
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
                    idToPiece.get(Integer.parseInt(parts[column])).setCoords(column, line);
                    idToPiece.get(Integer.parseInt(parts[column])).setInGame(true);

                } else {
                    board.setCoordsToId(column, line, 0); //empty square
                }
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

        if (getSquareInfo(x1, y1) != null && getSquareInfo(x1, y1).length != 0) {

            if (Integer.parseInt(getSquareInfo(x0, y0)[2]) == Integer.parseInt(getSquareInfo(x1, y1)[2])) {

                return false;
            }
        }
        return true;
    }

    public boolean checkValidMove(int x0, int y0, int x1, int y1) {


        if (!checkCoordsLimits(x0) || !checkCoordsLimits(y0) || !checkCoordsLimits(x1) || !checkCoordsLimits(y1)) {

            return false;
        }
        if (!checkPieceExists(x0, y0) || !checkTeamPlaying(x0, y0) || !checkSameTeamMove(x0, y0, x1, y1)) {

            return false;
        }

        return true; //se for valido
    }

    public boolean move(int x0, int y0, int x1, int y1) {

        boardHistory.push(board.copyClone()); //copys old board before making the move

        //tests: System.out.println("Assertions.assertTrue(gameManager.move(" + x0 + ", " + y0 + ", " + x1 + ", " + y1 + "));");

        if (!checkValidMove(x0, y0, x1, y1)) {

            board.addInvalidAttempt();
            return false;
        }


        if (!board.getCoordsToPiece(x0, y0).validPieceMovement(x0, y0, x1, y1, board)) {

            board.addInvalidAttempt();
            return false;
        }

        board.addValidPlays();

        if (board.getCoordsToId(x1, y1) != 0) { //eats piece

            board.resetPlaysWithoutCaptures();
            board.addPointCapture(x0, y0, x1, y1);
            board.getIdToPiece(Integer.parseInt(getSquareInfo(x1, y1)[0])).capture();

            if (getCurrentTeamID() == 10) {

                board.addCapturesBlack();
                board.addPointsBlack(board.getIdToPiece(board.getCoordsToId(x1, y1)).getPoints());

            } else {

                board.addCapturesWhite();
                board.addPointsWhite(board.getIdToPiece(board.getCoordsToId(x1, y1)).getPoints());
            }

        } else {

            board.addPlaysWithoutCaptures();
        }

        board.getIdToPiece(Integer.parseInt(getSquareInfo(x0, y0)[0])).move(x1, y1); //atualiza as coords na peça
        board.setCoordsToId(x1, y1, Integer.parseInt(getSquareInfo(x0, y0)[0])); //atualiza as coords da peça na nova posição no tabuleiro
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

        for (int pieceId = 1; count < board.getNumPieces(); pieceId++) {

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

    }

    public void undo() {
        if (!boardHistory.isEmpty()) {
            board = boardHistory.pop();
            board.updateTurnBasedPieces();
        }

    }

    public List<Comparable> getHints(int x, int y) {

        //se for pedido para a outra equipa ou um quadrado vazio da return de null
        return new ArrayList<>();
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

}
