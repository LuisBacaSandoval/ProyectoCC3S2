package org.example.sprintthree.englishdraughts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTest {

    @Test
    @DisplayName("Prueba de la creación de una pieza RED y verificacion de su color y estado de DAMA")
    public void testWhitePieceCreation() {
        Piece piece = new Piece(1);
        assertEquals(1, piece.getColor());
        assertFalse(piece.isKing());
    }

    @Test
    @DisplayName("Prueba de la creación de una pieza BLACK y verificacion de su color y estado de DAMA")
    public void testBlackPieceCreation() {
        Piece piece = new Piece(2);
        assertEquals(2, piece.getColor());
        assertFalse(piece.isKing());
    }

    @Test
    @DisplayName("Prueba de convertir una pieza normal en DAMA")
    public void testMakeKing() {
        Piece piece = new Piece(1);
        assertFalse(piece.isKing());
        piece.makeKing();
        assertTrue(piece.isKing());
    }
}