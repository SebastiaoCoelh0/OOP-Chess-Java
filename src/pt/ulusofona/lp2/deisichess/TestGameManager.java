package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class TestGameManager {


    @Test
    public void openFile() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/vitoriaInicio1.txt")));

        gameManager = new GameManager();
        Assertions.assertFalse(gameManager.loadGame(new File("test-files/iDontExist.txt")));

    }

    @Test
    public void vitoriaInicio() {

        GameManager gameManager = new GameManager();

        gameManager.loadGame(new File("test-files/vitoriaInicio1.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("Resultado: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));

        gameManager.loadGame(new File("test-files/vitoriaInicio2.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("Resultado: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));
    }

    @Test
    public void empateInicio() {

        GameManager gameManager = new GameManager();

        gameManager.loadGame(new File("test-files/empateInicio.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("Resultado: EMPATE", gameManager.getGameResults().get(1));

    }

    @Test
    public void test4x4() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));

        Assertions.assertEquals("[1, 0, 0, Chefe, em jogo, 1, 0]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals("[2, 0, 0, Selvagem, em jogo, 3, 0]", Arrays.toString(gameManager.getPieceInfo(2)));
        Assertions.assertEquals("[3, 0, 0, Grande Artista, em jogo, 2, 1]", Arrays.toString(gameManager.getPieceInfo(3)));
        Assertions.assertEquals("[4, 0, 1, O Maior, em jogo, 2, 3]", Arrays.toString(gameManager.getPieceInfo(4)));
        Assertions.assertEquals("[5, 0, 1, O Amigo, em jogo, 1, 3]", Arrays.toString(gameManager.getPieceInfo(5)));
        Assertions.assertEquals("[6, 0, 1, O Beberolas, em jogo, 1, 2]", Arrays.toString(gameManager.getPieceInfo(6)));

        Assertions.assertEquals("1 | 0 | 0 | Chefe @ (1, 0)", gameManager.getPieceInfoAsString(1));
        Assertions.assertEquals("2 | 0 | 0 | Selvagem @ (3, 0)", gameManager.getPieceInfoAsString(2));
        Assertions.assertEquals("3 | 0 | 0 | Grande Artista @ (2, 1)", gameManager.getPieceInfoAsString(3));
        Assertions.assertEquals("4 | 0 | 1 | O Maior @ (2, 3)", gameManager.getPieceInfoAsString(4));
        Assertions.assertEquals("5 | 0 | 1 | O Amigo @ (1, 3)", gameManager.getPieceInfoAsString(5));
        Assertions.assertEquals("6 | 0 | 1 | O Beberolas @ (1, 2)", gameManager.getPieceInfoAsString(6));

        Assertions.assertEquals(1, gameManager.board.getCoordsToId(1, 0));
        Assertions.assertEquals(2, gameManager.board.getCoordsToId(3, 0));
        Assertions.assertEquals(3, gameManager.board.getCoordsToId(2, 1));
        Assertions.assertEquals(4, gameManager.board.getCoordsToId(2, 3));
        Assertions.assertEquals(5, gameManager.board.getCoordsToId(1, 3));
        Assertions.assertEquals(6, gameManager.board.getCoordsToId(1, 2));

        Assertions.assertEquals(4, gameManager.getBoardSize());
        Assertions.assertEquals(0, gameManager.getCurrentTeamID());

        Assertions.assertFalse(gameManager.gameOver());

    }

    @Test
    public void testMoves() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 0, 0, -1));//limits

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(10, 3, 1, 1));//limits

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(0, 0, 1, 1));//piece exists

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 2, 2, 0));//team playing

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 0, 0, 2));//king move

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertFalse(gameManager.move(1, 0, 2, 1));//same team move

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
        Assertions.assertTrue(gameManager.move(1, 0, 1, 1));

        Assertions.assertEquals("[1, 0, 0, Chefe, em jogo, 1, 1]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals(1, gameManager.getCurrentTeamID());

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));
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
        Assertions.assertEquals("1", gameManager.getGameResults().get(6));
        Assertions.assertEquals("3", gameManager.getGameResults().get(8));
        Assertions.assertEquals("4", gameManager.getGameResults().get(9));
        Assertions.assertEquals("1", gameManager.getGameResults().get(10));

        Assertions.assertTrue(gameManager.gameOver());
    }

    @Test
    void testDraw10Plays() {

        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/4x4.txt"));
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

        System.out.println(gameManager.board.getPlaysWithoutCaptures());

        Assertions.assertTrue(gameManager.gameOver());

    }

    @Test
    void testMoves1() {
        GameManager gameManager = new GameManager();
        gameManager.loadGame(new File("test-files/4x4.txt"));

        Assertions.assertTrue(gameManager.move(2, 1, 1, 2));
        Assertions.assertEquals("[3, 0, 0, Grande Artista, em jogo, 1, 2]", Arrays.toString(gameManager.getPieceInfo(3)));
        Assertions.assertEquals("[6, 0, 1, O Beberolas, capturado, , ]", Arrays.toString(gameManager.getPieceInfo(6)));

        Assertions.assertTrue(gameManager.move(2, 3, 2, 2));
        Assertions.assertEquals("[4, 0, 1, O Maior, em jogo, 2, 2]", Arrays.toString(gameManager.getPieceInfo(4)));

        Assertions.assertTrue(gameManager.move(1, 2, 1, 3));
        Assertions.assertEquals("[3, 0, 0, Grande Artista, em jogo, 1, 3]", Arrays.toString(gameManager.getPieceInfo(3)));

        Assertions.assertTrue(gameManager.move(2, 2, 2, 3));
        Assertions.assertEquals("[4, 0, 1, O Maior, em jogo, 2, 3]", Arrays.toString(gameManager.getPieceInfo(4)));

        Assertions.assertTrue(gameManager.move(1, 3, 2, 3));
        Assertions.assertTrue(gameManager.gameOver());

    }

}
