package org.example.sprintthree.englishdraughts;

public class Piece {
    private int color;
    private boolean isKing;

    public Piece(int color) {
        this.color = color;
        this.isKing = false;
    }
    public void makeKing() {
        this.isKing = true;
    }
    public boolean isKing() {
        return this.isKing;
    }
    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }
}

