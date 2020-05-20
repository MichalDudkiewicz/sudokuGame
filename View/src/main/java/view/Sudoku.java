package view;

import java.io.IOException;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Sudoku extends Application {

    private static final Logger logger = Logger.getLogger(Sudoku.class);

    @Override
    public void start(Stage primaryStage)  {
        ResourceBundle resources = ResourceBundle.getBundle("bundles.messages");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainMenu.fxml"), resources);
        try {
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root, 700, 430));
        } catch (IOException ioe) {
            logger.fatal("Failed to load Parent root", ioe);
        }
        primaryStage.setTitle(resources.getString("app.title"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
