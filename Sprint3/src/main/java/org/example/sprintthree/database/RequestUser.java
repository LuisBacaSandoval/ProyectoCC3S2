package org.example.sprintthree.database;

import org.example.sprintthree.englishdraughts.User;
import org.example.sprintthree.others.Notification;
import org.example.sprintthree.others.PasswordEncryption;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestUser {
    public static User user = new User();
    public static void setUser(String usuario, String contrasenia){
        PasswordEncryption ps = new PasswordEncryption();
        String contraseniaEncriptada = ps.encryptPassword(contrasenia);
        try {
            // Preparar la consulta SQL con parámetros de marcador de posición (?)
            PreparedStatement stmt = ConnectionBD.getConexion().prepareStatement("SELECT id, username, email FROM usuarios WHERE email = ? AND password = ?");
            // Establecer los valores de los parámetros
            stmt.setString(1, usuario); // Establecer el valor del primer parámetro (correo)
            stmt.setString(2, contraseniaEncriptada); // Establecer el valor del segundo parámetro (contrasenia)
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {//usuario encontrado
                user.setId(rs.getInt("id"));
                user.setNombre(rs.getString("username"));
                user.setCorreo(rs.getString("email"));
            }else {//usuario no encontrado
                user.setId(99999999);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        // Verificar si el usuario no se encontró y mostrar una notificación en consecuencia
        if (user.getId() == 99999999) {
            Notification alerta = new Notification("El usuario no se encuentra registrado", "error");
            alerta.getNotification().showAndWait();
            ConnectionBD.endConnection();
        }
    }

    public static User getUser() {
        return user;
    }

}
