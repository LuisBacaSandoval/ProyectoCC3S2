package org.example.sprinttwo.guicontroller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.example.sprinttwo.englishdraughts.User;
import org.example.sprinttwo.interaction.Server;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, InvalidationListener {
    private static final int TAMANO_CASILLA = 43;
    private static final int TAMANO_TABLERO = 8;

    private User us1;
    @FXML
    public Label txtUser1;
    @FXML
    public Label txtEmail1;
    @FXML
    public GridPane gridBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (us1 != null) {
            txtUser1.setText(us1.getNombre());
            txtEmail1.setText(us1.getCorreo());
        }
        // asignar colores al tablero
        for (int row = 0; row < gridBoard.getRowCount(); row++) {
            for (int col = 0; col < gridBoard.getColumnCount(); col++) {
                Rectangle rectangle = new Rectangle(43,43); // Tamaño de cada casilla
                // Asignar color a las casillas alternas
                rectangle.setFill((row + col) % 2 == 0 ? Color.CHOCOLATE : Color.WHITE);
                gridBoard.add(rectangle, col, row);
            }
        }
        colocarPiezas(gridBoard);

    }
    private void colocarPiezas(GridPane tablero) {
        // Ejemplo de colocación de piezas, aquí debes implementar tu lógica real
        // Agregamos circulos rojos como piezas del jugador 1
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = (fila + 1) % 2; columna < TAMANO_TABLERO; columna += 2) {
                Circle piezaRoja = new Circle(20, Color.DARKRED);
                // Establecer el radio del borde
                piezaRoja.setStrokeWidth(3);
                // Establecer el color del borde
                piezaRoja.setStroke(Color.BLACK);
                tablero.add(piezaRoja, columna, fila);
            }
        }

        // Agregamos circulos negros como piezas del jugador 2
        for (int fila = TAMANO_TABLERO - 1; fila >= TAMANO_TABLERO - 3; fila--) {
            for (int columna = (fila + 1) % 2; columna < TAMANO_TABLERO; columna += 2) {
                Circle piezaNegra = new Circle(20, Color.DARKKHAKI);
                // Establecer el radio del borde
                piezaNegra.setStrokeWidth(3);
                // Establecer el color del borde
                piezaNegra.setStroke(Color.BLACK);
                tablero.add(piezaNegra, columna, fila);
            }
        }
    }
    public void init(){
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
