package org.example.sprinttwo.guicontroller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.sprinttwo.Main;
import org.example.sprinttwo.database.ConnectionBD;
import org.example.sprinttwo.englishdraughts.User;
import org.example.sprinttwo.others.Notification;

import java.io.IOException;

public class HomeLoginController{
    private String EXP_REG_USER = "[a-zA-Z0-9._%+-]+@uni\\.pe"; //exp Regular, valida usuario uni
    private String EXP_REG_PASSWORD = "\\d{8}"; //exp Regular, valida contraseña 8 digitos(numeros)
    @FXML
    public TextField user; //usuario ingresado por interfaz
    @FXML
    public PasswordField password; //contraseña ingresada por interfaz

    @FXML
    public void handleLogin() {
        String usuario = user.getText();
        String contrasenia = password.getText();

        if (verifyItems(usuario, contrasenia)){
            //conectarse a la base de datos
            ConnectionBD cnx = new ConnectionBD();
            cnx.startConnection();
            User us = new User();
            us.setUser(cnx, usuario, contrasenia);

            if(us.getId()==99999999) return;

            //Llamar a la ventana home-login
            try {
                // Cargar la vista FXML de home-login
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/home-view.fxml"));
                Parent root = loader.load();
                // Obtener el controlador de la ventana HomeLogin
                HomeController controller = loader.getController();
                controller.setUser(us);
                // Crear una nueva escena con la raíz cargada
                Scene scene = new Scene(root);
                // Configurar el escenario (Stage) y mostrar la escena
                Stage stage = new Stage();
                stage.setTitle("Home Damas");
                stage.setScene(scene);
                stage.show();
                //Comenzar el juego, implementar boton para "buscar partida"
                controller.initServer();
                // Opcional: cerrar la ventana de inicio de sesión actual
                ((Stage) user.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar cualquier excepción que pueda ocurrir al cargar la ventana HomeLogin
            }

            cnx.endConnection();
        }else {
            Notification alerta = new Notification("El usuario y/o contraseña son incorrectos", "error");
            alerta.getNotification().showAndWait();
        }
    }
    public Boolean verifyItems(String usuario, String contrasenia){
        return usuario.matches(EXP_REG_USER) && contrasenia.matches(EXP_REG_PASSWORD);
    }
}
