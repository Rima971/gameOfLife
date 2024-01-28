import org.game.Cell;
import org.game.ErrorType;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class CellTest {
    @Test()
    public void successfully_creates_cell(){
        new Cell(2,3);
        new Cell(22, 12, true);
    }

    @Test()
    public void cell_creation_exceptions(){
        Exception exception = assertThrows(InvalidParameterException.class, ()->new Cell(-2, -10));
        assertEquals(exception.getMessage(), ErrorType.CELL_NEGATIVE_COORDINATES.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Cell(-2, 10));
        assertEquals(exception.getMessage(), ErrorType.CELL_NEGATIVE_COORDINATES.message);

        exception = assertThrows(InvalidParameterException.class, ()->new Cell(2, -10));
        assertEquals(exception.getMessage(), ErrorType.CELL_NEGATIVE_COORDINATES.message);
    }

    @Test
    public void alive_cell_dies_when_surrounded_by_more_than_3_alive_cells(){
        Cell cell = new Cell(4,5, true);
        Cell[] neighbours = { new Cell(3, 4, true), new Cell(3, 5, true), new Cell(3, 6, true), new Cell(4, 4, false), new Cell(4, 6, true) };
        assertEquals(-1, cell.updateStatus(neighbours));
    }

    @Test
    public void empty_cell_stays_empty_when_surrounded_by_more_than_3_alive_cells(){
        Cell cell = new Cell(4,5, false);
        Cell[] neighbours = { new Cell(3, 4, true), new Cell(3, 5, true), new Cell(3, 6, true), new Cell(4, 4, false), new Cell(4, 6, true) };
        assertEquals(-1, cell.updateStatus(neighbours));
    }

    @Test
    public void alive_cell_dies_when_surrounded_by_less_than_2_alive_cells(){
        Cell cell = new Cell(4,5, true);
        Cell[] neighbours = { new Cell(3, 4, true), new Cell(3, 5, false), new Cell(3, 6, false), new Cell(4, 4, false), new Cell(4, 6, false) };
        assertEquals(-1, cell.updateStatus(neighbours));
    }

    @Test
    public void empty_cell_stays_empty_when_surrounded_by_less_than_2_alive_cells(){
        Cell cell = new Cell(4,5, false);
        Cell[] neighbours = { new Cell(3, 4, false), new Cell(3, 5, false), new Cell(3, 6, false), new Cell(4, 4, false), new Cell(4, 6, false) };
        assertEquals(-1, cell.updateStatus(neighbours));
    }

    @Test
    public void empty_cell_comes_alive_when_surrounded_by_3_alive_cells(){
        Cell cell = new Cell(4,5, false);
        Cell[] neighbours = { new Cell(3, 4, false), new Cell(3, 5, true), new Cell(3, 6, false), new Cell(4, 4, true), new Cell(4, 6, true) };
        assertEquals(1, cell.updateStatus(neighbours));
    }

    @Test
    public void alive_cell_stays_alive_when_surrounded_by_3_alive_cells(){
        Cell cell = new Cell(4,5, true);
        Cell[] neighbours = { new Cell(3, 4, false), new Cell(3, 5, true), new Cell(3, 6, false), new Cell(4, 4, true), new Cell(4, 6, true) };
        assertEquals(1, cell.updateStatus(neighbours));
    }

    @Test
    public void cloned_cell_has_the_same_state_as_original_cell(){
        Cell cell = new Cell(3,14, true);
        assertTrue(cell.equals(cell.duplicate()));

        cell = new Cell(5,14, false);
        assertTrue(cell.equals(cell.duplicate()));

        cell = new Cell(222,23, true);
        assertTrue(cell.equals(cell.duplicate()));
    }

}
