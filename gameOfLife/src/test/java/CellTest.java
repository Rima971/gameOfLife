import org.example.Cell;
import org.junit.Test;

public class CellTest {
    @Test()
    public void successfully_creates_cell(){
        new Cell(2,3);
        new Cell(22, 12, true);
    }
}
