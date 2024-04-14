package org.example.sprintOne.guicontroller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.example.sprintOne.englishdraughts.User;
import org.example.sprintOne.interaction.Server;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, InvalidationListener {
    private User us1;
    @FXML
    public Label txtUser1;
    @FXML
    public Label txtEmail1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (us1 != null) {
            txtUser1.setText(us1.getNombre());
            txtEmail1.setText(us1.getCorreo());
        }
    }

    public void jugar(){
        Server servidor = new Server();
        servidor.addListener(this);
        Thread t = new Thread(servidor);
        t.start();
    }

    public void setUser(User us){
        this.us1 = us;
    }

    @Override
    public void invalidated(Observable observable) {
        if (observable instanceof Server) {
            Server servidor = (Server) observable;
            Platform.runLater(() -> {
                txtUser1.setText(servidor.mensajeDesdeLaptop);
            });
        }
    }
}
