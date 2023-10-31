package pt.ulusofona.lp2.deisichess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

public class TestGameManager {

    @Test
    public void vitoriaInicio() {

        GameManager gameManager = new GameManager();

        gameManager.loadGame(new File("test-files/vitoriaInicio1.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("RESULTADO: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));

        gameManager.loadGame(new File("test-files/vitoriaInicio2.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("RESULTADO: VENCERAM AS PRETAS", gameManager.getGameResults().get(1));
    }

    @Test
    public void empateInicio() {

        GameManager gameManager = new GameManager();

        gameManager.loadGame(new File("test-files/empateInicio.txt"));
        Assertions.assertTrue(gameManager.gameOver());
        Assertions.assertEquals("RESULTADO: EMPATE", gameManager.getGameResults().get(1));

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

        Assertions.assertEquals(4, gameManager.getBoardSize());
        Assertions.assertEquals(0, gameManager.getCurrentTeamID());
        Assertions.assertFalse(gameManager.gameOver());

        gameManager.move(1, 0, 1, 1);
        Assertions.assertEquals("[1, 0, 0, Chefe, em jogo, 1, 1]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals(0, gameManager.getCurrentTeamID());
        Assertions.assertFalse(gameManager.gameOver());

    }

    @Test
    public void testMoves() {

        GameManager gameManager = new GameManager();

        Assertions.assertTrue(gameManager.loadGame(new File("test-files/4x4.txt")));

        Assertions.assertEquals("[1, 0, 0, Chefe, em jogo, 1, 0]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals(0, gameManager.getCurrentTeamID());
        Assertions.assertFalse(gameManager.gameOver());

        gameManager.move(1, 0, 1, 1);

        Assertions.assertEquals("[1, 0, 0, Chefe, em jogo, 1, 1]", Arrays.toString(gameManager.getPieceInfo(1)));
        Assertions.assertEquals(1, gameManager.getCurrentTeamID());
        Assertions.assertFalse(gameManager.gameOver());

    }

}
