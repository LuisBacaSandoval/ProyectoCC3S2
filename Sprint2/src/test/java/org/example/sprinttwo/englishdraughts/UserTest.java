package org.example.sprinttwo.englishdraughts;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("Test setUser de User con parametros: id, nombre, correo, colorFicha")
    void testSetUserWithConnectionParams() {
        // Arrange
        int id = 1;
        String nombre = "John";
        String correo = "john@example.com";
        String color = "NEGRO";

        User user = new User();

        // Act
        user.setUser(id, nombre, correo, color);

        // Assert
        assertEquals(1, user.getId(), "Se esperaba id : 1");
        assertEquals("John", user.getNombre(), "Se esperaba nombre : John");
        assertEquals("john@example.com", user.getCorreo(), "Se esperaba correo : john@example.com");
        assertEquals("NEGRO", user.getColor(), "Se esperaba ficha : NEGRO");
    }
}
