package org.game;

import java.security.InvalidParameterException;

public class Board {
    private Cell[][] cellGrid;
    private int rows, columns;

    private double seedingPercentage;
    public Board(int rows, int columns, double seedingPercentage){
        if (rows <= 0 || columns <= 0) throw new InvalidParameterException(ErrorType.BOARD_INVALID_DIMENSIONS.message);
        if (seedingPercentage <= 0 || seedingPercentage >= 1) throw new InvalidParameterException(ErrorType.BOARD_INVALID_SEEDING_PERCENTAGE.message);
        this.rows = rows;
        this.columns = columns;
        this.seedingPercentage = seedingPercentage;
    }

    private void initEmptyGrid(){

    }
}
