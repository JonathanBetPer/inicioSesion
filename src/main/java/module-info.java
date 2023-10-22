module com.example.iniciosesion {
    requires javafx.controls;
    requires javafx.fxml;
    requires password4j;
    requires org.slf4j;


    opens com.example.iniciosesion to javafx.fxml;
    exports com.example.iniciosesion;
    exports com.example.iniciosesion.controller;
    opens com.example.iniciosesion.controller to javafx.fxml;
}