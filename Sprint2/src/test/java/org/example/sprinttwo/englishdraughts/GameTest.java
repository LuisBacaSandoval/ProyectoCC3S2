package org.example.sprinttwo.englishdraughts;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private Game game;
    private GridPane gridBoard;
    private User user1;
    private User user2;
    @BeforeEach
    public void setup() {
        gridBoard = new GridPane();
        int[][] boardInt = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1},
                {2, 2, 2, 2, 2, 2, 2, 2}
        };
        user1 = new User();
        user1.setUser(1, "Luis", "example1@example.com", "RED");
        user2 = new User();
        user2.setUser(2, "Brian", "example2@example.com", "BLACK");
        game = new Game(gridBoard, boardInt, user1, user2);
    }

    @Test
    @DisplayName("Prueba: gana RED")
    public void winningRedPlayer(){
        game.setCurrentPlayer("RED");
        User winUser = game.winningPlayer();

        assertEquals(1, winUser.getId(), "El id del ganador deberia ser 1");
        assertEquals("Luis", winUser.getNombre(), "El nombre del ganador deberia ser Luis");
        assertEquals("example1@example.com", winUser.getCorreo(), "El email del ganador deberia ser example1@example.conm");
    }

    @Test
    @DisplayName("Prueba: gana BLACK")
    public void winningBlackPlayer(){
        game.setCurrentPlayer("BLACK");
        User winUser = game.winningPlayer();

        assertEquals(2, winUser.getId(), "El id del ganador deberia ser 2");
        assertEquals("Brian", winUser.getNombre(), "El nombre del ganador deberia ser Brian");
        assertEquals("example2@example.com", winUser.getCorreo(), "El email del ganador deberia ser example2@example.conm");
    }

    @Test
    @DisplayName("Prueba: juego no termina")
    public void testTheGameDoesntEnd(){
        Boolean finishGame = game.isGameOver();

        assertFalse(finishGame, "El juego no deberia terminar aun");
    }

    @Test
    @DisplayName("Prueba: juego termina")
    public void testTheGameEnd(){
        int[][] boardInt2 = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };
        game = new Game(gridBoard, boardInt2, user1, user2);
        Boolean finishGame = game.isGameOver();

        assertTrue(finishGame, "El juego deberia terminar");
    }
}