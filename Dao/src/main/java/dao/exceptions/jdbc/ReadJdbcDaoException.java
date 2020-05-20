package dao.exceptions.jdbc;

import dao.exceptions.DaoException;

public class ReadJdbcDaoException extends DaoException {

    public ReadJdbcDaoException() {
        super("game.jdbc.read.exception");
    }

    public ReadJdbcDaoException(String message) {
        super(message);
    }

    public ReadJdbcDaoException(Throwable cause) {
        super("game.jdbc.read.exception", cause);
    }

    public ReadJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
