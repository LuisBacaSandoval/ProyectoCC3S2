module org.example.sprintOne {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sprintOne to javafx.fxml;
    exports org.example.sprintOne;
    exports org.example.sprintOne.guicontroller;
}