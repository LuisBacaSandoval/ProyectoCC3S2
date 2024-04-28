package org.example.sprinttwo.englishdraughts;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.sprinttwo.others.Notification;

public class Game {
    Board board;
    GridPane gridBoard;
    User user1;
    User user2;
    private String currentPlayer;
    public Game(GridPane gridBoard, int[][] boardInt, User user1, User user2){
        this.gridBoard = gridBoard;
        board = new Board();
        board.initializeBoard(boardInt);
        this.user1 = user1;
        this.user2 = user2;
        this.currentPlayer = "BLACK";
    }
    public void handleMove(int fromRow,int fromCol,int toRow,int toCol, String jugador){
        if (jugador!=this.currentPlayer){
            return;
        }
        Image imageBoxBlack = new Image("/assets/EspacionNegro.png");
        ImageView imageBox = new ImageView(imageBoxBlack);
        imageBox.setFitWidth(43);
        imageBox.setFitHeight(43);

        Image imagePieceBR = new Image("/assets/fichaRn.jpg");
        if (board.grid[fromRow][fromCol].getColor() == 2){
            imagePieceBR = new Image("/assets/fichaNn.jpg");
        }
        ImageView imagePiece = new ImageView(imagePieceBR);
        imagePiece.setFitWidth(43);
        imagePiece.setFitHeight(43);

        if(board.movePiece(fromRow, fromCol, toRow, toCol)){
            Button button1;
            for (javafx.scene.Node node : gridBoard.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && columnIndex != null && rowIndex == fromRow && columnIndex == fromCol && node instanceof Button) {
                    button1 = (Button) node;
                    button1.setGraphic(imageBox);
                    break;
                }
            }
            for (javafx.scene.Node node : gridBoard.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && columnIndex != null && rowIndex == toRow && columnIndex == toCol && node instanceof Button) {
                    button1 = (Button) node;
                    button1.setGraphic(imagePiece);
                    break;
                }
            }
        }else{
            return;
        }

        if (board.eat){
            imageBoxBlack = new Image("/assets/EspacionNegro.png");
            imageBox = new ImageView(imageBoxBlack);
            imageBox.setFitWidth(43);
            imageBox.setFitHeight(43);
            for (javafx.scene.Node node : gridBoard.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && columnIndex != null && rowIndex == (fromRow+toRow)/2 && columnIndex == (fromCol+toCol)/2 && node instanceof Button) {
                    Button button = (Button) node;
                    button.setGraphic(imageBox);
                    break;
                }
            }
        }

        if (isGameOver() == true){
            User winUser = winningPlayer();
            Notification notification = new Notification("Juego finalizado", "information");
            notification.getNotification().showAndWait();
        }else{//verificar cuantas fichas restantes hay
            int totalBlack = 0;
            int totalRed = 0;
            for (int i = 0; i<8; i++){
                for (int j=0; j<8;j++){
                    if (board.grid[i][j].getColor()==1){
                        totalRed++;
                    }
                    if (board.grid[i][j].getColor()==2){
                        totalBlack++;
                    }
                }
            }
            System.out.println("Total RED: "+totalRed);
            System.out.println("Total BLACK: "+totalBlack);
        }
        //cambiamos de turno
        if (currentPlayer == "BLACK"){
            currentPlayer = "RED";
        }else {
            currentPlayer = "BLACK";
        }
    }
    public User winningPlayer(){
        return currentPlayer==user1.getColor()? user1 : user2;
    }
    public boolean isGameOver() {//metodo para verificar si el juego finalizó
        int totalBlack = 0;
        int totalRed = 0;
        for (int i = 0; i<8; i++){
            for (int j=0; j<8;j++){
                if (board.grid[i][j].getColor()==1){
                    totalRed++;
                }
                if (board.grid[i][j].getColor()==2){
                    totalBlack++;
                }
            }
        }
        if (totalRed==0 || totalBlack==0){
            return true;
        }
        return false;
    }
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
