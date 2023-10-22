module com.example.iniciosesion {
    requires javafx.controls;
    requires javafx.fxml;
    requires password4j;


    opens com.example.iniciosesion to javafx.fxml;
    exports com.example.iniciosesion;
}