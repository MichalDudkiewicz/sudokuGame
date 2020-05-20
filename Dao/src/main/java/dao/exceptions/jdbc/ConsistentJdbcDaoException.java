package dao.exceptions.jdbc;

import dao.exceptions.DaoException;

public class ConsistentJdbcDaoException extends DaoException {

    public ConsistentJdbcDaoException() {
        super("game.jdbc.consistent.exception");
    }

    public ConsistentJdbcDaoException(String message) {
        super(message);
    }

    public ConsistentJdbcDaoException(Throwable cause) {
        super("game.jdbc.consistent.exception", cause);
    }

    public ConsistentJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
