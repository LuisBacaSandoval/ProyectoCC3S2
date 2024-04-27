module org.example.sprintthree {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.sprintthree to javafx.fxml;
    exports org.example.sprintthree;
    exports org.example.sprintthree.guicontroller;
}