package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public abstract class Controller implements Initializable {

    protected String mainMenu = "mainMenu.fxml";
    protected String sudokuGame = "sudokuBoard.fxml";
    protected String savedGames = "savedGames";

    @FXML
    protected ResourceBundle resources;

    @FXML
    protected URL location;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    protected void update(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(location, resources);
        loadScene(loader, event);
    }

    protected void loadScene(FXMLLoader loader, ActionEvent event) throws IOException {
        Parent root = loader.load();
        Scene rootScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(rootScene);
        window.show();
    }
}