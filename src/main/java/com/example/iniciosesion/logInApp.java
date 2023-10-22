package com.example.iniciosesion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * @author JonathanBetPer
 * @version v1
 * @since 22/10/2023
 * Clase logInApp
 *
 * Clase principal del programa que carga la interfaz
 */
public class logInApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(logInApp.class.getResource("logInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setResizable(false);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
      launch();
    }


}
