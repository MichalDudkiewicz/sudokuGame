package dao.exceptions;

import dao.exceptions.file.ReadFileDaoException;
import dao.exceptions.file.WriteFileDaoException;
import dao.exceptions.jdbc.ConnectionJdbcDaoException;
import dao.exceptions.jdbc.ConsistentJdbcDaoException;
import dao.exceptions.jdbc.DeleteRecordsJdbcDaoException;
import dao.exceptions.jdbc.ReadJdbcDaoException;
import dao.exceptions.jdbc.WriteJdbcDaoException;
import org.junit.jupiter.api.Test;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

class DaoExceptionTest {

    private final ResourceBundle bundle = ResourceBundle.getBundle("daoBundles.daoExceptions");

    @Test
    void readExceptionGetLocalizedMessage() {
        ReadFileDaoException e1 = new ReadFileDaoException();
        ReadFileDaoException e2 = new ReadFileDaoException("game.save.error");
        ReadFileDaoException e3 = new ReadFileDaoException(new RuntimeException());
        ReadFileDaoException e4 = new ReadFileDaoException("game.save.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.save.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.save.error"));
    }

    @Test
    void writeExceptionGetLocalizedMessage() {
        WriteFileDaoException e1 = new WriteFileDaoException();
        WriteFileDaoException e2 = new WriteFileDaoException("game.read.error");
        WriteFileDaoException e3 = new WriteFileDaoException(new RuntimeException());
        WriteFileDaoException e4 = new WriteFileDaoException("game.read.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.save.error"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.save.error"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.read.error"));
    }

    @Test
    void writeJdbcExceptionGetLocalizedMessage() {
        WriteJdbcDaoException e1 = new WriteJdbcDaoException();
        WriteJdbcDaoException e2 = new WriteJdbcDaoException("game.read.error");
        WriteJdbcDaoException e3 = new WriteJdbcDaoException(new RuntimeException());
        WriteJdbcDaoException e4 = new WriteJdbcDaoException("game.read.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.jdbc.write.exception"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.jdbc.write.exception"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.read.error"));
    }

    @Test
    void readJdbcExceptionGetLocalizedMessage() {
        ReadJdbcDaoException e1 = new ReadJdbcDaoException();
        ReadJdbcDaoException e2 = new ReadJdbcDaoException("game.read.error");
        ReadJdbcDaoException e3 = new ReadJdbcDaoException(new RuntimeException());
        ReadJdbcDaoException e4 = new ReadJdbcDaoException("game.read.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.jdbc.read.exception"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.jdbc.read.exception"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.read.error"));
    }

    @Test
    void deleteJdbcExceptionGetLocalizedMessage() {
        DeleteRecordsJdbcDaoException e1 = new DeleteRecordsJdbcDaoException();
        DeleteRecordsJdbcDaoException e2 = new DeleteRecordsJdbcDaoException("game.read.error");
        DeleteRecordsJdbcDaoException e3 = new DeleteRecordsJdbcDaoException(new RuntimeException());
        DeleteRecordsJdbcDaoException e4 = new DeleteRecordsJdbcDaoException("game.read.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.jdbc.delete.exception"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.jdbc.delete.exception"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.read.error"));
    }

    @Test
    void connectionJdbcExceptionGetLocalizedMessage() {
        ConnectionJdbcDaoException e1 = new ConnectionJdbcDaoException();
        ConnectionJdbcDaoException e2 = new ConnectionJdbcDaoException("game.read.error");
        ConnectionJdbcDaoException e3 = new ConnectionJdbcDaoException(new RuntimeException());
        ConnectionJdbcDaoException e4 = new ConnectionJdbcDaoException("game.read.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.jdbc.connection.exception"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.jdbc.connection.exception"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.read.error"));
    }

    @Test
    void consistentJdbcExceptionGetLocalizedMessage() {
        ConsistentJdbcDaoException e1 = new ConsistentJdbcDaoException();
        ConsistentJdbcDaoException e2 = new ConsistentJdbcDaoException("game.read.error");
        ConsistentJdbcDaoException e3 = new ConsistentJdbcDaoException(new RuntimeException());
        ConsistentJdbcDaoException e4 = new ConsistentJdbcDaoException("game.read.error", new RuntimeException());
        assertEquals(e1.getLocalizedMessage(), bundle.getString("game.jdbc.consistent.exception"));
        assertEquals(e2.getLocalizedMessage(), bundle.getString("game.read.error"));
        assertEquals(e3.getLocalizedMessage(), bundle.getString("game.jdbc.consistent.exception"));
        assertEquals(e4.getLocalizedMessage(), bundle.getString("game.read.error"));
    }
}