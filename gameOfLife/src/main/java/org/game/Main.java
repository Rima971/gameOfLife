package org.game;

import java.io.Console;
import java.io.IOException;

import static java.lang.Thread.sleep;

public class Main {
    private static void clearScreenLinux(){
        System.out.print("\033\143");
    }

    private static void animateBoard(int rows, int columns, double seedingPercentage) throws InterruptedException {
        Board board = new Board(rows, columns, seedingPercentage);
        while(board.getAliveCellsCount()>0){
            Main.clearScreenLinux();
            System.out.println(board);
            board.update();
            System.out.println("Life Count: "+board.getAliveCellsCount()+"\n");
            sleep(1000);
        }
    }
    private static void start() throws InterruptedException{
        Console console = System.console();
        if (console == null) System.out.println("No console found");
        else{
            console.printf("Welcome to the Game Of Life! \n");
            console.printf("Enter the number of rows: ");
            String rows = console.readLine();
            console.printf("Enter the number of columns: ");
            String columns = console.readLine();
            console.printf("Enter the seeding percentage (0-1): ");
            String seedingPercentage = console.readLine();
            Main.animateBoard(Integer.valueOf(rows), Integer.valueOf(columns), Double.valueOf(seedingPercentage));
        }

    }
    public static void main(String[] args) throws IOException, InterruptedException {
        Main.start();
    }
}