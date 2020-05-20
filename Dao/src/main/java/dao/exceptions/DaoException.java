package dao.exceptions;

import java.util.ResourceBundle;

public abstract class DaoException extends Exception {

    private final ResourceBundle bundle = ResourceBundle.getBundle("daoBundles.daoExceptions");

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
