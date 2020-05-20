package dao.exceptions.file;

import dao.exceptions.DaoException;

public class WriteFileDaoException extends DaoException {

    public WriteFileDaoException() {
        super("game.save.error");
    }

    public WriteFileDaoException(String message) {
        super(message);
    }

    public WriteFileDaoException(Throwable cause) {
        super("game.save.error", cause);
    }

    public WriteFileDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}