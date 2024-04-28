package org.example.sprintthree.englishdraughts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @Test
    @DisplayName("Prueba de la inicializacion del tablero")
    public void testInitializeBoard() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // Ficha RED
        boardInt[7][7] = 2; // Ficha BLACK
        board.initializeBoard(boardInt);
        assertEquals(1, board.grid[0][0].getColor(), "La ficha RED no se inicializó correctamente");
        assertEquals(2, board.grid[7][7].getColor(), "La ficha BLACK no se inicializó correctamente");
    }

    @Test
    @DisplayName("Prueba de mover una pieza BLACK de manera valida")
    public void testMoveBlackPiece() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[1][0] = 2; // Ficha negra
        board.initializeBoard(boardInt);
        assertTrue(board.movePiece(1, 0, 0, 1), "No se pudo mover la pieza BLACK de manera valida");
        assertEquals(2, board.grid[0][1].getColor(), "La pieza BLACK no se movio correctamente");
        assertEquals(0, board.grid[1][0].getColor(), "La posicion original de la pieza BLACK no se vacio correctamente");
    }

    @Test
    @DisplayName("Prueba de mover una pieza RED de manera válida")
    public void testMoveRedPiece() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[6][7] = 1; // Ficha roja
        board.initializeBoard(boardInt);
        assertTrue(board.movePiece(6, 7, 7, 6), "No se pudo mover la pieza RED de manera válida");
        assertEquals(1, board.grid[7][6].getColor(), "La pieza RED no se movió correctamente");
        assertEquals(0, board.grid[6][7].getColor(), "La posición original de la pieza RED no se vació correctamente");
    }

    @Test
    @DisplayName("Prueba de mover una pieza a una posicion ocupada")
    public void testMovePieceToOccupiedSquare() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // Ficha roja
        boardInt[1][1] = 2; // Ficha negra
        board.initializeBoard(boardInt);
        assertFalse(board.movePiece(0, 0, 1, 1), "Se permitio mover una pieza a una posicion ocupada");
    }

    @Test
    @DisplayName("Prueba de mover una pieza en diagonal dos casillas")
    public void testMovePieceTwoSquaresDiagonally() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // Ficha roja
        board.initializeBoard(boardInt);
        assertFalse(board.movePiece(0, 0, 2, 2), "Se permitió mover una pieza en diagonal dos casillas");
    }

    @Test
    @DisplayName("Prueba de mover una pieza en una dirección que no sea diagonal una casilla")
    public void testMovePieceNonDiagonal() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // Ficha roja
        board.initializeBoard(boardInt);
        assertFalse(board.movePiece(0, 0, 0, 1), "Se permitió mover una pieza en una dirección que no sea diagonal una casilla");
    }

    @Test
    @DisplayName("Prueba de comer una pieza al saltar sobre ella")
    public void testEatPieceByJumpingOver() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // Ficha roja
        boardInt[1][1] = 2; // Ficha negra
        board.initializeBoard(boardInt);
        assertTrue(board.movePiece(0, 0, 2, 2), "No se pudo comer la pieza al saltar sobre ella");
        assertEquals(1, board.grid[2][2].getColor(), "La ficha roja no se movió correctamente después de comer");
        assertEquals(0, board.grid[0][0].getColor(), "La ficha roja no se eliminó correctamente de su posición original después de comer");
        assertEquals(0, board.grid[1][1].getColor(), "La ficha negra no se eliminó correctamente después de ser comida");
    }

    @Test
    @DisplayName("Prueba de comer pieza")
    public void testEatPiece() {
        Board board = new Board();
        int[][] boardInt = new int[8][8];
        boardInt[0][0] = 1; // Ficha roja
        boardInt[1][1] = 2; // Ficha negra
        board.initializeBoard(boardInt);
        assertTrue(board.canEatPiece(0, 0, 2, 2), "No se pudo comer la pieza");
        board.eatPiece(0, 0, 2, 2);
        assertEquals(1, board.grid[2][2].getColor(), "La ficha roja no se movió correctamente después de comer");
        assertEquals(0, board.grid[0][0].getColor(), "La ficha roja no se eliminó correctamente de su posición original después de comer");
        assertEquals(0, board.grid[1][1].getColor(), "La ficha negra no se eliminó correctamente después de ser comida");
    }
}