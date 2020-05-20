import org.junit.jupiter.api.Test;
import static java.lang.Math.pow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import model.SudokuBoard;
import model.SudokuField;
import model.Difficulty;

class DifficultyTest {

    @Test
    void getSudokuEasy() {
        Difficulty easyLevel = Difficulty.EASY;
        SudokuBoard board = easyLevel.buildSudoku();
        int zeroCounter = 0;
        for (SudokuField[] row: board.getBoard()) {
            for (SudokuField field: row) {
                if (field.getValue() == 0) {
                    ++zeroCounter;
                }
            }
        }
        assertEquals(zeroCounter, (int) ((100 - easyLevel.getPercentageFilled()) / 100 * pow(board.getSudokuWidth(), 2)), 1);
    }

    @Test
    void getSudokuMedium() {
        Difficulty mediumLevel = Difficulty.MEDIUM;
        SudokuBoard board = mediumLevel.buildSudoku();
        int zeroCounter = 0;
        for (SudokuField[] row: board.getBoard()) {
            for (SudokuField field: row) {
                if (field.getValue() == 0) {
                    ++zeroCounter;
                }
            }
        }
        assertEquals(zeroCounter, (int) ((100 - mediumLevel.getPercentageFilled()) / 100 * pow(board.getSudokuWidth(), 2)), 1);
    }

    @Test
    void getSudokuHard() {
        Difficulty hardLevel = Difficulty.HARD;
        SudokuBoard board = hardLevel.buildSudoku();
        int zeroCounter = 0;
        for (SudokuField[] row: board.getBoard()) {
            for (SudokuField field: row) {
                if (field.getValue() == 0) {
                    ++zeroCounter;
                }
            }
        }
        assertEquals(zeroCounter, (int) ((100 - hardLevel.getPercentageFilled()) / 100 * pow(board.getSudokuWidth(), 2)), 1);
    }
}