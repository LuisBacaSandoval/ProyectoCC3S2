package org.example.sprintthree.others;

import javafx.scene.control.Alert;

public class Notification {
    private final Alert alert;

    public Notification(String message, String type){
        Alert.AlertType alertType = getAlertType(type);
        alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setTitle(type.toUpperCase());
        alert.setContentText(message);
    }
    // Selecciona el tipo de alerta basado en el parámetro "type"
    private Alert.AlertType getAlertType(String type) {
        switch (type.toLowerCase()) {
            case "error":
                return Alert.AlertType.ERROR;
            case "information":
                return Alert.AlertType.INFORMATION;
            default:
                return Alert.AlertType.NONE;
        }
    }
    //Retorna la alerta segun el atributo "type"
    public Alert getNotification(){
        return this.alert;
    }
}

