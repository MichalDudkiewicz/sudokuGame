import model.StrangeSudokuSolver;
import model.SudokuBoard;
import model.SudokuBox;
import model.SudokuColumn;
import model.SudokuElement;
import model.SudokuRow;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    @Test
    void verifyTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        assertTrue(board.getColumn(1).verify());
        board.set(0,1,4);
        assertTrue(board.getColumn(1).verify());
        board.set(0,2,4);
        assertTrue(board.getColumn(1).verify());
        board.set(2,1,5);
        assertTrue(board.getColumn(1).verify());
        board.set(5,1,4);
        assertFalse(board.getColumn(1).verify());
        board.set(4,1,4);
        assertFalse(board.getColumn(1).verify());
        board.set(3,1,1);
        assertFalse(board.getColumn(1).verify());
    }

    @Test
    void rowToStringTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        SudokuElement row = new SudokuRow(0, board);
        row.update();
        assertEquals("[1 ROW=[0, 0, 0, 0, 0, 0, 0, 0, 0]]", row.toString());
    }

    @Test
    void boxToStringTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        SudokuElement box = new SudokuBox(0, 0, board);
        box.update();
        assertEquals("[1/1 BOX=[0, 0, 0, 0, 0, 0, 0, 0, 0]]", box.toString());
    }

    @Test
    void columnToStringTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        SudokuElement column = new SudokuColumn(0, board);
        column.update();
        assertEquals("[1 COLUMN=[0, 0, 0, 0, 0, 0, 0, 0, 0]]", column.toString());
    }

    @Test
    void columnEqualsTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        SudokuElement column = new SudokuColumn(1, board);
        column.update();
        assertEquals(board.getColumn(1), board.getColumn(1));
        assertEquals(board.getColumn(1), column);
        assertNotEquals(board.getColumn(1), board.getColumn(2));
        assertNotEquals(board.getColumn(1), null);
        assertNotEquals(board.getColumn(1), 5);
    }

    @Test
    void boxEqualsTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        assertEquals(board.getBox(1, 1), board.getBox(2, 1));
        assertEquals(board.getBox(1, 1), board.getBox(1, 2));
        assertEquals(board.getBox(1, 1), board.getBox(1, 1));
        assertNotEquals(board.getBox(1, 1), board.getBox(4, 1));
        assertNotEquals(board.getBox(1, 1), board.getBox(1, 4));
        assertNotEquals(board.getBox(1, 1), board.getBox(6, 4));
        assertNotEquals(board.getBox(1, 1), null);
        assertNotEquals(board.getBox(1, 1), 5);
    }

    @Test
    void rowEqualsTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        SudokuElement row = new SudokuRow(1, board);
        row.update();
        assertEquals(board.getRow(1), board.getRow(1));
        assertEquals(board.getRow(1), row);
        assertNotEquals(board.getRow(1), board.getRow(2));
        assertNotEquals(board.getRow(1), null);
        assertNotEquals(board.getRow(1), 5);
    }

    @Test
    void columnHashCodeTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        assertEquals(board.getColumn(1).hashCode(), board.getColumn(1).hashCode());
        assertNotEquals(board.getColumn(1).hashCode(), board.getColumn(2).hashCode());
    }

    @Test
    void boxHashCodeTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        assertEquals(board.getBox(1, 1).hashCode(), board.getBox(2, 1).hashCode());
        assertNotEquals(board.getBox(1, 1).hashCode(), board.getBox(4, 1).hashCode());
    }

    @Test
    void rowHashCodeTest() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        assertEquals(board.getRow(1).hashCode(), board.getRow(1).hashCode());
        assertNotEquals(board.getRow(1).hashCode(), board.getRow(2).hashCode());
    }

    @Test
    void cloneRowTest() throws CloneNotSupportedException {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.set(1, 1, 2);
        SudokuRow row = board.getRow(1).clone();
        assertNotSame(row, board.getRow(1));
        assertEquals(row, board.getRow(1));
        board.set(1, 2, 3);
        assertNotSame(row, board.getRow(1));
        assertEquals(row, board.getRow(1));
    }

    @Test
    void cloneColumnTest() throws CloneNotSupportedException {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.set(1, 1, 2);
        SudokuColumn column = board.getColumn(1).clone();
        assertNotSame(column, board.getColumn(1));
        assertEquals(column, board.getColumn(1));
        board.set(2, 1, 3);
        assertNotSame(column, board.getColumn(1));
        assertEquals(column, board.getColumn(1));
    }

    @Test
    void cloneBoxTest() throws CloneNotSupportedException {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.set(1, 1, 2);
        SudokuBox box = board.getBox(1, 1).clone();
        assertNotSame(box, board.getBox(1, 1));
        assertEquals(box, board.getBox(1, 1));
        board.set(1, 2, 3);
        assertNotSame(box, board.getBox(1, 1));
        assertEquals(box, board.getBox(1, 1));
    }
}