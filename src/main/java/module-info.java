module com.example.bipanacsd214test2b {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.bipanacsd214test2b to javafx.fxml;
    exports com.example.bipanacsd214test2b;
}