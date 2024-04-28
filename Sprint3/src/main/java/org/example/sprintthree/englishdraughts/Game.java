package org.example.sprintthree.englishdraughts;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import org.example.sprintthree.guicontroller.HomeController;
import org.example.sprintthree.interaction.Client;
import org.example.sprintthree.others.Notification;

public class Game {
    Board board;
    GridPane gridBoard;
    private String currentPlayer;
    public Game(GridPane gridBoard, int[][] boardInt){
        this.gridBoard = gridBoard;
        board = new Board();
        board.initializeBoard(boardInt);
        this.currentPlayer = "BLACK";
    }
    public void handleMove(int fromRow,int fromCol,int toRow,int toCol, String moveOf, Boolean isUserMove){
        if (!moveOf.equals(this.currentPlayer)){//verifica si es el turno del que hizo el movimiento
            return;
        }
        Image imageBoxBlack = new Image("/assets/EspacionNegro.png");
        ImageView imageBox = new ImageView(imageBoxBlack);
        imageBox.setFitWidth(43);
        imageBox.setFitHeight(43);

        Image imagePieceBR = new Image(board.grid[fromRow][fromCol].getColor() == 1?"/assets/fichaRn.jpg":"/assets/fichaNn.jpg");
        ImageView imagePiece = new ImageView(imagePieceBR);
        imagePiece.setFitWidth(43);
        imagePiece.setFitHeight(43);

        if(board.movePiece(fromRow, fromCol, toRow, toCol)){//si es un movimiento valido se muestra en la interfaz
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
        }else{//si no es un movimiento valido no hace nada
            return;
        }

        if (board.eat){//si una ficha fue "comida" desaparece la ficha de la interfaz
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

        if (board.grid[toRow][toCol].isKing()){
            Image imagePieceDama = new Image(board.grid[toRow][toCol].getColor() == 1?"/assets/fichaRRn.jpg":"/assets/fichaRNn.jpg");
            ImageView imgDama = new ImageView(imagePieceDama);
            imgDama.setFitWidth(43);
            imgDama.setFitHeight(43);
            for (javafx.scene.Node node : gridBoard.getChildren()) {
                Integer rowIndex = GridPane.getRowIndex(node);
                Integer columnIndex = GridPane.getColumnIndex(node);
                if (rowIndex != null && columnIndex != null && rowIndex == toRow && columnIndex == toCol && node instanceof Button) {
                    Button button = (Button) node;
                    button.setGraphic(imgDama);
                    break;
                }
            }
        }

        if (isUserMove){//enviamos movimiento al oponente
            Client.sendMove(fromRow, fromCol, toRow, toCol, moveOf);
        }
        Boolean gameOver = isGameOver();
        if (gameOver){//verficamos el fin del juego
            Player winPlayer = winningPlayer();
            Notification notification = new Notification("Juego finalizado, ganador: "+winPlayer.getNombre(), "information");
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
            System.out.println("Total rojas: "+totalRed);
            System.out.println("Total negras: "+totalBlack);
        }

        if (currentPlayer == "BLACK"){//cambiamos de turno
            currentPlayer = "RED";
        }else {
            currentPlayer = "BLACK";
        }
    }
    public Player winningPlayer(){
        return currentPlayer== HomeController.getPlayer1().getColorPiece() ? HomeController.getPlayer1() : HomeController.getPlayer2();
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
