import dao.file.FileSudokuBoardDao;
import dao.exceptions.file.ReadFileDaoException;
import dao.exceptions.file.WriteFileDaoException;
import model.StrangeSudokuSolver;
import model.SudokuBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FileSudokuBoardDaoTest {

    @TempDir
    static Path testsTempDir;

    @Test
    @Order(1)
    void write_thenContentIsCorrect() throws WriteFileDaoException, ReadFileDaoException {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        Path sudokuBoard = testsTempDir.resolve("sudokuBoard.bin");
        FileSudokuBoardDao dao = new FileSudokuBoardDao(sudokuBoard.toAbsolutePath().toString());
        dao.write(board);
        SudokuBoard boardRead = dao.read();
        assertEquals(board, boardRead);
    }

    @Test
    @Order(2)
    void read_thenContentIsCorrect() throws ReadFileDaoException {
        Path sudokuBoard = testsTempDir.resolve("sudokuBoard.bin");
        FileSudokuBoardDao dao = new FileSudokuBoardDao(sudokuBoard.toAbsolutePath().toString());
        SudokuBoard board = dao.read();
        Assertions.assertTrue(board.checkCorrectness());
    }

    @Test
    void readError() {
        FileSudokuBoardDao dao = new FileSudokuBoardDao("sampleDir/unknownSudokuBoard.bin");
        Assertions.assertThrows(ReadFileDaoException.class, dao::read);
    }

    @Test
    void writeError() {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        FileSudokuBoardDao dao = new FileSudokuBoardDao("sampleDir/unknownSudokuBoard.bin");
        Assertions.assertThrows(WriteFileDaoException.class, () -> dao.write(board));
    }
}