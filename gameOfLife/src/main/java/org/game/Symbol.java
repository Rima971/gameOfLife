package org.game;

public enum Symbol {
    ALIVE("x"),
    EMPTY("-");

    public final String representation;
    private Symbol(String representation){
        this.representation = representation;
    }
}
