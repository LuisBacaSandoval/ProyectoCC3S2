package org.example.sprintthree.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

public class ConnectionBDTest {

    @BeforeEach
    @DisplayName("Configuración inicial: Inicia la conexión a la base de datos antes de cada prueba")
    public void setUp() {
        ConnectionBD.startConnection();
    }

    @AfterEach
    @DisplayName("Desmontaje: Cierra la conexion a la base de datos despues de cada prueba")
    public void tearDown() {
        ConnectionBD.endConnection();
    }

    @Test
    @DisplayName("Prueba de inicio de conexion: Verifica que la conexion no sea nula despues de iniciarla")
    public void testStartConnection() {
        Connection connection = ConnectionBD.getConexion();
        assertNotNull(connection, "La conexion no deberia ser nula despues de iniciarla");
    }

    @Test
    @DisplayName("Prueba de cierre de conexion: Verifica que la conexion sea nula despues de cerrarla")
    public void testEndConnection() {
        ConnectionBD.endConnection();
        Connection connection = ConnectionBD.getConexion();
        assertNull(connection, "La conexion deberia ser nula despues de cerrarla");
    }

    @Test
    @DisplayName("Prueba de establecimiento de conexion: Verifica que la conexion sea nula despues de establecerla como nula")
    public void testSetConnection() {
        ConnectionBD.setConexion(null);
        Connection connection = ConnectionBD.getConexion();
        assertNull(connection, "La conexion deberia ser nula despues de establecerla como nula");
    }
}