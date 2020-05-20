import model.StrangeSudokuSolver;
import model.SudokuBoard;
import model.SudokuSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuSolverTest {

    @Test
    void solve() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        StrangeSudokuSolver solver = new StrangeSudokuSolver();
        solver.solve(board);
        assertTrue(board.checkCorrectness());
    }

    @Test
    void toStringTest() {
        SudokuSolver solver = new StrangeSudokuSolver();
        assertEquals("Strange Sudoku Solver", solver.toString());
    }

    @Test
    void hashCodeTest() {
        SudokuSolver solver = new StrangeSudokuSolver();
        assertEquals("Strange Sudoku Solver".hashCode(), solver.hashCode());
    }

    @Test
    void equalsTest() {
        SudokuSolver solver = new StrangeSudokuSolver();
        SudokuSolver solver2 = new StrangeSudokuSolver();
        assertEquals(solver, solver2);
        assertEquals(solver, solver);
        assertNotEquals(solver, null);
        assertNotEquals(solver, "solver");
    }
}