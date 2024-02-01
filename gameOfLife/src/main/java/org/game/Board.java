package org.game;

import java.security.InvalidParameterException;
import java.util.*;

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
        int[] neighboursProximity = {-1, 0, 1};

        for (int rowProximity: neighboursProximity){
            for (int columnProximity: neighboursProximity){
                int derivedRow = row + rowProximity;
                int derivedColumn = column + columnProximity;
                if ( derivedRow == row && derivedColumn == column) continue;
                if (derivedRow >= 0 && derivedRow < this.rows && derivedColumn >= 0 && derivedColumn < this.columns){
                    neighbours.add(this.clonedCellGrid[derivedRow][derivedColumn]);
                }
            }
        }

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

    public int getAliveCellsCount(){
        return this.aliveCellsCounter;
    }
}
