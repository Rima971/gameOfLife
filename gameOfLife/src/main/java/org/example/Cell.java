package org.example;

public class Cell {
    private int row, column;
    private boolean alive = false;
    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    }

    public Cell(int row, int column, boolean alive){
        this.row = row;
        this.column = column;
        this.alive = alive;
    }
}
