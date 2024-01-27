package org.game;

public enum ErrorType {
    CELL_NEGATIVE_COORDINATES("Cell coordinates cannot be negative.");

    public String message;
    private ErrorType(String message){
        this.message = message;
    }
}
