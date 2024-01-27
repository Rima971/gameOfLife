import org.game.Board;
import org.game.ErrorType;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class BoardTest {
    @Test
    public void succesfully_creates_board(){
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
}
