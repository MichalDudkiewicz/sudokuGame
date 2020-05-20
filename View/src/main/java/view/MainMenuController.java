package view;

import dao.Dao;
import dao.SudokuBoardDaoFactory;
import dao.exceptions.DaoException;
import dao.exceptions.file.ReadFileDaoException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Difficulty;
import model.SudokuBoard;

public class MainMenuController extends Controller {

    private Difficulty level = Difficulty.MEDIUM;

    @FXML
    private Label authorsLabel;

    @FXML
    private RadioButton changeLanguage;

    @FXML
    private MenuItem easyButton;

    @FXML
    private MenuItem mediumButton;

    @FXML
    private MenuItem hardButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.resources = resources;
        this.location = location;

        easyButton.setOnAction(e -> setDifficulty(Difficulty.EASY));

        mediumButton.setOnAction(e -> setDifficulty(Difficulty.MEDIUM));

        hardButton.setOnAction(e -> setDifficulty(Difficulty.HARD));

        setAuthors();
    }

    public void startNewGame(ActionEvent event) throws IOException {
        SudokuBoard game = level.buildSudoku();
        setup(game, event);
    }

    public void quit(ActionEvent event) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.close();
    }

    /**
     * Savefile loading function.
     * @param event An ActionEvent for savefile loading functionality.
     * @throws IOException Throws an IOException if the input/output process is interrupted.
     * @throws ReadFileDaoException Throws an exception if the savefile is not found.
     */
    public void loadGame(ActionEvent event) throws IOException, DaoException {
        //  JdbcDao:
        //  Dao<SudokuBoard> jdbcDao = SudokuBoardDaoFactory.getJdbcDao("lastGame");
        //  SudokuBoard game = jdbcDao.read();
        //  setup(game, event);

        //  FileDao:
        Stage stage = new Stage();
        stage.setAlwaysOnTop(true);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(savedGames));
        fileChooser.setTitle(resources.getString("game.load.monit"));
        fileChooser.getExtensionFilters().add(new FileChooser
                .ExtensionFilter("Binary files", "*.bin"));
        File savedFile = fileChooser.showOpenDialog(stage);
        if (savedFile != null) {
            Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao(savedFile.getAbsolutePath());
            SudokuBoard game = dao.read();
            setup(game, event);
        }
    }

    private void setDifficulty(Difficulty levelToSet) {
        level = levelToSet;
    }

    /**
     * Language selection function.
     * @param event An ActionEvent for language selection functionality.
     * @throws IOException Throws an IOException if the input/output process is interrupted.
     */
    public void changeLanguage(ActionEvent event) throws IOException {
        if (Locale.getDefault().getLanguage().equals("en")) {
            Locale.setDefault(new Locale("pl"));
        } else {
            Locale.setDefault(new Locale("en"));
        }
        resources = ResourceBundle.getBundle("bundles.messages");
        update(event);
    }

    private void setAuthors() {
        ResourceBundle resourceAuthors = ResourceBundle
                .getBundle("view.resources.Resources", resources.getLocale());
        authorsLabel.setText(resourceAuthors
                .getString("author.first") + "\n" + resourceAuthors.getString("author.second"));
    }

    private void setup(SudokuBoard game, ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(sudokuGame), resources);
        loader.setController(new SudokuBoardController(game));
        loadScene(loader, event);
    }
}