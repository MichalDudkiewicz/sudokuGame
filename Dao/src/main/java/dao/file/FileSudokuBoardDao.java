package dao.file;

import dao.Dao;
import dao.DaoImpl;
import dao.exceptions.file.ReadFileDaoException;
import dao.exceptions.file.WriteFileDaoException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import model.SudokuBoard;

public class FileSudokuBoardDao extends DaoImpl implements Dao<SudokuBoard> {

    public FileSudokuBoardDao(final String filename) {
        super(filename);
    }

    @Override
    public SudokuBoard read() throws ReadFileDaoException {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(save))) {
            return (SudokuBoard) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new ReadFileDaoException(e);
        }
    }

    @Override
    public void write(final SudokuBoard board) throws WriteFileDaoException {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(save))) {
            outputStream.writeObject(board);
        } catch (IOException e) {
            throw new WriteFileDaoException(e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public void close() {

    }
}
