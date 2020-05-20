package view;

import dao.Dao;
import dao.SudokuBoardDaoFactory;
import dao.exceptions.DaoException;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.util.StringConverter;
import model.SudokuBoard;
import model.SudokuField;
import org.apache.log4j.Logger;

public class SudokuBoardController extends Controller {

    public SudokuBoardController(SudokuBoard newGame) {
        game = newGame;
    }

    private final SudokuBoard game;
    private static final Logger logger = Logger.getLogger(SudokuBoardController.class);
    private final StringConverter<Number> converter = new SudokuConverter();

    @FXML
    private GridPane board;

    @FXML
    private Button saveButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;

        ArrayList<IntegerProperty> properties = null;
        try {
            properties = getFieldProperties();
        } catch (NoSuchMethodException e) {
            logger.fatal(resources.getString("game.load.properties.error"), e);
        }

        ArrayList<TextField> fields = getTextFields();

        bindFieldsAndPropetries(fields, properties);

        formatFields(fields);

        saveButton.setOnAction(e -> {
            try {
                saveGame();
            } catch (DaoException daoException) {
                logger.error(daoException);
            }
        });
    }

    private ArrayList<IntegerProperty> getFieldProperties() throws NoSuchMethodException {
        ArrayList<IntegerProperty> properties
                = new ArrayList<>((int) Math.pow(game.getSudokuWidth(), 2));
        for (SudokuField[] row: game.getBoard()) {
            for (SudokuField field: row) {
                properties.add(new JavaBeanIntegerPropertyBuilder()
                        .bean(field).name("value").build());
            }
        }
        return properties;
    }

    private ArrayList<TextField> getTextFields() {
        ArrayList<TextField> fields = new ArrayList<>((int) Math.pow(game.getSudokuWidth(), 2));
        for (Node child: board.getChildren()) {
            if (child instanceof TextField) {
                TextField field = (TextField) child;
                fields.add(field);
            }
        }
        return fields;
    }

    private void bindFieldsAndPropetries(ArrayList<TextField> fields,
                                     ArrayList<IntegerProperty> properties) {
        for (TextField field: fields) {
            Bindings.bindBidirectional(
                    field.textProperty(), properties.get(fields.indexOf(field)), converter);
        }
    }

    private void formatFields(ArrayList<TextField> fields) {
        for (TextField field: fields) {
            if (!field.getText().equals("")) {
                field.setEditable(false);
            }
            field.setTextFormatter(new TextFormatter<>(change -> {
                if (change.getText().matches("[1-9]")) {
                    if (field.getText().length() > 0) {
                        return null;
                    }
                    return change;
                }
                return null;
            }));
        }
    }

    private void saveGame() throws DaoException {
        DateFormat df = new SimpleDateFormat("dd-MM-yy-HH:mm:ss");
        Date dateobj = new Date();
        String fileName = df.format(dateobj) + "-savedGame";

        //  JdbcDao:
        //  Dao<SudokuBoard> jdbcDao = SudokuBoardDaoFactory.getJdbcDao("lastGame");
        //  JdbcSudokuBoardDao dao = (JdbcSudokuBoardDao) jdbcDao;
        //  dao.deleteRecords("lastGame");
        //  dao.write(game);

        //  fileDao:
        fileName += ".bin";
        String path = savedGames + '/' + fileName;
        Dao<SudokuBoard> fileDao = SudokuBoardDaoFactory.getFileDao(path);
        fileDao.write(game);
    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(mainMenu), resources);
        loadScene(loader, event);
    }
}
