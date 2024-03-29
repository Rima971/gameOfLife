package org.game;

public enum ErrorType {
    CELL_NEGATIVE_COORDINATES("Cell coordinates cannot be negative."),
    BOARD_INVALID_DIMENSIONS("Board cannot have 0 or less rows or columns"),
    BOARD_INVALID_SEEDING_PERCENTAGE("Seeding percentage has to be between 0 and 1"),
    BOARD_EMPTY_GRID("Board cannot accept matrix of 0 rows or columns"),
    NO_CONSOLE_FOUND("No console found");

    public final String message;
    private ErrorType(String message){
        this.message = message;
    }
}
