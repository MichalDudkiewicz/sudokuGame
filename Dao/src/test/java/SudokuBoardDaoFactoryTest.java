import dao.Dao;
import dao.file.FileSudokuBoardDao;
import dao.SudokuBoardDaoFactory;
import dao.exceptions.DaoException;
import dao.jdbc.JdbcSudokuBoardDao;
import model.StrangeSudokuSolver;
import model.SudokuBoard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;


class SudokuBoardDaoFactoryTest {

    @TempDir
    File testsTempDir;

    @Test
    void getFileDao() throws DaoException {
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        File sudokuBoard = new File(testsTempDir, "sudokuBoard.bin");
        FileSudokuBoardDao dao = new FileSudokuBoardDao(sudokuBoard.getAbsolutePath());
        dao.write(board);
        Dao<SudokuBoard> daoToTest = SudokuBoardDaoFactory.getFileDao(sudokuBoard.getAbsolutePath());
        SudokuBoard boardToTest = daoToTest.read();
        Assertions.assertTrue(boardToTest.checkCorrectness());
    }

    @Test
    void getJdbcDao() throws DaoException {
        String recordName = "testing";
        SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
        board.solveGame();
        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao(recordName);
        dao.write(board);
        Dao<SudokuBoard> daoToTest = SudokuBoardDaoFactory.getJdbcDao(recordName);
        SudokuBoard boardToTest = daoToTest.read();
        Assertions.assertTrue(boardToTest.checkCorrectness());
        dao.deleteRecords(recordName);
    }
}