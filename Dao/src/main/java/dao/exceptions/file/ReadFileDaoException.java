package dao.exceptions.file;

import dao.exceptions.DaoException;

public class ReadFileDaoException extends DaoException {

    public ReadFileDaoException() {
        super("game.read.error");
    }

    public ReadFileDaoException(String message) {
        super(message);
    }

    public ReadFileDaoException(Throwable cause) {
        super("game.read.error", cause);
    }

    public ReadFileDaoException(String message, Throwable cause) {
        super(message, cause);
    }

}