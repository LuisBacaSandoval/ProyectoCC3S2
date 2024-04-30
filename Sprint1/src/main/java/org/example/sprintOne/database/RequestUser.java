package org.example.sprintOne.database;

import org.example.sprintOne.englishdraughts.User;
import org.example.sprintOne.others.Notification;
import org.example.sprintOne.others.PasswordEncryption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestUser {
    public void setUser(User user, ConnectionBD conexion, String usuario, String contrasenia){
        PasswordEncryption ps = new PasswordEncryption();
        String contraseniaEncriptada = ps.encryptPassword(contrasenia);
        try {
            // Preparar la consulta SQL con parámetros de marcador de posición (?)
            PreparedStatement stmt = conexion.getConexion().prepareStatement("SELECT id, username, email FROM usuarios WHERE email = ? AND password = ?");
            // Establecer los valores de los parámetros
            stmt.setString(1, usuario); // Establecer el valor del primer parámetro (correo)
            stmt.setString(2, contraseniaEncriptada); // Establecer el valor del segundo parámetro (contrasenia)
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("username"));
                user.setCorreo(rs.getString("email"));

            }else {
                user.setId(99999999);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        // Verificar si el usuario no se encontró y mostrar una notificación en consecuencia
        if (user.getId() == 99999999) {
            Notification alerta = new Notification("El usuario no se encuentra registrado", "error");
            alerta.getNotification().showAndWait();
            conexion.endConnection();
        }
    }
}
