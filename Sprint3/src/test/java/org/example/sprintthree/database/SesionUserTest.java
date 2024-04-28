package org.example.sprintthree.database;

import org.example.sprintthree.englishdraughts.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SesionUserTest {
    User user;
    @BeforeEach
    public void setUp() {
        user = new User(1, "Luis Jhonatan", "example@example.pe");
        SesionUser.setUsuarioActual(user);
    }

    @Test
    @DisplayName("Prueba para establecer el usuario actual")
    public void testSetUsuarioActual() {
        SesionUser.setUsuarioActual(user);
        assertEquals(user, SesionUser.getUsuarioActual(), "El usuario actual debe ser el que acabamos de establecer");
    }

    @Test
    @DisplayName("Prueba para obtener el usuario actual")
    public void testGetUsuarioActual() {
        User user = SesionUser.getUsuarioActual();
        assertEquals(1, user.getId(), "El ID del usuario actual debe ser 1");
        assertEquals("Luis Jhonatan", user.getNombre(), "El nombre del usuario actual debe ser 'Nombre'");
        assertEquals("example@example.pe", user.getCorreo(), "El correo del usuario actual debe ser 'Correo'");
    }
}