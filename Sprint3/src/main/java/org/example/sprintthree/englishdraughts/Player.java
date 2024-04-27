package org.example.sprintthree.englishdraughts;

public class Player extends User{
    private String colorPiece;
    public Player(){

    }
    public Player(int id, String nombre, String correo, String colorPiece) {
        super(id, nombre, correo);
        this.colorPiece = colorPiece;
    }
    public String getColorPiece() {
        return colorPiece;
    }
    public void setColorPiece(String colorPiece) {
        this.colorPiece = colorPiece;
    }
}
