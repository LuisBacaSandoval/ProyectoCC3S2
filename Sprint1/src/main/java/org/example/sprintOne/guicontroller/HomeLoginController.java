package org.example.sprintOne.guicontroller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class HomeLoginController {
    @FXML
    public TextField user; //usuario ingresado por interfaz
    @FXML
    public PasswordField password; //contraseña ingresada por interfaz
    @FXML
    public void handleLogin(){
        System.out.println("Hola");
    }
}
