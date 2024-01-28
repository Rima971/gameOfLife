import org.game.Board;
import org.game.ErrorType;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BoardTest {
    @Test
    public void successfully_creates_board(){
        new Board(10,20, 0.1);
    }

    @Test
    public void board_creation_exceptions(){
        Exception exception = assertThrows(InvalidParameterException.class, ()->new Board(0, 0, 0.1));
        assertEquals(exception.getMessage(), ErrorType.BOARD_INVALID_DIMENSIONS.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Board(3, 0, 0.1));
        assertEquals(exception.getMessage(), ErrorType.BOARD_INVALID_DIMENSIONS.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Board(-3, 0, 0.1));
        assertEquals(exception.getMessage(), ErrorType.BOARD_INVALID_DIMENSIONS.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Board(-3, -12, 0.1));
        assertEquals(exception.getMessage(), ErrorType.BOARD_INVALID_DIMENSIONS.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Board(3, 12, 0));
        assertEquals(exception.getMessage(), ErrorType.BOARD_INVALID_SEEDING_PERCENTAGE.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Board(3, 12, 16));
        assertEquals(exception.getMessage(), ErrorType.BOARD_INVALID_SEEDING_PERCENTAGE.message);
    }

    @Test
    public void board_of_20x20_with_point2_seeding_percentage_generates_80_alive_cells_initially(){
        Board board = new Board(20, 20, 0.2);
        // round(0.2*20*20)
        assertEquals(80, board.getAliveCellsCount());
    }

    @Test
    public void board_of_7x11_with_point1_seeding_percentage_generates_8_alive_cells_initially(){
        Board board = new Board(7, 11, 0.1);
        // round( 0.1 * 7 * 11 )
        assertEquals(8, board.getAliveCellsCount());
    }

    @Test
    public void board_of_4x11_with_point1_seeding_percentage_generates_4_alive_cells_initially(){
        Board board = new Board(4, 11, 0.1);
        // round( 0.1 * 4 * 11 )
        assertEquals(4, board.getAliveCellsCount());
    }

    @Test
    public void board_is_updating_correctly_in_3x3_grid(){
        int[][] initialBoardState = {{1,0,1},{0,1,0},{0,0,0}};
        Board board = new Board(initialBoardState);
        System.out.println(board);
        board.update();
        System.out.println(board);
        String expected = "-x-\n-x-\n---\n";
        assertEquals(board.toString().replaceAll(" ", ""), expected);
        board.update();
        expected = "---\n---\n---\n";
        assertEquals(board.toString().replaceAll(" ", ""), expected);

        int[][] initialBoardState2 = {{1,0,0},{0,1,0},{0,1,0}};
        board = new Board(initialBoardState2);
        board.update();
        expected = "---\nxx-\n---\n";
        assertEquals(board.toString().replaceAll(" ", ""), expected);
        board.update();
        expected = "---\n---\n---\n";
        assertEquals(board.toString().replaceAll(" ", ""), expected);
    }

    @Test
    public void board_is_updating_correctly_in_4x10_grid(){
        int[][] initialBoardState = {
                {1,0,1,0,0,0,1,0,1,0},
                {0,1,0,0,0,0,1,1,0,0},
                {0,0,0,1,0,1,0,1,0,0},
                {0,0,0,1,0,1,0,1,0,0}};
        Board board = new Board(initialBoardState);
        board.update();
        String expected = "-x----x---\n-xx--x--x-\n--x--x-xx-\n----------\n";
        assertEquals(board.toString().replaceAll(" ", ""), expected);
    }
}
