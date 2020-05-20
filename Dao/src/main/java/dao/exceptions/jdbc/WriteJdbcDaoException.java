package dao.exceptions.jdbc;

import dao.exceptions.DaoException;

public class WriteJdbcDaoException extends DaoException {
    public WriteJdbcDaoException() {
        super("game.jdbc.write.exception");
    }

    public WriteJdbcDaoException(String message) {
        super(message);
    }

    public WriteJdbcDaoException(Throwable cause) {
        super("game.jdbc.write.exception", cause);
    }

    public WriteJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
