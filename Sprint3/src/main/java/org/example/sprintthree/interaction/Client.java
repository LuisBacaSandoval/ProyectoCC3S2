package org.example.sprintthree.interaction;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import org.example.sprintthree.database.SesionUser;
import org.example.sprintthree.englishdraughts.Player;
import org.example.sprintthree.guicontroller.HomeController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Observable {
    private static  PrintWriter salida;
    private BufferedReader entrada;
    public String mensaje="";
    private List<InvalidationListener> listeners = new ArrayList<>();//Clases a las que se les notificará el cambio en el mensaje
    public void initClient() {
        try {
            // Establece la conexión con el servidor (la computadora)
            Socket socket = new Socket("localhost", 8000);
            //Establece el objeto para enviar mensajes al servidor
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Inicia un hilo para escuchar los mensajes del servidor
            Thread thread = new Thread(this::listenServerMessages);
            thread.start();
            sendReady();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Método para escuchar continuamente los mensajes del servidor
    private void listenServerMessages() {
        try {
            while ((mensaje = entrada.readLine()) != null) {
                if (HomeController.getPlayer1().getId()==0 && HomeController.getPlayer2().getId()==0){
                    String[] players = mensaje.split(";");
                    String[] dataPlayer1 = players[0].split(",");
                    String[] dataPlayer2 = players[1].split(",");
                    HomeController.setPlayer1(new Player(
                            Integer.parseInt(dataPlayer1[0]),
                            dataPlayer1[1],
                            dataPlayer1[2],
                            "BLACK"
                    ));
                    HomeController.setPlayer2(new Player(
                            Integer.parseInt(dataPlayer2[0]),
                            dataPlayer2[1],
                            dataPlayer2[2],
                            "RED"
                    ));
                }
                notifyObservers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Envía una señal de "Listo" al otro servidor
    public void sendReady() {
        salida.println(SesionUser.getUsuarioActual().getId()+","+
                SesionUser.getUsuarioActual().getNombre()+","+
                SesionUser.getUsuarioActual().getCorreo()
        );
        salida.flush();
    }
    public static void sendMove(int fromRow,int fromCol,int toRow,int toCol, String moveOf){
        salida.println(
                fromRow+","+
                        fromCol+","+
                        toRow+","+
                        toCol+","+
                        moveOf+","
        );
        salida.flush();
    }
    private void notifyObservers() {
        List<InvalidationListener> listenersCopy = new ArrayList<>(listeners);
        for (InvalidationListener listener : listenersCopy) {
            listener.invalidated(this);
        }
    }
    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

}
