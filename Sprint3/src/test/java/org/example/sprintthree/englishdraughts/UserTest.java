package org.example.sprintthree.englishdraughts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Prueba del constructor con parametros")
    public void testConstructor() {
        User user = new User(1, "Luis Jhonatan", "correo@example.com");
        assertEquals(1, user.getId());
        assertEquals("Luis Jhonatan", user.getNombre());
        assertEquals("correo@example.com", user.getCorreo());
    }

    @Test
    @DisplayName("Prueba del setter y getter de id")
    public void testId() {
        User user = new User();
        user.setId(2);
        assertEquals(2, user.getId());
    }

    @Test
    @DisplayName("Prueba del setter y getter de nombre")
    public void testNombre() {
        User user = new User();
        user.setNombre("Javier");
        assertEquals("Javier", user.getNombre());
    }

    @Test
    @DisplayName("Prueba del setter y getter de correo")
    public void testCorreo() {
        User user = new User();
        user.setCorreo("nuevo@example.com");
        assertEquals("nuevo@example.com", user.getCorreo());
    }
}