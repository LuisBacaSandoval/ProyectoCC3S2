package org.example.sprintOne.englishdraughts;

import org.example.sprintOne.database.ConnectionBD;
import org.example.sprintOne.database.RequestUser;

public class User {
    private int id;
    private String nombre;
    private String correo;

    public void setUser(ConnectionBD conexion, String usuario, String contrasenia){
        RequestUser userDAO = new RequestUser();
        userDAO.setUser(this, conexion, usuario, contrasenia);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
