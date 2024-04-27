package org.example.sprinttwo.englishdraughts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    private final int[][] boardInt = {
            {0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {2, 0, 2, 0, 2, 0, 2, 0},
            {0, 2, 0, 2, 0, 2, 0, 2},
            {2, 0, 2, 0, 2, 0, 2, 0}
    };

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("Prueba: inicializar tablero")
    void testInitializeBoard() {
        board.initializeBoard(boardInt);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertEquals(boardInt[i][j], board.grid[i][j].getColor());
            }
        }
    }

    @Test
    @DisplayName("Prueba: mover ficha BLACK")
    void testMoveBlackPiece() {
        board.initializeBoard(boardInt);
        boolean result = board.movePiece(5, 0, 4, 1);
        assertTrue(result);
    }

    @Test
    @DisplayName("Prueba: mover ficha RED")
    void testMoveRedPiece() {
        board.initializeBoard(boardInt);
        boolean result = board.movePiece(2, 1, 3, 0);
        assertTrue(result);
    }

    @Test
    @DisplayName("Prueba: mover ficha a una posicion ocupada")
    void testInvalidMoveDestinationOcuppied() {
        int[][] boardInt2 = {
                {0, 1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1, 0},
                {0, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 0, 0},
                {0, 0, 2, 0, 2, 0, 2, 0},
                {0, 2, 0, 2, 0, 2, 0, 2},
                {2, 0, 2, 0, 2, 0, 2, 0}
        };
        board.initializeBoard(boardInt2);
        boolean result = board.movePiece(4, 1, 3, 0);
        assertFalse(result);
    }

    @Test
    @DisplayName("Prueba: movimiento invalido")
    void testInvalidMoveNoPiece() {
        board.initializeBoard(boardInt);
        boolean result = board.movePiece(4, 2, 3, 3);
        assertFalse(result);
    }

    @Test
    @DisplayName("Prueba: mover ficha dos casillas")
    void testInvalidMoveTwoBoxes() {
        board.initializeBoard(boardInt);
        boolean result = board.movePiece(5, 0, 3, 2);
        assertFalse(result);
    }

}