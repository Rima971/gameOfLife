import org.game.Board;
import org.junit.Test;

public class BoardTest {
    @Test
    public void succesfully_creates_board(){
        new Board(10,20);
    }
}
