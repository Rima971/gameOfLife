package org.game;

import java.security.InvalidParameterException;
import java.util.*;
import java.util.function.Function;

public class Board {
    private final Cell[][] cellGrid;
    private Cell[][] clonedCellGrid;
    private final int rows, columns, totalCellsCount;
    private int aliveCellsCounter=0;
    private final double initiallyAliveCellsRequired;

    public Board(int rows, int columns, double seedingPercentage){
        if (rows <= 0 || columns <= 0) throw new InvalidParameterException(ErrorType.BOARD_INVALID_DIMENSIONS.message);
        if (seedingPercentage <= 0 || seedingPercentage >= 1) throw new InvalidParameterException(ErrorType.BOARD_INVALID_SEEDING_PERCENTAGE.message);
        this.rows = rows;
        this.columns = columns;
        this.totalCellsCount = rows * columns;
        this.initiallyAliveCellsRequired = Math.round(seedingPercentage * this.totalCellsCount);
        this.cellGrid = new Cell[rows][columns];
        this.initialize();
        this.clonedCellGrid = this.cloneCellGrid();
    }

    public Board(int[][] grid){
        if (grid.length <= 0 || grid[0].length <=0 ) throw new InvalidParameterException(ErrorType.BOARD_EMPTY_GRID.message);
        this.rows = grid.length;
        this.columns = grid[0].length;
        this.cellGrid = new Cell[this.rows][this.columns];
        this.clonedCellGrid = new Cell[this.rows][this.columns];
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.columns; j++){
                this.cellGrid[i][j] = new Cell(i,j,grid[i][j]==1);
                this.clonedCellGrid[i][j] = new Cell(i,j,grid[i][j]==1);
            }
        }
        this.totalCellsCount = rows * columns;
        this.initiallyAliveCellsRequired = 0;
    }

    private void initialize(){
            this.createEmptyGrid();
            this.populateAliveCells();
    }

    private void createEmptyGrid(){
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.columns; j++){
                this.cellGrid[i][j] = new Cell(i, j, false);
            }
        }
    }

    private void populateAliveCells(){

        // creating a hashmap to record which coordinates were already populated randomly;
        HashMap<Integer, Boolean[]> record = new HashMap<Integer, Boolean[]>();
        Boolean[] columnRecordArray = new Boolean[columns];
        Arrays.fill(columnRecordArray, false);
        for (int i=0; i<this.rows; i++){
            record.put(i, columnRecordArray.clone());
        }

        while(this.aliveCellsCounter < this.initiallyAliveCellsRequired){
            int randomRow = this.getRandomRow();
            int randomColumn = this.getRandomColumn();
            if (record.get(randomRow)[randomColumn]){
                continue;
            }
            record.get(randomRow)[randomColumn] = true;
            this.cellGrid[randomRow][randomColumn] = new Cell(randomRow, randomColumn, true);
            this.aliveCellsCounter++;
        }

    }

    private int getRandomRow(){
        return (int) Math.floor(Math.random()*this.rows);
    }

    private int getRandomColumn(){
        return (int) Math.floor(Math.random()*this.columns);
    }

    private Cell[] getNeighbours(int row, int column){
        ArrayList<Cell> neighbours = new ArrayList<Cell>();

        boolean notTopmostRow = row>0 && row<this.rows;
        boolean notBottommostRow = row>=0 && row<this.rows-1;
        boolean notLeftmostColumn = column>0 && column<this.columns;
        boolean notRightmostColumn = column>=0 && column<this.columns-1;

        if (notTopmostRow) neighbours.add(this.clonedCellGrid[row-1][column]);
        if (notBottommostRow) neighbours.add(this.clonedCellGrid[row+1][column]);
        if (notLeftmostColumn) neighbours.add(this.clonedCellGrid[row][column-1]);
        if (notRightmostColumn) neighbours.add(this.clonedCellGrid[row][column+1]);
        if (notTopmostRow && notLeftmostColumn) neighbours.add(this.clonedCellGrid[row-1][column-1]);
        if (notBottommostRow && notLeftmostColumn) neighbours.add(this.clonedCellGrid[row+1][column-1]);
        if (notTopmostRow && notRightmostColumn) neighbours.add(this.clonedCellGrid[row-1][column+1]);
        if (notBottommostRow && notRightmostColumn) neighbours.add(this.clonedCellGrid[row+1][column+1]);

        return neighbours.toArray(new Cell[0]);
    }

    public void update(){
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.columns; j++){
                Cell[] neighbours = this.getNeighbours(i,j);
                this.aliveCellsCounter += this.cellGrid[i][j].updateStatus(neighbours);
            }
        }
        this.clonedCellGrid = this.cloneCellGrid();
    }

    private Cell[][] cloneCellGrid(){
        Cell[][] clonedGrid = new Cell[this.rows][this.columns];
        for (int i = 0; i<this.rows; i++){
            for (int j=0; j<this.columns; j++){
                clonedGrid[i][j] = this.cellGrid[i][j].duplicate();
            }
        }
        return clonedGrid;
    }

    @Override
    public String toString(){
        StringBuilder output = new StringBuilder();
        for (int i=0; i<this.rows; i++){
            for (int j=0; j<this.columns; j++){
                output.append(this.cellGrid[i][j].toString());
            }
            output.append("\n");
        }
        return output.toString();
    }

    public int testAliveCellsCount(){
        return this.aliveCellsCounter;
    }
}
