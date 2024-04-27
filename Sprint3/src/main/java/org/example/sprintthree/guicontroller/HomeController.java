package org.example.sprintthree.guicontroller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import org.example.sprintthree.englishdraughts.Board;
import org.example.sprintthree.englishdraughts.Game;
import org.example.sprintthree.englishdraughts.User;
import org.example.sprintthree.interaction.Client;
import org.example.sprintthree.interaction.Server;
import org.example.sprintthree.others.Notification;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, InvalidationListener {
    Client client;
    private User us1;
    private User us2;
    private int[][] board;
    private int firstButtonPressRow = -1;
    private int firstButtonPressColumn = -1;
    Game game;
    @FXML
    public Label welcome1;
    @FXML
    public Label welcome2;
    @FXML
    public Label txtUser1;
    @FXML
    public Label txtEmail1;
    @FXML
    public Label txtUser2;
    @FXML
    public Label txtEmail2;
    @FXML
    public Button btnInit;
    @FXML
    public GridPane gridBoard;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        board = new int[8][8];
        // asignar colores al tablero
        for (int row = 0; row < gridBoard.getRowCount(); row++) {
            for (int col = 0; col < gridBoard.getColumnCount(); col++) {
                Button button = new Button();
                button.setPrefSize(43, 43);
                button.setPadding(Insets.EMPTY); // Eliminar relleno
                button.setStyle("-fx-background-color: transparent;"); // Eliminar margen
                Image image;
                ImageView imageView;
                if ((row + col)%2==0){
                    image = new Image("/assets/EspacionBlanco.png"); // Ruta de la imagen
                    imageView = new ImageView(image);
                    imageView.setFitWidth(43); // Tamaño de la imagen dentro del botón
                    imageView.setFitHeight(43);
                }else {
                    image = new Image("/assets/EspacionNegro.png"); // Ruta de la imagen
                    imageView = new ImageView(image);
                    imageView.setFitWidth(43); // Tamaño de la imagen dentro del botón
                    imageView.setFitHeight(43);
                }

                int r = row;
                int c = col;
                button.setOnAction(event -> {//asignar eventos a cada boton
                    if (firstButtonPressRow == -1 && firstButtonPressColumn == -1) {
                        // Primer botón presionado
                        firstButtonPressRow = r;
                        firstButtonPressColumn = c;
                    } else {
                        String player = "";//variable para saber que jugador hace el movimiento
                        if(game.getBoard().grid[firstButtonPressRow][firstButtonPressColumn].getColor() == 1){
                            player = "RED";
                        }else{
                            player = "BLACK";
                        }
                        // Segundo botón presionado, llama a la función handleMove()
                        game.handleMove(firstButtonPressRow, firstButtonPressColumn, r, c, player);
                        // Reinicia las variables de estado para el próximo par de botones
                        firstButtonPressRow = -1;
                        firstButtonPressColumn = -1;
                    }
                });
                button.setGraphic(imageView);
                button.setDisable(true);
                gridBoard.add(button, col, row);
                board[row][col] = 0;
            }
        }
    }
    private void addPieces(GridPane tablero) {
        //agrega la imagen para la ficha roja
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = (fila + 1) % 2; columna < gridBoard.getColumnCount(); columna += 2) {
                Image fichaRoja = new Image("/assets/fichaRn.jpg");
                addPiece(tablero, fila, columna, fichaRoja);
                board[fila][columna] = 1;
            }
        }
        // agrega la imagen para la ficha negra
        for (int fila = gridBoard.getRowCount() - 1; fila >= gridBoard.getRowCount() - 3; fila--) {
            for (int columna = (fila + 1) % 2; columna < gridBoard.getColumnCount(); columna += 2) {
                Image fichaNegra = new Image("/assets/fichaNn.jpg");
                addPiece(tablero, fila, columna, fichaNegra);
                board[fila][columna] = 2;
            }
        }
    }
    private void addPiece(GridPane tablero, int fila, int columna, Image ficha) {
        ImageView imageViewRoja = new ImageView(ficha);
        imageViewRoja.setFitWidth(43);
        imageViewRoja.setFitHeight(43);
        Button button;
        for (javafx.scene.Node node : tablero.getChildren()) {
            Integer rowIndex = GridPane.getRowIndex(node);
            Integer columnIndex = GridPane.getColumnIndex(node);
            if (rowIndex != null && columnIndex != null && rowIndex == fila && columnIndex == columna && node instanceof Button) {
                button = (Button) node;
                button.setGraphic(imageViewRoja);
                break;
            }
        }
    }
    public void initServer(){
        Server servidor = new Server(us2 = new User());
        servidor.addListener(this);
        Thread t = new Thread(servidor);
        t.start();
    }
    @FXML
    public void initGame(){
        client = new Client();
        client.initClient(us1);
        btnInit.setVisible(false);
        for (javafx.scene.Node node : gridBoard.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setDisable(false);
            }
        }
        addPieces(gridBoard);
        game = new Game(gridBoard, board, us1, us2);
    }
    public void setUser(User us){
        this.us1 = us;
    }
    @Override
    public void invalidated(Observable observable) {
        if (observable instanceof Server) {
            Server servidor = (Server) observable;
            Platform.runLater(() -> {
                if (us2!=null && servidor.mensajeDesdeLaptop==""){
                    welcome1.setText("Bienvenido...");
                    welcome2.setText("Bienvenido...");
                    if (us1.getColor()=="NEGRO"){
                        txtUser1.setText(us1.getNombre());
                        txtEmail1.setText(us1.getCorreo());
                        txtUser2.setText(us2.getNombre());
                        txtEmail2.setText(us2.getCorreo());
                    }else{
                        txtUser1.setText(us2.getNombre());
                        txtEmail1.setText(us2.getCorreo());
                        txtUser2.setText(us1.getNombre());
                        txtEmail2.setText(us1.getCorreo());
                    }
                }
                if (servidor.mensajeDesdeLaptop!=""){
                    Notification notification = new Notification("Movimiento: "+servidor.mensajeDesdeLaptop, "information");
                    Alert alert = notification.getNotification();
                    alert.showAndWait();
                }
            });
        }
    }
}
