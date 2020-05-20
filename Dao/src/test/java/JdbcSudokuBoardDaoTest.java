import dao.exceptions.jdbc.*;
import dao.jdbc.JdbcSudokuBoardDao;
import model.StrangeSudokuSolver;
import model.SudokuBoard;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcSudokuBoardDaoTest {

    String testFile = "testing";
    JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao(testFile);

    JdbcSudokuBoardDaoTest() throws ConnectionJdbcDaoException {
    }

    @Test
    @Order(1)
    void write() throws WriteJdbcDaoException, ReadJdbcDaoException {
            SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
            board1.solveGame();
            dao.write(board1);
            SudokuBoard board2 = dao.read();
            assertEquals(board1, board2);
    }

    @Test
    @Order(2)
    void read() throws ReadJdbcDaoException {
        SudokuBoard board4 = dao.read();
        assertTrue(board4.checkCorrectness());
    }

    @Test
    @Order(3)
    void inconsistentWrite() {
        SudokuBoard board1 = new SudokuBoard(new StrangeSudokuSolver());
        board1.solveGame();
        Assertions.assertThrows(WriteJdbcDaoException.class, () -> dao.write(board1));
    }

    @Test
    @Order(4)
    void deleteRecords() throws SQLException, DeleteRecordsJdbcDaoException {
        Statement stm = dao.getConn().createStatement();
        ResultSet result = stm.executeQuery("SELECT COUNT(save) FROM FIELDS WHERE save='" + testFile + "'");
        result.next();
        assertEquals(81, result.getInt("COUNT(save)"));
        dao.deleteRecords(testFile);
        ResultSet resultAfter = stm.executeQuery("SELECT COUNT(save) FROM FIELDS WHERE save='" + testFile + "'");
        resultAfter.next();
        assertEquals(0, resultAfter.getInt("COUNT(save)"));
    }
}