package org.game;

import java.security.InvalidParameterException;

public class Cell {
    private int row, column;
    private boolean alive = false;
    public Cell(int row, int column){
        if (row<0 || column<0) throw new InvalidParameterException(ErrorType.CELL_NEGATIVE_COORDINATES.message);
        this.row = row;
        this.column = column;
    }

    public Cell(int row, int column, boolean alive){
        if (row<0 || column<0) throw new InvalidParameterException(ErrorType.CELL_NEGATIVE_COORDINATES.message);
        this.row = row;
        this.column = column;
        this.alive = alive;
    }
}
