module ViewProject {
    requires javafx.controls;
    requires javafx.fxml;
    requires DaoProject;
    requires ModelProject;
    requires log4j;
    opens view to javafx.fxml;
    exports view;
}