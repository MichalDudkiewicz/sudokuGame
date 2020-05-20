package dao.exceptions.jdbc;

import dao.exceptions.DaoException;

public class DeleteRecordsJdbcDaoException extends DaoException {

    public DeleteRecordsJdbcDaoException() {
        super("game.jdbc.delete.exception");
    }

    public DeleteRecordsJdbcDaoException(String message) {
        super(message);
    }

    public DeleteRecordsJdbcDaoException(Throwable cause) {
        super("game.jdbc.delete.exception", cause);
    }

    public DeleteRecordsJdbcDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
