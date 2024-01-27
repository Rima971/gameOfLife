package org.game;

public enum ErrorType {
    CELL_NEGATIVE_COORDINATES("Cell coordinates cannot be negative."),
    BOARD_INVALID_DIMENSIONS("Board cannot have 0 or less rows or columns"),
    BOARD_INVALID_SEEDING_PERCENTAGE("Seeding percentage has to be between 0 and 1");

    public final String message;
    private ErrorType(String message){
        this.message = message;
    }
}
