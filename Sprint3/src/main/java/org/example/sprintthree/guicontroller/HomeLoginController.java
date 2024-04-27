package org.example.sprintthree.guicontroller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.sprintthree.Main;
import org.example.sprintthree.database.ConnectionBD;
import org.example.sprintthree.database.RequestUser;
import org.example.sprintthree.database.SesionUser;
import org.example.sprintthree.others.Notification;

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
            ConnectionBD.startConnection();//conectarse a la base de datos

            RequestUser.setUser(usuario, contrasenia);//buscar el usuario en la bds

            SesionUser.setUsuarioActual(RequestUser.getUser());//asignar el usuario logueado

            if(SesionUser.getUsuarioActual().getId()==99999999) return;

            //Llamar a la ventana home-information-login
            try {
                // Cargar la vista FXML de home-information
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/home-information-view.fxml"));
                Parent root = loader.load();
                // Obtener el controlador
                HomeInformationController controller = loader.getController();
                controller.setInformation();
                // Crear una nueva escena con la raíz cargada
                Scene scene = new Scene(root);
                // Configurar el escenario (Stage) y mostrar la escena
                Stage stage = new Stage();
                stage.setTitle("Información de usuario");
                stage.setScene(scene);
                controller.initServer();
                stage.show();
                // cerrar la ventana de inicio de sesión actual
                ((Stage) user.getScene().getWindow()).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            Notification alerta = new Notification("El usuario y/o contraseña son incorrectos", "error");
            alerta.getNotification().showAndWait();
        }
    }
    public Boolean verifyItems(String usuario, String contrasenia){
        return usuario.matches(EXP_REG_USER) && contrasenia.matches(EXP_REG_PASSWORD);
    }
}
