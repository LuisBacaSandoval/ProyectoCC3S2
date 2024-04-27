package org.example.sprintthree.database;

import org.example.sprintthree.englishdraughts.User;

public class SesionUser {
    private static User userLogueado;

    public static User getUsuarioActual() {
        return userLogueado;
    }

    public static void setUsuarioActual(User usuario) {
        userLogueado = usuario;
    }
}
