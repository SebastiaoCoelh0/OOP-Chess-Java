package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pt.ulusofona.lp2.deisichess.movement.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestGameManager {

    @Test
    public void loadFile() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/vitoriaInicio1.txt")));

        gameManager = new GameManager();
        Assertions.assertFalse(gameManager.loadGameTest(new File("test-files/iDontExist.txt")));

    }

    //fazer teste mais baralhadas e rainha tenta capturar joker
    @Test
    public void hint() {

        GameManager gameManager = new GameManager();
        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/8x8.txt")));
        List<Comparable> hintsTemp = gameManager.getHints(5, 0);
        Collections.sort(hintsTemp);
        Assertions.assertEquals("(5,7) -> 3", hintsTemp.get(0).toString());
        Assertions.assertTrue(gameManager.move(1, 0, 4, 3));
        Assertions.assertTrue(gameManager.move(5, 7, 5, 4));
        hintsTemp = gameManager.getHints(4, 3);
        Collections.sort(hintsTemp);
        Assertions.assertEquals("(0,7) -> 1000", hintsTemp.get(0).toString());
        Assertions.assertEquals("(4,7) -> 3", hintsTemp.get(1).toString());
        Assertions.assertEquals("(5,4) -> 3", hintsTemp.get(2).toString());
    }

    @Test
    public void petterGriffin() {

        GameManager gameManager = new GameManager();
        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/petterTest.txt")));
        Assertions.assertTrue(gameManager.move(7, 3, 6, 3));//Move apenas para o homer nao estar a dormir
        Assertions.assertTrue(gameManager.move(7, 4, 6, 4));//Move apenas para o homer nao estar a dormir
        Assertions.assertTrue(gameManager.move(6, 6, 7, 7));
        Assertions.assertEquals(1000, gameManager.getBoard().getCoordsToPiece(7, 7).getPointsCaptured());
        Assertions.assertEquals("Doh! zzzzzz", gameManager.getBoard().getCoordsToPiece(7, 7).toString());
        Assertions.assertTrue(gameManager.move(7, 6, 7, 7));
        Assertions.assertEquals("4 | 8 | 2 | 20 | Quase o DDT @ (7, 7)", gameManager.getBoard().getCoordsToPiece(7, 7).toString());
        Assertions.assertEquals(2, gameManager.getBoard().getCoordsToPiece(7, 7).getPointsCaptured());
        Assertions.assertEquals("2 | Homer Simpson | 2 | 20 | O Amarelo @ (6, 7)", gameManager.getBoard().getCoordsToPiece(6, 7).toString());
        Assertions.assertEquals(1000, gameManager.getBoard().getCoordsToPiece(6, 7).getPointsCaptured());
        Assertions.assertTrue(gameManager.move(1, 0, 1, 2));
        Assertions.assertEquals("1 | 8 | 2 | 10 | O Dono Disto Tudo @ (1, 2)", gameManager.getBoard().getCoordsToPiece(1, 2).toString());
    }

    @Test
    public void save() {

        GameManager gameManager = new GameManager();
        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/8x8.txt")));

        Assertions.assertFalse(gameManager.move(1, 0, 0, 0));
        Assertions.assertFalse(gameManager.move(2, 0, 1, 0));
        Assertions.assertFalse(gameManager.move(2, 0, 1, 0));
        Assertions.assertFalse(gameManager.move(3, 0, 2, 0));
        Assertions.assertFalse(gameManager.move(3, 0, 2, 0));
        Assertions.assertFalse(gameManager.move(3, 0, 2, 0));
        Assertions.assertFalse(gameManager.move(4, 0, 3, 0));
        Assertions.assertFalse(gameManager.move(4, 0, 3, 0));
        Assertions.assertFalse(gameManager.move(4, 0, 3, 0));
        Assertions.assertFalse(gameManager.move(4, 0, 3, 0));
        Assertions.assertFalse(gameManager.move(5, 0, 4, 0));
        Assertions.assertFalse(gameManager.move(5, 0, 4, 0));
        Assertions.assertFalse(gameManager.move(5, 0, 4, 0));
        Assertions.assertFalse(gameManager.move(5, 0, 4, 0));
        Assertions.assertFalse(gameManager.move(5, 0, 4, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertFalse(gameManager.move(7, 0, 6, 0));
        Assertions.assertTrue(gameManager.move(0, 0, 0, 1));
        Assertions.assertFalse(gameManager.move(7, 7, 6, 7));
        Assertions.assertFalse(gameManager.move(7, 7, 6, 7));
        Assertions.assertFalse(gameManager.move(7, 7, 6, 7));
        Assertions.assertFalse(gameManager.move(7, 7, 6, 7));
        Assertions.assertFalse(gameManager.move(7, 7, 6, 7));
        Assertions.assertFalse(gameManager.move(7, 7, 6, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertFalse(gameManager.move(6, 7, 5, 7));
        Assertions.assertTrue(gameManager.move(5, 7, 5, 0));
        Assertions.assertTrue(gameManager.move(1, 0, 0, 0));

        Assertions.assertEquals("20:Homer Jay Simpson:12:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(0));
        Assertions.assertEquals("10:Hommie:7:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(1));
        Assertions.assertEquals("10:O Beberolas:7:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(2));
        ArrayList<String> teste = gameManager.getGameResults();

        try {
            gameManager.saveGame(new File("test-files/ficheiroTeste.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        gameManager.loadGameTest(new File("test-files/8x8.txt"));

        gameManager.loadGameTest(new File("test-files/ficheiroTeste.txt"));

        Assertions.assertEquals("20:Homer Jay Simpson:12:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(0));
        Assertions.assertEquals("10:Hommie:7:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(1));
        Assertions.assertEquals("10:O Beberolas:7:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(2));
        Assertions.assertEquals(20, gameManager.getCurrentTeamID());
        Assertions.assertEquals(teste, gameManager.getGameResults());
    }

    @Test
    public void statType() {

        Assertions.assertEquals("TOP_5_CAPTURAS", StatType.TOP_5_CAPTURAS.name());
        Assertions.assertEquals("PECAS_MAIS_BARALHADAS", StatType.PECAS_MAIS_BARALHADAS.name());
        Assertions.assertEquals("TOP_5_PONTOS", StatType.TOP_5_PONTOS.name());
        Assertions.assertEquals("TIPOS_CAPTURADOS", StatType.TIPOS_CAPTURADOS.name());
        Assertions.assertEquals("PECAS_MAIS_5_CAPTURAS", StatType.PECAS_MAIS_5_CAPTURAS.name());

    }


    @Test
    public void stats() {

        GameManager gameManager = new GameManager();
        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/8x8.txt")));

        Assertions.assertFalse(gameManager.move(1, 0, 1, 0)); //invalido rainha
        Assertions.assertTrue(gameManager.move(1, 0, 1, 3));
        Assertions.assertFalse(gameManager.move(7, 7, 7, 7)); //invalido joker branco
        Assertions.assertFalse(gameManager.move(0, 7, 1, 7)); //invalido chefe indios
        Assertions.assertTrue(gameManager.move(0, 7, 0, 6));
        Assertions.assertTrue(gameManager.move(1, 3, 5, 7));
        Assertions.assertFalse(gameManager.move(0, 6, 1, 7)); //invalido chefe indios
        Assertions.assertTrue(gameManager.move(0, 6, 0, 7));
        Assertions.assertTrue(gameManager.move(5, 7, 6, 7));
        Assertions.assertTrue(gameManager.move(1, 7, 1, 3));
        Assertions.assertFalse(gameManager.move(6, 7, 7, 7)); //rainha tenta comer joker rainha
        Assertions.assertTrue(gameManager.move(6, 7, 4, 7));
        Assertions.assertTrue(gameManager.move(1, 3, 4, 0));
        System.out.println(gameManager.board.getTurn());
        System.out.println(gameManager.board.getCoordsToPiece(4, 7).toString());
        System.out.println(gameManager.board.getCoordsToPiece(7, 7).toString());
        Assertions.assertTrue(gameManager.move(4, 7, 7, 7));
        Assertions.assertTrue(gameManager.move(4, 0, 3, 0));
        Assertions.assertTrue(gameManager.move(7, 7, 3, 7));
        Assertions.assertTrue(gameManager.move(3, 0, 2, 0));
        Assertions.assertTrue(gameManager.move(3, 7, 2, 7));
        Assertions.assertTrue(gameManager.move(2, 0, 1, 0));
        Assertions.assertTrue(gameManager.move(0, 0, 1, 0));

        Assertions.assertEquals("A Dama Selvagem (PRETA) fez 6 capturas", StatisticsKt.getStatsCalculator(StatType.TOP_5_CAPTURAS).invoke(gameManager).get(0));
        Assertions.assertEquals("A Barulhenta do Bairro (BRANCA) fez 3 capturas", StatisticsKt.getStatsCalculator(StatType.TOP_5_CAPTURAS).invoke(gameManager).get(1));
        Assertions.assertEquals("O Poderoso Chefao (PRETA) fez 1 capturas", StatisticsKt.getStatsCalculator(StatType.TOP_5_CAPTURAS).invoke(gameManager).get(2));

        Assertions.assertEquals("A Dama Selvagem (PRETA) tem 20 pontos", StatisticsKt.getStatsCalculator(StatType.TOP_5_PONTOS).invoke(gameManager).get(0));
        Assertions.assertEquals("A Barulhenta do Bairro (BRANCA) tem 11 pontos", StatisticsKt.getStatsCalculator(StatType.TOP_5_PONTOS).invoke(gameManager).get(1));
        Assertions.assertEquals("O Poderoso Chefao (PRETA) tem 8 pontos", StatisticsKt.getStatsCalculator(StatType.TOP_5_PONTOS).invoke(gameManager).get(2));

        Assertions.assertEquals("PRETA:A Dama Selvagem:6", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_5_CAPTURAS).invoke(gameManager).get(0));

        Assertions.assertEquals("20:O Bobo da Corte:1:0", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(0));
        Assertions.assertEquals("20:Chefe dos Indios:2:2", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(1));
        Assertions.assertEquals("10:A Dama Selvagem:2:7", StatisticsKt.getStatsCalculator(StatType.PECAS_MAIS_BARALHADAS).invoke(gameManager).get(2));

        Assertions.assertEquals("Homer Simpson", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(0));
        Assertions.assertEquals("Joker/TorreHor", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(1));
        Assertions.assertEquals("Padre da Vila", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(2));
        Assertions.assertEquals("Ponei Mágico", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(3));
        Assertions.assertEquals("Rainha", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(4));
        Assertions.assertEquals("TorreHor", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(5));
        Assertions.assertEquals("TorreVert", StatisticsKt.getStatsCalculator(StatType.TIPOS_CAPTURADOS).invoke(gameManager).get(6));

    }

    @Test
    public void filesWithErrors() throws IOException {

        GameManager gameManager = new GameManager();

        try {
            gameManager.loadGame(new File("test-files/testFileError1.txt"));
        } catch (InvalidGameInputException e) {
            Assertions.assertEquals(3, e.getLineWithError());
            Assertions.assertEquals("DADOS A MAIS (Esperava: 4 ; Obtive: 5)", e.getProblemDescription());
            //throw new RuntimeException(e);
        }

        try {
            gameManager.loadGame(new File("test-files/testFileError2.txt"));
        } catch (InvalidGameInputException e) {
            Assertions.assertEquals(4, e.lineWithError);
            Assertions.assertEquals("DADOS A MENOS (Esperava: 4 ; Obtive: 3)", e.problemDescription);
            //throw new RuntimeException(e);
        }


    }

    @Test
    public void classMovement() {

        GameManager gameManager = new GameManager();
        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));

        Movement test = new MoveVertical();
        Assertions.assertTrue(test.canMovePiece(0, 0, 0, 1, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 0, 3, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 1, 0, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 1, 1, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(2, 0, 2, 2, gameManager.board, 3));
        Assertions.assertFalse(test.canMovePiece(2, 2, 2, 0, gameManager.board, 3));

        test = new MoveHorizontal();
        Assertions.assertTrue(test.canMovePiece(0, 0, 1, 0, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 2, 0, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 0, 1, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 1, 1, gameManager.board, 1));

        test = new MoveDiagonally();
        Assertions.assertTrue(test.canMovePiece(0, 0, 1, 1, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 2, 2, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 0, 1, gameManager.board, 1));
        Assertions.assertFalse(test.canMovePiece(0, 0, 1, 0, gameManager.board, 1));

        test = new MoveMagicPoney();
        Assertions.assertTrue(test.canMovePiece(1, 2, 3, 0, gameManager.board, 0));
        Assertions.assertFalse(test.canMovePiece(1, 2, 1, 3, gameManager.board, 0));
        Assertions.assertFalse(test.canMovePiece(1, 2, 2, 2, gameManager.board, 0));

    }

    @Test
    public void homer() {

        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/homerTest.txt"));

        Assertions.assertFalse(gameManager.move(3, 4, 2, 3));//HOMER
        Assertions.assertTrue(gameManager.move(5, 0, 5, 1));
        Assertions.assertTrue(gameManager.move(6, 7, 5, 6));//HOMER
        Assertions.assertTrue(gameManager.move(3, 4, 2, 3));//HOMER
        Assertions.assertFalse(gameManager.move(5, 6, 4, 5));//HOMER
        Assertions.assertTrue(gameManager.move(0, 7, 0, 6));
        Assertions.assertTrue(gameManager.move(2, 3, 3, 4));//HOMER
        Assertions.assertTrue(gameManager.move(5, 6, 4, 5));//HOMER
        Assertions.assertFalse(gameManager.move(3, 4, 4, 5));//HOMER
        Assertions.assertTrue(gameManager.move(5, 1, 5, 0));
        Assertions.assertTrue(gameManager.move(4, 5, 5, 6));//HOMER

    }

    @Test
    public void toStringPecaComida() {


        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/8x8.txt"));

        Assertions.assertEquals("14 | TorreVert | 3 | 20 | Torre Trapalhona @ (5, 7)", gameManager.getBoard().getCoordsToPiece(5, 7).toString());
        Assertions.assertTrue(gameManager.move(5, 0, 5, 7));
        Assertions.assertEquals("6 | TorreVert | 3 | 10 | O Maior Grande @ (5, 7)", gameManager.getBoard().getCoordsToPiece(5, 7).toString());
        Assertions.assertEquals("14 | TorreVert | 3 | 20 | Torre Trapalhona @ (n/a)", gameManager.getBoard().getIdToPiece(14).toString());
    }

    @Test
    public void testUndo() {

        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/8x8.txt"));

        Assertions.assertEquals("[1, 0, 10, O Poderoso Chefao, 0_10.png]", Arrays.toString(gameManager.getSquareInfo(0, 0)));
        Assertions.assertEquals("[]", Arrays.toString(gameManager.getSquareInfo(0, 1)));
        Assertions.assertTrue(gameManager.move(0, 0, 0, 1));
        Assertions.assertEquals("[]", Arrays.toString(gameManager.getSquareInfo(0, 0)));
        Assertions.assertEquals("[1, 0, 10, O Poderoso Chefao, 0_10.png]", Arrays.toString(gameManager.getSquareInfo(0, 1)));

        gameManager.undo();

        Assertions.assertEquals("[1, 0, 10, O Poderoso Chefao, 0_10.png]", Arrays.toString(gameManager.getSquareInfo(0, 0)));
        Assertions.assertEquals("[]", Arrays.toString(gameManager.getSquareInfo(0, 1)));

        Assertions.assertFalse(gameManager.gameOver());
        Assertions.assertTrue(gameManager.move(0, 0, 0, 1));
    }

    @Test
    public void info() {

        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/8x8.txt"));

        Assertions.assertEquals("[1, 0, 10, O Poderoso Chefao, 0_10.png]", Arrays.toString(gameManager.getSquareInfo(0, 0)));
        Assertions.assertEquals("[2, 1, 10, A Dama Selvagem, 1_10.png]", Arrays.toString(gameManager.getSquareInfo(1, 0)));
        Assertions.assertEquals("[3, 2, 10, O Grande Artista, 2_10.png]", Arrays.toString(gameManager.getSquareInfo(2, 0)));
        Assertions.assertEquals("[4, 3, 10, Amante de Praia, 3_10.png]", Arrays.toString(gameManager.getSquareInfo(3, 0)));
        Assertions.assertEquals("[5, 4, 10, Artolas, 4_10.png]", Arrays.toString(gameManager.getSquareInfo(4, 0)));
        Assertions.assertEquals("[6, 5, 10, O Maior Grande, 5_10.png]", Arrays.toString(gameManager.getSquareInfo(5, 0)));
        Assertions.assertEquals("[7, 6, 10, Hommie, 6_10.png]", Arrays.toString(gameManager.getSquareInfo(6, 0)));
        Assertions.assertEquals("[8, 7, 10, O Beberolas, 7_10.png]", Arrays.toString(gameManager.getSquareInfo(7, 0)));

    }

    @Test
    public void vitoriaInicio1() {

        GameManager gameManager = new GameManager();

        gameManager.loadGameTest(new File("test-files/vitoriaInicio1.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("Resultado: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));
    }

    @Test
    public void vitoriaInicio2() {

        GameManager gameManager = new GameManager();

        gameManager.loadGameTest(new File("test-files/vitoriaInicio2.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("Resultado: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));
    }

    @Test
    public void empateInicio() {

        GameManager gameManager = new GameManager();

        gameManager.loadGameTest(new File("test-files/empateInicio.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("Resultado: EMPATE", gameManager.getGameResults().get(1));

    }

    @Test
    public void test4x4() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));

        Assertions.assertEquals(4, gameManager.getBoardSize());

        Assertions.assertEquals("[1, 0, 10, Chefe, em jogo, 1, 0]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals("[2, 0, 10, Selvagem, em jogo, 3, 0]", Arrays.toString(gameManager.getPieceInfo(2)));
        Assertions.assertEquals("[3, 0, 10, Grande Artista, em jogo, 2, 1]", Arrays.toString(gameManager.getPieceInfo(3)));
        Assertions.assertEquals("[4, 0, 20, O Maior, em jogo, 2, 3]", Arrays.toString(gameManager.getPieceInfo(4)));
        Assertions.assertEquals("[5, 0, 20, O Amigo, em jogo, 1, 3]", Arrays.toString(gameManager.getPieceInfo(5)));
        Assertions.assertEquals("[6, 0, 20, O Beberolas, em jogo, 1, 2]", Arrays.toString(gameManager.getPieceInfo(6)));

        Assertions.assertEquals("1 | Rei | (infinito) | 10 | Chefe @ (1, 0)", gameManager.getPieceInfoAsString(1));
        Assertions.assertEquals("2 | Rei | (infinito) | 10 | Selvagem @ (3, 0)", gameManager.getPieceInfoAsString(2));
        Assertions.assertEquals("3 | Rei | (infinito) | 10 | Grande Artista @ (2, 1)", gameManager.getPieceInfoAsString(3));
        Assertions.assertEquals("4 | Rei | (infinito) | 20 | O Maior @ (2, 3)", gameManager.getPieceInfoAsString(4));
        Assertions.assertEquals("5 | Rei | (infinito) | 20 | O Amigo @ (1, 3)", gameManager.getPieceInfoAsString(5));
        Assertions.assertEquals("6 | Rei | (infinito) | 20 | O Beberolas @ (1, 2)", gameManager.getPieceInfoAsString(6));

        Assertions.assertEquals(1, gameManager.board.getCoordsToId(1, 0));
        Assertions.assertEquals(2, gameManager.board.getCoordsToId(3, 0));
        Assertions.assertEquals(3, gameManager.board.getCoordsToId(2, 1));
        Assertions.assertEquals(4, gameManager.board.getCoordsToId(2, 3));
        Assertions.assertEquals(5, gameManager.board.getCoordsToId(1, 3));
        Assertions.assertEquals(6, gameManager.board.getCoordsToId(1, 2));

        Assertions.assertEquals(4, gameManager.getBoardSize());
        Assertions.assertEquals(10, gameManager.getCurrentTeamID());

        Assertions.assertFalse(gameManager.gameOver());

    }

    @Test
    public void testMoves() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 0, 0, -1));//limits

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(10, 3, 1, 1));//limits

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(0, 0, 1, 1));//piece exists

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 2, 2, 0));//teamPlaying playing

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 0, 0, 2));//king move

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 0, 2, 1));//same teamPlaying move

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertTrue(gameManager.move(1, 0, 1, 1));

        Assertions.assertEquals("[1, 0, 10, Chefe, em jogo, 1, 1]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals(20, gameManager.getCurrentTeamID());

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/4x4.txt")));
        Assertions.assertTrue(gameManager.move(1, 0, 1, 1));
        Assertions.assertFalse(gameManager.move(1, 3, 2, 3));
        Assertions.assertTrue(gameManager.move(1, 3, 0, 2));
        Assertions.assertFalse(gameManager.move(1, 1, 2, 1));
        Assertions.assertFalse(gameManager.move(1, 1, 3, 3));
        Assertions.assertTrue(gameManager.move(1, 1, 0, 1));
        Assertions.assertTrue(gameManager.move(0, 2, 0, 1));
        Assertions.assertTrue(gameManager.move(2, 1, 1, 1));
        Assertions.assertFalse(gameManager.move(0, 2, 1, 1));
        Assertions.assertTrue(gameManager.move(1, 2, 1, 1));
        Assertions.assertTrue(gameManager.move(3, 0, 2, 1));
        Assertions.assertTrue(gameManager.move(1, 1, 2, 1));

        Assertions.assertEquals("Resultado: VENCERAM AS BRANCAS", gameManager.getGameResults().get(1));
        Assertions.assertEquals("0", gameManager.getGameResults().get(4));
        Assertions.assertEquals("4", gameManager.getGameResults().get(5));
        Assertions.assertEquals("2", gameManager.getGameResults().get(6));
        Assertions.assertEquals("3", gameManager.getGameResults().get(8));
        Assertions.assertEquals("4", gameManager.getGameResults().get(9));
        Assertions.assertEquals("2", gameManager.getGameResults().get(10));

        Assertions.assertTrue(gameManager.gameOver());

        Assertions.assertTrue(gameManager.loadGameTest(new File("test-files/8x8.txt")));
        Assertions.assertFalse(gameManager.move(6, 0, 7, 1));//hommer sleep
        Assertions.assertTrue(gameManager.move(1, 0, 1, 3));
        Assertions.assertFalse(gameManager.move(1, 7, 1, 3));//queen cannot eat queen
        Assertions.assertFalse(gameManager.move(3, 7, 7, 3));//delta
        Assertions.assertTrue(gameManager.move(3, 7, 0, 4));
        //Assertions.assertTrue(gameManager.move(7, 1, 6, 2));//hommer sleep


    }

    @Test
    void testMoveVideo() {

        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/8x8.txt"));

        Assertions.assertTrue(gameManager.move(3, 0, 0, 3));
        Assertions.assertTrue(gameManager.move(1, 7, 4, 4));
        Assertions.assertTrue(gameManager.move(1, 0, 1, 1));
        Assertions.assertTrue(gameManager.move(2, 7, 0, 5));
        Assertions.assertTrue(gameManager.move(5, 0, 5, 7));
        Assertions.assertTrue(gameManager.move(0, 5, 2, 3));
        Assertions.assertFalse(gameManager.move(6, 0, 5, 1));
        Assertions.assertTrue(gameManager.move(2, 0, 0, 2));
        Assertions.assertTrue(gameManager.move(4, 4, 4, 2));
        Assertions.assertTrue(gameManager.move(0, 3, 2, 5));
        Assertions.assertTrue(gameManager.move(0, 7, 1, 7));
        Assertions.assertFalse(gameManager.move(1, 1, 1, 7));
        Assertions.assertFalse(gameManager.move(4, 2, 3, 3));
        Assertions.assertFalse(gameManager.move(1, 1, 1, 7));
        Assertions.assertTrue(gameManager.move(6, 0, 5, 1));
        Assertions.assertTrue(gameManager.move(3, 7, 0, 4));
        Assertions.assertTrue(gameManager.move(7, 0, 7, 3));
        Assertions.assertTrue(gameManager.move(4, 2, 5, 1));
        Assertions.assertTrue(gameManager.move(1, 1, 1, 5));
        Assertions.assertTrue(gameManager.move(5, 1, 2, 1));
        Assertions.assertTrue(gameManager.move(1, 5, 1, 7));
        Assertions.assertTrue(gameManager.gameOver());


    }

    @Test
    void testJoker() {

        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/8x8.txt"));
        Assertions.assertTrue(gameManager.move(7, 0, 4, 3));
        Assertions.assertTrue(gameManager.move(7, 7, 5, 5));
        Assertions.assertTrue(gameManager.move(4, 3, 6, 5));
        Assertions.assertTrue(gameManager.move(5, 5, 4, 5));
        Assertions.assertTrue(gameManager.move(6, 5, 6, 3));
        Assertions.assertTrue(gameManager.move(4, 5, 5, 4));
    }

    @Test
    void testDraw10Plays() {

        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/4x4.txt"));
        //move back and forth
        Assertions.assertTrue(gameManager.move(2, 1, 2, 2));
        Assertions.assertTrue(gameManager.move(1, 2, 1, 1));

        Assertions.assertTrue(gameManager.move(2, 2, 2, 1));
        Assertions.assertTrue(gameManager.move(1, 1, 1, 2));

        Assertions.assertTrue(gameManager.move(2, 1, 2, 2));
        Assertions.assertTrue(gameManager.move(1, 2, 1, 1));

        Assertions.assertTrue(gameManager.move(2, 2, 2, 1));
        Assertions.assertTrue(gameManager.move(1, 1, 1, 2));

        Assertions.assertTrue(gameManager.move(2, 1, 2, 2));
        Assertions.assertTrue(gameManager.move(1, 2, 1, 1));

        Assertions.assertFalse(gameManager.gameOver());

        gameManager.loadGameTest(new File("test-files/4x4.txt"));

        Assertions.assertTrue(gameManager.move(2, 1, 2, 2));
        Assertions.assertTrue(gameManager.move(1, 2, 2, 2));

        Assertions.assertTrue(gameManager.move(1, 0, 0, 0));
        Assertions.assertTrue(gameManager.move(1, 3, 1, 2));

        Assertions.assertTrue(gameManager.move(0, 0, 1, 0));
        Assertions.assertTrue(gameManager.move(1, 2, 1, 3));

        Assertions.assertTrue(gameManager.move(1, 0, 0, 0));
        Assertions.assertTrue(gameManager.move(1, 3, 1, 2));

        Assertions.assertTrue(gameManager.move(0, 0, 1, 0));
        Assertions.assertTrue(gameManager.move(1, 2, 1, 3));

        Assertions.assertTrue(gameManager.move(1, 0, 0, 0));
        Assertions.assertTrue(gameManager.move(1, 3, 1, 2));

        Assertions.assertTrue(gameManager.gameOver());

    }

    @Test
    void testMoves1() {
        GameManager gameManager = new GameManager();
        gameManager.loadGameTest(new File("test-files/4x4.txt"));

        Assertions.assertTrue(gameManager.move(2, 1, 1, 2));

        Assertions.assertEquals("[3, 0, 10, Grande Artista, em jogo, 1, 2]", Arrays.toString(gameManager.getPieceInfo(3)));
        Assertions.assertEquals("[6, 0, 20, O Beberolas, capturado, , ]", Arrays.toString(gameManager.getPieceInfo(6)));

        Assertions.assertTrue(gameManager.move(2, 3, 2, 2));
        Assertions.assertEquals("[4, 0, 20, O Maior, em jogo, 2, 2]", Arrays.toString(gameManager.getPieceInfo(4)));

        Assertions.assertTrue(gameManager.move(1, 2, 1, 3));
        Assertions.assertEquals("[3, 0, 10, Grande Artista, em jogo, 1, 3]", Arrays.toString(gameManager.getPieceInfo(3)));

        Assertions.assertTrue(gameManager.move(2, 2, 2, 3));
        Assertions.assertEquals("[4, 0, 20, O Maior, em jogo, 2, 3]", Arrays.toString(gameManager.getPieceInfo(4)));

        Assertions.assertTrue(gameManager.move(1, 3, 2, 3));
        Assertions.assertTrue(gameManager.gameOver());

    }

}
