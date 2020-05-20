import model.StrangeSudokuSolver;
import model.SudokuBoard;
import model.SudokuField;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void checkSudokuCorrectness() {
        SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
        board1.solveGame();
        assertTrue(board1.checkCorrectness());
    }

    @Test
    void checkSudokuRandomness() {
        SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
        SudokuBoard board2 = board1.clone();
        assertEquals(board1, board2);
        assertNotSame(board1, board2);
        board1.solveGame();
        board2.solveGame();
        assertNotEquals(board1, board2);
    }
    
    @Test
    void getBoardTest() {
        SudokuBoard board2 = new SudokuBoard(new StrangeSudokuSolver());
        SudokuField[][] boardCopy = board2.getBoard();
        assertArrayEquals(board2.getBoard(), boardCopy);
    }

    @Test
    void setWrongInRowDigitTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.set(0,0,1);
        board.set(0,2,1);
        assertFalse(board.checkCorrectness());
    }

    @Test
    void setWrongInColumnDigitTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.set(0,0,1);
        board.set(2,0,1);
        assertFalse(board.checkCorrectness());
    }

    @Test
    void setWrongInBothDigitTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.set(0,0,1);
        board.set(0,2,1);
        board.set(2,0,1);
        assertFalse(board.checkCorrectness());
    }

    @Test
    void getRowTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        for (int i = 0; i < 9; ++i) {
            board.set(0, i, i + 1);
        }
        int j = 1;
        for (SudokuField field: board.getRow(0).getElement()) {
            assertEquals(field.getValue(), j);
            ++j;
        }
    }

    @Test
    void getColumnTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        for (int i = 0; i < 9; ++i) {
            board.set(i, 4, i + 1);
        }
        int j = 1;
        for (SudokuField field: board.getColumn(4).getElement()) {
            assertEquals(field.getValue(), j);
            ++j;
        }
    }

    @Test
    void getBoxTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        int digit = 1;
        for (int i = 3; i < 6; ++i) {
            for (int j = 3; j < 6 ; ++j) {
                board.set(i, j, digit);
                ++digit;
            }
        }
        int j = 1;
        for (SudokuField field: board.getBox(3, 3).getElement()) {
            assertEquals(field.getValue(), j);
            ++j;
        }
    }

    @Test
    void toStringTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        System.out.println(board.toString());
        assertEquals(board.toString(), "[SUDOKU BOARD=\n" + Arrays.deepToString(board.getBoard()).replace("],", "],\n") + "]");
    }

    @Test
    void equalsTest() {
        SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
        assertEquals(board1, board1);
        assertNotEquals(board1, 2);
        assertNotEquals(board1, null);
    }

    @Test
    void hashCodeTest() {
        SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
        SudokuBoard board2 = board1.clone();
        assertEquals(board1.hashCode(), board1.hashCode());
        assertEquals(board1.hashCode(), board2.hashCode());
        board1.set(0,1,2);
        assertNotEquals(board1.hashCode(), board2.hashCode());
    }

    @Test
    void cloneTest() {
        SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
        SudokuBoard board2 = board1.clone();
        assertEquals(board2.getSudokuWidth(), board1.getSudokuWidth());
        assertNotSame(board1, board2);
        assertEquals(board1, board2);
        board1.solveGame();
        board2.solveGame();
        assertNotEquals(board1, board2);
    }
}