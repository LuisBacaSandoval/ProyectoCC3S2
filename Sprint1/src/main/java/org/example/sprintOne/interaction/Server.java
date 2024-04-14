package org.example.sprintOne.interaction;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Observable, Runnable {
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


            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while ((mensajeDesdeLaptop = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido desde la laptop: " + mensajeDesdeLaptop);
                if ("Quit".equals(mensajeDesdeLaptop)) {
                    closes(serverSocket, socket, entrada);
                    break; // Sal del bucle de lectura si recibimos "Quit"
                }
                notifyObservers();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
