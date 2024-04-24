package org.example.sprinttwo.interaction;

import org.example.sprinttwo.englishdraughts.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    PrintWriter salida;
    public void initClient(User us1) {
        try {
            // Establece la conexión con el servidor (la computadora)
            Socket socket = new Socket("localhost", 9000);
            //Establece el objeto para enviar mensajes al servidor
            salida = new PrintWriter(socket.getOutputStream(), true);
            sendReady(us1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Envía una señal de "Listo" al otro servidor
    public void sendReady(User us1) {
        salida.println("LISTO");
        salida.println(us1.getId()+"");
        salida.println(us1.getNombre());
        salida.println(us1.getCorreo());
        salida.println(us1.getColor());
        salida.flush();
    }
    public void sendMove(String fromRow, String fromColumn, String toRow , String tolumn){
        salida.println(fromRow+""+fromColumn+""+toRow+""+tolumn);
        salida.flush();
    }
}
