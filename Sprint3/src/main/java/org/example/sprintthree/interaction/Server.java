package org.example.sprintthree.interaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static ServerSocket serverSocket;
    private static Socket socket1;
    private static Socket socket2;
    private static BufferedReader entradaSocket1;
    private static BufferedReader entradaSocket2;
    private static PrintWriter salidaSocket1;
    private static PrintWriter salidaSocket2;
    private String player1="";
    private String player2="";

    public void recibirListo() {
        try {
            player1 = entradaSocket1.readLine();
            player2 = entradaSocket2.readLine();
            salidaSocket1.println(player1+";"+player2);
            salidaSocket2.println(player1+";"+player2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void closes(){
        try {
            // Cierra los flujos de entrada y salida de ambos sockets
            if (entradaSocket1 != null)
                entradaSocket1.close();
            if (entradaSocket2 != null)
                entradaSocket2.close();
            if (salidaSocket1 != null)
                salidaSocket1.close();
            if (salidaSocket2 != null)
                salidaSocket2.close();

            // Cierra los sockets
            if (socket1 != null)
                socket1.close();
            if (socket2 != null)
                socket2.close();

            // Cierra el servidor
            if (serverSocket != null)
                serverSocket.close();

            System.out.println("Conexiones cerradas. Servidor detenido.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            //Inicializa el servidor en el puerto xxxx
            serverSocket = new ServerSocket(8000);
            System.out.println("Servidor en la computadora: Esperando conexiones...");

            // Espera a que se conecte el cliente 1
            socket1 = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + socket1.getInetAddress().getHostName());

            // Espera a que se conecte el cliente 2
            socket2 = serverSocket.accept();
            System.out.println("Cliente conectado desde: " + socket2.getInetAddress().getHostName());

            entradaSocket1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
            entradaSocket2 = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            salidaSocket1 = new PrintWriter(socket1.getOutputStream(), true);
            salidaSocket2 = new PrintWriter(socket2.getOutputStream(), true);

            recibirListo();

            String message1;
            String message2;
            boolean cliente1Enviando = true; // Variable para alternar entre los clientes

            // Una vez que ambos servidores están listos, puedes inicializar el juego
            while (true) {
                if (cliente1Enviando) {
                    message1 = entradaSocket1.readLine();
                    salidaSocket2.println(message1);
                } else {
                    message2 = entradaSocket2.readLine();
                    salidaSocket1.println(message2);

                }
                cliente1Enviando = !cliente1Enviando; // Alternar entre los clientes
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
