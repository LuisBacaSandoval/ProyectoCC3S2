package org.example.sprintOne.guicontroller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.sprintOne.Main;
import org.example.sprintOne.database.ConnectionBD;
import org.example.sprintOne.englishdraughts.User;
import org.example.sprintOne.others.Notification;

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
            /*
            ************************************************************************************************
            * IMPORTANTE!
            ************************************************************************************************
            * COMENTAR EL SIGUIENTE IF/ELSE SI CONFIGURASTE EL SISTEMA DE PERSISTENCIA USANDO XAMPP
            * Y AGREGAR:
            * us.setUser(cnx, usuario, contrasenia);
            * EN VEZ DEL IF/ELSE
             ************************************************************************************************
            */
            if (cnx.getConexion()==null){
                us.setId(1);
                us.setNombre("Example1");
                us.setCorreo("example1.s@uni.pe");
            }else {
                us.setUser(cnx, usuario, contrasenia);
            }
            if(us.getId()==99999999) return;

            //Llamar a la ventana home
            try {
                // Cargar la vista FXML de home
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/home-view.fxml"));
                Parent root = loader.load();
                // Obtener el controlador de la ventana Home
                HomeController controller = loader.getController();
                controller.setUser(us);
                controller.initialize(loader.getLocation(), loader.getResources());
                // Crear una nueva escena con la raíz cargada
                Scene scene = new Scene(root);
                // Configurar el escenario (Stage) y mostrar la escena
                Stage stage = new Stage();
                stage.setTitle("Home");
                stage.setScene(scene);
                stage.show();
                //Comenzar el juego
                controller.init();
                // Cerrar la ventana de inicio de sesión actual
                ((Stage) user.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
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
