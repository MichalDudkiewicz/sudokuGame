package dao.jdbc;

import dao.Dao;
import dao.DaoImpl;
import dao.exceptions.jdbc.ConnectionJdbcDaoException;
import dao.exceptions.jdbc.ConsistentJdbcDaoException;
import dao.exceptions.jdbc.DeleteRecordsJdbcDaoException;
import dao.exceptions.jdbc.ReadJdbcDaoException;
import dao.exceptions.jdbc.WriteJdbcDaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.StrangeSudokuSolver;
import model.SudokuBoard;

public class JdbcSudokuBoardDao extends DaoImpl implements Dao<SudokuBoard> {

    Connection conn;

    /**
     * Function to control SudokuBoardDao connection.
     * @param name The name of the superclass object.
     * @throws ConnectionJdbcDaoException Throws exception in case of failed
     *              connection.
     */
    public JdbcSudokuBoardDao(String name) throws ConnectionJdbcDaoException {
        super(name);
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/sudokuBoard", "sa", "");
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionJdbcDaoException(e);
        }
    }


    @Override
    public SudokuBoard read() throws ReadJdbcDaoException {
        try (Statement stm = conn.createStatement()) {
            SudokuBoard board = new SudokuBoard(new StrangeSudokuSolver());
            ResultSet rs = stm.executeQuery("SELECT * FROM FIELDS WHERE save='" + save + "'");
            while (rs.next()) {
                board.set(rs.getInt("x"), rs.getInt("y"), rs.getInt("digit"));
            }
            return board;
        } catch (SQLException e) {
            throw new ReadJdbcDaoException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws WriteJdbcDaoException {
        try (Statement stm = conn.createStatement()) {
            stm.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS FIELDS(x INT, y INT, digit INT, save VARCHAR(30))");
            ResultSet resultSet = stm.executeQuery("SELECT 1"
                    + "FROM FIELDS "
                    + "WHERE save = '" + save + "'");
            if (resultSet.next()) {
                throw new ConsistentJdbcDaoException();
            }
            conn.setAutoCommit(false);
            for (int x = 0; x < obj.getSudokuWidth(); ++x) {
                for (int y = 0; y < obj.getSudokuWidth(); ++y) {
                    stm.executeUpdate(
                            "INSERT INTO FIELDS(x,y,digit,save) "
                                    + "VALUES (" + x + "," + y + ","
                                    + obj.get(x, y).getValue() + ",'" + save + "')");
                }
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException | ConsistentJdbcDaoException e) {
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throw new WriteJdbcDaoException(throwables);
            }
            throw new WriteJdbcDaoException(e);
        }
    }

    @Override
    public void close() throws Exception {
        conn.close();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        conn.close();
    }

    /**
     * Function to delete JDBC records.
     * @param fileName Name of the file to delete.
     * @throws DeleteRecordsJdbcDaoException Throws exception in case of failed
     *              deletion attempt.
     */
    public void deleteRecords(String fileName) throws DeleteRecordsJdbcDaoException {
        try (Statement stm = conn.createStatement()) {
            stm.executeUpdate("DELETE FROM FIELDS WHERE save='" + fileName + "'");
        } catch (SQLException e) {
            throw new DeleteRecordsJdbcDaoException(e);
        }
    }

    public Connection getConn() {
        return conn;
    }
}
