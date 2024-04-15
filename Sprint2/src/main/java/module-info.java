module org.example.sprinttwo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.sprinttwo to javafx.fxml;
    exports org.example.sprinttwo;
    exports org.example.sprinttwo.guicontroller;
}