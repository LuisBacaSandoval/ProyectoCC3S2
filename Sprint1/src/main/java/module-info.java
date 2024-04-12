module org.example.sprintOne {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.sprintOne to javafx.fxml;
    exports org.example.sprintOne;
    exports org.example.sprintOne.guicontroller;
}