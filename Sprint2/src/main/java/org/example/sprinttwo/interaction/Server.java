package org.example.sprinttwo.interaction;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Alert;
import org.example.sprinttwo.englishdraughts.User;
import org.example.sprinttwo.others.Notification;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Observable, Runnable {
    User us2;
    public Server(User us2){
        this.us2 = us2;
    }
    BufferedReader entrada;
    public String mensajeDesdeLaptop="";
    //Clases a las que se les notificará el cambio en el mensaje
    private List<InvalidationListener> listeners = new ArrayList<>();

    // Método para notificar a los observadores
    private void notifyObservers() {
        for (InvalidationListener listener : listeners) {
            listener.invalidated(this);
        }
    }
    public void closes(ServerSocket serverSocket, Socket socket, BufferedReader entrada) throws IOException {
        serverSocket.close();
        socket.close();
        entrada.close();
    }
    public void recibirListo() {
        try {
            String mensaje = entrada.readLine();
            if ("LISTO".equals(mensaje)) {
                Platform.runLater(() -> {
                    Notification notification = new Notification("Juego listo, inicia NEGRO", "information");
                    Alert alert = notification.getNotification();
                    alert.showAndWait();
                });
                // Recibir datos del otro usuario
                // Aquí asumes que el otro servidor envía los datos en el mismo orden que los envías
                us2.setUser(Integer.parseInt(entrada.readLine()), entrada.readLine(), entrada.readLine(), entrada.readLine());
                notifyObservers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void recibirMovimiento() {
        String[] digitos = mensajeDesdeLaptop.split("");
        int fromRow = Integer.parseInt(digitos[0]);
        int fromColumn = Integer.parseInt(digitos[1]);
        int toRow = Integer.parseInt(digitos[2]);
        int tolumn = Integer.parseInt(digitos[3]);

        Platform.runLater(() -> {
            Notification notification = new Notification("Movimiento: "+fromRow+" "+fromColumn+" "+ toRow+" "+tolumn, "information");
            Alert alert = notification.getNotification();
            alert.showAndWait();
        });
    }
    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void run() {
        try {
            //Inicializa el servidor en el puerto 5000
            ServerSocket serverSocket = new ServerSocket(9000);
            System.out.println("Servidor en la computadora: Esperando conexiones..."); //Opcional

            // Espera a que se conecte el cliente (la laptop)
            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + socket.getInetAddress().getHostName());

            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Espera a recibir una señal de "Listo" del otro servidor
            recibirListo();
            // Una vez que ambos servidores están listos, puedes inicializar el juego

            while ((mensajeDesdeLaptop = entrada.readLine()) != null) {
                if ("0000".equals(mensajeDesdeLaptop)) {
                    closes(serverSocket, socket, entrada);
                    break; // Sal del bucle de lectura si recibimos "Quit"
                }
                recibirMovimiento();
                notifyObservers();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void inicializarJuego() {
        // Aquí puedes agregar la lógica para inicializar el juego
        System.out.println("El juego se está inicializando...");
    }
}
