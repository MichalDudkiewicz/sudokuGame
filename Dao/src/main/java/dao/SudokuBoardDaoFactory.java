package dao;

import dao.exceptions.jdbc.ConnectionJdbcDaoException;
import dao.file.FileSudokuBoardDao;
import dao.jdbc.JdbcSudokuBoardDao;
import model.SudokuBoard;

public class SudokuBoardDaoFactory {

    private SudokuBoardDaoFactory() {}

    public static Dao<SudokuBoard> getFileDao(final String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getJdbcDao(final String fileName)
            throws ConnectionJdbcDaoException {
        return new JdbcSudokuBoardDao(fileName);
    }
}
