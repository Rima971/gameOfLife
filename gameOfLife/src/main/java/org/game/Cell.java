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
         * Any live cell with fewer than two live neighbors dies, as if by underpopulation.
         * Any live cell with two or three live neighbors lives on to the next generation.
         * Any live cell with more than three live neighbors dies, as if by overpopulation.
         * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
        */
        boolean previouslyALive = this.alive;
        int aliveNeighbours = 0;
        for (Cell neighbour : neighbours){
            if (neighbour.alive) aliveNeighbours++;
        }
        if (aliveNeighbours<2 || aliveNeighbours>3){
            this.alive = false;
        } else if (aliveNeighbours==3){
            this.alive = true;
        }
        if (previouslyALive!=this.alive){
            return this.alive ? 1 : -1;
        } else return 0;

    }

    @Override
    public String toString(){
        return this.alive ? Symbol.ALIVE.representation : Symbol.EMPTY.representation;
    }

    public Cell duplicate(){
        return new Cell(this.row, this.column, this.alive);
    }

    public boolean equals(Object o){
        if (o==this) return true;
        if (o==null || o.getClass()!=this.getClass()) return false;
        Cell cell = (Cell) o;
        return cell.row == this.row && this.column == cell.column && this.alive == cell.alive;
    }
}
