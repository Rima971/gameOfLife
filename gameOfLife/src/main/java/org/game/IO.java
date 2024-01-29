package org.game;

import java.io.Console;

import static java.lang.Thread.sleep;

public class IO {
    private Console console;
    public IO(){
        this.console = System.console();
        if (console==null) throw new RuntimeException(ErrorType.NO_CONSOLE_FOUND.message);
    }

    private void clearScreenLinux(){
        System.out.print("\033\143");
    }

    private void animateBoard(int rows, int columns, double seedingPercentage) throws InterruptedException {
        Board board = new Board(rows, columns, seedingPercentage);
        while(board.getAliveCellsCount()>0){
            this.clearScreenLinux();
            System.out.println(board);
            board.update();
            System.out.println("Life Count: "+board.getAliveCellsCount()+"\n");
            sleep(1000);
        }
    }
    public void start() throws InterruptedException {
        console.printf("Welcome to the Game Of Life! \n");
        console.printf("Enter the number of rows: ");
        String rows = console.readLine();
        console.printf("Enter the number of columns: ");
        String columns = console.readLine();
        console.printf("Enter the seeding percentage (0-1): ");
        String seedingPercentage = console.readLine();
        this.animateBoard(Integer.parseInt(rows), Integer.parseInt(columns), Double.parseDouble(seedingPercentage));
    }

}
