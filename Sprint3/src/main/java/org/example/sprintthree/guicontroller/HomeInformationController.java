package org.example.sprintthree.guicontroller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.example.sprintthree.Main;
import org.example.sprintthree.database.ConnectionBD;
import org.example.sprintthree.database.SesionUser;
import org.example.sprintthree.interaction.Client;
import org.example.sprintthree.interaction.Server;

import java.io.IOException;

public class HomeInformationController implements InvalidationListener {
    Server servidor;
    Client cliente;
    @FXML
    public Label txtUser;
    @FXML
    public void initGame(){
        cliente = new Client();
        cliente.initClient();
        cliente.addListener(this);
    }
    public void setInformation(){
        txtUser.setText(SesionUser.getUsuarioActual().getNombre());
    }
    public void initServer(){
        servidor = new Server();
        Thread t = new Thread(servidor);
        t.start();
    }
    @FXML
    public void signOff(){
        ConnectionBD.endConnection();
        // Detener el servidor
        Server.closes();
        try {
            // Cargar la vista FXML de home-login
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/home-login-view.fxml"));
            Parent root = loader.load();
            // Crear una nueva escena con la raíz cargada
            Scene scene = new Scene(root);
            // Configurar el escenario (Stage) y mostrar la escena
            Stage stage = new Stage();
            stage.setTitle("Información de usuario");
            stage.setScene(scene);
            stage.show();
            // Opcional: cerrar la ventana de inicio de sesión actual
            ((Stage) txtUser.getScene().getWindow()).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invalidated(Observable observable) {
        if (observable instanceof Client) {
            Platform.runLater(() -> {
                try {
                    // Cargar la vista FXML de home
                    FXMLLoader loader = new FXMLLoader(Main.class.getResource("gui/home-view.fxml"));
                    Parent root = loader.load();
                    // Obtener el controlador de la ventana home
                    HomeController homeController = loader.getController();
                    // Crear una nueva escena con la raíz cargada
                    Scene scene = new Scene(root);
                    // Configurar el escenario (Stage) y mostrar la escena
                    Stage stage = new Stage();
                    stage.setTitle("Juego Damas");
                    stage.setScene(scene);
                    stage.show();
                    cliente.addListener(homeController);
                    // Opcional: cerrar la ventana de inicio de sesión actual
                    ((Stage) txtUser.getScene().getWindow()).close();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Manejar cualquier excepción que pueda ocurrir al cargar la ventana HomeLogin
                }
            });
        }
        cliente.removeListener(this);
    }
}
