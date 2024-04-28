package org.example.sprintthree.englishdraughts;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @DisplayName("Test cuando el juego termina")
    @Test
    public void testGameEnds() {
        // Configurar el tablero para que el juego termine
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // 1 representa al usuario 1
        boardInt[0][1] = 0; // 0 representa un espacio vacío

        Game game = new Game(null, boardInt);
        game.handleMove(0,0,1,1,"RED", true);
        assertTrue(game.isGameOver());
    }

    @DisplayName("Test cuando el juego continúa")
    @Test
    public void testGameContinues() {
        // Configurar el tablero para que el juego continúe
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // 1 representa al usuario 1
        boardInt[0][1] = 2; // 2 representa al usuario 2

        Game game = new Game(null, boardInt);
        game.handleMove(0,0,1,1,"RED", true);
        assertFalse(game.isGameOver());
    }
}