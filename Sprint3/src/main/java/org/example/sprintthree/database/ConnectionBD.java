package org.example.sprintthree.database;

import java.sql.*;
public class ConnectionBD {
    private static  Connection conexionBD = null;
    public static void startConnection(){//conectarse a la base de datos
        // Credenciales de la base de datos
        String bd = "proyectocc3s2";
        String url = "jdbc:mysql://localhost:3306/";
        String usuario = "root";
        String contrasenia = "";

        try {
            // Establecer la conexión
            setConexion(DriverManager.getConnection(url+bd, usuario, contrasenia));
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }

    public static void endConnection(){//desconectarse de la base de datos
        try {
            if (conexionBD != null) {
                conexionBD.close();
                System.out.println("Conexión cerrada correctamente.");
                conexionBD = null; // Limpiar la conexión después de cerrarla
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    public static Connection getConexion() {
        return conexionBD;
    }

    public static void setConexion(Connection conexion) {
        conexionBD = conexion;
    }
}
