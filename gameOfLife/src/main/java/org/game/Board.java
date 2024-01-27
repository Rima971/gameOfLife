package org.game;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;

public class Board {
    private Cell[][] cellGrid;
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
    }

    public void initialize(){
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

    public int testAliveCellsCount(){
        return this.aliveCellsCounter;
    }
}
