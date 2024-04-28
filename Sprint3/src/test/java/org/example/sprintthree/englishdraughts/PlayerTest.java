package org.example.sprintthree.englishdraughts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    @DisplayName("Prueba del constructor con parametros")
    public void testConstructor() {
        Player player = new Player(1, "Luis Jhonatan", "correo@example.com", "Rojo");
        assertEquals(1, player.getId());
        assertEquals("Luis Jhonatan", player.getNombre());
        assertEquals("correo@example.com", player.getCorreo());
        assertEquals("Rojo", player.getColorPiece());
    }

    @Test
    @DisplayName("Prueba del setter y getter de id")
    public void testId() {
        Player player = new Player();
        player.setId(2);
        assertEquals(2, player.getId());
    }

    @Test
    @DisplayName("Prueba del setter y getter de nombre")
    public void testNombre() {
        Player player = new Player();
        player.setNombre("Nuevo Nombre");
        assertEquals("Nuevo Nombre", player.getNombre());
    }

    @Test
    @DisplayName("Prueba del setter y getter de correo")
    public void testCorreo() {
        Player player = new Player();
        player.setCorreo("nuevo@example.com");
        assertEquals("nuevo@example.com", player.getCorreo());
    }

    @Test
    @DisplayName("Prueba del setter y getter de colorPiece")
    public void testColorPiece() {
        Player player = new Player();
        player.setColorPiece("Azul");
        assertEquals("Azul", player.getColorPiece());
    }
}