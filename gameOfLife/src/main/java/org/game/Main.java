package org.game;

import java.io.Console;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        IO io = new IO();
        io.start();
    }
}