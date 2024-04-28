package org.example.sprintthree.englishdraughts;

public class Board {
    public Piece[][] grid;
    public boolean eat = false;

    public Board() {
        this.grid = new Piece[8][8]; // Tablero de 8x8
    }

    public void initializeBoard(int[][] boardInt) { //inicializa el tablero
        for (int i=0; i<8; i++){
            for(int j =0 ; j<8;j++){
                Piece piece;
                if (boardInt[i][j] == 1){
                    piece = new Piece(1);//ficha RED
                    grid[i][j] = piece;
                } else if (boardInt[i][j] == 2) {
                    piece = new Piece(2);//ficha BLACK
                    grid[i][j] = piece;
                }else{
                    piece = new Piece(0);
                    grid[i][j] = piece;
                }
            }
        }
    }

    public boolean movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // Verificar si las coordenadas están dentro del tablero
        if (!isValidSquare(fromRow, fromCol) || !isValidSquare(toRow, toCol)) {
            System.out.println("Coordenadas fuera del tablero. Inténtalo de nuevo.");
            return false;
        }

        // Verificar si hay una pieza en la casilla de origen
        if (grid[fromRow][fromCol].getColor() == 0) {
            System.out.println("No hay una pieza en la casilla de origen. Inténtalo de nuevo.");
            return false;
        }

        // Verificar si la casilla de destino está vacía
        if (grid[toRow][toCol].getColor() != 0) {
            System.out.println("La casilla de destino no está vacía. Inténtalo de nuevo.");
            return false;
        }

        // Verificar si se puede comer una pieza
        if (canEatPiece(fromRow, fromCol, toRow, toCol)) {
            // Comer la pieza
            eatPiece(fromRow, fromCol, toRow, toCol);
            eat = true;
            return true;
        }

        // Verificar si el movimiento es válido (diagonalmente una casilla hacia adelante)
        if (!isValidDiagonalMove(fromRow, fromCol, toRow, toCol)) {
            System.out.println("Movimiento inválido. Las damas solo pueden moverse en diagonal una casilla hacia adelante.");
            return false;
        }

        // Realizar el movimiento
        Piece p = new Piece(0);
        grid[toRow][toCol] = grid[fromRow][fromCol];
        grid[fromRow][fromCol] = p;
        eat = false;
        return true;
    }
    public boolean canEatPiece(int fromRow, int fromCol, int toRow, int toCol) {// Verificar si se puede comer una pieza
        int rowDifference = toRow - fromRow;
        int colDifference = toCol - fromCol;

        // Verificar si el movimiento es diagonal de dos casillas
        if (Math.abs(rowDifference) == 2 && Math.abs(colDifference) == 2) {
            int midRow = fromRow + rowDifference / 2;
            int midCol = fromCol + colDifference / 2;

            // Verificar si hay una pieza en la casilla intermedia
            if (grid[midRow][midCol].getColor()!= 0 && grid[midRow][midCol].getColor()!= grid[fromRow][fromCol].getColor()) {
                return true;
            }
        }
        return false;
    }
    public void eatPiece(int fromRow, int fromCol, int toRow, int toCol) {// Comer la pieza
        int rowDifference = toRow - fromRow;
        int colDifference = toCol - fromCol;

        int midRow = fromRow + rowDifference / 2;
        int midCol = fromCol + colDifference / 2;

        // Eliminar la pieza comida
        grid[midRow][midCol] = new Piece(0);

        // Realizar el movimiento
        Piece p = new Piece(0);
        grid[toRow][toCol] = grid[fromRow][fromCol];
        grid[fromRow][fromCol] = p;
    }
    // Verificar si las coordenadas están dentro del tablero
    public boolean isValidSquare(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }
    // Verificar si el movimiento es diagonal de una casilla
    public boolean isValidDiagonalMove(int fromRow, int fromCol, int toRow, int toCol) {
        int rowDifference = Math.abs(toRow - fromRow);
        int colDifference = Math.abs(toCol - fromCol);

        // Verificar si el movimiento es diagonal de una casilla
        if (rowDifference!= 1 || colDifference!= 1) {
            return false;
        }

        // Verificar si la pieza se mueve hacia adelante
        Piece piece = grid[fromRow][fromCol];
        if (piece.getColor() == 1) { // Dama roja
            return toRow > fromRow; // Solo se puede mover hacia abajo
        } else if (piece.getColor() == 2) { // Dama negra
            return toRow < fromRow; // Solo se puede mover hacia arriba
        }

        return false;
    }

    public int[][] getPossibleMoves() {
        // Implementar la obtención de todos los movimientos posibles para el jugador actual
        return null;
    }

}
