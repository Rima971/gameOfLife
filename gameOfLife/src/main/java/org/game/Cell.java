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

    public int updateStatus(Cell[] neighbours){
        /*
         * Any live cell with fewer than two live neighbors dies, as if by underpopulation. done
         * Any live cell with two or three live neighbors lives on to the next generation.
         * Any live cell with more than three live neighbors dies, as if by overpopulation. done
         * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        */

        int aliveNeighbours = 0;
        for (Cell neighbour : neighbours){
            if (neighbour.alive) aliveNeighbours++;
        }
        if (aliveNeighbours<2 || aliveNeighbours>3){
            this.alive = false;
        } else if (aliveNeighbours==3){
            this.alive = true;
        }
        return this.alive ? 1 : 0;
    }
}
