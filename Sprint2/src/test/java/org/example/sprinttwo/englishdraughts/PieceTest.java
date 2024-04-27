package org.example.sprinttwo.englishdraughts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class PieceTest {
    @Test
    @DisplayName("Prueba: la ficha no es reina")
    public void testIsKing() {
        Piece piece = new Piece(2);
        assertFalse(piece.isKing(), "Se esperaba false, no es reina");
    }

    @Test
    @DisplayName("Prueba: la ficha si es reina")
    public void testMakeKing() {
        Piece piece = new Piece(1);
        piece.makeKing();
        assertTrue(piece.isKing(), "Se esperaba true, es una reina");
    }

    @Test
    @DisplayName("Prueba: tipo de color asignado")
    public void testGetColor() {
        Piece piece = new Piece(1);
        assertEquals(1, piece.getColor(), "Se esperaba 1, color: rojo");
        piece.setColor(2);
        assertEquals(2, piece.getColor(), "Se esperaba 2, color: negro");
    }

}