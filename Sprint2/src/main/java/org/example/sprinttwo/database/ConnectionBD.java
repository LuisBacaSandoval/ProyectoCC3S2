package org.example.sprinttwo.database;

import java.sql.*;
public class ConnectionBD {
    private  Connection conexion = null;
    public  void startConnection(){
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

    public void endConnection(){//desconectarse de la base de datos
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
                conexion = null; // Limpiar la conexión después de cerrarla
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }
    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
}
