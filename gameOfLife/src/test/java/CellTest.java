import org.game.Cell;
import org.game.ErrorType;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

}
