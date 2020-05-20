package dao.exceptions.jdbc;

import dao.exceptions.DaoException;

public class ConnectionJdbcDaoException extends DaoException {

    public ConnectionJdbcDaoException() {
        super("game.jdbc.connection.exception");
    }

    public ConnectionJdbcDaoException(String message) {
        super(message);
    }

    public ConnectionJdbcDaoException(Throwable cause) {
        super("game.jdbc.connection.exception", cause);
    }

    public ConnectionJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
