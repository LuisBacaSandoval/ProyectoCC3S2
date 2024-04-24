package org.example.sprinttwo.englishdraughts;

import org.example.sprinttwo.database.ConnectionBD;
import org.example.sprinttwo.database.RequestUser;

public class User {
    private int id;
    private String nombre;
    private String correo;
    private String color;

    public void setUser(int id, String nombre, String correo, String color){
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.color = color;
    }
    public void setUser(ConnectionBD conexion, String usuario, String contrasenia){
        RequestUser userDAO = new RequestUser();
        userDAO.setUser(this, conexion, usuario, contrasenia);
        this.color = "NEGRO";
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
