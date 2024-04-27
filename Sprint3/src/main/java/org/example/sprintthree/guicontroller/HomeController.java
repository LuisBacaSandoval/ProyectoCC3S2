package org.example.sprintthree.guicontroller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.sprintthree.englishdraughts.Game;
import org.example.sprintthree.englishdraughts.Player;
import org.example.sprintthree.interaction.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable, InvalidationListener {
    private boolean isUserMove = true; // Agrega esta bandera
    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private int[][] initBoard;
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
    public GridPane gridBoard;
    @FXML
    public ImageView imgRed;
    @FXML
    public ImageView imgBlack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setValues();//asigna datos de los jugadores
        initBoard = new int[8][8];//arreglo que guardara las posiciones iniciales de las fichas
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
                        String moveOf = "";//variable para saber que jugador hace el movimiento
                        if(game.getBoard().grid[firstButtonPressRow][firstButtonPressColumn].getColor() == 1){
                            moveOf = player2.getColorPiece();
                        }else{
                            moveOf = player1.getColorPiece();
                        }
                        // Segundo botón presionado, llama a la función handleMove()
                        game.handleMove(firstButtonPressRow, firstButtonPressColumn, r, c, moveOf, isUserMove);
                        // Reinicia las variables de estado para el próximo par de botones
                        firstButtonPressRow = -1;
                        firstButtonPressColumn = -1;
                    }
                });
                button.setGraphic(imageView);//añadimos las imagenes para cada boton
                gridBoard.add(button, col, row);//añadimos el boton a cada casilla del gridpane
                initBoard[row][col] = 0;
            }
        }
        addPieces(gridBoard);//añadimos las piezas al tablero
        game = new Game(gridBoard, initBoard);// iniciamos el juego
    }
    private void addPieces(GridPane tablero) {
        //agrega la imagen para la ficha roja
        for (int fila = 0; fila < 3; fila++) {
            for (int columna = (fila + 1) % 2; columna < gridBoard.getColumnCount(); columna += 2) {
                Image fichaRoja = new Image("/assets/fichaRn.jpg");
                addPiece(tablero, fila, columna, fichaRoja);
                initBoard[fila][columna] = 1;
            }
        }
        // agrega la imagen para la ficha negra
        for (int fila = gridBoard.getRowCount() - 1; fila >= gridBoard.getRowCount() - 3; fila--) {
            for (int columna = (fila + 1) % 2; columna < gridBoard.getColumnCount(); columna += 2) {
                Image fichaNegra = new Image("/assets/fichaNn.jpg");
                addPiece(tablero, fila, columna, fichaNegra);
                initBoard[fila][columna] = 2;
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
    public void setValues(){
        welcome1.setText("Bienvenido...");
        welcome2.setText("Bienvenido...");
        Image image1 = new Image("/assets/fichaRn.jpg");
        Image image2 = new Image("/assets/fichaNn.jpg");
        imgRed.setImage(image1);
        imgBlack.setImage(image2);
        txtUser1.setText(player2.getNombre());
        txtEmail1.setText(player2.getCorreo());
        txtUser2.setText(player1.getNombre());
        txtEmail2.setText(player1.getCorreo());

    }
    public static Player getPlayer1() {
        return player1;
    }
    public static void setPlayer1(Player player1) {
        HomeController.player1 = player1;
    }
    public static Player getPlayer2() {
        return player2;
    }
    public static void setPlayer2(Player player2) {
        HomeController.player2 = player2;
    }
    @Override
    public void invalidated(Observable observable) {
        if (observable instanceof Client) {
            Client cliente = (Client) observable;
            String[] message = cliente.mensaje.split(",");
            int fromRow = Integer.parseInt(message[0]);
            int fromCol = Integer.parseInt(message[1]);
            int toRow = Integer.parseInt(message[2]);
            int toCol = Integer.parseInt(message[3]);
            String moveOf = message[4];
            Platform.runLater(() -> {
                game.handleMove(fromRow, fromCol, toRow, toCol, moveOf, false);
            });
        }
    }
}
